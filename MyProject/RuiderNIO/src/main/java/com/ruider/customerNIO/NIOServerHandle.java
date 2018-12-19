package com.ruider.customerNIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by mahede on 2018/11/20.
 */
public class NIOServerHandle implements Runnable{

    private int port;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private volatile boolean start;

    public NIOServerHandle(int port) {
        this.port = port;
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            start = true;
            System.out.println("NIOServer启动服务,端口：" + port);

        }catch (Exception e) {
            System.out.println("封装NIO服务失败");
            System.out.println(e);
        }
    }
    @Override
    public void run(){
        while(start) {
            try{
                //没1秒唤醒一次，轮询一遍事件
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey selectionKey = null;
                while(it.hasNext()) {
                    selectionKey = it.next();
                    it.remove();
                    try{
                        handleInput(selectionKey);
                    }catch(Exception e){
                        System.out.println("服务获取信息失败");
                        System.out.println(e);
                    }
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        }
        if(selector != null)
            try{
                selector.close();
            }catch (Exception e) {
                e.printStackTrace();
            }

    }

    public void stop() { start = false;}

    private void handleInput(SelectionKey key) throws Exception{
        if(key.isValid()) {
            if(key.isAcceptable()) {
                ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = channel.accept();
                socketChannel.configureBlocking(false);
                socketChannel.register(selector, SelectionKey.OP_READ);

            }
            if(key.isReadable()) {
                SocketChannel socketChannel = (SocketChannel)key.channel();
                //分配1M的buffer
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int size = socketChannel.read(byteBuffer);
                if(size > 0) {
                    //将缓冲区当前的limit设置为position=0，用于后续对缓冲区的读取操作
                    byteBuffer.flip();
                    byte[] bytes =  new byte[byteBuffer.remaining()];
                    //复制到bytes中
                    byteBuffer.get(bytes);
                    String contents = new String(bytes, "UTF-8");
                    System.out.println("NIO服务收到消息："+contents);

                    String expression = new String(bytes,"UTF-8");
                    System.out.println("服务器收到消息：" + expression);
                    doWrite(socketChannel, "收到信息，你好，我是NIO");
                }
                else if(size < 0) {
                    key.cancel();
                    socketChannel.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel channel, String msg) {
        try {
            byte[] bytes = msg.getBytes();
            ByteBuffer writebuffer = ByteBuffer.allocate(bytes.length);
            writebuffer.put(bytes);
            //postion,limit值的交换，用于输出buffer
            writebuffer.flip();
            channel.write(writebuffer);
        }catch (Exception e) {
            System.out.print("[error]: NIOserver发送信息失败");
            e.printStackTrace();
        }
    }
}
