package battleship;

import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Shot player1 = new Shot();
        Shot player2 = new Shot();
        player1.fillEmptyField();
        player2.fillEmptyField();
        Map<String, Integer> namesOfShips = new LinkedHashMap<>();
        namesOfShips.put("Aircraft Carrier", 5);
        namesOfShips.put("Battleship", 4);
        namesOfShips.put("Submarine", 3);
        namesOfShips.put("Cruiser", 3);
        namesOfShips.put("Destroyer", 2);
        player1.fillField();
        player1.printField();
        System.out.println("Player 1, place your ships on the game field");
        placementOfShips(player1, namesOfShips);
        enterToCont();
        player2.fillField();
        player2.printField();
        System.out.println("Player 2, place your ships on the game field");
        placementOfShips(player2, namesOfShips);
        System.out.println("The game starts!");
        while (!player1.checkOnFinish() || !player2.checkOnFinish()) {
            enterToCont();
            player1.printEmptyField();
            System.out.println("---------------------");
            player1.printField();
            System.out.println("Player 1, it's your turn:");
            player1.setShotCoordinate(scanner.nextLine(), player2.getField(), player2.getEmptyField());
            String hitOrMiss = player1.checkOnHit();
            while (hitOrMiss == null) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                player1.setShotCoordinate(scanner.nextLine(), player2.getField(), player2.getEmptyField());
                hitOrMiss = player1.checkOnHit();
            }
            System.out.println(hitOrMiss);
            if (player1.checkOnDestroyed()) {
                System.out.println("You sank a ship!");
            }
            if (player1.checkOnFinish()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
            }
            enterToCont(); //next player
            player2.printEmptyField();
            System.out.println("---------------------");
            player2.printField();
            System.out.println("Player 2, it's your turn:");
            player2.setShotCoordinate(scanner.nextLine(), player1.getField(), player1.getEmptyField());
            hitOrMiss = player2.checkOnHit();
            while (hitOrMiss == null) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                player2.setShotCoordinate(scanner.nextLine(), player1.getField(), player1.getEmptyField());
                hitOrMiss = player2.checkOnHit();
            }
            System.out.println(hitOrMiss);
            if (player2.checkOnDestroyed()) {
                System.out.println("You sank a ship!");
            }
            if (player2.checkOnFinish()) {
                System.out.println("You sank the last ship. You won. Congratulations!");
            }
        }
    }

    private static void placementOfShips(Ships field, Map<String, Integer> namesOfShips) {
        for (var entry : namesOfShips.entrySet()) {
            System.out.printf("Enter the coordinates of the %s (%d cells):\n", entry.getKey(), entry.getValue());
            String coordinates = scanner.nextLine();
            String[] separatedCoordinates = coordinates.split(" ");
            field.setCoordinates(separatedCoordinates);
            if (!field.firstChecker(entry.getValue()) || !field.secondChecker()) {
                if (!field.firstChecker(entry.getValue())) {
                    while (!field.firstChecker(entry.getValue())) {
                        System.out.println("Error! Wrong ship location! Try again:");
                        System.out.printf("Enter the coordinates of the %s (%d cells):\n", entry.getKey(), entry.getValue());
                        coordinates = scanner.nextLine();
                        separatedCoordinates = coordinates.split(" ");
                        field.setCoordinates(separatedCoordinates);
                    }
                } else {
                    while (!field.secondChecker()) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        System.out.printf("Enter the coordinates of the %s (%d cells):\n", entry.getKey(), entry.getValue());
                        coordinates = scanner.nextLine();
                        separatedCoordinates = coordinates.split(" ");
                        field.setCoordinates(separatedCoordinates);
                    }
                }
            }
            field.putShips();
            field.printField();
        }
    }

    public static void enterToCont() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%nPress Enter and pass the move to another player");
        scanner.nextLine();
    }


}
