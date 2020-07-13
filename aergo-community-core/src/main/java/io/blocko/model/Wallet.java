package io.blocko.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Wallet {

	@Id
	private Long id;
	
	@Column
	private String address;
	
	@Column
	private BigDecimal amount;
	
	@Column
	private String createdDate;
	
	@Column
	private String updatedDate;
}
