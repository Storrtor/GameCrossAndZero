package lesson4;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {

        createDiag(5);
        createLineI(5);
    }

    public static void createDiag(int n){
        int[][] array = new int[n][n];
        for (int i = 0; i < array.length ; i++) {
            for (int j = 0; j < array[i].length ; j++) {
                array[i][j] = 0;
                if (i == j || i + j == array.length - 1){
                    array[i][j] = 1;
                }
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void createLineI(int n){
        int[][] array = new int[n][n];
        for (int i = 0; i < array.length ; i++) {
            for (int j = 0; j < array.length - (array.length - 1); j++) {

                    array[i][j] = 1;

                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
