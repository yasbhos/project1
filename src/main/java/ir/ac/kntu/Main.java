package ir.ac.kntu;

import java.util.Scanner;

import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Main class of the project
 *
 * @author Hossein Yasbolaghi
 * @author Yasbolaghi.sharrahi@gmail.com
 * @version 1.0
 */
public class Main {

    /**
     * A map to save new Fariborz-Arrays
     * key of each entery is name fo the value of the entery
     */
    private static Map<String, FariborzArray> fariborzArrays = new HashMap<>();

    /**
     * Anci codes to change color of the output text in command prompt
     */
    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN = "\u001B[32m";

    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        /**
         * represents the number of entered lines
         */
        int counter = 0;
        System.out.print(ANSI_GREEN + "[" + counter + "]" + ": " + ANSI_RESET);

        while (scanner.hasNext()) {
            input = scanner.nextLine();
            inputHandler(input);
            counter++;
            System.out.print(ANSI_GREEN + "[" + counter + "]" + ": " + ANSI_RESET);
        }

        scanner.close();
    }

    /**
     * This method is used to handle the input and calls the appropriate methods
     *
     * @param input
     */
    public static void inputHandler(String input) {
        if (isInitializingStatement(input)) {
            fariborzArrayPrinter(initializingStatementHandler(input));
        } else if (isChangingStatement(input)) {
            fariborzArrayPrinter(changeStatementHandler(input));
        } else if (isElementWiseStatement(input)) {
            fariborzArrayPrinter(elementWiseStatementHandler(input));
        } else if (isSharpStatement(input)) {
            fariborzArrayPrinter(sharpStatementHandler(input));
        } else if (isMatrixMultiplicationStatement(input)) {
            fariborzArrayPrinter(matrixMultiplicationStatementHandler(input));
        } else if (isTransposingStatement(input)) {
            fariborzArrayPrinter(transposingStatementHandler(input));
        } else if (isCutingStatement(input)) {
            fariborzArrayPrinter(cutingStatementHandler(input));
        } else if (isCombinationOperations(input)) {
            fariborzArrayPrinter(combinationOperationsHandler(input));
        } else {
            System.out.println(ANSI_RED + "Invalid statement entered!" + ANSI_RESET);
            return;
        }
    }

    /**
     * This method is used to check if the input is a valid initializing statement
     *
     * @param input the entered line
     * @return true if the input is a valid initializing statement otherwise return
     * false
     */
    public static boolean isInitializingStatement(String input) {
        if (input.matches("\s*[a-zA-Z]+\\w*\s*=\s*\\[.*\\]")) {
            return true;
        }

        return false;
    }

    /**
     * This method is used to check if the input is a valid changing statement
     *
     * @param input the entered line
     * @return true if the input is a valid changing statement otherwise return
     * false
     */
    public static boolean isChangingStatement(String input) {
        if (input
                .matches("\s*[a-zA-Z]+\\w*\s*(\\[\\d+\\])+\s*=\s*(\\d+|\\d+\\.|\\.\\d+|\\d+\\.\\d+|\".{2,}\"|\'.\')")) {
            return true;
        }

        return false;
    }

    /**
     * This method is used to check if the input is a valid element-wise statement
     *
     * @param input the entered line
     * @return true if the input is a valid element-wise statement otherwise return
     * false
     */
    public static boolean isElementWiseStatement(String input) {
        if (input.matches("\s*[a-zA-Z]+\\w*\s*=\s*[a-zA-Z]+\\w*\s*(\\*|\\/|\\+|\\-)\s*[a-zA-Z]+\\w*\s*")) {
            return true;
        } else if (input.matches("\s*[a-zA-Z]+\\w*\s*(\\*|\\/|\\+|\\-)\s*[a-zA-Z]+\\w*\s*")) {
            return true;
        }

        return false;
    }

    /**
     * This method is used to check if the input is a valid sharp statement
     *
     * @param input the entered line
     * @return true if the input is a valid sharp statement otherwise return false
     */
    public static boolean isSharpStatement(String input) {
        if (input.matches("\s*[a-zA-Z]+\\w*\s*=\s*[a-zA-Z]+\\w*\s*\\#\s*[a-zA-Z]+\\w*\s*")) {
            return true;
        } else if (input.matches("\s*[a-zA-Z]+\\w*\s*\\#\s*[a-zA-Z]+\\w*\s*")) {
            return true;
        }

        return false;
    }

    /**
     * This method is used to check if the input is a valid matrix multiplication
     * statement
     *
     * @param input the entered line
     * @return true if the input is a valid matrix multiplication statement
     * otherwise return false
     */
    public static boolean isMatrixMultiplicationStatement(String input) {
        if (input.matches("\s*[a-zA-Z]+\\w*\s*=\s*[a-zA-Z]+\\w*\s*\\@\s*[a-zA-Z]+\\w*\s*")) {
            return true;
        } else if (input.matches("\s*[a-zA-Z]+\\w*\s*\\@\s*[a-zA-Z]+\\w*\s*")) {
            return true;
        }

        return false;
    }

    /**
     * This method is used to check if the input is a valid transposing statement
     *
     * @param input the entered line
     * @return true if the input is a valid transposing statement otherwise return
     * false
     */
    public static boolean isTransposingStatement(String input) {
        if (input.matches("\s*[a-zA-Z]+\\w*\s*=\s*\\&\s*[a-zA-Z]+\\w*\s*")) {
            return true;
        } else if (input.matches("\s*\\&\s*[a-zA-Z]+\\w*\s*")) {
            return true;
        }

        return false;
    }

    /**
     * This method is used to check if the input is a valid cutting statement
     *
     * @param input the entered line
     * @return true if the input is a valid cutting statement otherwise return false
     */
    public static boolean isCutingStatement(String input) {
        if (input.matches(
                "\s*[a-zA-Z]+\\w*\s*=\s*[a-zA-Z]+\\w*\s*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))+")) {
            return true;
        } else if (input.matches("\s*[a-zA-Z]+\\w*\s*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))+")) {
            return true;
        }

        return false;
    }

    /**
     * This method is used to check if the input is a valid combination operations
     * statement
     *
     * @param input the entered line
     * @return true if the input is a valid combination operations otherwise return
     * false
     */
    public static boolean isCombinationOperations(String input) {
        if (input.matches(
                "\\s*[a-zA-Z]+\\w*\\s*=\\s*[a-zA-Z]+\\w*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))*(\\s*(\\*|\\/|\\+|\\-)\\s*[a-zA-Z]+\\w*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))*)+\\s*")) {
            return true;
        } else if (input.matches(
                "\\s*[a-zA-Z]+\\w*\\s*=\\s*[a-zA-Z]+\\w*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))*(\\s*\\#\\s*[a-zA-Z]+\\w*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))*)+\\s*")) {
            return true;
        } else if (input.matches(
                "\\s*[a-zA-Z]+\\w*\\s*=\\s*\\&?\\s*[a-zA-Z]+\\w*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))*(\\s*\\@\\s*\\&?\\s*[a-zA-Z]+\\w*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))*)*")) {
            return true;
        }

        return false;
    }

    /**
     * This method is used to handle the initializing statements
     *
     * @param input the entered line
     * @return the initialized FariborzArray
     */
    public static FariborzArray initializingStatementHandler(String input) {
        FariborzArray newArray = new FariborzArray(input);

        if (newArray.getError() != null) {
            System.out.println(ANSI_RED + newArray.getError() + ANSI_RESET);
            return null;
        }

        if (fariborzArrays.containsKey(newArray.getName())) {
            fariborzArrays.replace(newArray.getName(), newArray);
        } else {
            fariborzArrays.put(newArray.getName(), newArray);
        }
        ;

        return newArray;
    }

    /**
     * This method is used to handle the sharp statements
     *
     * @param input the entered line
     * @return the new changed FariborzArray
     */
    public static FariborzArray changeStatementHandler(String input) {
        String nameOfTargetArray = input.replaceAll("\\[|\\]", " ").trim().split(" ")[0];
        String keyOfTargetElement = input.split("\s*=\s*")[0].replace(nameOfTargetArray, "").trim();
        String valueOfTargetElement = input.split("\s*=\s")[1].trim();

        if (!fariborzArrays.containsKey(nameOfTargetArray)) {
            System.out.println(ANSI_RED + "(" + nameOfTargetArray + ")" + " Not found!" + ANSI_RESET);
            return null;
        }

        fariborzArrays.get(nameOfTargetArray).setElement(keyOfTargetElement, valueOfTargetElement);
        if (fariborzArrays.get(nameOfTargetArray).getError() != null) {
            System.out.println(ANSI_RED + fariborzArrays.get(nameOfTargetArray).getError() + ANSI_RESET);
            fariborzArrays.get(nameOfTargetArray).setError(null);
            return null;
        }

        return fariborzArrays.get(nameOfTargetArray);
    }

    /**
     * This method is used to handle the element-wise statements
     *
     * @param input the entered line
     * @return the new FariborzArray
     */
    public static FariborzArray elementWiseStatementHandler(String input) {
        FariborzArray array1 = fariborzArrays
                .get(input.replaceAll("\s*[a-zA-Z]+\\w*\s*=\s*", "").split("\s*(\\*|\\/|\\+|\\-)\s*")[0].trim());
        FariborzArray array2 = fariborzArrays
                .get(input.replaceAll("\s*[a-zA-Z]+\\w*\s*=\s*", "").split("\s*(\\*|\\/|\\+|\\-)\s*")[1].trim());

        if (array1.getType().matches("char|string") || array2.getType().matches("char|string")) {
            System.out.println("wrong type");
            return null;
        } else if (!array1.getType().equals(array2.getType())) {
            System.out.println("inequality of types");
            return null;
        } else if (array1.getDimension() != array2.getDimension()) {
            System.out.println("inequality of dimensions");
            return null;
        } else if (!array1.isEqualSizes(array2)) {
            System.out.println("inequality of sizes");
            return null;
        }

        FariborzArray resultArray = new FariborzArray();
        if (input.matches(".*=.*")) {
            resultArray.setName(input.split("\s*=\s*")[0].trim());
        }
        resultArray.setDimension(array1.getDimension());
        resultArray.setType(array1.getType());

        Map<String, String> resultData = new HashMap<>();

        if (array1.getType().equals("float")) {
            for (String key : array1.getElements().keySet()) {
                float number1 = Float.parseFloat(array1.getElements().get(key));
                float number2 = Float.parseFloat(array2.getElements().get(key));

                float result = 0;
                if (input.matches(".*\\+.*")) {
                    result = number1 + number2;
                } else if (input.matches(".*\\-.*")) {
                    result = number1 - number2;
                } else if (input.matches(".*\\*.*")) {
                    result = number1 * number2;
                } else if (input.matches(".*\\/.*")) {
                    result = number1 / number2;
                }
                resultData.put(key, Float.toString(result));
            }
        } else {
            for (String key : array1.getElements().keySet()) {
                int number1 = Integer.parseInt(array1.getElements().get(key));
                int number2 = Integer.parseInt(array2.getElements().get(key));

                int result = 0;
                if (input.matches(".*\\+.*")) {
                    result = number1 + number2;
                } else if (input.matches(".*\\-.*")) {
                    result = number1 - number2;
                } else if (input.matches(".*\\*.*")) {
                    result = number1 * number2;
                } else if (input.matches(".*\\/.*")) {
                    result = number1 / number2;
                }

                resultData.put(key, Integer.toString(result));
            }
        }

        resultArray.setElements(resultData);
        if (input.matches(".*=.*")) {
            if (fariborzArrays.containsKey(resultArray.getName())) {
                fariborzArrays.replace(resultArray.getName(), resultArray);
            } else {
                fariborzArrays.put(resultArray.getName(), resultArray);
            }
        }

        return resultArray;
    }

    /**
     * This method is used to handle the sharp statements
     *
     * @param input the entered line
     * @return the new FariborzArray
     */
    public static FariborzArray sharpStatementHandler(String input) {
        FariborzArray array1 = fariborzArrays
                .get(input.replaceAll("\s*[a-zA-Z]+\\w*\s*=\s*", "").split("\s*\\#\s*")[0].trim());
        FariborzArray array2 = fariborzArrays
                .get(input.replaceAll("\s*[a-zA-Z]+\\w*\s*=\s*", "").split("\s*\\#\s*")[1].trim());

        if (array1.getType().matches("int|float") || array2.getType().matches("int|float")) {
            System.out.println("wrong type");
            return null;
        } else if (!array1.getType().equals(array2.getType())) {
            System.out.println("inequality of types");
            return null;
        } else if (array1.getDimension() != array2.getDimension()) {
            System.out.println("inequality of dimensions");
            return null;
        } else if (!array1.getChandDarChand().equals(array2.getChandDarChand())) {
            System.out.println("inequality of sizes");
            return null;
        }

        FariborzArray resultArray = new FariborzArray();
        if (input.matches(".*=.*")) {
            resultArray.setName(input.split("\s*=\s*")[0].trim());
        }

        resultArray.setDimension(array1.getDimension());
        resultArray.setType("string");

        Map<String, String> resultData = new HashMap<>();

        if (array1.getType().equals("char")) {
            for (String key : array1.getElements().keySet()) {
                String char1 = array1.getElements().get(key);
                String char2 = array2.getElements().get(key);
                String result = char1 + char2;
                resultData.put(key, result);
            }
        } else {
            for (String key : array1.getElements().keySet()) {
                String number1 = array1.getElements().get(key);
                String number2 = array2.getElements().get(key);
                String result = number1 + number2;
                resultData.put(key, result);
            }
        }

        resultArray.setElements(resultData);
        if (input.matches(".*=.*")) {
            if (fariborzArrays.containsKey(resultArray.getName())) {
                fariborzArrays.replace(resultArray.getName(), resultArray);
            } else {
                fariborzArrays.put(resultArray.getName(), resultArray);
            }
        }

        return resultArray;
    }

    /**
     * This method is used to handle the matrix multiplication statements
     *
     * @param input the entered line
     * @return the new FariborzArray
     */
    public static FariborzArray matrixMultiplicationStatementHandler(String input) {
        FariborzArray array1 = fariborzArrays
                .get(input.replaceAll("\s*[a-zA-Z]+\\w*\s*=\s*", "").split("\s*\\@\s*")[0].trim());
        FariborzArray array2 = fariborzArrays
                .get(input.replaceAll("\s*[a-zA-Z]+\\w*\s*=\s*", "").split("\s*\\@\s*")[1].trim());

        if (array1.getType().matches("char|string") || array2.getType().matches("char|string")) {
            System.out.println("wrong type");
            return null;
        } else if (!array1.getType().equals(array2.getType())) {
            System.out.println("inequality of types");
            return null;
        } else if (array1.getDimension() != 2 || array2.getDimension() != 2) {
            System.out.println("wrong dimensions");
            return null;
        } else if (array1.getChandDarChand()[1] != array2.getChandDarChand()[0]) {
            System.out.println("incompatible dimension");
            return null;
        }

        FariborzArray resultArray = new FariborzArray();
        if (input.matches(".*=.*")) {
            resultArray.setName(input.split("\s*=\s*")[0].trim());
        }
        resultArray.setDimension(array1.getDimension());
        resultArray.setType(array1.getType());

        Map<String, String> resultData = new HashMap<>();

        if (array1.getType().equals("float")) {
            for (int i = 0; i < array1.getChandDarChand()[0]; i++) {
                for (int j = 0; j < array2.getChandDarChand()[1]; j++) {
                    String key;
                    float sum = 0;

                    for (int k = 0; k < array1.getChandDarChand()[1]; k++) {
                        float number1 = Float.parseFloat(array1.getElements().get("[" + i + "]" + "[" + k + "]"));
                        float number2 = Float.parseFloat(array2.getElements().get("[" + k + "]" + "[" + j + "]"));
                        sum += number1 * number2;
                    }

                    key = "[" + i + "]" + "[" + j + "]";
                    resultData.put(key, Float.toString(sum));
                }
            }
        } else {
            for (int i = 0; i < array1.getChandDarChand()[0]; i++) {
                for (int j = 0; j < array2.getChandDarChand()[1]; j++) {
                    String key;
                    int sum = 0;

                    for (int k = 0; k < array1.getChandDarChand()[1]; k++) {
                        int number1 = Integer.parseInt(array1.getElements().get("[" + i + "]" + "[" + k + "]"));
                        int number2 = Integer.parseInt(array2.getElements().get("[" + k + "]" + "[" + j + "]"));
                        sum += number1 * number2;
                    }

                    key = "[" + i + "]" + "[" + j + "]";
                    resultData.put(key, Integer.toString(sum));
                }
            }
        }

        resultArray.setElements(resultData);
        if (input.matches(".*=.*")) {
            if (fariborzArrays.containsKey(resultArray.getName())) {
                fariborzArrays.replace(resultArray.getName(), resultArray);
            } else {
                fariborzArrays.put(resultArray.getName(), resultArray);
            }
        }

        return resultArray;
    }

    /**
     * This method is used to handle the transpose statements
     *
     * @param input the entered line
     * @return the new FariborzArray
     */
    public static FariborzArray transposingStatementHandler(String input) {
        FariborzArray array = fariborzArrays
                .get(input.replaceAll("\s*[a-zA-Z]+\\w*\s*=\s*", "").replaceAll("\\&", "").trim());

        if (array.getType().matches("char|string")) {
            System.out.println("wrong type");
            return null;
        } else if (array.getDimension() != 2) {
            System.out.println("wrong dimensions");
            return null;
        }

        FariborzArray resultArray = new FariborzArray();
        if (input.matches(".*=.*")) {
            resultArray.setName(input.split("\s*=\s*")[0].trim());
        }
        resultArray.setDimension(array.getDimension());
        resultArray.setType(array.getType());
        int[] newChandDarChand = new int[2];
        newChandDarChand[0] = array.getChandDarChand()[1];
        newChandDarChand[1] = array.getChandDarChand()[0];
        resultArray.setChandDarChand(newChandDarChand);

        Map<String, String> resultData = new HashMap<>();

        for (int i = 0; i < array.getChandDarChand()[0]; i++) {
            for (int j = 0; j < array.getChandDarChand()[1]; j++) {
                String key;
                String value = array.getElements().get("[" + i + "]" + "[" + j + "]");
                key = "[" + j + "]" + "[" + i + "]";
                resultData.put(key, value);
            }
        }

        resultArray.setElements(resultData);
        if (input.matches(".*=.*")) {
            if (fariborzArrays.containsKey(resultArray.getName())) {
                fariborzArrays.replace(resultArray.getName(), resultArray);
            } else {
                fariborzArrays.put(resultArray.getName(), resultArray);
            }
        }

        return resultArray;
    }

    /**
     * This method is used to handle the cutting statements
     *
     * @param input the entered line
     * @return the new FariborzArray
     */
    public static FariborzArray cutingStatementHandler(String input) {
        FariborzArray array = fariborzArrays
                .get(input.replaceAll("\s*[a-zA-Z]+\\w*\s*=\s*", "").replaceAll("\\[", " ").split(" ")[0]);

        FariborzArray resultArray = new FariborzArray();
        if (input.matches(".*=.*")) {
            resultArray.setName(input.split("\s*=\s*")[0].trim());
        }
        resultArray.setType(array.getType());

        Map<String, String> resultData = new HashMap<>();

        String[] keyParts = input.replaceAll("\s*[a-zA-Z]+\\w*\s*=\s*", "").replaceAll("\s*[a-zA-Z]+\\w*\s*", "")
                .replaceAll("\\]\\[", "\\] \\[").trim().split(" ");
        int start = 0, end = 0, step = 0;

        String[] keys;
        String[] keys2;
        String[] keys3;

        String[] parts;
        if (keyParts[0].matches("\\[\\d+:\\d+:\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = Integer.parseInt(parts[1]);
            step = Integer.parseInt(parts[2]);
        } else if (keyParts[0].matches("\\[\\d+:\\d+:\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = Integer.parseInt(parts[1]);
            step = 1;
        } else if (keyParts[0].matches("\\[\\d+::\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = array.getChandDarChand()[0];
            step = Integer.parseInt(parts[2]);
        } else if (keyParts[0].matches("\\[:\\d+:\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = 0;
            end = Integer.parseInt(parts[1]);
            step = Integer.parseInt(parts[2]);
        } else if (keyParts[0].matches("\\[::\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = 0;
            end = array.getChandDarChand()[0];
            step = Integer.parseInt(parts[2]);
        } else if (keyParts[0].matches("\\[:\\d+:\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = 0;
            end = Integer.parseInt(parts[1]);
            step = 1;
        } else if (keyParts[0].matches("\\[\\d+::\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = array.getChandDarChand()[0];
            step = 1;
        } else if (keyParts[0].matches("\\[::\\]")) {
            start = 0;
            end = array.getChandDarChand()[0];
            step = 1;
        } else if (keyParts[0].matches("\\[\\d+:\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = Integer.parseInt(parts[1]);
            step = 1;
        } else if (keyParts[0].matches("\\[\\d+:\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = array.getChandDarChand()[0];
            step = 1;
        } else if (keyParts[0].matches("\\[:\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = 0;
            end = Integer.parseInt(parts[1]);
            step = 1;
        } else if (keyParts[0].matches("\\[:\\]")) {
            start = 0;
            end = array.getChandDarChand()[0];
            step = 1;
        }

        keys = new String[((end - start) / step) + ((end - start) % step)];
        for (int j = 0; j < keys.length; j++) {
            keys[j] = "[" + (start + j * step) + "]";
        }

        for (int i = 1; i < keyParts.length; i++) {
            if (keyParts[i].matches("\\[\\d+:\\d+:\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = Integer.parseInt(parts[1]);
                step = Integer.parseInt(parts[2]);

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[\\d+:\\d+:]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = Integer.parseInt(parts[1]);
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[\\d+::\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = array.getChandDarChand()[0];
                step = Integer.parseInt(parts[2]);

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[:\\d+:\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = Integer.parseInt(parts[1]);
                step = Integer.parseInt(parts[2]);

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[::\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = array.getChandDarChand()[0];
                step = Integer.parseInt(parts[2]);

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[:\\d+:\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = Integer.parseInt(parts[1]);
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[\\d+::\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = array.getChandDarChand()[0];
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[::\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = array.getChandDarChand()[0];
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[\\d+:\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = Integer.parseInt(parts[1]);
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[\\d+:\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = array.getChandDarChand()[0];
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[:\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = Integer.parseInt(parts[1]);
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            } else if (keyParts[i].matches("\\[:\\]")) {
                start = 0;
                end = array.getChandDarChand()[0];
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + (start + j * step) + "]";
                }

                keys3 = new String[keys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < keys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = keys[k] + keys2[l];
                        j++;
                    }
                }

                keys = keys3;
            }
        }

        String[] newArrayKeys = new String[keys.length];

        if (keyParts[0].matches("\\[\\d+:\\d+:\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = Integer.parseInt(parts[1]);
            step = Integer.parseInt(parts[2]);
        } else if (keyParts[0].matches("\\[\\d+:\\d+:\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = Integer.parseInt(parts[1]);
            step = 1;
        } else if (keyParts[0].matches("\\[\\d+::\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = array.getChandDarChand()[0];
            step = Integer.parseInt(parts[2]);
        } else if (keyParts[0].matches("\\[:\\d+:\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = 0;
            end = Integer.parseInt(parts[1]);
            step = Integer.parseInt(parts[2]);
        } else if (keyParts[0].matches("\\[::\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = 0;
            end = array.getChandDarChand()[0];
            step = Integer.parseInt(parts[2]);
        } else if (keyParts[0].matches("\\[:\\d+:\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = 0;
            end = Integer.parseInt(parts[1]);
            step = 1;
        } else if (keyParts[0].matches("\\[\\d+::\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = array.getChandDarChand()[0];
            step = 1;
        } else if (keyParts[0].matches("\\[::\\]")) {
            start = 0;
            end = array.getChandDarChand()[0];
            step = 1;
        } else if (keyParts[0].matches("\\[\\d+:\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = Integer.parseInt(parts[1]);
            step = 1;
        } else if (keyParts[0].matches("\\[\\d+:\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = Integer.parseInt(parts[0]);
            end = array.getChandDarChand()[0];
            step = 1;
        } else if (keyParts[0].matches("\\[:\\d+\\]")) {
            parts = keyParts[0].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
            start = 0;
            end = Integer.parseInt(parts[1]);
            step = 1;
        } else if (keyParts[0].matches("\\[:\\]")) {
            start = 0;
            end = array.getChandDarChand()[0];
            step = 1;
        }

        newArrayKeys = new String[((end - start) / step) + ((end - start) % step)];
        for (int j = 0; j < newArrayKeys.length; j++) {
            newArrayKeys[j] = "[" + j + "]";
        }

        for (int i = 1; i < keyParts.length; i++) {
            if (keyParts[i].matches("\\[\\d+:\\d+:\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = Integer.parseInt(parts[1]);
                step = Integer.parseInt(parts[2]);

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[\\d+:\\d+:]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = Integer.parseInt(parts[1]);
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[\\d+::\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = array.getChandDarChand()[0];
                step = Integer.parseInt(parts[2]);

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[:\\d+:\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = Integer.parseInt(parts[1]);
                step = Integer.parseInt(parts[2]);

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[::\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = array.getChandDarChand()[0];
                step = Integer.parseInt(parts[2]);

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[:\\d+:\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = Integer.parseInt(parts[1]);
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[\\d+::\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = array.getChandDarChand()[0];
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[::\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = array.getChandDarChand()[0];
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[\\d+:\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = Integer.parseInt(parts[1]);
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[\\d+:\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = Integer.parseInt(parts[0]);
                end = array.getChandDarChand()[0];
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[:\\d+\\]")) {
                parts = keyParts[i].replaceAll("\\[", "").replaceAll("\\]", "").split(":");
                start = 0;
                end = Integer.parseInt(parts[1]);
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            } else if (keyParts[i].matches("\\[:\\]")) {
                start = 0;
                end = array.getChandDarChand()[0];
                step = 1;

                keys2 = new String[((end - start) / step) + ((end - start) % step)];
                for (int j = 0; j < keys2.length; j++) {
                    keys2[j] = "[" + j + "]";
                }

                keys3 = new String[newArrayKeys.length * keys2.length];
                int j = 0;
                for (int k = 0; k < newArrayKeys.length; k++) {
                    for (int l = 0; l < keys2.length; l++) {
                        keys3[j] = newArrayKeys[k] + keys2[l];
                        j++;
                    }
                }

                newArrayKeys = keys3;
            }
        }

        for (int i = 0; i < newArrayKeys.length; i++) {
            resultData.put(newArrayKeys[i], array.getElement(keys[i]));
        }
        resultArray.setElements(resultData);
        resultArray.setDimension(array.getDimension());

        if (fariborzArrays.containsKey(resultArray.getName())) {
            fariborzArrays.replace(resultArray.getName(), resultArray);
        } else {
            fariborzArrays.put(resultArray.getName(), resultArray);
        }


        return resultArray;
    }

    /**
     * This method is used to handle the combination operations statements
     *
     * @param input the entered line
     * @return the new FariborzArray
     */
    public static FariborzArray combinationOperationsHandler(String input) {
        input = input.split("\s*=\s*")[1].trim();

        ArrayList<String> operations = new ArrayList<>(Arrays.asList(input.split("\s*(\\*|\\/|\\+|\\-|\\@)\s*")));

        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i)
                    .matches("\\s*[a-zA-Z]+\\w*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))+\\s*")) {
                FariborzArray array = cutingStatementHandler(operations.get(i));
                fariborzArrays.put(i + "a", array);
                operations.remove(i);
                operations.add(i, i + "a");
                i = 0;
            }
        }

        ArrayList<String> operators = new ArrayList<>(Arrays.asList(
                input.replaceAll("\s*[a-zA-Z]+\\w*((\\[[0-9]*:[0-9]*\\])|(\\[[0-9]*:[0-9]*:[0-9]*\\]))*\s*", " ")
                        .trim().split("\s+")));

        for (int i = 0; i < operators.size(); i++) {
            operations.add(i * 2 + 1, operators.get(i));
        }

        ArrayList<String> tempFariborzArraysNames = new ArrayList<>();

        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).matches("\\*|\\/")) {
                String statement = operations.get(i - 1) + operations.get(i) + operations.get(i + 1);

                FariborzArray array = elementWiseStatementHandler(statement);
                fariborzArrays.put(i + "b", array);
                tempFariborzArraysNames.add(i + "b");

                operations.remove(i - 1);
                operations.remove(i - 1);
                operations.remove(i - 1);

                operations.add(i - 1, i + "b");
                i = 0;
            }
        }

        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).matches("\\+|\\-")) {
                String statement = operations.get(i - 1) + operations.get(i) + operations.get(i + 1);

                FariborzArray array = elementWiseStatementHandler(statement);
                fariborzArrays.put(i + "c", array);
                tempFariborzArraysNames.add(i + "c");

                operations.remove(i - 1);
                operations.remove(i - 1);
                operations.remove(i - 1);

                operations.add(i - 1, i + "c");
                i = 0;
            }
        }

        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).matches("\\#")) {
                String statement = operations.get(i - 1) + operations.get(i) + operations.get(i + 1);

                FariborzArray array = elementWiseStatementHandler(statement);
                fariborzArrays.put(i + "d", array);
                tempFariborzArraysNames.add(i + "d");

                operations.remove(i - 1);
                operations.remove(i - 1);
                operations.remove(i - 1);

                operations.add(i - 1, i + "d");
                i = 0;
            }
        }

        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).matches("\\&\s*[a-zA-Z]+\\w*\s*")) {
                FariborzArray array = transposingStatementHandler(operations.get(i));
                fariborzArrays.put(i + "e", array);
                tempFariborzArraysNames.add(i + "e");
                operations.remove(i);
                operations.add(i, i + "e");
                i = 0;
            }
        }

        for (int i = 0; i < operations.size(); i++) {
            if (operations.get(i).matches("\\@")) {
                String statement = operations.get(i - 1) + operations.get(i) + operations.get(i + 1);

                FariborzArray array = matrixMultiplicationStatementHandler(statement);
                fariborzArrays.put(i + "f", array);
                tempFariborzArraysNames.add(i + "f");
                operations.remove(i - 1);
                operations.remove(i - 1);
                operations.remove(i - 1);

                operations.add(i - 1, i + "f");
                i = 0;
            }
        }

        FariborzArray resultArray = fariborzArrays.get(operations.get(0));
        fariborzArrays.put(resultArray.getName(), resultArray);

        for (int i = 0; i < tempFariborzArraysNames.size(); i++) {
            fariborzArrays.remove(tempFariborzArraysNames.get(i));
        }


        return resultArray;
    }

    /**
     * This method is used to print a fariborzArray
     *
     * @param ex
     */
    public static void fariborzArrayPrinter(FariborzArray ex) {
        if (ex == null) {
            return;
        }

        System.out.println(ANSI_GREEN + ex + ANSI_RESET);
    }
}