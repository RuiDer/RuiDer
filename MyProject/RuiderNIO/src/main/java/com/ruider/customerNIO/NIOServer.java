package com.ruider.customerNIO;

/**
 * Created by mahede on 2018/11/20.
 */
public class NIOServer {
    private static final int NIO_PORT = 1234;
    private static NIOServerHandle nioServerHandle;

    public static void start() { start(NIO_PORT); }

    private synchronized static void start(int port) {
        if(nioServerHandle != null) { nioServerHandle.stop(); }
        nioServerHandle = new NIOServerHandle(NIO_PORT);
        new Thread(nioServerHandle, "Server").start();
    }

}
