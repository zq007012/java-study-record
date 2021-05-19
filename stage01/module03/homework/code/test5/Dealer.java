package com.lagou.zq.homework.code.test5;

import java.util.LinkedList;

/**
 * 发牌员
 */
public class Dealer {
    private ADeckOfPlayingCards aDeckOfPlayingCards;
    private Player[] players;
    private LinkedList<Poker> bottomCards;
    private int bottomCardsAmount;

    public Dealer(ADeckOfPlayingCards aDeckOfPlayingCards, int bottomCardsAmount, Player...players) {
        this.aDeckOfPlayingCards = aDeckOfPlayingCards;
        //System.out.println(players.getClass());//players是个数组
        //setBottomCards(bottomCardsAmount);
        this.bottomCardsAmount = bottomCardsAmount;
        this.players = players;
    }

    private void setBottomCards(int bottomCardsAmount) {
        bottomCards = new LinkedList<>();
        for (int i = 0 ; i < bottomCardsAmount ; i++){
            bottomCards.add(aDeckOfPlayingCards.getaDeckOfPlayingCards().pollLast());
        }
    }

    public void deal(){
        System.out.println("发牌前将牌复位, 检查牌有无缺失, 检查中...");
        //1. 复位扑克牌
        aDeckOfPlayingCards.resetCards();
        System.out.println("复位后的牌: " + aDeckOfPlayingCards.getaDeckOfPlayingCards().toString());
        System.out.println("---------------------检查结束------------------------------");
        System.out.println("---------------------开始洗牌------------------------------");
        //2. 洗牌
        aDeckOfPlayingCards.shuffle();
        System.out.println("---------------------洗牌结束------------------------------");
        System.out.println("洗完后的牌:" + aDeckOfPlayingCards.getaDeckOfPlayingCards().toString());
        //3. 设置底牌,并将底牌从牌中取出
        setBottomCards(bottomCardsAmount);
        //4. 轮流玩家发牌, 直到发完
        while (true){
            for (Player player :
                    players) {
                if (!player.getPoker()){
                    return;
                }
            }
        }
    }

    public ADeckOfPlayingCards getaDeckOfPlayingCards() {
        return aDeckOfPlayingCards;
    }

    public Player[] getPlayers() {
        return players;
    }

    public LinkedList<Poker> getBottomCards() {
        return bottomCards;
    }
}
