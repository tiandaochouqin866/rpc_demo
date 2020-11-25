package rpc.demo;

import rpc.demo.client.proxy.MyProxyFactory;
import rpc.demo.server.api.MyService;

/**
 * @Author 18224
 * @Date 2020/11/24 22:57
 * @Version 1.0
 */
public class RpcClientMain {

    public static void main(String[] args) throws Exception {
        MyService myService = (MyService) MyProxyFactory.createInterfaceInvokeProxyObj(MyService.class);
        for (; ; ) {
            System.out.println("RPC调用返回：" + myService.sendMsg("这是sendMsg方法参数"));
            Thread.sleep(3000);

        }
    }
}
