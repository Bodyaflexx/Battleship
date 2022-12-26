package battleship;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Ships extends Field {
    private String[] coordinates;
    protected static final Map<Character, Integer> symbols = new HashMap<>();
    private static int x1, x2, y1, y2;
    protected Field enemyField;

    public Ships() {
        this.coordinates = null;
    }

    public Ships(String[] coordinates) {
        this.coordinates = coordinates;
    }

    public void setCoordinates(String[] coordinates) {
        this.coordinates = coordinates;
    }

    protected static void fillMap() {
        symbols.put('A', 1);
        symbols.put('B', 2);
        symbols.put('C', 3);
        symbols.put('D', 4);
        symbols.put('E', 5);
        symbols.put('F', 6);
        symbols.put('G', 7);
        symbols.put('H', 8);
        symbols.put('I', 9);
        symbols.put('J', 10);
    }

    private void takeCoordinates() {
        y1 = Integer.parseInt(String.valueOf(coordinates[0].charAt(1)));
        if (coordinates[0].length() == 3) {
            y1 = 10;
        }
        x1 = symbols.get(coordinates[0].charAt(0));

        y2 = Integer.parseInt(String.valueOf(coordinates[1].charAt(1)));
        if (coordinates[1].length() == 3) {
            y2 = 10;
        }
        x2 = symbols.get(coordinates[1].charAt(0));
    }

    private boolean horizontal() {
        return x1 == x2;
    }

    public void putShips() {
        fillMap();
        takeCoordinates();
        char ourShip = 'O';
        if (horizontal()) {
            for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                field[x1 - 1][i - 1] = String.valueOf(ourShip);
            }
        } else {
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                field[i - 1][y1 - 1] = String.valueOf(ourShip);
            }
        }
    }

    public boolean firstChecker(int length) {
        fillMap();
        takeCoordinates();
        return x1 == x2 && Math.abs(y1 - y2) == length - 1 || y1 == y2 && Math.abs(x1 - x2) == length - 1;
    }

    public boolean secondChecker() {
        fillMap();
        takeCoordinates();
        if (horizontal()) {
            for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                try {
                    if (Objects.equals(field[x1 - 2][i - 2], "O") || Objects.equals(field[x1 - 1][i - 1], "O") || Objects.equals(field[x1][i], "O")) {
                        return false;
                    }
                } catch (Exception ignored) {
                }
            }
        } else {
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                try {
                    if (Objects.equals(field[i][y1 - 1], "O") || Objects.equals(field[i - 1][y1 - 2], "O") || Objects.equals(field[i + 1][y1], "O")) {
                        return false;
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return true;
    }

}

