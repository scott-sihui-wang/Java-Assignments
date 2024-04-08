public class BinaryFormatException extends NumberFormatException{
	private char errChar;
	private int location;
	public BinaryFormatException(){
		errChar='\0';
		location=0;
	}
	public BinaryFormatException(char ch,int l) {
		errChar=ch;
		location=l;
	}
	public String toString() {
		return "The character '"+errChar+"' at location: "+location+" is illegal!";
	}
}
