package rpc.demo;

import rpc.demo.server.RpcServer;

/**
 * @Author 18224
 * @Date 2020/11/23 23:49
 * @Version 1.0
 */
public class RpcServerMain {
    public static void main(String[] args) {
        RpcServer.startServer("localhost",8088);
    }
}
