package com.sparta.springcore.dto;

import lombok.Getter;

@Getter
public class ProductMypriceRequestDto {
    private int myprice;

    public ProductMypriceRequestDto(int myprice){
        this.myprice = myprice;
    }
}