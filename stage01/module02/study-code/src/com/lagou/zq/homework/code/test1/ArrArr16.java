package com.lagou.zq.homework.code.test1;

import java.util.Arrays;

/*定义一个长度为[16][16]的整型二维数组并输入或指定所有位置的元素值，
分别实现二维数组中所有行和所有列中所有元素的累加和并打印。
再分别实现二维数组中左上角到右下角和右上角到左下角所有元素的累加和并打印*/
public class ArrArr16 {
    /*
    思路:
        1. 声明一个长度为[16][16]的二维数组
        2. 给二维数组中的一维数组依次输入1-n的值
        3. 实现所有行和所有列中所有元素的累加合并打印
        4. 实现二维数组中左上角到右下角的所有元素的累加和
        5. 实现二维数组中右上角到左下角所有元素的累加和并打印
     */
    public static void main(String[] args) {
        //1. 声明一个长度为[16][16]的二维数组
        int[][] arrArr = new int[16][16];
        //2. 给二维数组中的一维数组依次输入1-n的值
        valueForAllElements(arrArr);
        //打印二维数组
        System.out.println("这个二维数组里的所有元素分别是: ");
        printArrArr(arrArr);
        //3.实现所有行和所有列中所有元素的累加合并打印
        System.out.println("这个二维数组所有元素的累加和是: " + sumOfAllElements(arrArr));
        //4. 实现二维数组中左上角到右下角的所有元素的累加和
        System.out.println("这个二维数组左上角到右下角的所有元素的累加和是: " +
                sumOfTopLeftToTopRight(arrArr));

        //5. 实现二维数组中右上角到左下角所有元素的累加和并打印
        System.out.println("这个二维数组右上角到左下角的所有元素的累加和是: " +
                sumOfTopRightToTopLeft(arrArr));
    }

    private static int sumOfTopRightToTopLeft(int[][] arrArr) {
        int sum = 0;
        for (int i = 0; i < arrArr.length ; i++){
            sum += arrArr[i][arrArr.length - 1 - i];
        }
        return sum;
    }

    private static int sumOfTopLeftToTopRight(int[][] arrArr) {
        int sum = 0;
        for (int i = 0 ; i < arrArr.length ; i++){
            sum += arrArr[i][i];
        }
        return sum;
    }

    private static int sumOfAllElements(int[][] arrArr) {
        int sum = 0;
        for (int i = 0 ; i < arrArr.length ; i++){
            for (int j = 0; j < arrArr.length ; j++){
                sum += arrArr[i][j];
            }
        }
        return sum;
    }

    private static void printArrArr(int[][] arrArr) {
        for (int i = 0 ; i < arrArr.length ; i++){
            System.out.println(Arrays.toString(arrArr[i]));
        }
    }

    private static void valueForAllElements(int[][] arrArr) {
        int value = 1 ;
        for (int i = 0; i < arrArr.length ; i ++){
            for (int j = 0; j < arrArr.length ; j++){
                arrArr[i][j] = value;
                value++;
            }
        }
    }
}
