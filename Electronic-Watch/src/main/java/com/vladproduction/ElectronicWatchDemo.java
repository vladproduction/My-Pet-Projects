package com.vladproduction;

import java.util.Scanner;

public class ElectronicWatchDemo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int seconds = scanner.nextInt();

        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int remain_seconds = seconds % 60;

        System.out.printf("%d:%02d:%02d%n", hours, minutes, remain_seconds);

        /*Examples:
        Input: 60
        Output: 0:01:00

        Input: 3599
        Output: 0:59:59

        Input: 86229
        Output: 23:57:09

        Input: 86400
        Output: 0:00:00*/

    }
}
