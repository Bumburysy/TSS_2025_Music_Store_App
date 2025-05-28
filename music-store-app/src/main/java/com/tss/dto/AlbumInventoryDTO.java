package com.tss.dto;

public class AlbumInventoryDTO {
    private String id;
    private String title;
    private int quantity;

    public AlbumInventoryDTO() {}

    public AlbumInventoryDTO(String id, String title, int quantity) {
        this.id = id;
        this.title = title;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
