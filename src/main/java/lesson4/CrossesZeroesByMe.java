package lesson4;

import java.util.Random;
import java.util.Scanner;


public class CrossesZeroesByMe {

    public static char[][] map;
    public static int size;
    public static int dots_to_win;

    public static int countX = 0;
    public static int countO = 0;

    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final char DOT_EMPTY = '*';

    public static final Scanner scanner = new Scanner(System.in);
    public static final Random random = new Random();

    public static void initMap(int size) {
        map = new char[size][size];
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

    public static void humanTurn(int size) {
        int x, y;
        do {
            System.out.println("Введите координаты хода, где Х - (cтрока) У - (столбец)");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y, size));
        map[x][y] = DOT_X;

    }

    public static boolean isCellValid(int x, int y, int size) {
        if (x < 0 || x >= size || y < 0 && y >= size) {
            return false;
        }
        if (map[x][y] == DOT_EMPTY) {
            return true;
        }
        return false;
    }

    public static void aiTurn(int size) {
        System.out.println();
        int x, y;
        do {
            x = random.nextInt(size);
            y = random.nextInt(size);
        } while (!isCellValid(x, y, size));
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


    public static boolean checkWin(char dot, int dots_to_win) {
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
                        if (dots_to_win == countX || dots_to_win == countO) {
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
                            if (dots_to_win == countX || dots_to_win == countO)
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
                            if (dots_to_win == countX || dots_to_win == countO) {
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
                            if (dots_to_win == countX || dots_to_win == countO) {
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
                        if (dots_to_win == countX || dots_to_win == countO) {
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
                        if (dots_to_win == countX || dots_to_win == countO) {
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
                            if (dots_to_win == countX || dots_to_win == countO) {
                                return true;
                            }
                        }

                    }
                }
            }
        }

        return false;
    }


    public static void play(int size) {
        initMap(size);
        printMap();
        while (true) {
            humanTurn(size);
            printMap();
            if (checkWin(DOT_X, dots_to_win)) {
                System.out.println("Победил человек");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn(size);
            printMap();
            if (checkWin(DOT_O, dots_to_win)) {
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
        int d, d1;
        do {
            System.out.println("Введите размер поля на котором хотите поиграть от 1 до 10");
            d = scanner.nextInt();
        } while (d < 1 || d > 10);
        do {
            System.out.println("Введите количество заполненных клеточек, необходимых для победы от 3 до 10, но не больше выбранного вами размера поля");
            dots_to_win = scanner.nextInt();
        } while (dots_to_win < 1 || dots_to_win > d);
        play(d);

    }
}
