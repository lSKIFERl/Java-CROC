package com.company;

import java.util.Scanner;

public class Main {

    static void bytesTheDust(double number)
    {
        byte index = 0;
        String[] unit = new String[] {"B", "KB", "MB", "GB", "TB", "PB", "HB", "ZB", "YB"};
        for (; number > 1023 && index < unit.length-1; index++)
            number /= 1024;
        System.out.println(String.format("%.1f", number) + " " + unit[index]);
    }

    public static void main(String[] args) {
        bytesTheDust(new Scanner(System.in).nextDouble());
    }
}
