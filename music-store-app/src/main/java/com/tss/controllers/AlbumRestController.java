package com.tss.controllers;

import com.tss.entities.Album;
import com.tss.repositories.AlbumRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/albums")
public class AlbumRestController {

    @Autowired
    private AlbumRepository albumRepository;
   
    @GetMapping
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
 
    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable String id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Album nie znaleziony: " + id));
    }
    
    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album input) {
        try {
            Album album = new Album();
            album.setTitle(input.getTitle());
            album.setArtist(input.getArtist());
            album.setGenre(input.getGenre());
            album.setQuantity(input.getQuantity());
            album.setPrice(input.getPrice());
            Album savedAlbum = albumRepository.save(album);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAlbum);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Błąd podczas zapisu albumu", e);
        }
    } 
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAlbum(@PathVariable String id, @RequestBody Album updatedAlbum) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Album nie znaleziony: " + id));
        album.setTitle(updatedAlbum.getTitle());
        album.setArtist(updatedAlbum.getArtist());
        album.setPrice(updatedAlbum.getPrice());
        album.setGenre(updatedAlbum.getGenre());
        album.setQuantity(updatedAlbum.getQuantity());
        albumRepository.save(album);
        return ResponseEntity.ok(album);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable String id) {
        if (!albumRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Album nie znaleziony: " + id);
        }
        albumRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}