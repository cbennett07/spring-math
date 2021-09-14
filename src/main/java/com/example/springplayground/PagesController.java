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
        return MathService.getCalculate(operation, x, y);
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
}
