package Day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class CubeConundrum{

    public static void main(String[] args) {
        String filePath = "puzzleInput.txt";
        int intValue = 0;
        int powerValue = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String game;
            while ((game = reader.readLine()) != null) {
                int id = Integer.parseInt(getId(game));
                String[] subSets = getSubSets(game);
                ArrayList<ArrayList<Integer>> AmountOfCubes = extractAmountOfCubesFromSets(subSets);
                int[] minCubes = minimumCubesInSet(AmountOfCubes);
                powerValue += powerOfMinSet(minCubes);
                if(evaluateSubsets(AmountOfCubes)){
                    intValue += id;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    
        System.out.println("Part 1: Sum of GameID - " + intValue);
        System.out.println("Part 2: power of minimum Cubes sum - " + powerValue);
    }
  

    public static String getId(String puzzleInput){
        String[] splitString = puzzleInput.split(":");
        String gameId = splitString[0].split(" ")[1];
        return gameId;
    }

    public static String[] getSubSets(String puzzleInput){
        String[] splitString = puzzleInput.split(":");
        String[] subSets = splitString[1].split(";");
        return subSets;
    }

    public static ArrayList<Integer> extractAmountOfCubesFromSet(String subSet){
        ArrayList<Integer> amountOfCubes = new ArrayList<>();
        int redCubes = 0;
        int greenCubes = 0;
        int blueCubes = 0;

        String[] colorCubes = subSet.split(",");
        for (int i = 0; i < colorCubes.length; i++) {
            colorCubes[i] = colorCubes[i].replace(" ", ""); // Remove spaces
        }        

        for(String AmountOfCubes : colorCubes){
            String amountString = "";
            String color = "";
            for(int i = 0; Character.isDigit(AmountOfCubes.charAt(i)); i++){
                amountString += Character.toString(AmountOfCubes.charAt(i));
                color = Character.toString(AmountOfCubes.charAt(i+1));
            }
            int amount = Integer.parseInt(amountString);
            if(color.equals("r")){
                redCubes = amount;
            }

            if(color.equals("g")){
                greenCubes = amount;
            }

            if(color.equals("b")){
                blueCubes = amount;
            }
        }

        amountOfCubes.add(redCubes);
        amountOfCubes.add(greenCubes);
        amountOfCubes.add(blueCubes);
        return amountOfCubes;
    }

    public static ArrayList<ArrayList<Integer>> extractAmountOfCubesFromSets(String[] subsets){
        ArrayList<ArrayList<Integer>> AmoutOfCubesAllSubSets = new ArrayList<>();
        for(String subset: subsets){
            AmoutOfCubesAllSubSets.add(CubeConundrum.extractAmountOfCubesFromSet(subset));
        }
        return AmoutOfCubesAllSubSets;
    }

    
    public static boolean evaluateSubsets(ArrayList<ArrayList<Integer>> cubes){
        int redCubes = 0;
        int greenCubes = 0;
        int blueCubes = 0;
        for(ArrayList<Integer> set : cubes){
            for(int i = 0; i < set.size(); i++){
                int amount = set.get(i);
                if(i == 0 && redCubes < amount){
                    redCubes = amount;
                }
                if(i == 1 && greenCubes < amount){
                    greenCubes = amount;
                }
                if(i == 2 && blueCubes < amount){
                    blueCubes = amount;
                }
            }
        }
        
        if(redCubes > 12){
            return false;
        } else if (greenCubes > 13){
           return false; 
        } else if (blueCubes > 14){
            return false;
        }
        
        return true;
    }

    public static int[] minimumCubesInSet(ArrayList<ArrayList<Integer>> cubes){
        int redCubes = 0;
        int greenCubes = 0;
        int blueCubes = 0;
        for(ArrayList<Integer> set : cubes){
            for(int i = 0; i < set.size(); i++){
                int amount = set.get(i);
                if(i == 0 && redCubes < amount){
                    redCubes = amount;
                }
                if(i == 1 && greenCubes < amount){
                    greenCubes = amount;
                }
                if(i == 2 && blueCubes < amount){
                    blueCubes = amount;
                }
            }
        }

        int[] minimumCubes = {redCubes, greenCubes, blueCubes};
        return minimumCubes;
    }

    public static int powerOfMinSet(int[] minSet){
        return minSet[0] * minSet[1] * minSet[2];
    }
}


