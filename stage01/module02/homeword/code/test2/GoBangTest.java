package com.lagou.zq.homework.code.test2;

/*2. 编程实现控制台版并支持两人对战的五子棋游戏。

        （1）绘制棋盘 - 写一个成员方法实现

       dfadfa665456 （2）提示黑方和白方分别下棋并重新绘制棋盘 - 写一个成员方法实现。

        （3）每当一方下棋后判断是否获胜 - 写一个成员方法实现。

        （4）提示： 采用二维数组来模拟并描述棋盘，棋盘如下：*/
public class GoBangTest {
    public static void main(String[] args) {
        //new一个五子棋对象,开始玩游戏
        GoBang goBang = new GoBang();
        goBang.playGame();
    }
}
