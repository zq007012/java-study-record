package com.lagou.zq.homework.code.test5;

public enum PokerSuit {
    SPADE("黑桃", 1),
    HEART("红心", 2),
    IAMOND("方块", 3),
    CLUBS("梅花", 4);
    private final String suit;
    private final int rank;
    private PokerSuit(String suit,int rank){
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }
}
