package es.uji.ei102716cdg.util;

import java.io.UnsupportedEncodingException;

public class Encoding {

	public static String convertToUTF8(String text){
		try {
			return new String(text.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
