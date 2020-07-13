package io.blocko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Board {

	@Id
	private Long id;
	
	@Column
	private String content;
	
	@Column
	private String createdDate;
	
	@Column
	private String updatedDate;
}
