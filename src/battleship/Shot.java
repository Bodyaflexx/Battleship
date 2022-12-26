package battleship;

import java.util.Objects;

public class Shot extends Ships {
    String shotCoordinate;
    String[][] enemyField;
    String[][] enemyEmptyField;

    public void setShotCoordinate(String shotCoordinate, String[][] enemyField, String[][] enemyEmptyField) {
        this.shotCoordinate = shotCoordinate;
        this.enemyField = enemyField;
        this.enemyEmptyField = enemyEmptyField;
    }


    public String checkOnHit() {
        try {
            StringBuilder number = new StringBuilder();
            for (int i = 1; i < shotCoordinate.length(); i++) {
                number.append(shotCoordinate.charAt(i));
            }
            int y = Integer.parseInt(String.valueOf(number));
            if (y > 10) {
                return null;
            }
            int x = symbols.get(shotCoordinate.charAt(0));
            if (Objects.equals(enemyField[x - 1][y - 1], "O") || Objects.equals(enemyField[x - 1][y - 1], "X")) {
                enemyField[x - 1][y - 1] = "X";
                emptyField[x - 1][y - 1] = "X";
                return "You hit a ship!";
            } else {
                enemyField[x - 1][y - 1] = "M";
                emptyField[x - 1][y - 1] = "M";
                return "You missed!";
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean checkOnDestroyed() {
        StringBuilder number = new StringBuilder();
        for (int i = 1; i < shotCoordinate.length(); i++) {
            number.append(shotCoordinate.charAt(i));
        }
        int y = Integer.parseInt(String.valueOf(number));
        int x = symbols.get(shotCoordinate.charAt(0));
        try {
            if (Objects.equals(enemyField[x-2][y-1], "O")) {
                return false;
            }
        } catch (Exception ignored) {

        }
        try {
            if (Objects.equals(enemyField[x - 1][y - 2], "O")) {
                return false;
            }
        } catch (Exception ignored) {

        }
        try {
            if (Objects.equals(enemyField[x][y - 1], "O")) {
                return false;
            }
        } catch (Exception ignored) {

        }
        try {
            if (Objects.equals(enemyField[x - 1][y], "O")) {
                return false;
            }
        } catch (Exception ignored) {

        }
        return true;
    }

    public boolean checkOnFinish() {
        try {
            for (String[] strings : enemyField) {
                for (String string : strings) {
                    if (Objects.equals(string, "O")) {
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception ignored) {

        }
        return false;
    }
}
