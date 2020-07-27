package io.blocko.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Board {

	@Id
	private Long id;
	
	@Lob
	@Column
	private Blob photo;
	
	@Column
	private String content;
	
	@Column
	private String createdDate;
	
	@Column
	private String updatedDate;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
}
