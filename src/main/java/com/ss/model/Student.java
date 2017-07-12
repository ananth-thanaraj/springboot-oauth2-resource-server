package com.ss.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Document(collection="student")
public @Data class Student {

	

	@Id
	private	   String id;
	
	private  String firstname;
	private  String lastname;
	private  String grade;
	
}
