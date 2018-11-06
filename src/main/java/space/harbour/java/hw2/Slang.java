package space.harbour.java.hw2;

import java.util.Scanner;

public class Slang {
    public static String fixAbbreviations(String input) {
        input = input.replace("PLZ", "please");
        input = input.replace("FYI", "for your information");
        input = input.replace("GTFO", "please, leave me alone");
        input = input.replace("ASAP", "as soon as possible");
        return input;
    }

    public static String fixSmiles(String input) {
        input = input.replace(":)", "[smiling]");
        input = input.replace(":(", "[sad]");
        input = input.replace("¯\\_(ツ)_/¯", "[such is life]");
        return input;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input: ");
        String input = scanner.nextLine();
        input = fixAbbreviations(input);
        input = fixSmiles(input);
        System.out.println("Output: " + input);
    }
}