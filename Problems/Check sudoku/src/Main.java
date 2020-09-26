import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = sc.nextInt();
        int sqSize = size * size;
        Integer[][] matrix = new Integer[sqSize][sqSize];
        for (int i = 0; i < sqSize; i++) {
            for (int j = 0; j < sqSize; j++) {
                matrix[j][i] = sc.nextInt();
            }
        }
        Integer[] values = new Integer[sqSize];
        for (int i = 0; i < sqSize; i++) {
            values[i] = i + 1;
        }
        if (checkMatrix(matrix, values, size)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
    public static boolean checkMatrix(Integer[][] matrix, Integer[] values, int boxSize) {
        Set<Integer> set = new HashSet<>(Arrays.asList(values));
        for (Integer[] row : matrix)  {
            Set<Integer> rowSet = new HashSet<>(Arrays.asList(row));
            if (!rowSet.equals(set)) {return false;}
        }
        for (int i = 0; i < values.length; i++) {
            Set<Integer> colSet = new HashSet<>(Arrays.asList(getColumn(i, matrix)));
            if (!colSet.equals(set)) {return false;}
        }
        for (int x = 0; x < values.length; x++) {
            Set<Integer> boxSet = new HashSet<>(Arrays.asList(getBox(x, matrix, boxSize)));
            if (!boxSet.equals(set)) {return false;}
        }
        return true;
    }
    public static Integer[] getColumn(int i, Integer[][] matrix) {
        int size = matrix.length;
        Integer[] col = new Integer[size];
        for (int j = 0; j < size; j++) {
            col[j] = matrix[j][i];
        }
        return col;
    }
    public static Integer[] getBox(int x, Integer[][] matrix, int boxSize) {
        int size = matrix.length;
        int iAdjust = (x % boxSize) * boxSize;
        int jAdjust = (x / boxSize) * boxSize;
        Integer[] box = new Integer[size];
        for (int j = 0; j < boxSize; j++) {
            for (int i = 0; i < boxSize; i++) {
                box[j * boxSize + i] = matrix[jAdjust + j][iAdjust + i];
            }
        }
        return box;
    }
}
