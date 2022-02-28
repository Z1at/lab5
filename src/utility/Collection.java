package utility;

import commands.Operations;
import data.*;
import java.io.*;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.LinkedHashMap;
import java.io.FileReader;

public class Collection {
    final LinkedHashMap<String, City> collection;
    private final String initTime;
    int id = 0;
    static Scanner scanner = new Scanner(System.in);

    public Collection(){
        collection = new LinkedHashMap<>();
        initTime = new Date().toString();
    }

    public void getInfo(){
        System.out.println(TextFormatting.getGreenText("Information about collection:"));
        System.out.println(TextFormatting.getBlueText("Type of collection: " + "LinkedHashMap" + "\n" +
                "Type of collection items: " + "Cities" + "\n" +
                "Initialization date: " + initTime + "\n" +
                "Number of items in the collection: " + collection.size()));
        System.out.println();
    }

    public void show(){
        if(collection.size() == 0){
            System.out.println(TextFormatting.getGreenText("Collection is empty"));
        }
        else {
            System.out.println(TextFormatting.getGreenText("All elements of the collection:"));
            for (String key : collection.keySet()) {
                System.out.println(TextFormatting.getYellowText(key + ":"));
                System.out.println(TextFormatting.getBlueText(collection.get(key).toString()));
            }
        }
    }

    public void insert(String key){
        String name = FieldReceiver.getName();
        Coordinates coordinates = FieldReceiver.getCoordinates();
        ZonedDateTime creationDate = ZonedDateTime.now();
        double area = FieldReceiver.getArea();
        Long population = FieldReceiver.getPopulation();
        Integer metersAboveSeaLevel = FieldReceiver.getMetersAboveSeaLevel();
        Climate climate = FieldReceiver.getClimate();
        Government government = FieldReceiver.getGovernment();
        StandardOfLiving standardOfLiving = FieldReceiver.getStandardOfLiving();
        Human governor = FieldReceiver.getGovernor();
        if(collection.containsKey(key)){
            collection.put(key, new City(collection.get(key).getId(), name, coordinates, creationDate, area, population,
                    metersAboveSeaLevel, climate, government, standardOfLiving, governor));
        }
        else{
            collection.put(key, new City(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel,
                    climate, government, standardOfLiving, governor));
            id++;
        }
        System.out.println(TextFormatting.getGreenText("City added to collection"));
        System.out.println();
    }

    public void updateId(int id){
        String copyKey = "";
        for(String key : collection.keySet()){
            if(collection.get(key).getId() == id){
                copyKey = key;
                break;
            }
        }
        if(copyKey.equals("")){
            System.out.println(TextFormatting.getRedText("There is no such id"));
        }
        else{
            System.out.println(TextFormatting.getGreenText("Enter the name of the field you want to change, " +
                    "Or type 'complete' if you want to complete the operations"));
            while (true) {
                String[] string = scanner.nextLine().strip().split(" ");
                StringBuilder concatenation = new StringBuilder();
                for (String s : string) {
                    concatenation.append(s);
                }
                boolean f = false;
                switch (concatenation.toString()) {
                    case "name" -> {
                        String name = FieldReceiver.getName();
                        collection.get(copyKey).setName(name);
                    }
                    case "coordinates" -> {
                        Coordinates coordinates = FieldReceiver.getCoordinates();
                        collection.get(copyKey).setCoordinates(coordinates);
                    }
                    case "area" -> {
                        double area = FieldReceiver.getArea();
                        collection.get(copyKey).setArea(area);
                    }
                    case "population" -> {
                        Long population = FieldReceiver.getPopulation();
                        collection.get(copyKey).setPopulation(population);
                    }
                    case "metersabvovesealevel" -> {
                        Integer metersAboveSeaLevel = FieldReceiver.getMetersAboveSeaLevel();
                        collection.get(copyKey).setMetersAboveSeaLevel(metersAboveSeaLevel);
                    }
                    case "climate" -> {
                        Climate climate = FieldReceiver.getClimate();
                        collection.get(copyKey).setClimate(climate);
                    }
                    case "government" -> {
                        Government government = FieldReceiver.getGovernment();
                        collection.get(copyKey).setGovernment(government);
                    }
                    case "standardofliving" -> {
                        StandardOfLiving standardOfLiving = FieldReceiver.getStandardOfLiving();
                        collection.get(copyKey).setStandardOfLiving(standardOfLiving);
                    }
                    case "governor" -> {
                        Human governor = FieldReceiver.getGovernor();
                        collection.get(copyKey).setGovernor(governor);
                    }
                    case "complete" -> {
                        System.out.println(TextFormatting.getGreenText("You have exited the method of adding elements"));
                        System.out.println();
                        return;
                    }
                    default -> f = true;
                }
                if(f){
                    System.out.println(TextFormatting.getRedText("There is no such field, enter again:"));
                }
                else{
                    System.out.println(TextFormatting.getGreenText("Element replaced"));
                }
                System.out.println(TextFormatting.getGreenText("Type 'complete' if you want to complete the operations"));
            }
        }
        System.out.println();
    }

    public void removeKey(String key){
        int len = collection.size();
        collection.remove(key);
        if(len != collection.size()){
            System.out.println(TextFormatting.getGreenText("Element was delete"));
        }
        else{
            System.out.println(TextFormatting.getRedText("There is no such key"));
        }
        System.out.println();
    }

    public void clear(){
        collection.clear();
        System.out.println(TextFormatting.getGreenText("The collection has been cleared"));
        System.out.println();
    }

    public void save(String path) {
        try {
            FileOutputStream file = new FileOutputStream(path + ".csv");
            OutputStreamWriter writer = new OutputStreamWriter(file);
            writer.write("key" + "," + "id" + "," + "name" + "," + "coordinates" + "," + "creation date" + "," +
                    "area" + "," + "population" + "," + "meters above sea level" + "," + "climate" + "," +
                    "government" + "," + "standard of living" + "," + "governor(name. height. birthday)" + "\n");
            for(String key : collection.keySet()){
                City city = collection.get(key);
                writer.write(key + ',' + city.getId() + "," + city.getName() + "," + "(" + city.getCoordinates().getX()
                        + "; " + city.getCoordinates().getY() + ")" + "," +
                        city.getCreationDate() + "," + city.getArea() + "," + city.getPopulation() + "," +
                        city.getMetersAboveSeaLevel() + "," + city.getClimate() + "," + city.getGovernment() + "," +
                        city.getStandardOfLiving() + "," + city.getGovernor().getName() + ". " +
                        city.getGovernor().getHeight() + ". " + city.getGovernor().getBirthday() + "\n");
            }

            writer.close();
            System.out.println(TextFormatting.getGreenText("The file was saved successfully"));
        }
        catch (IOException exception){
            System.out.println(TextFormatting.getRedText("If you have a file open with the path you specified, " +
                                                         "then close it and repeat the command"));
        }
        System.out.println();
    }

    public void executeScript(String path, Collection collection) {
        try {
            FileReader file = new FileReader(path);
            String string = "";
            String[] concatenation;
            while (file.ready()) {
                char c = (char) file.read();
                if (c == '\n') {
                    concatenation = string.toLowerCase().strip().split(" ");
                    if(concatenation[0].equals("insert") & concatenation.length == 2){
                        if(!concatenation[1].contains(",")) {
                            FileOutput.insert(collection, concatenation[1], file);
                        }
                    }
                    else if(concatenation[0].equals("update") & concatenation.length == 2){
                        try {
                            FileOutput.updateId(collection.collection, Integer.parseInt(concatenation[1]), file);
                        } catch (NumberFormatException ignored) {

                        }
                    }
                    else{
                        Operations.run(concatenation, collection);
                    }
                    string = "";
                }
                else {
                    string += String.valueOf(c);
                }
            }
            Operations.run(string.toLowerCase().strip().split(" "), collection);
            System.out.println(TextFormatting.getGreenText("All commands were executed"));
            file.close();
        } catch (IOException e) {
            System.out.println(TextFormatting.getRedText("Invalid command"));
        }
        System.out.println();
    }

    public void removeGreater(int id){
        Vector<String> keys = new Vector<>();
        for(String key : collection.keySet()){
            if(collection.get(key).getId() > id){
                keys.add(key);
            }
        }
        for(String key : keys){
            collection.remove(key);
        }
        System.out.println(TextFormatting.getGreenText("All elements larger than the specified one have been deleted"));
        System.out.println();
    }

    public void removeLower(int id){
        Vector<String> keys = new Vector<>();
        for(String key : collection.keySet()){
            if(collection.get(key).getId() < id){
                keys.add(key);
            }
        }
        for(String key : keys){
            collection.remove(key);
        }
        System.out.println(TextFormatting.getGreenText("All elements smaller than the specified one have been deleted"));
        System.out.println();
    }

    public void removeGreaterKey(String key){
        Vector<String> keys = new Vector<>();
        for(String temporary : collection.keySet()){
            if(temporary.compareTo(key) > 0){
                keys.add(temporary);
            }
        }
        for(String temporary : keys){
            collection.remove(temporary);
        }
        System.out.println(TextFormatting.getGreenText("All keys larger than the specified one have been deleted"));
        System.out.println();
    }

    public void removeAnyByGovernor(Human governor){
        String temporary = "";
        for(String key : collection.keySet()){
            if(collection.get(key).getGovernor().getName().equals(governor.getName()) &
                    Objects.equals(collection.get(key).getGovernor().getHeight(), governor.getHeight())){
                temporary = key;
                break;
            }
        }
        if(temporary.equals("")){
            System.out.println(TextFormatting.getRedText("There is no such governor in the collection"));
        }
        else{
            collection.remove(temporary);
            System.out.println(TextFormatting.getGreenText("Element was delete"));
        }
        System.out.println();
    }

    public void countLessThanGovernment(Government government){
        int counter = 0;
        for(String key : collection.keySet()){
            if(collection.get(key).getGovernment().compareTo(government) < 0){
                counter++;
            }
        }
        System.out.println(TextFormatting.getGreenText(String.valueOf(counter)));
        System.out.println();
    }

    public void countGreaterThanGovernment(Government government){
        int counter = 0;
        for(String key : collection.keySet()){
            if(collection.get(key).getGovernment().compareTo(government) > 0){
                counter++;
            }
        }
        System.out.println(TextFormatting.getGreenText(String.valueOf(counter)));
        System.out.println();
    }
}
