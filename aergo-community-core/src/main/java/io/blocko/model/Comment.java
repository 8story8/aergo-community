package io.blocko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
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
