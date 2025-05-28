package com.tss.services;

import com.tss.dto.AlbumDTO;
import com.tss.entities.Album;
import com.tss.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AlbumDTO getAlbumById(String id) {
        return albumRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new IllegalArgumentException("Album nie znaleziony: " + id));
    }

    public AlbumDTO addAlbum(Album album) {
        return convertToDTO(albumRepository.save(album));
    }

    public AlbumDTO updateAlbum(String id, Album album) {
        album.setId(id);
        return convertToDTO(albumRepository.save(album));
    }

    public void deleteAlbum(String id) {
        albumRepository.deleteById(id);
    }

    private AlbumDTO convertToDTO(Album album) {
        return new AlbumDTO(album.getId(), album.getTitle(), album.getArtist(), album.getPrice(), album.getGenre(), album.getQuantity());
    }
}