package lesson4;

import java.util.Random;
import java.util.Scanner;


public class CrossesZeroesByMe {

    public static char[][] map;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;

    public static int countX = 0;
    public static int countO = 0;


    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_EMPTY = '*';

    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    public static void initMap(){
        map = new char[SIZE][SIZE];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap(){
        //Нумерация столбцов
        for (int i = 0; i <= map.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        //Нумерация строк
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn(){
        int x, y;
        do {
            System.out.println("Введите координаты хода, где Х - (cтрока) У - (столбец)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;

    }

    public static boolean isCellValid(int x, int y){
        if(x < 0 || x >= SIZE || y < 0 && y >= SIZE){
            return false;
        }
        if(map[x][y] == DOT_EMPTY){
            return true;
        }
        return false;
    }

    public static void aiTurn(){
        System.out.println();
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в " + (x+1) + " " + (y+1));
        map[x][y] = DOT_O;

    }

    public static boolean isFull(){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }



    public static boolean checkWin(char dot){

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == j || i + j == map.length - 1){
                    if(map[i][j] == dot && DOTS_TO_WIN == countX || map[i][j] == dot && DOTS_TO_WIN == countO) {
                        return true;
                    }
                }
            }
        }



        return false;
    }

    public static void play(){
        initMap();
        printMap();
        while(true) {
            humanTurn();
            printMap();
            if(checkWin(DOT_X)){
                System.out.println("Победил человек");
                break;
            }
            if(isFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)){
                System.out.println("Победил компуктер");
                break;
            }
            if(isFull()) {
                System.out.println("Ничья");
                break;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Пора поиграть!");
        play();
    }






}
