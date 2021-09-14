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

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";

    }

    //example of
    @GetMapping("/individual-example/{q}/{from}") // matches /individual-example/foo/bar
    public String getIndividualParams(@PathVariable String from, @PathVariable("q") String query) {
        return String.format("q:%s from:%s", query, from);
    }

    @GetMapping("/header")
    public String getHeader(@RequestHeader String host) {
        return host;
    }

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

}
