package com.example.springplayground;

import org.springframework.util.MultiValueMap;

import java.util.List;

public class MathService {
    static String addAll(List<String> qString) {
        StringBuilder output = new StringBuilder();
        int total = 0;
        for (int i = 0; i < qString.size(); i++) {
            total += Integer.parseInt(qString.get(i));
            output.append(qString.get(i));
            if (i == qString.size() - 1) {
                output.append(" = " + total);
            } else {
                output.append(" + ");
            }
        }
        return output.toString();
    }
    public static String getCalculate(String operation, int x, int y){
        switch (operation) {
            case "add":
                return String.format("%s + %s = %s", x, y, x + y);
            case "subtract":
                return String.format("%s - %s = %s", x, y, x - y);
            case "multiply":
                return String.format("%s * %s = %s", x, y, x * y);
            case "divide":
                return String.format("%s / %s = %s", x, y, x / y);
            default:
                return String.format("%s + %s = %s", x, y, x + y);
        }
    }

}
