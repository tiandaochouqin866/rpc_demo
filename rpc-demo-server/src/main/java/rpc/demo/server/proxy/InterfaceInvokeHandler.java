package rpc.demo.server.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author damokelisijian886
 * @Date 2020/11/23 17:23
 * @Version 1.0
 */
public class InterfaceInvokeHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理调用的接口："+method.getDeclaringClass());
        System.out.println("代理调用的方法："+method.getName());
        System.out.println("代理调用的方法的入参"+ Arrays.asList(args));
        return "代理调用方法返回结果";

    }
}
