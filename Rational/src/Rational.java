import java.util.Scanner;
import java.math.BigInteger;
import java.math.BigDecimal;
public class Rational extends Number implements Comparable<Rational> {
  // Data fields for numerator and denominator
  private BigInteger numerator;
  private BigInteger denominator;

  /** Construct a rational with default properties */
  public Rational() {
    this(BigInteger.valueOf(0),BigInteger.valueOf(1));
  }
  
  public Rational(String numerator,String denominator) {
	this(new BigInteger(numerator),new BigInteger(denominator));
  }

  /** Construct a rational with specified numerator and denominator */
  public Rational(BigInteger numerator, BigInteger denominator) {
    BigInteger GCD = numerator.gcd(denominator);
    this.numerator = numerator.multiply(BigInteger.valueOf(denominator.signum())).divide(GCD);
    this.denominator = denominator.abs().divide(GCD);
  }


  /** Return numerator */
  public BigInteger getNumerator() {
    return numerator;
  }

  /** Return denominator */
  public BigInteger getDenominator() {
    return denominator;
  }

  /** Add a rational number to this rational */
  public Rational add(Rational secondRational) {
    BigInteger n = numerator.multiply(secondRational.getDenominator()).add(denominator.multiply(secondRational.getNumerator()));
    BigInteger d = denominator.multiply(secondRational.getDenominator());
    return new Rational(n, d);
  }

  /** Subtract a rational number from this rational */
  public Rational subtract(Rational secondRational) {
    BigInteger n = numerator.multiply(secondRational.getDenominator()).subtract(denominator.multiply(secondRational.getNumerator()));
    BigInteger d = denominator.multiply(secondRational.getDenominator());
    return new Rational(n, d);
  }

  /** Multiply a rational number to this rational */
  public Rational multiply(Rational secondRational) {
    BigInteger n = numerator.multiply(secondRational.getNumerator());
    BigInteger d = denominator.multiply(secondRational.getDenominator());
    return new Rational(n, d);
  }

  /** Divide a rational number from this rational */
  public Rational divide(Rational secondRational) {
    BigInteger n = numerator.multiply(secondRational.getDenominator());
    BigInteger d = denominator.multiply(secondRational.numerator);
    return new Rational(n, d);
  }

  @Override  
  public String toString() {
    if (denominator.longValue() == 1)
      return numerator + "";
    else
      return numerator + "/" + denominator;
  }

  @Override // Override the equals method in the Object class 
  public boolean equals(Object other) {
    if ((this.subtract((Rational)(other))).getNumerator().signum() == 0)
      return true;
    else
      return false;
  }

  @Override
  public int hashCode(){
    return this.numerator.multiply(BigInteger.valueOf(121)).add(this.denominator.multiply(BigInteger.valueOf(7))).intValue();
  }
  @Override // Implement the abstract intValue method in Number 
  public int intValue() {
    return (int)doubleValue();
  }

  @Override // Implement the abstract floatValue method in Number 
  public float floatValue() {
    return (float)doubleValue();
  }

  @Override // Implement the doubleValue method in Number 
  public double doubleValue() {
	BigDecimal numerator2=new BigDecimal(numerator);
	BigDecimal denominator2=new BigDecimal(denominator);
    return numerator2.divide(denominator2,19,BigDecimal.ROUND_HALF_UP).doubleValue();
  }

  @Override // Implement the abstract longValue method in Number
  public long longValue() {
    return (long)doubleValue();
  }

  @Override // Implement the compareTo method in Comparable
  public int compareTo(Rational o) {
	  
    return this.subtract(o).getNumerator().signum();
  }
  public static void main(String args[]) {
	  Scanner scan=new Scanner(System.in);
	  System.out.print("Enter the first rational number:");
	  Rational r1=new Rational(scan.next(),scan.next());
	  System.out.print("Enter the second rational number:");
	  Rational r2=new Rational(scan.next(),scan.next());
	  System.out.println(r1.toString()+" + "+r2.toString()+" = "+r1.add(r2).toString());
	  System.out.println(r1.toString()+" - "+r2.toString()+" = "+r1.subtract(r2).toString());
	  System.out.println(r1.toString()+" * "+r2.toString()+" = "+r1.multiply(r2).toString());
	  System.out.println(r1.toString()+" / "+r2.toString()+" = "+r1.divide(r2).toString());
	  System.out.println(r2.toString()+" is "+r2.doubleValue());
  }
}
