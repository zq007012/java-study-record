package com.lagou.zq.homework.code.test5;

import java.util.Comparator;
import java.util.TreeSet;

public class Player {
    private TreeSet<Poker> playerCards;
    private ADeckOfPlayingCards aDeckOfPlayingCards;

    {
        playerCards = new TreeSet<>(new Comparator<Poker>() {
            @Override
            public int compare(Poker o1, Poker o2) {
                return o2.getRank() - o1.getRank();
            }
        });
    }

    /**
     * 一局里的玩家必须使用同一副牌
     * @param aDeckOfPlayingCards
     */
    public Player(ADeckOfPlayingCards aDeckOfPlayingCards){
        this.aDeckOfPlayingCards = aDeckOfPlayingCards;
    }

    public boolean getPoker(){
        Poker poker = aDeckOfPlayingCards.deal();
        if (null == poker){
            return false;
        }
        playerCards.add(poker);
        return true;
    }

    public TreeSet<Poker> getPlayerCards() {
        return playerCards;
    }

    public ADeckOfPlayingCards getaDeckOfPlayingCards() {
        return aDeckOfPlayingCards;
    }

}
