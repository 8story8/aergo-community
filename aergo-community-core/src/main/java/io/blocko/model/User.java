package io.blocko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class User {

	@Id
	private Long id;
	
	@Column
	private String email;
	
	@Column
	private String name;
	
	@Column
	private String password;
	
	@Column
	private String createdDate;
	
	@Column
	private String updatedDate;
}
