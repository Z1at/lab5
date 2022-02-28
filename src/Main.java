import commands.Operations;
import utility.Collection;

import java.util.Scanner;

/**
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        Collection collection = new Collection();
        Scanner scanner = new Scanner(System.in);
        String[] command;
        do {
            command = scanner.nextLine().toLowerCase().strip().split(" ");
        } while (!Operations.run(command, collection));
        scanner.close();
    }
}

