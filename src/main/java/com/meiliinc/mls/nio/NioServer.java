package com.meiliinc.mls.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 17/2/16
 * Time: 下午11:36
 * Email:jiexiu@mogujie.com
 */
public class NioServer {

    static class Reactor implements Runnable {
        private final Selector selector;
        private final ServerSocketChannel serverSocket;

        public Reactor(LinkedBlockingQueue<SocketChannel> socketChannels, int port) throws IOException {
            selector = Selector.open();
            serverSocket = ServerSocketChannel.open();
            serverSocket.socket().bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            sk.attach(new Acceptor(socketChannels, serverSocket));
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                try {
                    int cnt = selector.select();
                    if (cnt <=0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                    while (iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if (selectionKey.isAcceptable()){
                            Runnable runnable = (Runnable) selectionKey.attachment();
                            runnable.run();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Acceptor implements Runnable {

        private ServerSocketChannel serverSocket;

        private LinkedBlockingQueue<SocketChannel> socketChannels;

        Acceptor(LinkedBlockingQueue<SocketChannel> socketChannels, ServerSocketChannel serverSocket){
            this.serverSocket = serverSocket;
            this.socketChannels = socketChannels;
        }

        public void run() {
            try {
                SocketChannel socketChannel = serverSocket.accept();
                socketChannels.put(socketChannel);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    static class Handler implements Runnable {

        private SelectionKey selectionKey;

        private SocketChannel socketChannel;

        Handler(SocketChannel socketChannel, SelectionKey selectionKey){
            this.socketChannel = socketChannel;
            this.selectionKey = selectionKey;
        }

        @Override
        public void run() {
            if (selectionKey.isReadable()){
                readMsg();
            }else if (selectionKey.isWritable()){
                writeMsg();
            }
        }

        private void readMsg(){
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            try {
                StringBuilder sb = new StringBuilder();
                while (socketChannel.read(buffer) != -1){
                    buffer.flip();
                    sb.append(new String(buffer.array()));
                }
                System.out.println(sb.toString());
                selectionKey.interestOps(SelectionKey.OP_WRITE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void writeMsg(){
            try {
                socketChannel.write(ByteBuffer.wrap("hello client".getBytes()));
                selectionKey.interestOps(SelectionKey.OP_READ);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static class SubReactor implements Runnable {

        private Selector selector;

        private LinkedBlockingQueue<SocketChannel> socketChannels;

        SubReactor(Selector selector, LinkedBlockingQueue<SocketChannel> socketChannels){
            this.selector = selector;
            this.socketChannels = socketChannels;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                //获取新的连接
                SocketChannel newChannel = null;
                try {
                    newChannel = socketChannels.take();
                    newChannel.configureBlocking(false);
                    SelectionKey newSelectionKey = newChannel.register(this.selector, SelectionKey.OP_READ);
                    newSelectionKey.attach(new Handler(newChannel, newSelectionKey));
                    int cnt = selector.select();
                    if (cnt <= 0) {
                        continue;
                    }
                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                    while (iterator.hasNext()){
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        Runnable handler = (Runnable) selectionKey.attachment();
                        handler.run();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        newChannel.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    static class SubReactorPool {

        Selector[] selectors;

        ThreadPoolExecutor executor;

        int size = 0;

        private LinkedBlockingQueue<SocketChannel> socketChannels;

        SubReactorPool(int size, LinkedBlockingQueue<SocketChannel> socketChannels){
            this.size = size;
            selectors = new Selector[size];
            executor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(100));
            this.socketChannels = socketChannels;
            this.selectors = new Selector[5];

            for (int i = 0; i < 5; i++){
                try {
                    selectors[i] = Selector.open();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public  void start(){
            for (int i = 0; i < size; i++){
                executor.submit(new SubReactor(selectors[i], this.socketChannels));
            }
        }
    }

    public static void main(String[] args)  throws Exception{

        LinkedBlockingQueue<SocketChannel> socketChannels = new LinkedBlockingQueue<>();

        SubReactorPool subReactorPool = new SubReactorPool(5, socketChannels);
        subReactorPool.start();

        Thread reactor = new Thread(new Reactor(socketChannels, 8080));
        reactor.start();
    }

}
