package ir.ac.kntu;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Represents a FariborzArray
 *
 * @author Hossein Yasbolaghi
 * @author Yasbolaghi.sharrahi@gmail.com
 * @version 1.0
 */
public class FariborzArray {

    /**
     * represents name of the FariborzArray
     */
    private String name;

    /**
     * represents the type of the FariborzArray elements
     */
    private String type;

    /**
     * represents the dimension of the FariborzArray
     */
    private int dimension;

    /**
     * represents the elements of the FariborzArray in a map with their indexes as key of the elements
     */
    private Map<String, String> elements;

    /**
     * represents the size of the FariborzArray
     */
    private int[] size;

    /**
     * cantains an error message if there is an error in the FariborzArray
     */
    private String error;

    /**
     * Creates a FariborzArray
     */
    public FariborzArray() {
        elements = new HashMap<>();
    }

    /**
     * Creates a FariborzArray
     *
     * @param input the entered line
     */
    public FariborzArray(String input) {
        setName(input.split("\s*=\s*")[0].trim());
        if (error != null) {
            return;
        }

        elements = new HashMap<>();
        setElements(input);
        if (error != null) {
            return;
        }

        dimension = dimension(input.split("\\s*=\\s*")[1].trim());

        setSize();
    }

    /**
     * This is a getter for the name of the FariborzArray
     *
     * @return the name of the FariborzArray
     */
    public String getName() {
        return name;
    }

    /**
     * This is a setter for the name of the FariborzArray
     *
     * @param name the name of the FariborzArray
     */
    public void setName(String name) {
        if (name.matches("[a-zA-Z]+\\w*")) {
            this.name = name;
        } else {
            error = "invalid name";
            return;
        }
    }

    /**
     * This is a getter for the type of the FariborzArray
     *
     * @return the type of the FariborzArray
     */
    public String getType() {
        return type;
    }

    /**
     * This is a setter for the type of the FariborzArray
     *
     * @param type the type of the FariborzArray
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * This is a getter for the dimension of the FariborzArray
     *
     * @return the dimension of the FariborzArray
     */
    public int getDimension() {
        return dimension;
    }

    /**
     * This is a setter for the dimension of the FariborzArray
     *
     * @param dimension the dimension of the FariborzArray
     */
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    /**
     * This is a getter for the elements of the FariborzArray
     *
     * @return the elements of the FariborzArray
     */
    public Map<String, String> getElements() {
        return elements;
    }

    /**
     * This is a setter for the elements of the FariborzArray
     *
     * @param elements the elements of the FariborzArray
     */
    public void setElements(Map<String, String> elements) {
        this.elements = elements;
    }

    /**
     * This is a setter for the elements of the FariborzArray
     *
     * @param input the entered line
     */
    private void setElements(String input) {
        input = input.split("\s*=\s*")[1].trim();

        String key = "";
        while (input.matches("^\s*\\[.*\\]\s*$")) {
            input = input.replaceAll("^\s*\\[|\\]\s*$", "");
            key += "[0]";
        }

        type = typeOfElements(input);
        if (error != null) {
            return;
        }

        String[] datas = getPeacesOf(input);
        if (error != null) {
            return;
        }

        int elementIndex = 0;
        for (int i = 0; i < datas.length; i++) {
            if (datas[i].equals("]")) {
                elementIndex = 0;
                int counter = 1;
                while (datas[i + 1].equals("]")) {
                    counter++;
                    i++;
                }

                String temp = "";
                for (int j = 0; j < counter; j++) {
                    temp += "[0]";
                }
                key = key.substring(0, key.length() - (counter + 1) * 3) + "[" + (Character.getNumericValue(key.charAt(key.length() - counter * 3 - 2)) + 1) + "]" + temp;
            } else if (datas[i].equals("[")) {
                continue;
            } else {
                key = key.substring(0, key.length() - 3) + "[" + elementIndex++ + "]";
                if (datas[i].matches("\'.\'") || datas[i].matches("\".+\"")) {
                    datas[i] = datas[i].substring(1, datas[i].length() - 1);
                }
                elements.put(key, datas[i]);
            }
        }
    }

    /**
     * This is a getter for the size of the FariborzArray
     *
     * @return the size of the FariborzArray
     */
    public int[] getSize() {
        return size;
    }

    /**
     * This is a setter for the size of the FariborzArray
     *
     * @param size the size of the FariborzArray
     */
    public void setSize(int[] size) {
        this.size = size;
    }

    /**
     * This method sets the size of the FariborzArray using the dimension and the elements
     */
    private void setSize() {
        size = new int[dimension];

        for (int i = 0; i < dimension; i++) {
            int frequency = 0;
            boolean condition = true;
            for (int j = 0; condition; j++) {
                condition = false;
                for (Map.Entry<String, String> m : elements.entrySet()) {
                    if (m.getKey().matches(".{" + (i * 3) + "}\\[" + j + "\\]" + ".{" + ((dimension - i - 1) * 3) + "}")) {
                        frequency++;
                        condition = true;
                        break;
                    }
                }
            }

            size[i] = frequency;
        }
    }

    /**
     * This is a getter method for the error of the FariborzArray
     *
     * @return the error of the FariborzArray
     */
    public String getError() {
        return error;
    }

    /**
     * This is a setter method for the error of the FariborzArray
     *
     * @param error the error of the FariborzArray
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * This method returns a specific element of the FariborzArray
     *
     * @param key the key of the specific element
     */
    public String getElement(String key) {
        return elements.get(key);
    }

    /**
     * This method sets a specific element of the FariborzArray
     *
     * @param key   the key of the specific element
     * @param newValue the value of the specific element
     */
    public void setElement(String key, String newValue) {
        if (elements.get(key) == null) {
            error = "out of bounds";
            return;
        } else if (!typeOf(newValue).equals(type)) {
            error = "inequality of types";
            return;
        }

        if (newValue.matches("\\d+\\.")) {
            newValue = newValue + 0;
        } else if (newValue.matches("\\.\\d+")) {
            newValue = 0 + newValue;
        } else if (newValue.matches("\\.")) {
            error = "incompatible type";
            return;
        } else if (newValue.matches("\".{2,}\"") || newValue.matches("\'.\'")) {
            newValue = newValue.substring(1, newValue.length() - 1);
        }

        elements.replace(key, newValue);
    }

    /**
     * This method returns the peaces of the input with the delimiter witespace after refactoring
     *
     * @param input the input that will be refactored and separated
     * @return the peaces of the input with the delimiter witespace after refactoring
     */
    private String[] getPeacesOf(String input) {
        input = reFactoring(input);
        if (error != null) {
            return null;
        }

        return input.split("\s+");
    }

    /**
     * This method refactors the input
     *
     * @param input the input that will be refactored
     * @return the refactored input
     */
    private String reFactoring(String input) {
        String[] parts = input.trim().split("\s*\\,\s*");

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].matches("[^\\[][^\\s]*\\]")) {
                parts[i] = parts[i].replace("]", " ]");
            } else if (parts[i].matches("\\[[^\\s]*[^\\]]")) {
                parts[i] = parts[i].replace("[", "[ ");
            } else if (parts[i].matches("\\[[^\\s]*.*[^\\s]\\]")) {
                parts[i] = parts[i].replace("[", "[ ").replace("]", " ]");
            }
        }

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].matches("\\d+\\.")) {
                parts[i] = parts[i] + 0;
            } else if (parts[i].matches("\\.\\d+")) {
                parts[i] = 0 + parts[i];
            } else if (parts[i].matches("\\.")) {
                error = "incompatible type";
                return null;
            }
        }


        return String.join(" ", parts);
    }

    /**
     * This method returns the type of the elements of the input
     *
     * @param input the input that will be checked its elements type
     * @return the type of the elements of the input
     */
    private String typeOfElements(String input) {
        String type = null;
        input = input.trim();
        String[] datas = getPeacesOf(input);
        if (datas == null) {
            return null;
        }

        ArrayList<String> newDatas = new ArrayList<>();
        for (int i = 0; i < datas.length; i++) {
            if (datas[i].equals("[") || datas[i].equals("]")) {
                continue;
            } else {
                newDatas.add(datas[i]);
            }
        }
        for (String data : newDatas) {
            if (typeOf(data).equals("unknown type")) {
                error = "unknown type";
                return null;
            } else if (type == null) {
                type = typeOf(data);
            } else if (!typeOf(data).equals(type)) {
                error = "inequality of types";
                return null;
            }
        }

        return type;
    }

    /**
     * This method returns the type of the specific element
     *
     * @param element the element that will be checked its type
     * @return the type of the specific element
     */
    private String typeOf(String element) {
        if (element.matches("\\d+")) {
            return "int";
        } else if (element.matches("\\d+\\.\\d+")) {
            return "float";
        } else if (element.matches("\'.\'")) {
            return "char";
        } else if (element.matches("\".{2,}\"")) {
            return "string";
        } else {
            return "unknown type";
        }
    }

    /**
     * This method returns the size of the FariborzArray
     *
     * @param data the data that will be checked its size
     * @return the size of the FariborzArray
     */
    private int dimension(String data) {
        int numberOfZeroLastDigitIndex = 0;
        for (String key : elements.keySet()) {
            if (key.matches(".*\\[0\\]")) {
                numberOfZeroLastDigitIndex++;
            }
        }

        int counter;
        boolean flag = true;
        for (int i = 1; flag; i++) {
            counter = 0;
            flag = false;
            for (String key : elements.keySet()) {
                if (key.matches(".*\\[" + i + "\\]")) {
                    counter++;
                    flag = true;
                }
            }

            if (!flag) {
                break;
            }

            if (counter != numberOfZeroLastDigitIndex) {
                error = "different lenghs of shapes";
                break;
            }
        }

        int n = 0;
        while (data.matches("^\s*\\[.*\\]\s*$")) {
            n++;
            data = data.replaceAll("^\s*\\[|\\]\s*$", "");
        }

        return n;
    }

    /**
     * This method returns toString of the FariborzArray's elements
     *
     * @return toString of the FariborzArray's elements
     */
    @Override
    public String toString() {
        String data = "";
        String key = "";
        for (int i = 0; i < dimension; i++) {
            key += "[0]";
            data += "[";
        }

        for (int i = 0; i < elements.size(); i++) {
            if (elements.containsKey(key)) {
                data += elements.get(key) + " ";
                key = key.substring(0, key.length() - 3) + "[" + (Character.getNumericValue(key.charAt(key.length() - 2)) + 1) + "]";
            } else {
                data = data.trim();
                int index = 0;
                do {
                    index++;
                    String temp = "";
                    for (int j = 0; j < index; j++) {
                        temp += "[0]";
                    }
                    key = key.substring(0, key.length() - (index + 1) * 3) + "[" + (Character.getNumericValue(key.charAt(key.length() - index * 3 - 2)) + 1) + "]" + temp;
                } while (!elements.containsKey(key));

                for (int j = 0; j < index; j++) {
                    data += "]";
                }
                for (int j = 0; j < index; j++) {
                    data += "[";
                }
                data += elements.get(key) + " ";
                key = key.substring(0, key.length() - 3) + "[" + (Character.getNumericValue(key.charAt(key.length() - 2)) + 1) + "]";
            }
        }

        for (int i = 0; i < dimension; i++) {
            data = data.trim();
            data += "]";
        }

        return data;
    }

    /**
     * This method cheks size of two FariborzArrays
     *
     * @param otherArray the other FariborzArray
     * @return true if the size of two FariborzArrays is equal, false otherwise
     */
    public boolean isEqualSizes(FariborzArray otherArray) {
        for (int i = 0; i < size.length; i++) {
            if (size[i] != otherArray.getSize()[i]) {
                return false;
            }
        }

        return true;
    }
}