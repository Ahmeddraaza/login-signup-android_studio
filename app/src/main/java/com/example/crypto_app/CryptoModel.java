package com.example.crypto_app;

public class CryptoModel {
    private int id;
    private String name;
    private String symbol;
    private double price;
    private double percentChange24h;
    private double turnover;

    public CryptoModel( int id, String name, String symbol, double price, double percentChange24h, double turnover) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.price = price;
        this.percentChange24h = percentChange24h;
        this.turnover = turnover;
    }

    // Getters and setters


    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    public double getPercentChange24h() {
        return percentChange24h;
    }

    public double getTurnover() {
        return turnover;
    }

    public int getid(){
        return id;
    }
}
