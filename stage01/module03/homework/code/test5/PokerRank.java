package com.lagou.zq.homework.code.test5;

public enum PokerRank {
    //手中扑克牌从大到小的摆放顺序：大王,小王,2,A,K,Q,J,10,9,8,7,6,5,4,3
    //rank越大, 等级越高
    THREE("3", 1), FOUR("4", 2),
    FIVE("5", 3), SIX("6", 4),
    SERVEN("7", 5), EIGHT("8", 6),
    NINE("9", 7), TEN("10", 8),
    JACK("J", 9), QUEEN("Q", 10),
    KING("K", 11), ACE("A", 12),
    DEUCE("2", 13);

    private final String rankName;
    private final int rank;

    PokerRank(String rankName, int rank) {
        this.rankName = rankName;
        this.rank = rank;
    }

    public String getRankName() {
        return rankName;
    }

    public int getRank() {
        return rank;
    }
}
