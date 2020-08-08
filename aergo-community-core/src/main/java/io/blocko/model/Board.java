package io.blocko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostLoad;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Board extends TimeEntity {

	@Id
	@GenericGenerator(name = "id_generator", strategy = "io.blocko.id.IdGenerator")
	@GeneratedValue(generator = "id_generator")
	private Long id;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private String fileName;

	@Column
	private String filePath;

	@Column
	private Integer viewCount;

	@Transient
	private Integer previousViewCount;

	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "id")
	private SimpleUser user;

	@PostLoad
	public void setPreviousViewCount() {
		this.previousViewCount = this.viewCount;
	}
	
	@PreUpdate
	@Override
	public void setUpdatedDate() {
		if(!isModifiedViewCount()) {
			setUpdatedDate(getDate());
		}
	}
	
	private boolean isModifiedViewCount() {
		boolean isModified = false;
		if(this.viewCount != this.previousViewCount) {
			isModified = true;
		}
		return isModified;
	}
	
	// Register, File X
	public Board(String title, String content, Integer viewCount, SimpleUser user) {
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.user = user;
	}
	
	// Register, File O
	public Board(String title, String content, String fileName, String filePath, Integer viewCount, SimpleUser user) {
		this.title = title;
		this.content = content;
		this.fileName = fileName;
		this.filePath = filePath;
		this.viewCount = viewCount;
		this.user = user;
	}
}
