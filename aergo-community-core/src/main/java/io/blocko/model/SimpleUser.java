package io.blocko.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
public class SimpleUser extends TimeEntity {

	@Id
	@GenericGenerator(name="id_generator", strategy="io.blocko.id.IdGenerator")
	@GeneratedValue(generator="id_generator")
	private Long id;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	
	public SimpleUser(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
}
