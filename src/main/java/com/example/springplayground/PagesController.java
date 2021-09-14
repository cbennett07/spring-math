package com.example.springplayground;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@RestController
public class PagesController {


    @GetMapping("/math/calculate")
    public String getMathString(@RequestParam(required = false, defaultValue = "add") String operation, @RequestParam int x, @RequestParam int y) {
//
        return MathService.getAnswer(operation, x, y);
    }

    @PostMapping("/math/sum")
    public String postMathSum(@RequestParam MultiValueMap<String, String> qString) {
        List<String> myList = qString.get("n");
        return MathService.addAll(myList);
    }

    @RequestMapping("/math/volume/{length}/{width}/{height}")
    public String calcVolume(@PathVariable int length, @PathVariable int width, @PathVariable int height){
        return String.format("The volume of a %sx%sx%s rectangle is %s", length, width, height, length*width*height);
    }

    @PostMapping(value = "/math/area", params = {"type", "radius"})
    public String calcCircleArea(@RequestParam String type, @RequestParam String radius){
        int intRadius;
        if (type.equals("circle")) {
            try{
                intRadius = Integer.parseInt(radius);}
            catch (Exception e) {return "Invalid";}
            return String.format("Area of a circle with a radius of %s is %.5f", radius, intRadius * intRadius * Math.PI);
        }
        else {return "Invalid";}
    }
    @PostMapping(value = "/math/area", params = {"type", "width", "height"})
    public String calcRectangleArea(@RequestParam String type, @RequestParam String width, @RequestParam String height){
        int intWidth;
        int intHeight;
        if (type.equals("rectangle")) {
            try{
                intWidth = Integer.parseInt(width);
                intHeight= Integer.parseInt(height);}
            catch (Exception e) {return "Invalid";}
            return String.format("Area of a %dx%d rectangle is %d", intWidth, intHeight, intHeight * intWidth);
        }
        else {return "Invalid";}
    }
}
