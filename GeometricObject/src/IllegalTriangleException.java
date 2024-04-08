
public class IllegalTriangleException extends Exception {
	private int errCode;
	private static String[] errInfo= {"The length should be greater than zero!","The length of the sides violates the constraints!"};
	public IllegalTriangleException(){
		errCode=0;
	}
	public IllegalTriangleException(int errVal) {
		errCode=errVal;
	}
	public String toString() {
		return errInfo[errCode];
	}
}
