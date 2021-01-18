package com.example.currency.dto;

public class ResponseDto {

    private String gifUrl;

    public ResponseDto() {
    }

    public ResponseDto(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }
}
