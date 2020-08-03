package io.blocko.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResultForm {

	private Object data;
	
	private Integer code;

	private Boolean status;

	public static ResultForm of(Object data, Integer code, Boolean status) {
		return new ResultForm(data, code, status);
	}
}
