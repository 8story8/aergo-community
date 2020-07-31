package io.blocko.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResultForm {

	private Object data;

	private boolean status;

	public static ResultForm of(Object data, boolean status) {
		return new ResultForm(data, status);
	}
}
