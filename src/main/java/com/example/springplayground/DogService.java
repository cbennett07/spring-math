package com.example.springplayground;

public class DogService {

    private String name;
    private String color;
    private String weight;

    public String dogMessage(){
        StringBuilder output = new StringBuilder();
        output.append(this.name + " is a good " + this.color + " dog");
        if (Integer.parseInt(weight)>80){
            output.append("...that weighs TOO MUCH!");
        }
        return output.toString();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

}
