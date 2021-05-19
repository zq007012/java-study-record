package com.lagou.zq.homework.code.test2;

import java.util.Arrays;
import java.util.Scanner;

/*（1）绘制棋盘 - 写一个成员方法实现

        （2）提示黑方和白方分别下棋并重新绘制棋盘 - 写一个成员方法实现。

        （3）每当一方下棋后判断是否获胜 - 写一个成员方法实现。

        （4）提示： 采用二维数组来模拟并描述棋盘，棋盘如下：*/
/*
思路:
    1.根据落子点的信息绘制棋盘
    2.提醒用户下棋, 用户每下一子,就判断一次胜负,如果下棋方没有赢,那就保存落子点的信息,重新绘制棋盘,提醒用户下棋
    如果下棋方方赢了,就结束游戏,

 */
public class GoBang {
    /*
        0 -- 黑子
        1 -- 白子
        2 -- 未落子
        0 1 是为了与false和true对应,方便思考,不易出错
     */
    private int[][] chessBoard = new int[16][16];
    /*false -- 黑方落子
      true   -- 白方落子
     */
    private boolean playingSide = false;
    private String[] pointSigns = new String[16];

    public GoBang(){
        resetChessBoard();
        for (int i = 0 ; i < pointSigns.length ; i++){
            pointSigns[i] = Integer.toHexString(i);
        }
    }
    /*将chessBoard的所有元素的元素的之都改为2

     */
    private void resetChessBoard() {
        for (int i = 0 ; i < chessBoard.length ; i++){
            Arrays.fill(chessBoard[i], 2);
        }
    }

    public void playGame(){
        //1. 绘制棋盘
        drawChessBoard();
        //2. 提示黑方和白方分别下棋并重新绘制棋盘 - 写一个成员方法实现
        //有黑方和白方下棋,那么每个位置就有三种状态, 那就需要一个容器能够描述这些位置,并且能描述这些位置上的棋子变化
        //二维数组就很符合,这个二维数组到处都会用到所以把它声明成成员变量,这样的话打印棋盘就需要进行一些改变了
        startPlay();
    }

    //2. 提示黑方和白方分别下棋并重新绘制棋盘
    private void startPlay() {
        Scanner scanner = new Scanner(System.in);
        //下棋是黑方下完白方下,白方下完黑方下,直到决出胜负,不清楚循环次数,所以用while循环控制
        Outer: while (true) {
            //提示黑方和白方分别下棋, 在下棋之前我们因该判断是谁在下棋,声明一个变量来记录是哪一方在下棋
            //boolean类型正好合适,只有两个值,正好和黑方白方匹配上,false表示该白方下,true表示该黑方下
            if (playingSide) {
                System.out.println("请白方下棋");
            } else {
                System.out.println("请黑方下棋");
            }
            System.out.println("请输入您下棋到第几行(用0 - f表示)");
            //把用户输入的字符串转化为int数值
            int xPoint = getPoint(scanner);
            //如果下棋方输入的行号不符合0-f,那就提醒下棋方重新下
            if (xPoint == -1){
                System.out.println("行数输入错误, 请重新输入------------------------------");
                continue ;
            }
            System.out.println("请输入您下棋到第几列(用0 - f表示)");
            int yPoint = getPoint(scanner);
            //如果下棋方输入的行号不符合0-f,那就提醒下棋方重新下
            if (yPoint == -1){
                System.out.println("列数输入错误, 请重新输入------------------------------");
                continue  ;
            }
            //如果此处已经有了棋子,那么就要求下棋方重新下
            if (hasChessPiece(xPoint,yPoint)){
                System.out.println("此处已有棋子, 请选择其他的位置");
                continue ;
            }
            //把下棋信息保存到chessBoard数组中
            saveSolution(xPoint, yPoint);
            //重新绘制棋盘
            drawChessBoard();

            //判断是否决出胜负
            switch (winner(xPoint,yPoint)){
                case 2 :
                    //还未决出胜负,循环继续
                    break;
                case 0 :
                    System.out.println("恭喜黑方获胜");
                    break Outer;
                case 1:
                    System.out.println("恭喜白方获胜");
                    break Outer;
            }

            //每次循环的最后都要改一下下棋方
            playingSide = !playingSide;
        }

        //while循环控制执行结束说明决出了胜负,那我们就需要复位下棋方,和代表棋盘的二维数组,以便开启下一个游戏
        playingSide = false;
        resetChessBoard();
    }

    private boolean hasChessPiece(int xPoint, int yPoint) {
        return 2 != chessBoard[xPoint][yPoint] ? true : false;
    }

    /*
    返回值为 2 ,说明未决出胜负
            0 , 说明黑方获胜
            1 , 说明白方获胜
     */
    private int winner(int xPoint, int yPoint) {
        /*
        得出获胜者的思路:
            1. 提取落子点横向上前四个和后四个落子点的元素值,依次与落子点组成一个有九个元素的数组
            2. 从下标0到下标4,每次从这个数组中提取5个元素组成新的数组,判断新数组的每个值是否跟落子一方的值
            全部一样,若全部一致,说明落子方获胜,若不一致则说明还未决出胜负
            3. 以相同原理对纵向 左斜 右斜方向进行判断
         */
        int winner = 2;

        //判断横向上是否有五个同色棋子连成一线
        int[] landscapeNineChessPieces = getLandscapeArray(xPoint, yPoint);
        int judgeResult1 = getPlayingResult(landscapeNineChessPieces);
        if (judgeResult1 != 2){
            //System.out.println("横向");
            return judgeResult1;
        }

        //判断纵向上是否有五个同色棋子连成一线
        int[] portraitNineChessPieces = getPortraitArray(xPoint, yPoint);
        int judgeResult2 = getPlayingResult(portraitNineChessPieces);
        if (judgeResult2 != 2){
            //System.out.println("纵向");
            return judgeResult2;
        }

        //判断左斜方向上是否有五个同色棋子连成一线
        int[] leftObliqueNineChessPieces = getLeftObliqueArray(xPoint, yPoint);
        int judgeResult3 = getPlayingResult(leftObliqueNineChessPieces);
        if (judgeResult3 != 2){
            //System.out.println("左斜方");
            return judgeResult3;
        }

        //判断右斜方向上是否有五个同色棋子连成一线
        int[] rightObliqueNineChessPieces = getRightObliqueArray(xPoint, yPoint);
        int judgeResult4 = getPlayingResult(rightObliqueNineChessPieces);
        if (judgeResult4 != 2){
            //System.out.println("右斜方");
            return judgeResult4;
        }

        return winner;
    }

    private int[] getRightObliqueArray(int xPoint, int yPoint) {
        int[] nineChessPieces = new int[9];
        Arrays.fill(nineChessPieces,2);
        for (int i = xPoint - 4 , j = yPoint + 4 , k = 0; i <= xPoint + 4 && j >= yPoint - 4; i ++ , j--){
            if (i < 0 || j > 15){
                continue;
            }
            if (i > 15 || j < 0){
                break;
            }
            nineChessPieces[k] = chessBoard[i][j];
            k++;
        }
        return nineChessPieces;
    }

    private int[] getLeftObliqueArray(int xPoint, int yPoint) {
        int[] nineChessPieces = new int[9];
        Arrays.fill(nineChessPieces,2);
        for (int i = xPoint -4 , j = yPoint - 4 , k = 0; i <= xPoint + 4 && j <= yPoint + 4; i ++ , j++){
            if (i < 0 || j < 0){
                continue;
            }
            if (i > 15 || j > 15){
                break;
            }
            nineChessPieces[k] = chessBoard[i][j];
            k++;
        }
        return nineChessPieces;
    }

    private int[] getPortraitArray(int xPoint, int yPoint) {
        int[] nineChessPieces = new int[9];
        Arrays.fill(nineChessPieces,2);
        for (int i = xPoint -4 , j = 0; i <= xPoint + 4 ; i ++){
            if (i < 0){
                continue;
            }
            if (i > 15){
                break;
            }
            nineChessPieces[j] = chessBoard[i][yPoint];
            j++;
        }
        return nineChessPieces;
    }


    private int[] getLandscapeArray(int xPoint, int yPoint) {
        int[] nineChessPieces = new int[9];
        Arrays.fill(nineChessPieces,2);
        for (int i = yPoint -4 , j = 0 ; i <= yPoint + 4 ; i ++){
            if (i < 0){
                continue;
            }
            if (i > 15){
                break;
            }
            nineChessPieces[j] = chessBoard[xPoint][i];
            j++;
        }
        return nineChessPieces;
    }


    private int getPlayingResult(int[] nineChessPieces) {
        int playingResult = 2;

        int[] fiveChessPieces = new int[5];
        int chessPiece = playingSide ? 1 : 0;
        int count;
        Outer: for (int i = 0 ; i <=4 ; i++){
            fiveChessPieces = Arrays.copyOfRange(nineChessPieces,i,i+5);
            count = 0 ;
            for (int j = 0 ; j < 5 ;j++){
                if (chessPiece == fiveChessPieces[j]){
                    count++;
                }
            }
            if (5 == count){
                playingResult = chessPiece;
                break Outer;
            }
        }

        return playingResult;
    }



    private void saveSolution(int xPoint, int yPoint) {
        chessBoard[xPoint][yPoint] = playingSide ? 1 : 0;//true白方落子, false黑方落子
    }

    private int getPoint(Scanner scanner) {
        //把0-f最为字符串保存到数组pointSigns中,用户输入的字符串在这个数组中的下标就是用户输入的坐标的int值了
        //如果用户输入的字符串不符合0 - f 那就返回一个int类型的-1
        String key = scanner.next();
        int index = -1;
        for (int i = 0 ; i < pointSigns.length ; i++){
            if (key.equals(pointSigns[i])){
                index = i;
                break;
            }
        }
        return index;
    }

    private void drawChessBoard() {
        //绘制棋盘很简单,上一模块就做过了
        //1. 打印第一行0 - f的十六进制数字,开头为空格
        System.out.print("  ");
        for (int i = 0 ; i < 16 ; i++){
            System.out.print(Integer.toHexString(i) + " ");//数字后加个空格,画出来的棋盘比较美观
        }
        System.out.println();//一行打印结束后换行
        //2. 打印生下的16行和17列, 用双重for循环
        for (int i = 0 ; i < 16 ; i++){
            //因为每一行的第一列与其他列有明显的规律不同,所以第一列专门提出来用相应的规律打印
            System.out.print(Integer.toHexString(i) + " ");
            for (int j = 0; j < 16 ; j++){
                //二维数组的元素的每一个元素只会有三个值:0 1 2
                /*2代表该位置还未落子
                    0代表该位置落的是黑子
                    1代表该位置落的是白子*/
                //有三个确定值的分支,使用switch分支结构
                switch (chessBoard[i][j]){
                    case 2:
                        System.out.print("+ ");
                        break;
                    case 0:
                        System.out.print("* ");
                        break;
                    case 1:
                        System.out.print("@ ");
                        break;
                }
            }
            System.out.println();//每行打印结束要换行
        }
    }
}
