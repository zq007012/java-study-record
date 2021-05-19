package com.zq.client;

import java.util.Scanner;

/**
 * @ClassName: ClientScanner
 * @Description: TODO
 * @Author: zq007
 * @Date: 2021/1/19 9:45
 * @Version: V1.0
 */
public class ClientScanner {
    private static ClientScanner clientScanner;
    private Scanner scanner;

    private ClientScanner(){
        scanner = new Scanner(System.in);
    }

    public static ClientScanner getInstance(){
        if (null == clientScanner){
            synchronized (ClientScanner.class){
                if (null == clientScanner){
                    clientScanner = new ClientScanner();
                }
            }
        }
        return clientScanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void closeScanner(){
        scanner.close();
    }
}
