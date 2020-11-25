package rpc.demo.server.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Author damokelisijian886
 * @Date 2020/11/23 17:31
 * @Version 1.0
 */
public class InterfaceProxyFactory {
    public static Object createInterfaceInvokeProxyObj(final Class<?> interfaceClass) {
        InvocationHandler handler = new InterfaceInvokeHandler();
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
               new Class<?>[]{ interfaceClass},handler);
    }
}
