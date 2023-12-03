package Day2;

import java.util.Arrays;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        String id = CubeConundrum.getId("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        String[] subSets = CubeConundrum.getSubSets("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green");
        ArrayList<ArrayList<Integer>> AmountOfCubes = CubeConundrum.extractAmountOfCubesFromSets(subSets);
        System.out.println(CubeConundrum.evaluateSubsets(AmountOfCubes));

        System.out.println("Game ID: " + id);
    }
}
