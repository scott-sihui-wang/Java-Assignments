import java.io.*;
import java.util.*;
import java.util.regex.*;
public class ConvertNHighlighting {
	private static File sourceFile,targetFile;
	private static Scanner input;
	private static PrintWriter output;
	private static String generalStyleColor="blue";
	private static String commentStyleColor="green";
	private static String keywordStyleColor="navy";
	private static boolean generalStyleBold=false,generalStyleItalic=false;
	private static boolean commentStyleBold=false,commentStyleItalic=false;
	private static boolean keywordStyleBold=true,keywordStyleItalic=false;
	private static boolean Comment=false;
	private static String keywords[]= {"abstract","assert","boolean","break","byte","case","catch","char","class","const","continue","default","do","double","else","enum","extends","final","finally","float","for","goto","if","implements","import","instanceof","int","interface","long","native","new","package","private","protected","public","return","strictfp","short","static","super","switch","synchronized","this","throw","throws","transient","try","void","volatile","while"};
	private static void displayUsage() {
		System.out.println("Usage: java ConvertNHighlighting javaFilename htmlFilename");
	}
	private static String Head(String colorStyle,boolean isHeadOfHtml,boolean isBold,boolean isItalic){
		String s="";
		if(isHeadOfHtml)s+="<html><body><pre>";
		s=s+"<font color="+colorStyle+">";
		if(isBold)s+="<b>";
		if(isItalic)s+="<i>";
		return s;
	}
	private static String Tail(boolean isTailOfHtml,boolean isBold,boolean isItalic){
		String s="";
		if(isItalic)s+="</i>";
		if(isBold)s+="</b>";
		s+="</font>";
		if(isTailOfHtml)s+="</pre></body></html>";
		return s;
	}
	private static String getComment(String s) {
		return Tail(false,generalStyleBold,generalStyleItalic)+Head(commentStyleColor,false,commentStyleBold,commentStyleItalic)+s+Tail(false,commentStyleBold,commentStyleItalic)+Head(generalStyleColor,false,generalStyleBold,generalStyleItalic);
	}
	private static String getHighlight(String s) {
		return Tail(false,generalStyleBold,generalStyleItalic)+Head(keywordStyleColor,false,keywordStyleBold,keywordStyleItalic)+s+Tail(false,keywordStyleBold,keywordStyleItalic)+Head(generalStyleColor,false,generalStyleBold,generalStyleItalic);
	}
	private static String preprocess(String s) {
		s=s.replace("&", "&amp");
		s=s.replace("<", "&lt");
		s=s.replace(">", "&gt");
		return s;
	}
	private static String toHtml(String s,boolean isComment) {
		if(isComment) {
			String p="\\x2a\\x2f.*$";
			Pattern pat=Pattern.compile(p);
			Matcher mat=pat.matcher(s);
			if(mat.find()) {
				Comment=false;
				String[] sub=s.split(p);
				return getComment(sub[0]+"*/")+toHtml(mat.group().substring(2),Comment);
			}
			else {
				Comment=true;
				return getComment(s);
			}
		}
		else {
			int len=s.length();
			int i=getUnquotedSlashPlus(s);
			Comment=false;
			if(i<0) {
				return processLiterals(s,false,false);
			}
			else if(i<len) {
				return processLiterals(s.substring(0, i),false,false)+getComment(s.substring(i));
			}
			else {
				Comment=true;
				return processLiterals(s.substring(0, i-len),false,false)+toHtml(s.substring(i-len),Comment);
			}
		}
	}
	private static int getUnquotedSlashPlus(String s) {
		int len=s.length();
		boolean inQuote=false;
		boolean quoteType=false;
		for(int i=0;i<len;i++) {
			if(!inQuote) {
				if(s.charAt(i)=='\'') {
					inQuote=true;
					quoteType=false;
				}
				else if(s.charAt(i)=='\"') {
					inQuote=true;
					quoteType=true;
				}
				else if(s.charAt(i)=='/' && i<len-1) {
					if(s.charAt(i+1)=='/') {
						return i;
					}
					else if(s.charAt(i+1)=='*') {
						return i+len;
					}
				}
			}
			else {
				if(s.charAt(i)=='\'' && s.charAt(i-1)!='\\' && quoteType==false) {
					inQuote=false;
				}
				else if(s.charAt(i)=='\"' && s.charAt(i-1)!='\\' && quoteType==true) {
					inQuote=false;
				}
			}
		}
		return -1;
	}
	private static String processLiterals(String s,boolean inQuote,boolean quoteType) {//unquoted, separated by legal terms $ _ " ' num
		if(!inQuote) {
			String p="\".*|\'.*";
			Pattern pat=Pattern.compile(p);
			Matcher mat=pat.matcher(s);
			if(mat.find()) {
				inQuote=true;
				String sub[]=s.split(p);
				String recur=mat.group();
				if(recur.charAt(0)=='\'') {
					quoteType=false;
				}
				else {
					quoteType=true;
				}
				if(sub.length>0) {
					return keywordsHighlighting(sub[0])+recur.charAt(0)+processLiterals(recur.substring(1),inQuote,quoteType);
				}
				else {
					return recur.charAt(0)+processLiterals(recur.substring(1),inQuote,quoteType);
				}
			}
			else {
				return keywordsHighlighting(s);
			}
		}
		else {
			String p,c;
			if(quoteType) {
				p="\".*";
				c="\"";
			}
			else {
				p="\'.*";
				c="\'";
			}
			Pattern pat=Pattern.compile(p);
			Matcher mat=pat.matcher(s);
			if(mat.find()) {
				inQuote=false;
				String sub[]=s.split(p);
				String recur=mat.group();
				if(sub.length>0) {
					return sub[0]+c+processLiterals(recur.substring(1),inQuote,quoteType);
				}
				else {
					return c+processLiterals(recur.substring(1),inQuote,quoteType);
				}
			}
			else {
				return s;
			}
		}
	}
	private static String keywordsHighlighting(String s) {
		String result="";
		String p="";
		for(int i=0;i<keywords.length-1;i++) {
			p+="\\b"+keywords[i]+"\\b"+"|";
		}
		p+="\\b"+keywords[keywords.length-1]+"\\b";
		Pattern pat=Pattern.compile(p);
		Matcher mat=pat.matcher(s);
		String sub[]=s.split(p);
		int cnt=0;
		while(mat.find()) {
			result+=sub[cnt++];
			result+=getHighlight(mat.group());
		}
		if(sub.length>cnt) {
			result+=sub[cnt];
		}
		return result;
	}
	private static void fileCheck (String in,String out) throws FileCheckError {
		sourceFile=new File(in);
		if(!sourceFile.exists()) {
			throw new FileCheckError(0,in);
		}
		else if(!Pattern.matches(".+\\.java$", in)) {
			throw new FileCheckError(2,in);
		}
		targetFile=new File(out);
		if(targetFile.exists()) {
			throw new FileCheckError(1,out);
		}
		else if(!Pattern.matches(".+\\.html|.+\\.htm", out)){
			throw new FileCheckError(3,out);
		}
		else {
			try {
				targetFile.createNewFile();
			}
			catch(Exception e) {
				System.out.println(e.toString());
			}
		}
	}
	private static void convert(){
		try {
			input=new Scanner(sourceFile);
			output=new PrintWriter(targetFile);
			output.println(Head(generalStyleColor,true,generalStyleBold,generalStyleItalic));
			while(input.hasNext()) {
				String s=input.nextLine();
				s=preprocess(s);
				output.println(toHtml(s,Comment));
			}
			output.println(Tail(true,generalStyleBold,generalStyleItalic));
			input.close();
			output.close();
		}		
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	public static void main(String args[]) {
		try {
			if(args.length==2) {
				fileCheck(args[0],args[1]);
				convert();
			}
			else {
				displayUsage();
			}
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
}