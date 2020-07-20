package com.example.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;
import java.util.Map;

@RestController
public class SpringApp {

    @GetMapping("/math/pi")
    public String piGet(){
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String getDirect(WebRequest webRequest){
        String operation = webRequest.getParameter("operation");
        String value1 = webRequest.getParameter("value1");
        String value2 = webRequest.getParameter("value2");
        Map<String, String[]> params = webRequest.getParameterMap();
        {
            //System.out.println(params[0]);
            System.out.println(operation+value1+value2);

        if (operation.equals("add") || operation == null){
            System.out.println("In the add function");
            return ("Addition Operation: "+(Integer.valueOf(value1) + Integer.valueOf(value2)));
        }
        else if (operation.equals("subtract")){
            return ("Substration Operation: "+(Integer.valueOf(value1) - Integer.valueOf(value2)));
        }
        else if (operation.equals("multiply")){
            return ("Multiply Operation: "+(Integer.valueOf(value1) * Integer.valueOf(value2)));
        }
        else if (operation.equals("divide")){
            return ("Divide Operation: "+(Integer.valueOf(value1) / Integer.valueOf(value2)));
        }
        else { return "No operation"; }
        }
    }

//    public List<T> getAll(WebRequest webRequest){
//        Map<String, String[]> params = webRequest.getParameterMap();
//        System.out.println( params );

//    @GetMapping("/math/calculate")
//    public String getMathOpts(
//        @RequestParam(value="operation", defaultValue = "add") String operation ,
//        @RequestParam String value1,
//        @RequestParam String value2){
//        //return String.format("Operation is : %s", operation);
//        System.out.println("Made it to the calculate function -"+operation+value1+value2);
//        if (operation == "add"){
//            System.out.println("In the add function");
//            return ("Addition Operation: "+(value1 + value2));
//        }
//        else if (operation == "subtract"){
//            return value1 - value2;
//        }
//        else if (operation == "multiply"){
//            return value1 * value2;
//        }
//        else if (operation == "divide"){
//            return value1 / value2;
//        }
//        else { return 0; }
//    }

//    @GetMapping("/math/sum")
//    public String piGet(){
//        return "3.141592653589793";
//    }


    @GetMapping("/hello")
    public String helloGet(){
        return "hello get";
    }

    @PostMapping("/hello")
    public String helloPost(){
        return "hello post";
    }

    @DeleteMapping("/hello")
    public String helloDelete(){
        return "hello Delete";
    }

    @PutMapping("/hello")
    public String helloPut(){
        return "hello Put ";
    }

    @PatchMapping("/hello")
    public String helloPatch(){
        return "hello Patch";
    }
}

