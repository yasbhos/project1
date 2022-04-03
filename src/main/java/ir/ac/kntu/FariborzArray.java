package ir.ac.kntu;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Represents a Fariborz-Array
 *
 * @author Hossein Yasbolaghi
 * @author Yasbolaghi.sharrahi@gmail.com
 * @version 1.0
 */
public class FariborzArray {
    private String name;

    private String type;

    private int dimension;

    private Map<String, String> elements;

    private int[] chandDarChand;

    private String error;

    /**
     * Creates a Fariborz-Array
     */
    public FariborzArray() {
        elements = new HashMap<>();
    }

    /**
     * Creates a Fariborz-Array
     *
     * @param input the entered line
     */
    public FariborzArray(String input) {
        name = input.split("\s*=\s*")[0].trim();
        elements = new HashMap<>();
        setElements(input);
        if (error != null) {
            return;
        }

        dimension = dimension(input.split("\\s*=\\s*")[1].trim());

        setChandDarChand();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getDimension() {
        return dimension;
    }

    public Map<String, String> getElements() {
        return elements;
    }

    public int[] getChandDarChand() {
        return chandDarChand;
    }

    public String getError() {
        return error;
    }

    public String getElement(String key) {
        return elements.get(key);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public void setElements(Map<String, String> elements) {
        this.elements = elements;
    }

    public void setChandDarChand(int[] chandDarChand) {
        this.chandDarChand = chandDarChand;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setElement(String key, String newValue) {
        if (elements.get(key) == null) {
            error = "out of bounds";
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
            newValue = newValue.substring(1, newValue.length());
        }

        elements.replace(key, newValue);
    }

    private void setElements(String input) {
        input = input.split("\s*=\s*")[1].trim();

        String key = "";
        int elementIndex = 0;

        while (input.matches("^\s*\\[.*\\]\s*$")) {
            input = input.replaceAll("^\s*\\[|\\]\s*$", "");
            key += "[" + 0 + "]";
        }

        type = typeOfElements(input);
        if (error != null) {
            return;
        }

        String[] datas = getElements(input);
        if (error != null) {
            return;
        }

        for (int i = 0; i < datas.length; i++) {
            if (datas[i].equals("]")) {
                elementIndex = 0;
                int counter = 1;
                String temp = "";

                while (datas[i + 1].equals("]")) {
                    counter++;
                    i++;
                }

                for (int j = 0; j < counter; j++) {
                    temp += "[0]";
                }
                key = key.substring(0, key.length() - (counter + 1) * 3) + "[" + (Character.getNumericValue(key.charAt(key.length() - counter * 3 - 2)) + 1) + "]" + temp;
            } else if (datas[i].equals("[")) {
                continue;
            } else {
                key = key.substring(0, key.length() - 3) + "[" + elementIndex + "]";

                if (datas[i].matches("\'.\'")) {
                    datas[i] = datas[i].replaceAll("\'", "");
                } else if (datas[i].matches("\".+\"")) {
                    datas[i] = datas[i].replaceAll("\"", "");
                }

                elements.put(key, datas[i]);
                elementIndex++;
            }
        }

    }

    private void setChandDarChand() {
        chandDarChand = new int[dimension];

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

            chandDarChand[i] = frequency;
        }
    }

    private String[] getElements(String input) {
        input = reFactoring(input);
        if (error != null) {
            return null;
        }

        return input.split("\s+");
    }

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

    private String typeOfElements(String input) {
        String type = null;
        input = input.trim();
        String[] datas = getElements(input);
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

    @Override
    public String toString() {
        String data = "";
        String key = "";
        for (int i = 0; i < dimension; i++) {
            key += "[0]";
        }

        for (int i = 0; i < dimension; i++) {
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

    public boolean isEqualSizes(FariborzArray otherArray) {
        for (int i = 0; i < chandDarChand.length; i++) {
            if (chandDarChand[i] != otherArray.getChandDarChand()[i]) {
                return false;
            }
        }

        return true;
    }
}