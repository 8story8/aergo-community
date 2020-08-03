package io.blocko.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Board {

	@Id
	@GenericGenerator(name="id_generator", strategy="io.blocko.id.IdGenerator")
	@GeneratedValue(generator="id_generator")
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
	
	@Column
	private Integer viewCount;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private User user;
}
