public class FileCheckError extends Exception{
	private int errCode;
	private String filename;
	private static String[]errInfoPrefix= {"Source file","Target file","Source file","Target file"};
	private static String[]errInfoSuffix= {"does not exist!","already exist!","is not a .java file!","is not a .html or .htm file!"};
	public FileCheckError(int errCase,String _filename) {
		errCode=errCase;
		filename=_filename;
	}
	public String toString() {
		return errInfoPrefix[errCode]+" "+filename+" "+errInfoSuffix[errCode];
	}
}
