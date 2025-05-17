package com.tss.repositories;

import com.tss.entities.Album;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends MongoRepository<Album, String> {
    
    @Override
    java.util.List<Album> findAll();
}
