package space.harbour.java.hw2;

import java.util.Scanner;

public class Pascal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input: ");
        int size = scanner.nextInt();

        for(int i = 0; i < size; i ++) {
            int num = 1;
            int r = i + 1;

            for(int j = 0; j < size - i; j ++) {
                System.out.print(" ");
            }

            for(int j = 0; j <= i; j++) {
                if(j > 0)
                    num = num * (r - j) / j;
                System.out.print(num + " ");
            }

            System.out.println();
        }
    }
}