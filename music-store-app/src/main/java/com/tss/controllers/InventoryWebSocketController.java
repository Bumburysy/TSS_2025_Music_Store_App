package com.tss.controllers;

import com.tss.entities.Album;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InventoryWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public InventoryWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendInventoryUpdate(List<Album> albums) {
        System.out.println("Wysyłam dane WebSocket: " + albums);
        messagingTemplate.convertAndSend("/topic/inventory", albums);
    }
}