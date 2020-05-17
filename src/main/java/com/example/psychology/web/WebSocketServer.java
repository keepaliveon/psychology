package com.example.psychology.web;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{id}")
public class WebSocketServer {

    private static Map<String, WebSocketServer> clients = new ConcurrentHashMap<>();

    private Session session;

    @OnOpen
    public void onOpen(@PathParam("id") String id, Session session) {
        this.session = session;
        clients.put(id, this);
        System.out.println(id + "连接了");
    }

    @OnMessage
    public void onMessage(@PathParam("id") String id, String data) {
        String[] split = data.split(":");
        System.out.println(split[0]);
        System.out.println(split[1]);
        Session session = clients.get(split[0]).session;
        if (session != null) {
            session.getAsyncRemote().sendText(split[1]);
        }
    }

    @OnClose
    public void onClose(@PathParam("id") String id) {
        System.out.println(id + "关闭了");
    }

}
