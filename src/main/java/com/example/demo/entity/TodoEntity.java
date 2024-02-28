package com.example.demo.entity;

import java.util.Date;
import java.util.UUID;
import java.text.SimpleDateFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Todo")
@Getter @Setter @NoArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @Column(nullable = false, updatable = false)
    private UUID id;

    private String title;

    private String description;

    private String createdAt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z").format(new Date());

    private Boolean status = false;

    public TodoEntity(String theTitle, String theDescription){
        this.title = theTitle;
        this.description = theDescription;
    }

    @Override
	public String toString() {
		return String.format(
            "Todo[id=%s, title='%s', description='%s']",
			id, title, description
        );
	}
}