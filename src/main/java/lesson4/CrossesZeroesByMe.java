package lesson4;

import java.util.Random;
import java.util.Scanner;


public class CrossesZeroesByMe {

    public static char[][] map;
    public static final int SIZE = 5;
    public static final int DOTS_TO_WIN = 4;

    public static int countX = 0;
    public static int countO = 0;

    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_EMPTY = '*';

    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
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

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты хода, где Х - (cтрока) У - (столбец)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));
        map[x][y] = DOT_X;

    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 && y >= SIZE) {
            return false;
        }
        if (map[x][y] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

    public static void aiTurn() {
        System.out.println();
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в " + (x + 1) + " " + (y + 1));
        map[x][y] = DOT_O;

    }

    public static boolean isFull() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }


    public static boolean checkWin(char dot) {
        //Условия победы диагоналей
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i + j == map.length - 1) {
                    if (map[i][j] != dot) {
                        countX--;
                        countO--;
                    }
                    if (map[i][j] == dot) {
                        countX++;
                        countO++;
                        if (DOTS_TO_WIN == countX || DOTS_TO_WIN == countO) {
                            return true;
                        }
                    }
                }
            }
        }
            countX = 0;
            countO = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (i == j) {
                        if (map[i][j] != dot) {
                            countX--;
                            countO--;
                        }
                        if (map[i][j] == dot) {
                            countX++;
                            countO++;
                            if (DOTS_TO_WIN == countX || DOTS_TO_WIN == countO)
                                return true;
                        }
                    }
                }
            }

            //Условия победы строк
        countX = 0;
        countO = 0;
        int step = 0;
        for (int i = 0; i < map.length; i++) {
            if (i == step) {
                countX = 0;
                countO = 0;
                for (int j = 0; j < map[i].length; j++) {
                        if (map[i][j] != dot) {
                            countX--;
                            countO--;
                        }
                        if (map[i][j] == dot) {
                            countX++;
                            countO++;
                            if (DOTS_TO_WIN == countX || DOTS_TO_WIN == countO) {
                                return true;
                            }
                        }
                    }
                step++;
            }
        }

        //Условие победы столбцов
        countX = 0;
        countO = 0;
        step = 0;
        do {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {

                    if(j == step) {
                        if (map[i][j] != dot) {
                            countX--;
                            countO--;
                        }
                        if (map[i][j] == dot) {
                            countX++;
                            countO++;
                            if (DOTS_TO_WIN == countX || DOTS_TO_WIN == countO) {
                                return true;
                            }
                        }
                    }
                }

            }
            countX = 0;
            countO = 0;
            step++;
        } while (step < map.length);


        //Условие победы побочных диагоналей при DOTS_TO_WIN = 4 на поле 5;
        /**
         * Я пыталась написать в общем виде, чтобы на любом размере поля больше 5 при параметре DOTS_TO_WIN = 4
         * все возможные диагонали давали победу при 4х точках, но условие очень сложное получается.
         * Я не исключаю, что можно найти закономерность, но у меня не получилось((
         * Поэтому DOTS_TO_WIN = 4 будет работать только на поле 5 ((
         */
        countX = 0;
        countO = 0;
        step = 1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == dot) {
                    if (i + j == step) {
                        countX++;
                        countO++;
                        if (DOTS_TO_WIN == countX || DOTS_TO_WIN == countO) {
                            return true;
                        }
                        step = step + 2;
                    }
                }
            }
        }

        countX = 0;
        countO = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i + j == 3) {
                    if (map[i][j] != dot) {
                        countX--;
                        countO--;
                    }
                    if (map[i][j] == dot) {
                        countX++;
                        countO++;
                        if (DOTS_TO_WIN == countX || DOTS_TO_WIN == countO) {
                            return true;
                        }
                    }
                    if (i + j == 5) {
                        if (map[i][j] != dot) {
                            countX--;
                            countO--;
                        }
                        if (map[i][j] == dot){
                            countX++;
                            countO++;
                            if (DOTS_TO_WIN == countX || DOTS_TO_WIN == countO) {
                                return true;
                            }
                        }

                    }
                }
            }
        }


        return false;
    }


    public static void play() {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил компуктер");
                break;
            }
            if (isFull()) {
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
