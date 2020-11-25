package rpc.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import rpc.demo.server.handler.NettyServerHandler;

/**
 * @Author 18224
 * @Date 2020/11/23 22:43
 * @Version 1.0
 */
public class RpcServer {
    public static void startServer(String hostName, int port) {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            NioEventLoopGroup enentLoopGroup = new NioEventLoopGroup();
            bootstrap.group(enentLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline p = socketChannel.pipeline();
                            p.addLast(new StringDecoder(CharsetUtil.UTF_8));
                            p.addLast(new StringEncoder(CharsetUtil.UTF_8));
                            p.addLast(new NettyServerHandler());
                        }
                    });
            bootstrap.bind(hostName,port).sync();
            System.out.println("rpc demo server started");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

