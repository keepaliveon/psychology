package com.example.psychology.web;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint("/websocket/{id}")
public class WebSocketServer {

    private final Map<String, Session> sessionMap = new HashMap<>();

    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        sessionMap.put(id, session);
        System.out.println(id + "连接了");
    }

    @OnMessage
    public void onMessage(@PathParam("id") String id, String message) {
        sessionMap.get(id).getAsyncRemote().sendText("来自" + id + "的" + message + "已收到");
    }

    @OnClose
    public void onClose(@PathParam("id") String id) {
        sessionMap.remove(id);
        System.out.println(id + "关闭了");
    }

}
