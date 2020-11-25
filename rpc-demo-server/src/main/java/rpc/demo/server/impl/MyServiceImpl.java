package rpc.demo.server.impl;

import rpc.demo.server.api.MyService;

/**
 * @Author 18224
 * @Date 2020/11/23 22:39
 * @Version 1.0
 */
public class MyServiceImpl implements MyService {
    @Override
    public String sendMsg(String msg) {
        return "sendMsg:" + msg;
    }

}
