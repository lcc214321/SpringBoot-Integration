package com.xlhj.websocket.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: lcj
 * @Date: 2020/11/6 11:38
 * @Description: TODO
 * @Version: 0.0.1
 */
@Controller
@ServerEndpoint("/websocket")
public class WebSocketController {

    private Session session;
    private final static Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    private static CopyOnWriteArraySet<Socket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //webSocketSet.add(this);
        logger.info("websocket有新链接：" + webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
    }

    public void onMessage(String message) throws IOException {
        for (Socket socket : webSocketSet) {
            //socket.sess
        }
    }
}
