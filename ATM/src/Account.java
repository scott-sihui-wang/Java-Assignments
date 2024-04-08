import java.util.Date;
public class Account {
	private int id=0;
	private double balance=0;
	private static double annualInterestRate=0;
	private Date dateCreated;
	public Account(){
		dateCreated=new Date();
	}
	public Account(int _id,double _balance) {
		id=_id;
		balance=_balance;
		dateCreated=new Date();
	}
	public int getID() {
		return id;
	}
	public void setID(int _id) {
		id=_id;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double _balance) {
		balance=_balance;
	}
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	public void setAnnualInterestRate(double r) {
		annualInterestRate=r;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public double getMonthlyInterestRate() {
		double monthlyInterestRate=annualInterestRate/12.0;
		return monthlyInterestRate;
	}
	public double getMonthlyInterest() {
		double monthlyInterest=balance*annualInterestRate/12.0/100.0;
		return monthlyInterest;
	}
	public void withDraw(double money) {
		balance-=money;
	}
	public void deposit(double money) {
		balance+=money;
	}
}
