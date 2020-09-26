package tictactoe;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String str = "_________";
        String[] grid = getGrid(str);
        printGrid(grid);
        char player = 'X';
        while (checkStatus(str)) {
            int[] move = null;
            while (move == null) {
                move = checkMove(str);
                if (move != null) {
                    str = makeMove(player, str, move);
                    grid = getGrid(str);
                    if (player == 'X') {
                        player = 'O';
                    } else {
                        player = 'X';
                    }
                }
            }
            printGrid(grid);
        }
    }
    public static void printGrid(String[] grid) {
        for (String s : grid) {
            System.out.println(s);
        }
    }
    public static int[] checkMove(String str) {
        System.out.println("Enter the coordinates:");
        int[] ints = {-1, -1};
        String s1 = input.next();
        String s2 = input.next();
        try {
            ints[0] = Integer.parseInt(s1.trim());
            ints[1] = Integer.parseInt(s2.trim());
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("You should enter numbers!");
            return null;
        }
        if (3 < ints[0] || ints[0] < 1 || 3 < ints[1] || ints[1] < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            return null;
        }
        if (str.charAt((3 - ints[1]) * 3 + ints[0] - 1) != '_') {
            System.out.println("This cell is occupied! Choose another one!");
            return null;
        }
        return ints;
    }
    public static String makeMove(char c, String str, int[] move) {
        int index = (3 - move[1]) * 3 + move[0] - 1;
        return str.substring(0, index) + c + str.substring(index + 1);
    }
    public static Boolean checkStatus(String str) {
        int[] counts = countXO(str);
        if (counts[0] + counts[1] + counts[2] != 9) {
            System.out.println("Impossible");
            return false;
        }
        if (Math.abs(counts[0] - counts[1]) >= 2) {
            System.out.println("Impossible");
            return false;
        }
        boolean xWin = check3inaRow(str, 'X');
        boolean oWin = check3inaRow(str, 'O');
        if (xWin && oWin) {
            System.out.println("Impossible");
            return false;
        }
        if (xWin) {
            System.out.println("X wins");
            return false;
        }
        if (oWin) {
            System.out.println("O wins");
            return false;
        }
        if (counts[2] == 0) {
            System.out.println("Draw");
            return false;
        }
        return true;

    }
    public static Boolean check3inaRow(String str, char c) {
        boolean threeInRow = true;
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (str.charAt(3 * j + i) != c) {
                    threeInRow = false;
                    break;
                }
            }
            if (threeInRow) {return true;}
            threeInRow = true;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (str.charAt(3 * j + i) != c) {
                    threeInRow = false;
                    break;
                }
            }
            if (threeInRow) {return true;}
            threeInRow = true;
        }
        for (int i = 0; i < 9; i+= 4) {
            if (str.charAt(i) != c) {
                threeInRow = false;
                break;
            }
        }
        if (threeInRow) {return true;}
        threeInRow = true;
        for (int i = 2; i < 7; i += 2) {
            if (str.charAt(i) != c) {
                threeInRow = false;
                break;
            }
        }
        return threeInRow;
    }
    public static int[] countXO(String str) {
        int[] counts = {0, 0, 0};
        for (int i = 0; i < 9; i++) {
            switch (str.charAt(i)) {
                case 'X':
                    counts[1] += 1;
                    break;
                case 'O':
                    counts[0] += 1;
                    break;
                case '_':
                    counts[2] += 1;
                    break;
                default:
                    break;
            }
        }
        return counts;
    }

    public static char[][] stripGrid(String[] grid) {
        char[][] sGrid = new char[3][3];
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                sGrid[j][i] = grid[j+1].charAt(2 * i + 2);
            }
        }
        return sGrid;
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
