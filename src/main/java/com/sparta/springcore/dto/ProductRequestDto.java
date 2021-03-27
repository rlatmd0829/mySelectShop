package com.sparta.springcore.dto;

import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String title;
    private String link;
    private String image;
    private int lprice;

    public ProductRequestDto(String title, String image, String link, int lprice){
        this.title = title;
        this.link = link;
        this.image = image;
        this.lprice = lprice;
    }
}
