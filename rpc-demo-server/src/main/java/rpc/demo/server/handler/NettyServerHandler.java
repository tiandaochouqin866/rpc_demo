package rpc.demo.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import rpc.demo.server.api.MyService;
import rpc.demo.server.impl.MyServiceImpl;

/**
 * @Author 18224
 * @Date 2020/11/23 23:13
 * @Version 1.0
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 类路径以及名称
     */
    private static final String CLASSNAME = "interface rpc.demo.server.api.public interface MyServiceTest";
    /**
     * 方法名
     */
    private static final String SEND_MSG = "sendMsg";

    /**
     * 方法名
     */
    private static final String HELLO_WORD = "helloWorld";


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String str = msg.toString();
        String[] strArr = str.split("#");
        String interfaceClass = strArr[0];
        String method = strArr[1];
        String params = strArr[2];
        System.out.println("服务端接收到的接口：" + interfaceClass);
        System.out.println("服务端接收到的方法名：" + method);
        System.out.println("服务端接收到的参数：" + params);

        //如果符合约定，调用本地方法返回数据
        if (CLASSNAME.equals(interfaceClass)) {
            MyService myService = new MyServiceImpl();
            if (SEND_MSG.equals(method)) {
                ctx.writeAndFlush(myService.sendMsg(params));
            }
        }


    }
}
