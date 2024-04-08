import java.util.Scanner;
public class MyNumberConversion {
	public static long bin2Dec(String binaryString) {
		long ret=0;
		int len=binaryString.length();
		for(int i=0;i<len;i++) {
			ret=ret<<1;
			char cur=binaryString.charAt(i);
			if(cur=='1') {
				++ret;
			}
			else if(cur!='0') {
				throw new BinaryFormatException(cur,i+1);
			}
		}
		return ret;
	}
	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		System.out.print("Please enter a binary string:");
		try {
			System.out.print("The binary number can be converted to the decimal number of: "+bin2Dec(scan.next()));
		}
		catch(BinaryFormatException e) {
			System.out.print(e.toString());
		}
	}
}
