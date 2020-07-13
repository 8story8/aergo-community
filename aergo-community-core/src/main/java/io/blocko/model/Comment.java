package io.blocko.model;

import javax.persistence.Column;
import javax.persistence.Id;

public class Comment {

	@Id
	private Long id;
	
	@Column
	private String content;
	
	@Column
	private String createdDate;
	
	@Column
	private String updatedDate;
}
