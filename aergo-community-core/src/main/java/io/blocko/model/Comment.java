package io.blocko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Comment {
	
	@Id
	@GenericGenerator(name="id_generator", strategy="io.blocko.id.IdGenerator")
	@GeneratedValue(generator="id_generator")
	private Long id;

	@Column
	private String content;

	@Column
	private String createdDate;

	@Column
	private String updatedDate;

	@ManyToOne
	@JoinColumn(name = "boardId", referencedColumnName = "id")
	private Board board;
}
