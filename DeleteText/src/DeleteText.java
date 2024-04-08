import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class DeleteText {
	public static void main(String args[])throws Exception {
		if(args.length==2) {
			File sourceFile=new File(args[1]);
			File targetFile;
			if(!sourceFile.exists()) {
				System.out.println("Source file "+args[1]+" does not exist!");
				System.exit(1);
			}
			while(true) {
				SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
				targetFile=new File(sourceFile.getParent()+df.format(new Date()));
				if(!targetFile.exists()) {
					targetFile.createNewFile();
					break;
				}
			}
			try(
					Scanner input=new Scanner(sourceFile);
					PrintWriter output=new PrintWriter(targetFile);
					){
				while(input.hasNext()) {
					String s1=input.nextLine();
					String s2=s1.replace(args[0], "");
					output.println(s2);
				}
				input.close();
				output.close();
				sourceFile.delete();
				targetFile.renameTo(sourceFile);
			}
		}
		else {
			System.out.println("Usage: java DeleteText targetText filename");
		}
	}
}
