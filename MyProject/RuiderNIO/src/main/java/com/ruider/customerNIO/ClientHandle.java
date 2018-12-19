package com.ruider.customerNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by mahede on 2018/11/21.
 */
public class ClientHandle implements Runnable {

    private String ipAddress;
    private int port;
    private SocketChannel socketChannel;
    private Selector selector;
    private static boolean start;

    public ClientHandle(String host, int port) {
        try{
            this.ipAddress = host;
            this.port = port;
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            //socketChannel.connect(new InetSocketAddress(ipAddress, port));
            //socketChannel.register(selector, SelectionKey.OP_CONNECT);
            start = true;
            System.out.println("客户端启动");
        }catch (Exception e) {
            System.out.println("客户端启动失败");
            e.printStackTrace();
        }

    }

    public static void stop() { start = false;}

    public void sendMsg(String msg) throws Exception{
        socketChannel.register(selector, SelectionKey.OP_READ);
        doWrite(socketChannel, msg);
    }

    @Override
    public void run() {
        try{
            doConnect();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        while (start) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    }
                    catch (Exception e) {
                        if(key != null){
                            key.cancel();
                            if(key.channel() != null){
                                key.channel().close();
                            }
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
        try{
            if(selector != null) {
                selector.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleInput(SelectionKey key) {
        if(key.isValid()) {
            try{
                SocketChannel socketChannel = (SocketChannel)key.channel();
                if(key.isConnectable()){
                    if(socketChannel.finishConnect());
                    else System.exit(1);
                }
                if(key.isReadable()){
                    //创建ByteBuffer，并开辟一个1M的缓冲区
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //读取请求码流，返回读取到的字节数
                    int readBytes = socketChannel.read(buffer);
                    //读取到字节，对字节进行编解码
                    if(readBytes>0){
                        //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                        buffer.flip();
                        //根据缓冲区可读字节数创建字节数组
                        byte[] bytes = new byte[buffer.remaining()];
                        //将缓冲区可读字节数组复制到新建的数组中
                        buffer.get(bytes);
                        String result = new String(bytes,"UTF-8");
                        System.out.println("客户端收到消息：" + result);
                    }
                    //没有读取到字节 忽略
//				else if(readBytes==0);
                    //链路已经关闭，释放资源
                    else if(readBytes<0){
                        key.cancel();
                        socketChannel.close();
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    //异步发送消息
    private void doWrite(SocketChannel channel,String request) throws IOException {
        //将消息编码为字节数组
        byte[] bytes = request.getBytes();
        //根据数组容量创建ByteBuffer
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        //将字节数组复制到缓冲区
        writeBuffer.put(bytes);
        //flip操作
        writeBuffer.flip();
        //发送缓冲区的字节数组
        channel.write(writeBuffer);
        //****此处不含处理“写半包”的代码
    }

    private void doConnect() throws IOException{
        if(socketChannel.connect(new InetSocketAddress(ipAddress, port)));
        else socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }
}
