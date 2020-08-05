package io.blocko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Board {

	@Id
	@GenericGenerator(name="id_generator", strategy="io.blocko.id.IdGenerator")
	@GeneratedValue(generator="id_generator")
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private String filePath;
	
	@Column
	private String createdDate;
	
	@Column
	private String updatedDate;
	
	@Column
	private Integer viewCount;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private SimpleUser user;
	
	public Board(String title, String content, String filePath, SimpleUser user) {
		this.title = title;
		this.content = content;
		this.filePath = filePath;
		this.viewCount = 0;
		this.user = user;
	}
}
