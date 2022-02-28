package commands;

import data.Government;
import data.Human;
import utility.Collection;
import utility.TextFormatting;

/**
 * A class for overriding an operation to the desired thread
 */
public class Operations {
    public static boolean run(String [] command, Collection collection) {
        if (command[0].equals("help") & command.length == 1) {
            Help.create(Help.vocabulary.size());
            Help.help();
        } else if (command[0].equals("info") & command.length == 1) {
            collection.getInfo();
        } else if (command[0].equals("show") & command.length == 1) {
            collection.show();
        } else if (command[0].equals("insert") & command.length == 2) {
            if(command[1].contains(",")){
                System.out.println(TextFormatting.getRedText("There can be no commas in the key"));
            }
            else {
                collection.insert(command[1]);
            }
        } else if (command[0].equals("update") & command.length == 2) {
            try {
                collection.updateId(Integer.parseInt(command[1]));
            } catch (NumberFormatException exception) {
                System.out.println(TextFormatting.getRedText("No such id"));
            }
        } else if (command[0].equals("remove_key") & command.length == 2) {
            collection.removeKey(command[1]);
        } else if (command[0].equals("clear") & command.length == 1) {
            collection.clear();
        } else if (command[0].equals("save") & command.length == 2) {
            collection.save(command[1]);
        } else if (command[0].equals("execute_script") & command.length == 2) {
            collection.executeScript(command[1], collection);
        } else if (command[0].equals("remove_greater") & command.length == 2) {
            try {
                collection.removeGreater(Integer.parseInt(command[1]));
            } catch (NumberFormatException exception) {
                System.out.println(TextFormatting.getRedText("Wrong argument, enter again:"));
            }
        } else if (command[0].equals("remove_lower") & command.length == 2) {
            try {
                collection.removeLower(Integer.parseInt(command[1]));
            } catch (NumberFormatException exception) {
                System.out.println(TextFormatting.getRedText("Wrong argument, enter again:"));
            }
        } else if (command[0].equals("remove_greater_key") & command.length == 2) {
            collection.removeGreaterKey(command[1]);
        } else if (command[0].equals("remove_any_by_governor") & command.length == 3) {
            try {
                collection.removeAnyByGovernor(new Human(command[1], Double.parseDouble(command[2])));
            } catch (NumberFormatException exception) {
                System.out.println(TextFormatting.getRedText("Wrong arguments, enter again:"));
            }
        } else if (command[0].equals("count_less_than_government") & command.length == 2) {
            if (!Government.isPresent(command[1].toUpperCase())) {
                System.out.println(TextFormatting.getRedText("There is no such element"));
            } else {
                collection.countLessThanGovernment(Government.valueOf(command[1].toUpperCase()));
            }
        } else if (command[0].equals("count_greater_than_government") & command.length == 2) {
            if (!Government.isPresent(command[1].toUpperCase())) {
                System.out.println(TextFormatting.getRedText("There is no such element"));
            } else {
                collection.countGreaterThanGovernment(Government.valueOf(command[1].toUpperCase()));
            }
        } else if (command[0].equals("exit") & command.length == 1) {
            return true;
        } else {
            System.out.println(TextFormatting.getRedText("An unknown command was entered"));
            System.out.println(TextFormatting.getGreenText("Enter again: "));
            System.out.println();
        }
        return false;
    }
}
