package com.meiliinc.mls.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 17/2/17
 * Time: 上午12:42
 * Email:jiexiu@mogujie.com
 */
public class NioClient {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        socketChannel.register(selector, SelectionKey.OP_WRITE);

        boolean connected = socketChannel.finishConnect();

        System.out.println("连接成功");

        while (true){
            int cnt = selector.select();
            if (cnt <= 0){
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                if (selectionKey.isWritable()){
                    socketChannel.write(ByteBuffer.wrap("hello server".getBytes()));
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }else if (selectionKey.isReadable()){
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    StringBuilder sb = new StringBuilder();
                    while (socketChannel.read(buffer) != -1){
                        buffer.flip();
                        sb.append(new String(buffer.array()));
                    }
                    System.out.println(sb.toString());
                    selectionKey.interestOps(SelectionKey.OP_WRITE);
                }
            }
        }
    }
}
