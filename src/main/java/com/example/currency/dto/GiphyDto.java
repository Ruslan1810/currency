package com.example.currency.dto;

public class GiphyDto {

    private GiphyData data;

    public GiphyDto() {
    }

    public GiphyDto(GiphyData data) {
        this.data = data;
    }

    public GiphyData getData() {
        return data;
    }

    public void setData(GiphyData data) {
        this.data = data;
    }

    public static class GiphyData {

        private String url;

        public GiphyData(String url) {
            this.url = url;
        }

        public GiphyData() {
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
