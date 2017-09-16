import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

class Slots {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // Initialize scanner for collecting user inputs
        String[] options = new String[] { "🐶", "🐱", "🐭", "🐰", "🐵", "🦄", "🐧" }; // options
        int points = 5; // starting number of coins
        Random gen = new Random(); // initalize random generator
        System.out.println("Note: This program does not run correctly in eclipse or repl.it\n"
                + "You must run it by navagating to the folder that it is in, then typing `$ javac slots.java`, then `$ java slots`.\n"
                + "This program has only been tested in the MacOS Terminal, not linux"
                + "(where it should work in theory) or Windows (i'm not sure)."); // warning about using eclipse and
        // repl.it
        System.out.println("🎰 Welcome to the casino! 🎰\n"); // Welcome players!
        System.out.println("You have " + points + "💰"); // Display initial points
        while (true) {
            System.out.println("What machine would you like to play?\n"); // Ask user how they want to play
            System.out.println();
            System.out.println(
                    "PRESS 1 for Machine 1 | 3 Slots, 0 of a kind = -1💰, 2 of a kind = 1💰, 3 of a kind = 2💰");
            if (points > 1) {
                System.out.println(
                        "PRESS 2 for Machine 2 | 5 Slots, 0-2 of a kind = -2💰, 3 of a kind = 5💰, 4 of a kind = 10💰, 5 of a kind = 100💰");
            } else {
                System.out.println("You cannot afford to play Machine 2."); // If the user doesn't have enough money to
                // play machine 2, we still let them for
                // easter egg on line 83
            }
            int machine = 0;
            try {
                machine = scanner.nextInt();
            } catch (InputMismatchException e) { // catch for if the user doesn't type an integer
                scanner.next(); // reset scanner token by moving to the next line so we don't end up with an
                // infinite loop.
            }
            if (machine == 1) {
                String slot1 = null; // init slots
                String slot2 = null;
                String slot3 = null;
                for (int i = 0; i < 30; i++) {
                    System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b"); // clear current line
                    slot1 = options[gen.nextInt(options.length)]; // get random things for slots
                    slot2 = options[gen.nextInt(options.length)];
                    slot3 = options[gen.nextInt(options.length)];
                    System.out.print(slot1 + " | " + slot2 + " | " + slot3 + "   ");
                    Thread.sleep(i * 10); // Animation thing
                }
                System.out.println();
                String[] results = new String[] { slot1, slot2, slot3 };
                HashMap<String, Integer> resultsValues = new HashMap<String, Integer>(); // Uses a HM to keep track of
                // which slots have occurred
                // most often
                int biggestValue = 0; // start the biggest value at zero
                for (String slot : results) { // iterate over each slot, to see what is the most common and set biggest
                    // value to the number of times it appears
                    if (resultsValues.get(slot) != null) { // if there is already an entry in the HM for the thing that
                        // the slot was set to
                        int count = resultsValues.get(slot); // increase the count...
                        count++; // by one
                        resultsValues.put(slot, count); // then save it back into the HM
                    } else { // otherwise...
                        resultsValues.put(slot, 1); // put it in as the first time that that slot appeared
                    }
                    if (resultsValues.get(slot) > biggestValue) { // and if the number of times it appeared is greater
                        // than the current biggestValue
                        biggestValue = resultsValues.get(slot); // set the biggestValue to the number of times that slot
                        // appeared
                    }
                }
                if (biggestValue == 3) { // coordinate the number of times the biggest value appeared to the prize
                    System.out.println("JACKPOT!!!!!");
                    System.out.println("Congrats, you just got +2💰");
                    points += 2;
                } else if (biggestValue == 2) {
                    System.out.println("Congrats, you just got +1💰");
                    points++;
                } else {
                    System.out.println("Sorry, you lost 1💰");
                    points--;
                }
                if (points < 2) {
                    System.out.println("Sorry, you don't have enough 💰 to play again.");
                }
                System.out.println("You have " + points + "💰"); // Tell player the remaining points
                System.out.println("Press p to play again! (Press any other key to leave the casino)");
                String again = scanner.next();
                if (!again.equals("p")) {
                    break;
                }
            } else if (machine == 2) { // Look at the code for machine one, its all commented there and all that
                // happens here is we add another 2 slots.
                if (points < 2) {
                    System.out.println("We warned you. If you lose this game we'll start taking fingers 🔪.");
                }
                String slot1 = null;
                String slot2 = null;
                String slot3 = null;
                String slot4 = null;
                String slot5 = null;
                for (int i = 0; i < 30; i++) {
                    System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
                    slot1 = options[gen.nextInt(options.length)];
                    slot2 = options[gen.nextInt(options.length)];
                    slot3 = options[gen.nextInt(options.length)];
                    slot4 = options[gen.nextInt(options.length)];
                    slot5 = options[gen.nextInt(options.length)];
                    System.out.print(slot1 + " | " + slot2 + " | " + slot3 + " | " + slot4 + " | " + slot5 + "   ");
                    Thread.sleep(i * 10);
                }
                System.out.println();
                String[] results = new String[] { slot1, slot2, slot3, slot4, slot5 };
                HashMap<String, Integer> resultsValue = new HashMap<String, Integer>(); // See Line 36
                int biggestValue = 0;
                for (String slot : results) {
                    if (resultsValue.get(slot) != null) {
                        int count = resultsValue.get(slot);
                        count++;
                        resultsValue.put(slot, count);
                    } else {
                        resultsValue.put(slot, 1);
                    }
                    if (resultsValue.get(slot) > biggestValue) {
                        biggestValue = resultsValue.get(slot);
                    }
                }
                if (biggestValue == 5) {
                    System.out.println("MEGAJACKPOT!!!!!");
                    System.out.println("Congrats, you just got +100💰");
                    points += 100;
                } else if (biggestValue == 4) {
                    System.out.println("Congrats, you just got +10💰");
                    points += 10;
                } else if (biggestValue == 3) {
                    System.out.println("Congrats, you just got +5💰");
                    points += 5;
                } else {
                    System.out.println("Sorry, you lost 2💰");
                    points -= 2;
                }
                if (points < 1) {
                    System.out.println("Sorry, you don't have enough 💰 to play again.");
                    break;
                }
                System.out.println("You have " + points + "💰");
                System.out.println("Press p to play again! (Press any other key to leave the casino)");
                String again = scanner.next();
                if (!again.equals("p")) {
                    break;
                }
            } else {
                System.out.println("That isn't a valid machine, please try again.");
            }
        }
        scanner.close();
    }
}