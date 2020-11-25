package rpc.demo.client.handler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.*;

/**
 * @Author 18224
 * @Date 2020/11/23 23:54
 * @Version 1.0
 */
public class MyInvokeHandler implements InvocationHandler {

    /**
     * 执行线程池
     */
    private static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors(),
            TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
            new ThreadPoolExecutor.DiscardOldestPolicy());
    private static NettyClientHandler client;

    /**
     * 执行方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("客户端接收到的接口：" + method.getDeclaringClass());
        System.out.println("客户端端接收到的方法名：" + method.getName());
        System.out.println("客户端接收到的参数：" + Arrays.asList(args));
        if (client == null) {
            initClient();
        }

        StringBuilder sb = new StringBuilder(method.getDeclaringClass()
                + "#" + method.getName() + "#" + Arrays.asList(args));

        client.setPara(sb.toString());
        return THREAD_POOL_EXECUTOR.submit(client).get();
    }

    /**
     * 初始化netty客户端
     */
    private static void initClient() {
        client = new NettyClientHandler();
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup enentLoopGroup = new NioEventLoopGroup();
        bootstrap.group(enentLoopGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        ChannelPipeline p = socketChannel.pipeline();
                        p.addLast(new StringDecoder(CharsetUtil.UTF_8));
                        p.addLast(new StringEncoder(CharsetUtil.UTF_8));
                        p.addLast(client);
                    }
                });
        try {
            bootstrap.connect("localhost", 8088).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
