package rpc.demo.server.proxy;

/**
 * @Author damokelisijian886
 * @Date 2020/11/23 17:58
 * @Version 1.0
 */
public class InterfaceProxyTest {

    public static void main(String[] args) {
        MyServiceTest myService = (MyServiceTest)InterfaceProxyFactory.createInterfaceInvokeProxyObj(MyServiceTest.class);
        myService.printMsg("你好，CSDN && github");
    }
}
