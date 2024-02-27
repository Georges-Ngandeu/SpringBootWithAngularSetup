package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

@Data
@Entity
class User {

    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    public User(String firstname, String lastname, Integer theAge){
        this.firstName = firstname;
        this.lastName = lastname;
        this.age = theAge;
    }
   
    @Override
	public String toString() {
		return String.format(
            "Customer[id=%d, firstname='%s', lastname='%s']",
			id, firstName, lastName
        );
	}
}