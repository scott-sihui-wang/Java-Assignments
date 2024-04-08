import java.util.Scanner;
public class ATM {
	public static void main(String[] args) {
		Account[] accounts=new Account[10];
		for(int i=0;i<10;i++) {
			accounts[i]=new Account(i,100.0);
		}
		Scanner input=new Scanner(System.in);
		int inputChoice,inputID;
		double inputMoney;
		while(true) {
			System.out.print("Enter an id:");
			inputID=input.nextInt();
			if(inputID<0||inputID>9) {
				System.out.println("Illegal id! Please input a legal number between 0 to 9.");
				continue;
			}
			else {
				while(true) {
					System.out.println("Main menu");
					System.out.println("1: check balance");
					System.out.println("2: withdraw");
					System.out.println("3: deposit");
					System.out.println("4: exit");
					System.out.print("Enter a choice:");
					inputChoice=input.nextInt();
					if(inputChoice<1||inputChoice>4) {
						System.out.println("Illegal choice! Please input a legal number between 1 to 4.");
						continue;
					}
					else if(inputChoice==1) {
						System.out.println("The balance is "+accounts[inputID].getBalance());
					}
					else if(inputChoice==2) {
						System.out.print("Enter an amount to withdraw:");
						inputMoney=input.nextDouble();
						accounts[inputID].withDraw(inputMoney);
					}
					else if(inputChoice==3) {
						System.out.print("Enter an amount to deposit:");
						inputMoney=input.nextDouble();
						accounts[inputID].deposit(inputMoney);
					}
					else if(inputChoice==4) {
						break;
					}
				}
			}
		}
	}
}
