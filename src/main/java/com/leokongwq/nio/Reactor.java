package com.leokongwq.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 17/2/13
 * Time: 下午3:49
 * Email:leokongwq@gmail.com
 */
public class Reactor implements Runnable {

    private static final int MAXIN = 4096;

    private static final int MAXOUT = 4096;

    private final Selector selector;
    private final ServerSocketChannel serverSocket;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override
    public void run() {
        while (!Thread.interrupted()){
            try {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    dispatch((SelectionKey)(it.next()));
                }
                selected.clear();
            }catch (Exception e){

            }
        }
    }

    private void dispatch(SelectionKey selectionKey){
        Runnable r = (Runnable)selectionKey.attachment();
        if (null != r){
            r.run();
        }
    }

    class Acceptor implements Runnable { // inner
        public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null) {
                    new Handler(selector, c);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    final class Handler implements Runnable {
        final SocketChannel socket;
        final SelectionKey sk;
        ByteBuffer input = ByteBuffer.allocate(MAXIN);
        ByteBuffer output = ByteBuffer.allocate(MAXOUT);
        static final int READING = 0, SENDING = 1;
        int state = READING;

        Handler(Selector sel, SocketChannel socket) throws IOException {
            this.socket = socket;
            socket.configureBlocking(false);
            // Optionally try first read now
            sk = socket.register(sel, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            sel.wakeup();
        }

        @Override
        public void run() {
            try {
                socket.read(input);
                if (inputIsComplete()) {
                    process();
                    sk.attach(new Sender());
                    sk.interestOps(SelectionKey.OP_WRITE);
                    sk.selector().wakeup();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        boolean inputIsComplete() {
            return true;
        }

        boolean outputIsComplete() {
            return true;
        }

        void process() {

        }

        class Sender implements Runnable {
            public void run() { // ...
                try {
                    socket.write(output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (outputIsComplete()) {
                    sk.cancel();
                }
            }
        }
    }
}


