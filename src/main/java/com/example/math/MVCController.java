package com.example.math;


import com.example.math.Expresions.Expresion;
import com.example.math.Expresions.ExpresionFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class MVCController {

    Parser parser = new ParserImpl();

    @PostMapping("/sendStrings")
    ModelAndView calc(@RequestParam(value = "param[]") String[] strings, ModelAndView modelAndView) {
        List<Double> list = Arrays.stream(strings).map(parser::parse).map(Expresion::execute).collect(Collectors.toList());
        List<String> strings1 = Arrays.asList(strings);

        String res = "";
        String res2 = "";
        for (int i = 0; i < strings1.size(); i++) {
            res += strings1.get(i) + "\n";
            res2 += "=" + list.get(i) + "\n";
        }

        modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("str", res);
        modelAndView.addObject("str2", res2);
        modelAndView.addObject("cash", new ArrayList<>(ExpresionFactory.variableServise.variables.entrySet()));
        return modelAndView;
    }

    @GetMapping("/getCash")
    Map<String, Double> getCash() {
        return ExpresionFactory.variableServise.variables;
    }

    @GetMapping("/home")
    ModelAndView home(ModelAndView modelAndView) {
        modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("cash", new ArrayList<>(ExpresionFactory.variableServise.variables.entrySet()));
        return modelAndView;
    }

}
