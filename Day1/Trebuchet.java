import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Trebuchet{
    
    private final static String filePath = "puzzleInput.txt"; // Replace with your file path
    private final static Map<String,Integer> DigitStringToNumberMap = createHashMapStringToInteger();
    
    
    public static void main(String[] args) {
        int totalCalibration = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalCalibration += calibrationFinder(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println(totalCalibration);
    }




    public static Integer calibrationFinder(String line){
        Integer firstNumber = null;
        Integer secondNumber = null;
        Integer indexOfFirstNumber = null;
        Integer indexOfSecondNumber = null;
        String firstDigitString = "";
        String secondDigitString = "";
        String reverseSecondDigitString = "";
        boolean foundFirstDigit = false;
        boolean foundSecondDigit = false;
    


        for(int i = 0; i < line.length(); i++){
            char firstDigit = line.charAt(i);

            if(!Character.isDigit(firstDigit) && !foundFirstDigit){
                firstDigitString += String.valueOf(firstDigit);
            }
            String numberString = containsNumberString(firstDigitString);
            if (numberString != null && !foundFirstDigit){
                firstNumber = DigitStringToNumberMap.get(numberString);
                foundFirstDigit = true;
            }

            if(Character.isDigit(firstDigit) && !foundFirstDigit){
                firstNumber = Integer.parseInt(String.valueOf(firstDigit));
                indexOfFirstNumber = i;
                foundFirstDigit = true;
            }
        }

        for(int j = line.length() - 1; j >= 0; j--){
            char secondDigit = line.charAt(j);
            if(!Character.isDigit(secondDigit) && !foundSecondDigit){
                secondDigitString += String.valueOf(secondDigit);
            }
            reverseSecondDigitString = reverseString(secondDigitString);
            String numberString = containsNumberString(reverseSecondDigitString);
            if (numberString != null && !foundSecondDigit){
                secondNumber = DigitStringToNumberMap.get(numberString);
                // System.out.println(secondNumber);

                foundSecondDigit = true;
            }
            if(Character.isDigit(secondDigit) && !foundSecondDigit){
                secondNumber = Integer.parseInt(String.valueOf(secondDigit));
                indexOfFirstNumber = j;
                foundSecondDigit = true;
            }
        }

            if(firstNumber == null && secondNumber != null){
                return secondNumber;
            } else if (firstNumber != null && secondNumber == null){
                return firstNumber;
            } else {
                return putTogetherIntegers(firstNumber, secondNumber);
            }
    
    }

    public static Integer putTogetherIntegers(Integer firstNumber, Integer secondNumber){
        String result = Integer.toString(firstNumber) + Integer.toString(secondNumber);
        return Integer.parseInt(result);
    }

    public static Map<String, Integer> createHashMapStringToInteger(){
        Map<String, Integer> DigitStringToNumberMap = new HashMap<>();
        DigitStringToNumberMap.put("zero", 0);
        DigitStringToNumberMap.put("one", 1);
        DigitStringToNumberMap.put("two", 2);
        DigitStringToNumberMap.put("three", 3);
        DigitStringToNumberMap.put("four", 4);
        DigitStringToNumberMap.put("five", 5);
        DigitStringToNumberMap.put("six", 6);
        DigitStringToNumberMap.put("seven", 7);
        DigitStringToNumberMap.put("eight", 8);
        DigitStringToNumberMap.put("nine", 9);
        return DigitStringToNumberMap;
    }

    public static String reverseString(String digitString){
        String reverseString = "";
            for (int i = digitString.length() - 1; i >= 0; i--){
                    reverseString += String.valueOf(digitString.charAt(i));
            }
        return reverseString;
    }

    public static String containsNumberString(String digitString){
        ArrayList<String> suffixArray = new ArrayList<>();
        for (int i = 0; i < digitString.length(); i++) {
            // Add the substring from the current index to the end of the string
            suffixArray.add(digitString.substring(i));
        }

        for(String s : suffixArray){
            String testString = "";
            for(int i = 0; i < Math.min(s.length(), 5); i++){
                testString += String.valueOf(s.charAt(i));
                if(DigitStringToNumberMap.containsKey(testString)){
                    return testString;
                }
            }
        }

        return null;
    }



}
