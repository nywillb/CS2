import javax.xml.bind.SchemaOutputResolver;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class DotWars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Initialize scanner for collecting user inputs
        Random gen = new Random(); // initalize random generator
        String badGuys = "terrorist forces";
        if (gen.nextInt(9) == 0){ badGuys = "Doodledum Navy"; }
        boolean[][] hiddenBoard = new boolean[5][5]; //Boolean 2d array for placing ships
        hiddenBoard[0] = new boolean[5];
        hiddenBoard[1] = new boolean[5];
        hiddenBoard[2] = new boolean[5];
        hiddenBoard[3] = new boolean[5];
        hiddenBoard[4] = new boolean[5];
        String[][] showingBoard = new String[5][5]; //Char 2d array to show the user
        showingBoard[0] = new String[5];
        showingBoard[2] = new String[5];
        showingBoard[1] = new String[5];
        showingBoard[3] = new String[5];
        showingBoard[4] = new String[5];
        for(int x = 0; x<5; x++) {
            for (int y = 0; y<5; y++) {
                showingBoard[x][y] = "•";
                hiddenBoard[x][y] = false;
            }
        }
        for(int i = 0; i<=4; i++) {
            int x = gen.nextInt(5);
            int y = gen.nextInt(5);
            if(hiddenBoard[x][y]){
                i--;
            } else {
                hiddenBoard[x][y] = true;
            }
        }

        System.out.println(" ____        _ __        __             \n" +
                "|  _ \\  ___ | |\\ \\      / /_ _ _ __ ___ \n" +
                "| | | |/ _ \\| __\\ \\ /\\ / / _` | '__/ __|\n" +
                "| |_| | (_) | |_ \\ V  V / (_| | |  \\__ \\\n" +
                "|____/ \\___/ \\__| \\_/\\_/ \\__,_|_|  |___/\n\n");
        System.out.println("Welcome to DotWars. The board has 5 randomly paced " + badGuys + " submarines" +
                "Sink all of the submarines to win the game. \nBut watch out, if you take more than 10 shots," +
                "the " + badGuys + " will be able to triangulate your location and stop you. \n");
        while(true) {
            int turns = 10;
            int subsRemaining = 5;
            while(turns != 0 && subsRemaining != 0) {
                System.out.println("Here is the board:");
                System.out.println("You have " + turns + " turns remaining. There are " + subsRemaining + " submarines remaining.");
                printBoard(showingBoard);
                System.out.println("Where would you like to make your first strike?");
                int x = getGuess("x");
                int y = getGuess("y");
                if(hiddenBoard[x][y]) {
                    System.out.println("HIT!");
                    showingBoard[x][y] = "H";
                    hiddenBoard[x][y] = false;
                    subsRemaining--;
                } else {
                    System.out.println("miss");
                    showingBoard[x][y] = "m";
                }
                turns--;
            }
            if(turns == 0) {
                System.out.println("You are out of turns. The " + badGuys + " were able to triangulate your location with " + subsRemaining + " submarines remaining.");
            } else {
                System.out.println("Congrats! You won with " + turns + "turns remaining.");
            }
            System.out.println("Would you like to play again? (y/n)");
            String response = scanner.next().toLowerCase();
            if(response.equals("y")) {
                continue;
            } else if(response.equals("n")) {
                System.out.println("Goodbye :(");
                break;
            } else {
                System.out.println("You aren't following directions.");
                break;
            }
        }
    }

    private static void printBoard(String[][] board) {
        for(String[] row : board) {
            System.out.println("-----------");
            System.out.print("|");
            for (String slot : row) {
                System.out.print(slot + "|");
            }
            System.out.println();
        }
        System.out.println("-----------");
    }
    private static int getGuess(String prefixQuery) {
        Scanner scanner = new Scanner(System.in);
        int exitValue;
        while (true){
            try {
                System.out.print(prefixQuery + " = ");
                exitValue = scanner.nextInt();
            } catch (InputMismatchException e) { // catch for if the user doesn't type an integer
                scanner.next(); // reset scanner token by moving to the next line so we don't end up with an
                // infinite loop.
                System.out.println("You must type an integer between 1 and 5 here.");
                continue;
            }
            if (exitValue > 5 || exitValue < 1) {
                System.out.println("You must type an integer between 1 and 5 here.");
            } else {
                exitValue--;
                return exitValue;
            }
        }
    }
}
