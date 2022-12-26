package battleship;

import java.util.Arrays;

public class Field {
    private final static int row = 10;
    private final static int column = 10;
    public String[][] field = new String[row][column];
    public String[][] emptyField = new String[row][column];


    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String[][] getField() {
        return field;
    }

    public String[][] getEmptyField() {
        return emptyField;
    }

    public void printField() {
        System.out.print("  ");
        for (int i = 1; i <= row; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        int i = 0;
        for (char c = 'A'; c <= 'J'; c++) {

            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < field[i].length; j++) {
                tmp.append(field[i][j]).append(" ");

            }
            System.out.println(c + " " + tmp);
            i++;
        }
    }

    public void fillField() {
        for (String[] row : field) {
            Arrays.setAll(row, i -> "~");
        }
    }

    public void fillEmptyField() {
        for (String[] row : emptyField) {
            Arrays.setAll(row, i -> "~");
        }
    }

    public void printEmptyField() {
        System.out.print("  ");
        for (int i = 1; i <= row; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();
        int i = 0;
        for (char c = 'A'; c <= 'J'; c++) {

            StringBuilder tmp = new StringBuilder();
            for (int j = 0; j < emptyField[i].length; j++) {
                tmp.append(emptyField[i][j]).append(" ");

            }
            System.out.println(c + " " + tmp);
            i++;
        }
    }

}
