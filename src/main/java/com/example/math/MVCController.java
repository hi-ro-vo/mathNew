package com.example.math;



import com.example.math.Expresions.Expresion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RestController
public class MVCController {

    Parser parser = new ParserImpl();

    @PostMapping("/sendStrings")
    List<Double> calc(@RequestParam(value = "param[]") String[] strings){
        List<Double> list =  Arrays.stream(strings).map(parser::parse).map(Expresion::execute).collect(Collectors.toList());

        return list;
    }
}
