package com.ruider.customerNIO;

/**
 * Created by mahede on 2018/11/21.
 */
public class Client {
    private static final String IP_ADDRESS = "localhost";
    private static final int PORT = 1234;
    private static ClientHandle clientHandle;

    public static void start(){ start(IP_ADDRESS, PORT);}

    private synchronized static void start(String host, int port) {
        if(clientHandle != null) {
            clientHandle.stop();
        }
        clientHandle = new ClientHandle(IP_ADDRESS, PORT);
        new Thread(clientHandle, "Client").start();
    }

    //向服务器发送消息
    public static boolean sendMsg(String msg) throws Exception{
        if(msg.equals("q")) return false;
        clientHandle.sendMsg(msg);
        return true;
    }

}
