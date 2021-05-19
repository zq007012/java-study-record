package com.lagou.zq.homework.code.test5;

import java.util.*;

/**
 * 一套扑克牌
 * 扑克牌从大到小的摆放顺序：大王,小王,2,A,K,Q,J,10,9,8,7,6,5,4,3
 */
public class ADeckOfPlayingCards {
    /**
     * 应该有一套扑克牌,使用LinkedList集合,因为玩家会有大量的获取和删除操作
     */
    private LinkedList<Poker> aDeckOfPlayingCards;
    public ADeckOfPlayingCards(){
        aDeckOfPlayingCards = new LinkedList<>();
        resetCards();
    }

    public void resetCards() {
        aDeckOfPlayingCards.clear();
        aDeckOfPlayingCards.add(Poker.getRedJoker());
        aDeckOfPlayingCards.add(Poker.getBlackJoker());
        PokerSuit[] pokerSuits = PokerSuit.values();
        PokerRank[] pokerRanks = PokerRank.values();
        for (PokerSuit pokerSuit :
                pokerSuits) {
            for (PokerRank pokerRank :
                    pokerRanks) {
                aDeckOfPlayingCards.add(new Poker(pokerSuit, pokerRank));
            }
        }
    }

    public LinkedList<Poker> shuffle(){
        Collections.shuffle(aDeckOfPlayingCards);
        return aDeckOfPlayingCards;
    }

    /**
     * 向玩家发牌,发的牌就不再这套牌中了
     * @return
     */
    public Poker deal(){
        return aDeckOfPlayingCards.poll();
    }

    public LinkedList<Poker> getaDeckOfPlayingCards() {
        return aDeckOfPlayingCards;
    }
}
