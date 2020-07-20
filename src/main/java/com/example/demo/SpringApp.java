package com.example.demo;

import com.sun.source.tree.ReturnTree;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Map;

@RestController
public class SpringApp {

    public String piGet(){
        return "3.141592653589793";
    }

    @PostMapping("/math/area")
    public String areaCalc(
            @RequestParam String type,
            @RequestParam double[] nums) {
        double result = 0;
        //System.out.println("Type: "+type+" Numbers : "+ nums[0]);
        if (type.equals("circle")){
            double r = nums[0];
            result = (float) (3.14 * r*r);
            //System.out.println(result);
        }
        if (type.equals("rectangle")){
            if (nums.length != 2){
                return "Invalid - Incorrect number of variables passed";
            }
            else {
                result = nums[0]*nums[1];

            }
        }
        return String.format("The area of a %s is %s", type, result);
    }

    @RequestMapping("/hwl/{height}/{width}/{length}")
    public String getMapParams(@PathVariable Map<String, String> querystring){
        int lwh = 1;
        for (String i : querystring.keySet()) {
            lwh *= Integer.valueOf(querystring.get(i));

        }
        return String.valueOf(lwh);
    }

//////////////////////////////  First attempt using Mapping for number values
//    @GetMapping("/math/calculate")
//    public String getDirect(WebRequest webRequest){
//        String operation = webRequest.getParameter("operation");
//        String value1 = webRequest.getParameter("value1");
//        String value2 = webRequest.getParameter("value2");
//        Map<String, String[]> params = webRequest.getParameterMap();
//
//        {
//            //System.out.println(params[0]);
//            System.out.println(operation+value1+value2);
//
//        if (operation.equals("add") || operation == null){
//            System.out.println("In the add function");
//            return ("Addition Operation: "+(Integer.valueOf(value1) + Integer.valueOf(value2)));
//        }
//        else if (operation.equals("subtract")){
//            return ("Substration Operation: "+(Integer.valueOf(value1) - Integer.valueOf(value2)));
//        }
//        else if (operation.equals("multiply")){
//            return ("Multiply Operation: "+(Integer.valueOf(value1) * Integer.valueOf(value2)));
//        }
//        else if (operation.equals("divide")){
//            return ("Divide Operation: "+(Integer.valueOf(value1) / Integer.valueOf(value2)));
//        }
//        else { return "No operation"; }
//        }
//    }
//////////////////////////////////////////////

/////////////////////////////////  Pulling 1 unique and a unknown number of nums from URL
    @GetMapping("/math/calculate")
    public String getMathOpts(
                    @RequestParam (value="operation", defaultValue = "add") String operation,
                    @RequestParam (value="n", required=true) Integer[] nums) {
        Integer tempValue = 0;
        if (operation.equals("add") || operation == null){
            System.out.println("In the add function");
            for (int i=0;i<nums.length;i++){
                tempValue += nums[i]; }
            return ("Addition Operation: "+ tempValue);
        }
        else if (operation.equals("subtract")){
            for (int i=0;i<nums.length;i++){
                tempValue -= nums[i]; }
            return ("Substraction Operation: "+ tempValue);
        }
        else if (operation.equals("multiply")){
            for (int i=0;i<nums.length;i++){
                tempValue *= nums[i]; }
            return ("Multiplication Operation: "+ tempValue);
        }
        else if (operation.equals("divide")){
            for (int i=0;i<nums.length;i++){
                tempValue /= nums[i]; }
            return ("Division Operation: "+ tempValue);
        }
        else { return "No operation";
        }
    }

////// Not functional
//    public List<T> getAll(WebRequest webRequest){
//        Map<String, String[]> params = webRequest.getParameterMap();
//        System.out.println( params );


    //////////////////////////////  Getting 1 to 1 Value from URL
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

