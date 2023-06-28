import java.util.Scanner;



class Main {
    public static boolean symmetric(int[][] matrix, int a) {
        boolean sym = true;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    sym = false;
                    return sym;
                }
            }
        }
        return sym;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int[][] matrix = new int[a][a];

        boolean sym = true;
        for (int l = 0; l < a; l++) {
            for (int r = 0; r < a; r++) {
                int num = scanner.nextInt();
                matrix[l][r] = num;
            }
        }

        if (symmetric(matrix, a)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}