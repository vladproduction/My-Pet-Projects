package com.vladproduction;

import java.util.Scanner;

public class HelloStrangers {
    public static void main(String[] args) {

        //Write a program, asks for a number - amount of strangers to meet.
        //Then reads stranger names line by line and prints line by line "Hello, ...".
        Scanner scanner = new Scanner(System.in);
        int numberOfStrangers = scanner.nextInt();
        if(numberOfStrangers < 0){
            System.out.println("Seriously? Why so negative?");
        } else if (numberOfStrangers == 0) {
            System.out.println("Oh, it looks like there is no one here");
        } else {
            scanner.nextLine();
            int count = 0;
            while (count < numberOfStrangers){
                String nameOfStranger = scanner.nextLine();
                if(!nameOfStranger.isEmpty()){
                    System.out.println("Hello, " + nameOfStranger);
//                    System.out.printf("Hello, %s%n", nameOfStranger);
//                    StringBuilder sb = new StringBuilder("Hello, ");
//                    sb.append(nameOfStranger);
//                    System.out.println(sb);
                    count++;
                }
            }
        }
        scanner.close();

    }
}
