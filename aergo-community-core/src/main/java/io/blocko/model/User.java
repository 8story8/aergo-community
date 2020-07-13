package io.blocko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	private String id;
	
	@Column
	private String password;
	
	@Column
	private String createdDate;
	
	@Column
	private String updatedDate;
}
