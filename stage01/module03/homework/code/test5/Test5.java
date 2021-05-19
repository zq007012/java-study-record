package com.lagou.zq.homework.code.test5;

/*
5. 编程题

 使用集合实现斗地主游戏的部分功能，要求如下：

 （1）首先准备 54 张扑克牌并打乱顺序。

 （2）由三个玩家交替摸牌，每人 17 张扑克牌，最后三张留作底牌。

 （3）查看三个玩家手中的扑克牌和底牌。

 （4）其中玩家手中的扑克牌需要按照大小顺序打印，规则如下：

    手中扑克牌从大到小的摆放顺序：大王,小王,2,A,K,Q,J,10,9,8,7,6,5,4,3

 */
public class Test5 {
    public static void main(String[] args) {
        //创建一套牌
        ADeckOfPlayingCards aDeckOfPlayingCards = new ADeckOfPlayingCards();
        Player player1 = new Player(aDeckOfPlayingCards);
        Player player2 = new Player(aDeckOfPlayingCards);
        Player player3 = new Player(aDeckOfPlayingCards);

        Dealer dealer = new Dealer(aDeckOfPlayingCards, 3, player1, player2, player3);

        dealer.deal();
        System.out.println("---------------------------发牌结束---------------------------------");

        System.out.println("玩家1的牌: " + player1.getPlayerCards());
        System.out.println("玩家2的牌: " + player2.getPlayerCards());
        System.out.println("玩家3的牌: " + player3.getPlayerCards());
        System.out.println("底牌: " + dealer.getBottomCards());
    }
}
