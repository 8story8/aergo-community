package io.blocko.model;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class TimeEntity {

	private String createdDate;

	private String updatedDate;

	@PrePersist
	public void setCreatedDate() {
		if (this.createdDate == null) {
			this.createdDate = getDate();
		}
	}

	@PreUpdate
	public void setUpdatedDate() {
		this.updatedDate = getDate();
	}

	private String getDate() {
		final ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
		final String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
		return date;
	}
}
