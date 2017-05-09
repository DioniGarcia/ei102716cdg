package es.uji.ei102716cdg.util;


import java.text.DateFormat;
import java.text.ParseException;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.StringUtils;
public class CustomSqlDateEditor extends CustomDateEditor {

	public CustomSqlDateEditor(DateFormat dateFormat, boolean allowEmpty) {
		super(dateFormat, allowEmpty);
		// TODO Auto-generated constructor stub
	}

	private boolean allowEmpty;
	// private int exactDateLength;
	private DateFormat dateFormat;

	public CustomSqlDateEditor(DateFormat dateFormat, boolean allowEmpty,
			int exactDateLength) {
		super(dateFormat, allowEmpty, exactDateLength);
		this.dateFormat = dateFormat;
		this.allowEmpty = allowEmpty;
	}
	
	public void setAsText(String text) {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			// Treat empty String as null value.
			setValue(null);
		}
		else {
			try {
				java.util.Date date = dateFormat.parse(text);
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				setValue(sqlDate);
			} catch (ParseException ex) {
				ex.printStackTrace();
			}
		}
	}

}
