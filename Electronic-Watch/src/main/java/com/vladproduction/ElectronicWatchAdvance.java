package com.vladproduction;

import java.util.Scanner;

public class ElectronicWatchAdvance {

    public static final int SECONDS_A_DAY = 86400;
    public static final int SECONDS_IN_HOUR = 3600;
    public static final int SECONDS_IN_MINUTE = 60;

    public static void main(String[] args) {

        Scanner scannerAdv = new Scanner(System.in);
        int seconds = scannerAdv.nextInt();

        seconds = seconds % SECONDS_A_DAY;

        int hours = seconds / SECONDS_IN_HOUR;
        int minutes = (seconds % SECONDS_IN_HOUR) / SECONDS_IN_MINUTE;
        int remain_seconds = seconds % SECONDS_IN_MINUTE;

        System.out.printf("%d:%02d:%02d%n", hours, minutes, remain_seconds);

        /*Examples:
        Input: 60
        Output: 0:01:00

        Input: 3599
        Output: 0:59:59

        Input: 86229
        Output: 23:57:09

        Input: 86400
        Output: 0:00:00

        Input: 89999 (advance)
        Output: 0:59:59

        Input: 86460 (advance)
        Output: 0:01:00*/
    }
}
