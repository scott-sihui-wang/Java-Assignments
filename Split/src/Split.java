import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Split {
	public static String[] split(String s,String regex){
		String[]splited=s.split(regex);
		String[]matched=new String[s.length()];
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(s);
		int flagMatched=0;
		int flagSplited=splited.length;
		while(matcher.find()) {
			matched[flagMatched++]=matcher.group();
		}
		int i=0;
		int j=0;
		int num=0;
		int bias=0;
		String result[]=new String[flagSplited+flagMatched];
		while(i<flagSplited && j<flagMatched) {
			if(s.indexOf(splited[i].bias)>s.indexOf(matched[j].bias)) {
				result[num++]=matched[j];
				bias+=matched[j++].length();
			}
			else {
				if(splited[i].length()!=0) {
					result[num++]=splited[i];
				}
				bias+=splited[i++].length();
			}
		}
		while(i<flagSplited) {
			if(splited[i].length()!=0) {
				result[num++]=splited[i];
			}
			i++;
		}
		while(j<flagMatched) {
			result[num++]=matched[j++];
		}
		String[]r=new String[num];
		for(int q=0;q<num;q++) {
			r[q]=result[q];
		}
		return r;
	}
	public static void main(String[]args) {
		System.out.println("Input a string to split:");
		Scanner scan=new Scanner(System.in);
		String x=scan.next();
		System.out.print("Input the regex:");
		String y=scan.next();
		String[]r=split(x,y);
		System.out.println("The results are:");
		for(int i=0;i<r.length;i++) {
			System.out.print(r[i]+" ");
		}
		System.out.println(" ");
	}
}
