package io.blocko.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResultForm {
	
	private String msg;
	
	private Integer code;

	private Boolean status;

	public static ResultForm of(String msg, Integer code, Boolean status) {
		return new ResultForm(msg, code, status);
	}
}
