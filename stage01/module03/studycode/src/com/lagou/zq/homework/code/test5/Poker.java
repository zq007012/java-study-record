package com.lagou.zq.homework.code.test5;

public class Poker {
    private String name;
    private int rank;
    private static Poker redJoker = new Poker("大王",54);
    private static Poker blackJoker = new Poker("小王",53);

    private Poker(String name, int rank) {
        this.name = name;
        this.rank = rank;
    }

    public Poker(PokerSuit pokerSuit, PokerRank pokerRank){
        name = pokerSuit.getSuit() + pokerRank.getRankName();
        rank = pokerSuit.getRank() + (pokerRank.getRank() -1) * 4;
    }

    public static Poker getBlackJoker() {
        return blackJoker;
    }

    public static Poker getRedJoker() {
        return redJoker;
    }

    public String getName() {
        return name;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return name;
    }
}
