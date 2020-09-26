import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();

        String[][] matrix = new String[size][size];
        for (int index = 0; index < size * size; index++) {
            int[] coords = getCoords(index, size - 1);
            matrix[coords[1]][coords[0]] = Integer.toString(index + 1);
        }
        for (String[] line : matrix) {
            System.out.println(String.join(" ", line));
        }
    }
    public static int[] getCoords(int index, int asize) {
        int[] coords = new int[]{0, 0};
        if (asize == 0) {
            return coords;
        }
        if (index >= 4 * asize) {
            int[] acoords = getCoords(index - 4 * asize, asize - 2);
            coords[0] = acoords[0] + 1;
            coords[1] = acoords[1] + 1;
            return coords;
        }
        int side = index / asize;
        switch (side) {
            case 0: 
                coords[0] = index;
                coords[1] = 0;
                break;
            case 1: 
                coords[0] = asize;
                coords[1] = index - asize;
                break;
            case 2: 
                coords[0] = 3 * asize - index;
                coords[1] = asize;
                break;
            case 3: 
                coords[0] = 0;
                coords[1] = 4 * asize - index;
                break;
        }
        return coords;
    }
}
