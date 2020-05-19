package com.example.psychology.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

    private static Map<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(@PathParam("id") String path, Session session) {
        String[] split = path.split(":");
        String fromId = split[0];
        String toId = split[1];
        sessionMap.put(fromId, session);
        if (sessionMap.get(toId) != null) {
            sessionMap.get(toId).getAsyncRemote().sendText("系统提示：对方上线了");
            sessionMap.get(fromId).getAsyncRemote().sendText("系统提示：对方已在线");
        } else {
            sessionMap.get(fromId).getAsyncRemote().sendText("系统提示：等待对方上线");

        }
    }

    @OnMessage
    public void onMessage(String data) {
        JSONObject jsonObject = JSON.parseObject(data);
        String fromName = jsonObject.getString("fromName");
        String toName = jsonObject.getString("toName");
        String fromId = jsonObject.getString("fromId");
        String toId = jsonObject.getString("toId");
        String text = jsonObject.getString("text");
        if (sessionMap.get(toId) != null) {
            sessionMap.get(fromId).getAsyncRemote().sendText(fromName + "：" + text);
            sessionMap.get(toId).getAsyncRemote().sendText(fromName + "：" + text);
        } else {
            sessionMap.get(fromId).getAsyncRemote().sendText("系统提示：发送失败，" + toName + "对方不在线");
        }
    }

    @OnClose
    public void onClose(@PathParam("id") String path) {
        String[] split = path.split(":");
        String fromId = split[0];
        String toId = split[1];
        sessionMap.remove(fromId);
        if (sessionMap.get(toId) != null) {
            sessionMap.get(toId).getAsyncRemote().sendText("系统提示：对方掉线");
        }
    }

}
