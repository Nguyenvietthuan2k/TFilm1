package com.example.tfilm.Model;

public class Artists {

    String name;
    String img_link;
    int thumbnail;

    public Artists(){}

    public Artists(String name,String img_link, int thumbnail) {
        this.name = name;
        this.img_link = img_link;
        this.thumbnail = thumbnail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_link() {
        return img_link;
    }

    public void setImg_link(String img_link) {
        this.img_link = img_link;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

}
