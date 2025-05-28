package com.tss.controllers;

import com.tss.dto.AlbumInventoryDTO;
import com.tss.entities.Album;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class InventoryWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;

    public InventoryWebSocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendInventoryUpdate(List<Album> albums) {
        List<AlbumInventoryDTO> dtos = albums.stream()
                .map(album -> new AlbumInventoryDTO(album.getId(), album.getTitle(), album.getQuantity()))
                .collect(Collectors.toList());
        
        System.out.println("Wysyłam dane WebSocket DTO: " + dtos);
        messagingTemplate.convertAndSend("/topic/inventory", dtos);
    }
}
