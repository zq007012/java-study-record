package com.lagou.zq.code.task5;

public enum DirectionEmum{
    UP("向上"),DOWN("向下"),LEFT("向左"),RIGHT("向右");
    private final String direction;

    DirectionEmum(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
    public void show(){
        DirectionEmum.values();
    }
}
