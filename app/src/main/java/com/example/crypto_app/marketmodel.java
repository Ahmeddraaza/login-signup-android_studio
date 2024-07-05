package com.example.crypto_app;

import java.util.List;

public class marketmodel {
    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        private List<CryptoCurrencyList> cryptoCurrencyList;

        public List<CryptoCurrencyList> getCryptoCurrencyList() {
            return cryptoCurrencyList;
        }

        public void setCryptoCurrencyList(List<CryptoCurrencyList> cryptoCurrencyList) {
            this.cryptoCurrencyList = cryptoCurrencyList;
        }
    }

    public static class CryptoCurrencyList {
        private int id;
        private String name;
        private String symbol;
        private List<Quote> quotes;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public List<Quote> getQuotes() {
            return quotes;
        }

        public void setQuotes(List<Quote> quotes) {
            this.quotes = quotes;
        }

        public static class Quote {
            private String name;
            private double price;
            private double percentChange24h;
            private double turnover;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getPercentChange24h() {
                return percentChange24h;
            }

            public void setPercentChange24h(double percentChange24h) {
                this.percentChange24h = percentChange24h;
            }

            public double getTurnover() {
                return turnover;
            }

            public void setTurnover(double turnover) {
                this.turnover = turnover;
            }
        }
    }
}
