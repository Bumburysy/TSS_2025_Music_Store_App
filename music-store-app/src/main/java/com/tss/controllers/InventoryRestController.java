package com.tss.controllers;

import com.tss.entities.Album;
import com.tss.repositories.AlbumRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/inventory")
public class InventoryRestController {

    private final AlbumRepository albumRepository;
    private final InventoryWebSocketController wsController;
    private final Random random = new Random();

    public InventoryRestController(AlbumRepository albumRepository, InventoryWebSocketController wsController) {
        this.albumRepository = albumRepository;
        this.wsController = wsController;
    }

    @GetMapping
    public List<Album> getInventory() {
        return albumRepository.findAll();
    }

    @PostMapping("/refresh")
    public ResponseEntity<String> refreshInventory() {
        List<Album> albums = albumRepository.findAll();
        for (Album album : albums) {
            int delta = random.nextInt(9) - 6;
            int newQuantity = album.getQuantity() + delta;
            album.setQuantity(Math.max(1, newQuantity));
        }
        albumRepository.saveAll(albums);
        wsController.sendInventoryUpdate(albums);
        return ResponseEntity.ok("Zmieniono stan magazynu.");
    }
}
