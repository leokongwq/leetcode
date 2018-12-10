package com.leokongwq.nio.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: jiexiu
 * Date: 17/2/19
 * Time: 下午5:58
 * Email:leokongwq@gmail.com
 */
public class SimpleClient {

    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress("127.0.0.1", 8888));
            System.out.println("connect success");
            while (true){
                try {
                    Thread.sleep(1000 * 30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
