package com.tss.dto;

public class AlbumDTO {
    private String id;
    private String title;
    private String artist;
    private double price;
    private String genre;
    private int quantity;

    public AlbumDTO() {}

    public AlbumDTO(String id, String title, String artist, double price, String genre, int quantity) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.price = price;
        this.genre = genre;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}