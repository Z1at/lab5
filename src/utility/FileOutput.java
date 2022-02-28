package utility;

import data.*;
import java.io.FileReader;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

/**
 * Class for executing two commands from a file
 */
public class FileOutput {
    public static void insert(Collection collection, String key, FileReader file) throws IOException {
        String name = FieldReceiverForFile.getName(file);
        Coordinates coordinates = FieldReceiverForFile.getCoordinates(file);
        ZonedDateTime creationDate = ZonedDateTime.now();
        double area = FieldReceiverForFile.getArea(file);
        Long population = FieldReceiverForFile.getPopulation(file);
        Integer metersAboveSeaLevel = FieldReceiverForFile.getMetersAboveSeaLevel(file);
        Climate climate = FieldReceiverForFile.getClimate(file);
        Government government = FieldReceiverForFile.getGovernment(file);
        StandardOfLiving standardOfLiving = FieldReceiverForFile.getStandardOfLiving(file);
        Human governor = FieldReceiverForFile.getGovernor(file);
        if(name != null & coordinates.getY() != (long)-1 & area != (double)-1 & population != (long)-1 &
        metersAboveSeaLevel != null & climate != null & government != null & standardOfLiving != null & governor != null) {
            if (collection.collection.containsKey(key)){
                collection.collection.put(key, new City(collection.collection.get(key).getId(), name, coordinates,
                        creationDate, area, population, metersAboveSeaLevel, climate, government, standardOfLiving,
                        governor));
            }
            else {
                collection.collection.put(key, new City(collection.id, name, coordinates, creationDate, area, population,
                        metersAboveSeaLevel, climate, government, standardOfLiving, governor));
                collection.id++;
            }
        }
    }

    public static void updateId(LinkedHashMap<String, City> collection, int id, FileReader file) throws IOException {
        String copyKey = "";
        for(String key : collection.keySet()){
            if(collection.get(key).getId() == id){
                copyKey = key;
                break;
            }
        }
        if(!copyKey.equals("")){
            while (file.ready()) {
                String[] string = getLine(file).toLowerCase().strip().split(" ");
                StringBuilder concatenation = new StringBuilder();
                for(String temporary : string){
                    concatenation.append(temporary);
                }

                switch (concatenation.toString()){
                    case "name" -> {
                        String name = FieldReceiverForFile.getName(file);
                        if(name != null) {
                            collection.get(copyKey).setName(name);
                        }
                    }
                    case "coordinates" -> {
                        Coordinates coordinates = FieldReceiverForFile.getCoordinates(file);
                        if(coordinates.getY() != -1){
                            collection.get(copyKey).setCoordinates(coordinates);
                        }
                    }
                    case "area" -> {
                        double area = FieldReceiverForFile.getArea(file);
                        if(area > 0){
                            collection.get(copyKey).setArea(area);
                        }
                    }
                    case "population" -> {
                        Long population = FieldReceiverForFile.getPopulation(file);
                        if(population > 0){
                            collection.get(copyKey).setPopulation(population);
                        }
                    }
                    case "metersabvovesealevel" -> {
                        Integer metersAboveSeaLevel = FieldReceiverForFile.getMetersAboveSeaLevel(file);
                        if(metersAboveSeaLevel != null){
                            collection.get(copyKey).setMetersAboveSeaLevel(metersAboveSeaLevel);
                        }
                    }
                    case "climate" -> {
                        Climate climate = FieldReceiverForFile.getClimate(file);
                        if(climate != null){
                            collection.get(copyKey).setClimate(climate);
                        }
                    }
                    case "government" -> {
                        Government government = FieldReceiverForFile.getGovernment(file);
                        if(government != null){
                            collection.get(copyKey).setGovernment(government);
                        }
                    }
                    case "standardofliving" -> {
                        StandardOfLiving standardOfLiving = FieldReceiverForFile.getStandardOfLiving(file);
                        if(standardOfLiving != null){
                            collection.get(copyKey).setStandardOfLiving(standardOfLiving);
                        }
                    }
                    case "governor" -> {
                        Human governor = FieldReceiverForFile.getGovernor(file);
                        if(governor != null){
                            collection.get(copyKey).setGovernor(governor);
                        }
                    }
                    case "complete" -> {
                        return;
                    }
                }
            }
        }
    }

    public static String getLine(FileReader file) throws IOException {
        String string = "";
        while(file.ready()){
            char c = (char)file.read();
            if(c == '\n'){
                break;
            }
            string += c;
        }
        return string;
    }
}
