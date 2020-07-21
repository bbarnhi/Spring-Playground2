package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.source.tree.ReturnTree;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class SpringApp {
////////////////////////////Deserialization
    static class Manifest { //Level 1 Json

//        private String firstName;
//        private String lastName;
        //Private String passengers;
        private double price;

//        public String getFirstName() { return firstName; }
//        public void setFirstName(String firstName) { this.firstName = firstName; }
//        public String getLastName() { return lastName; }
//        public void setLastName(String lastName) { this.lastName = lastName; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }

    static class Flight { //Level 2 Json
        private Manifest[] tickets;


        public Manifest[] getTickets() { return tickets; }
        public void setTickets(Manifest[] tickets) { this.tickets = tickets; }

    }
@PostMapping("/flights/tickets/total")
    public double getNested(@RequestBody Flight flight) {
        double total = 0;
        for (int i =0; i < flight.getTickets().length; i++) {
            total +=flight.tickets[i].getPrice();
        }
    return total;
    }
////////////////////////////////End Deserialization


//////////////////////////////JSON Manipulation
//    @GetMapping("/flights/flight")  //add 1 person
//    public Flight getFlight(){
//        Flight flight = new Flight();
//        flight.departsOn = (new Date(2017-04-21)); //Outer Json Data -- Flight
//        //flight.price = 200;
//        return flight;
//    }
//    @GetMapping("/flights/flight")  //Adds 2 flights to current setup
//    public List<Flight> getFlights() {
//        Flight flight1 = new Flight();
//        flight1.setId(4);
//        flight1.setDestination("London");
//        flight1.setDepartsOn(new Date(2014 - 1900, 5, 8));
//
//        Flight flight2 = new Flight();
//        flight2.setId(4);
//        flight2.setDestination("London");
//        flight2.setDepartsOn(new Date(2014 - 1900, 5, 8));
//
//        return Arrays.asList(flight1, flight2);
//    }


    @GetMapping("/passengers") //add 2 people
    public List<Person> getPeople(){
        Person person1 = new Person();
        person1.firstName = "Jane";
        person1.lastName = "Doe";
        person1.birthDate = new Date();

        Person person2 = new Person();
        person2.firstName = "Jane";
        person2.lastName = null;

        Person pilot = new Person();
        pilot.firstName = "Pilot";
        pilot.lastName = "SuperPilot";

        return Arrays.asList(person1, person2, pilot);
    }
    ///////////////////////End JSON Manipulation



    public String piGet() {
        return "3.141592653589793";
    }

///////////////////////// Cookie Manipulation
    @GetMapping("/set-cookie")
    public String setCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("userName", "SomeValue");
        response.addCookie(cookie);
        System.out.println();
        int x = 123;
        String.valueOf(x);
        return "Username is set";
    }

    @GetMapping("/get-cookie")
    public String getCookie(@CookieValue(name="userName") String cookie){
        return cookie;
    }
/////////////////////// End Cookie Manipulation

/////////////////// JSON DEMO DAta
//public class Person{
//       private String firstName
//       private String lastName;
//}



/////////////////    Area Manipulation from Post Mapping
    @PostMapping("/math/area")
    public String areaCalc(
            @RequestParam String type,
            @RequestParam double[] nums) {
        double result = 0;
        //System.out.println("Type: "+type+" Numbers : "+ nums[0]);
        if (type.equals("circle")) {
            double r = nums[0];
            result = (float) (3.14 * r * r); /// Note r*r ~= r^2.  ^ is XOR.
            //System.out.println(result);
        }
        if (type.equals("rectangle")) {
            if (nums.length != 2) {
                return "Invalid - Incorrect number of variables passed";
            } else {
                result = nums[0] * nums[1];

            }
        }
        return String.format("The area of a %s is %s", type, result);
        // Useful formatting... must start with String.format to use the %s functionality.
    }
//////////////////////////  End Post Mapping

////////////////////////// Path Mapping with multiple variables
    @RequestMapping("/hwl/{height}/{width}/{length}")
    public String getMapParams(@PathVariable Map<String, String> querystring) {
        int lwh = 1;
        for (String i : querystring.keySet()) {   //for each item in keystring by unique key.
            lwh *= Integer.valueOf(querystring.get(i)); // returns the value at given key.

        }
        return String.valueOf(lwh);
    }
//////////////////////////  End Path Mapping


///////////////////////// RequestParam Mapping.  1 Unique + Multiple Unknowns
    @GetMapping("/math/calculate")
    public String getMathOpts(
            @RequestParam(value = "operation", defaultValue = "add") String operation,
            @RequestParam(value = "n", required = true) Integer[] nums) {
        Integer tempValue = 0;
        //Note::  operation == "add" DOES NOT WORK
        if (operation.equals("add") || operation == null) {
            System.out.println("In the add function");
            for (int i = 0; i < nums.length; i++) {
                tempValue += nums[i];
            }
            return ("Addition Operation: " + tempValue);
        } else if (operation.equals("subtract")) {
            for (int i = 0; i < nums.length; i++) {
                tempValue -= nums[i];
            }
            return ("Substraction Operation: " + tempValue);
        } else if (operation.equals("multiply")) {
            for (int i = 0; i < nums.length; i++) {
                tempValue *= nums[i];
            }
            return ("Multiplication Operation: " + tempValue);
        } else if (operation.equals("divide")) {
            for (int i = 0; i < nums.length; i++) {
                tempValue /= nums[i];
            }
            return ("Division Operation: " + tempValue);
        } else {
            return "No operation";
        }
    }
///////////////////////////////////  End Param Mapping

////////////////////////////// Not functional example of HashMapping the data
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
//  ....
/////////////////////////////////////   Forcing java to interpret value as a number
//            return ("Addition Operation: "+(Integer.valueOf(value1) + Integer.valueOf(value2)));


 ///////////////////////  Basic URL Mapping Returns
    @GetMapping("/hello")
    public String helloGet() {
        return "hello get";
    }

    @PostMapping("/hello")
    public String helloPost() {
        return "hello post";
    }

    @DeleteMapping("/hello")
    public String helloDelete() {
        return "hello Delete";
    }

    @PutMapping("/hello")
    public String helloPut() {
        return "hello Put ";
    }

    @PatchMapping("/hello")
    public String helloPatch() {
        return "hello Patch";
    }
/////////////////////////  End Generic URL Mapping Returns
}

