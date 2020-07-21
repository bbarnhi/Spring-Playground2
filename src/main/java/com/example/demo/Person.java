package com.example.demo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;


    public class Person {
        public String firstName;
        public String lastName;
        @JsonFormat(pattern = "yyyy-MM-dd") //forces a specific pattern
        public Date birthDate;

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public Date getBirthDate() { return birthDate; }
        public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }
    }


