package rpc.demo.server.api;

/**
 * @Author 18224
 * @Date 2020/11/23 22:36
 * @Version 1.0
 */
public interface MyService {
    /**
     * 发送消息
     * @param msg
     * @return
     */
    String sendMsg(String msg);
}
