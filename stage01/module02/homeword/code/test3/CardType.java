package com.lagou.zq.homework.code.test3;

public enum CardType {
    SIM("大卡"),MICRO_SIM("小卡"),NANO_SIM("微型卡");

    private final String cardType;

    CardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardType() {
        return cardType;
    }
}
