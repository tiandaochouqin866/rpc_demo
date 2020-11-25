package rpc.demo.client.proxy;

import rpc.demo.client.handler.MyInvokeHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author 18224
 * @Date 2020/11/24 22:48
 * @Version 1.0
 */
public class MyProxyFactory {

    /**
     * 工厂模式创建接口代理类
     * @param interfaceClass
     * @return
     */
    public static Object createInterfaceInvokeProxyObj(final Class<?> interfaceClass){
        InvocationHandler handler = new MyInvokeHandler();

        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{interfaceClass},handler);

    }

}
