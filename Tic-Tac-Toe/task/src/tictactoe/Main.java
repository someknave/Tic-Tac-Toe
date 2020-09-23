package tictactoe;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        String[] grid = getGrid(str);
        printGrid(grid);
    }
    public static void printGrid(String[] grid) {
        for (String s : grid) {
            System.out.println(s);
        }
    }

    public static String[] getGrid(String str) {
        String[] lines = new String[5];
        lines[0] = "---------";
        lines[4] = lines[0];
        for (int j = 0; j < 3; ++j) {
            StringBuilder build = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                switch (i) {
                    case 0:
                        build = new StringBuilder("| ");
                        break;
                    case 4:
                        build.append("|");
                        break;
                    default:
                        build.append(str.charAt(i - 1 + j * 3));
                        build.append(' ');
                        break;
                }
            }
            lines[j + 1] = build.toString();
        }
        return lines;
    }
}
