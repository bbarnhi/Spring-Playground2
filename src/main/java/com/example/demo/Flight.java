package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

class Flight {
    private int id;
    private String destination;
    private Date departsOn;
    private List<Person> passengers;
    private Person pilot;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDestination() { return destination; }

    public void setDestination(String destination) { this.destination = destination; }

    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getDepartsOn() { return departsOn; }

    public void setDepartsOn(Date dateTime) { this.departsOn = dateTime; }

    public List<Person> getPassengers() { return passengers; }

    public void setPassengers(List<Person> passengers) { this.passengers = passengers; }

    public Person getPilot() { return pilot; }

    public void setPilot(Person pilot) { this.pilot = pilot; }

    static class Person {
        private String name;

        public String getName() { return name; }

        public void setName(String name) { this.name = name; }
    }
}

//@JsonInclude(JsonInclude.Include.NON_NULL) //removes any values if Null only
//public class Flight {
//    @JsonProperty("Flight-Cost") //forces key change in JSON
//    public Integer price;
//    @JsonIgnore  ///completely removes from section
//    public Date departs;
//    private List<Person> passengers;
//    private Person pilot;
//
//    public Integer getPrice() { return price; }
//    public void setPrice(Integer price) { this.price = price; }
//
//    public Date getDeparts() { return departs; }
//    public void setDeparts(Date departs) { this.departs = departs; }
//
//    public List<Person> getPassengers() { return getPassengers(); }
//    public void setPassengers(List<Person> passengers) { this.passengers=passengers; }
//
//    public Person getPilot() { return pilot; }
//    public void setPilot(Person pilot) { this.pilot=pilot; }
//
//}

