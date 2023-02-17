package mySQL;

import java.io.UnsupportedEncodingException;

public class DB_Encoder {
	
	public static final String[] encodingsArray={"utf8","Windows-1251","koi8-r", "koi8","latin1","ISO-8859-1", "ASCII","Cp866", "koi8"};
	
	private String in;
	private String out;
	
	DB_Encoder(){
		 in=encodingsArray[0];
		 out=encodingsArray[1];
	}
	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		
		this.in = in;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}
	public String[] convert(String[] strings) throws UnsupportedEncodingException{
		return encode(strings, in, out);
	}
	
	public static String[] encode(String[] strings, String fromEncoding, String toEncoding) throws UnsupportedEncodingException{
		
		String input;
		String value;
		if(strings!=null){
			for(int i=0;i<strings.length; i++){
				input=strings[i];
				byte ptext[] = input.getBytes(fromEncoding);
				value = new String(ptext, toEncoding);
				strings[i]=value;
			}
		}
		return strings;
	}
	
	public String convert(String strings) throws UnsupportedEncodingException{
		return encode(strings, in, out);
	}
	
	public static String encode(String string, String fromEncoding, String toEncoding) throws UnsupportedEncodingException{
		
		String input=null;
		String value = null;
		if(string!=null){
				input=string;
				byte ptext[] = input.getBytes(fromEncoding);
				value = new String(ptext, toEncoding);				
		}
		return value;
	}
}
