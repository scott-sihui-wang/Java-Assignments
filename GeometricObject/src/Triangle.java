import java.lang.Math;
import java.util.Scanner;
public class Triangle extends GeometricObject{
	private double side1,side2,side3;
	protected Triangle() throws IllegalTriangleException{
		this(1.0,1.0,1.0);
	}
	protected Triangle(double s1,double s2,double s3)throws IllegalTriangleException {
		if(s1<=0 || s2<=0 || s3<=0) {
			throw new IllegalTriangleException(0);
		}
		else if( s1+s2<=s3 || s1+s3<=s2 || s2+s3<=s1) {
			throw new IllegalTriangleException(1);
		}
		side1=s1;
		side2=s2;
		side3=s3;
	}
	protected Triangle(double s1,double s2,double s3,String c,Boolean f)throws IllegalTriangleException {
		super(c,f);
		if(s1<=0 || s2<=0 || s3<=0) {
			throw new IllegalTriangleException(0);
		}
		else if( s1+s2<=s3 || s1+s3<=s2 || s2+s3<=s1) {
			throw new IllegalTriangleException(1);
		}
		side1=s1;
		side2=s2;
		side3=s3;
	}
	public double getSide1() {
		return side1;
	}
	public double getSide2() {
		return side2;
	}
	public double getSide3() {
		return side3;
	}
	public double getArea() {
		double p=getPerimeter()/2;
		double s=p*(p-side1)*(p-side2)*(p-side3);
		s=Math.sqrt(s);
		return s;
	}
	public double getPerimeter() {
		return side1+side2+side3;
	}
	public String toString() {
		return "Triangle: side1= "+side1+" side2= "+side2+" side3= "+side3;
	}
	public void display() {
		
	}
	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		double s1,s2,s3;
		String c;
		Boolean f;
		System.out.print("Please enter the length of the three sides of the triangle:");
		s1=scan.nextDouble();
		s2=scan.nextDouble();
		s3=scan.nextDouble();
		System.out.print("Please enter the color of the triangle:");
		c=scan.next();
		System.out.print("The triangle is filled or not (true or false)?");
		f=scan.hasNextBoolean();
		try {
			Triangle t=new Triangle(s1,s2,s3,c,f);
			System.out.println("The area is: "+t.getArea());
			System.out.println(t.toString());
			System.out.println("The color is: "+t.getColor());
			System.out.println("The triangle is filled? "+t.isFilled());
		}
		catch(IllegalTriangleException e) {
			System.out.println(e.toString());
		}
	}
}
