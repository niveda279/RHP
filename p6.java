import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int R = sc.nextInt();
        int C = sc.nextInt();

        int grid[][] = new int[R][C];

        for (int row = 0; row < R; row++) {
            for (int col = 0; col < C; col++) {
                grid[row][col] = sc.nextInt();
            }
        }

        int row = sc.nextInt();
        int col = sc.nextInt();

        int diff[][] = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
        };

        long sum = 0;

        for (int i = 0; i < 8; i++) {
            int adjRow = row + diff[i][0];
            int adjCol = col + diff[i][1];

            if (adjRow >= 0 && adjRow < R &&
                adjCol >= 0 && adjCol < C) {

                sum += grid[adjRow][adjCol];
            }
        }

        System.out.println(sum);
    }
};