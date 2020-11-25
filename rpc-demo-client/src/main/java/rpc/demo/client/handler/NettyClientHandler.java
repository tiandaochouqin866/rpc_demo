package rpc.demo.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @Author 18224
 * @Date 2020/11/24 0:06
 * @Version 1.0
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable {

    private String para;

    /**
     * 保存 Channel 相关的所有上下文信息，同时关联一个 ChannelHandler 对象
     */
    private ChannelHandlerContext context;

    private String result;


    public void setPara(String para) {
        this.para = para;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        context = ctx;
    }

    /**
     * 收到服务端的数据，唤醒等待线程
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) {
        result = msg.toString();
        notify();

    }

    /**
     * 写出数据开始等待唤醒
     *
     * @return
     * @throws Exception
     */
    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(para);
        wait();
        return result;
    }



}
