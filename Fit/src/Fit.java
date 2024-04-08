import java.util.ArrayList;
import java.util.Scanner;
public class Fit {
	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		System.out.print("Enter the number of objects:");//输入objects总数
		int num=scan.nextInt();
		System.out.print("Enter the weights of the objects:");//输入每个object的重量
		ArrayList<Integer>weights=new ArrayList<Integer>();
		for(int i=0;i<num;i++) {
			weights.add(scan.nextInt());
		}
		int id=1;//Container标号
		boolean overflow=false;//是否存在重量大于10的object
		while(!weights.isEmpty()) {//当object列表不空的情况下
			int capacity=10;//container初始容量为10
			boolean fitIn=false;//尚未找到第一个可以放入container的object
			for(int i=0;i<weights.size();i++) {//遍历objects
				if(weights.get(i)<=capacity) {//找到可以放入container的object
					if(!fitIn) {//该object是第一个放入该container的object
						System.out.print("Container "+id+" contains objects with weight ");//打印文字信息
						fitIn=true;//表明当前container中至少存在一个object，当前container的文字信息已经打印过了
						id++;//指向下一个待打印的container
					}
					System.out.print(weights.get(i)+" ");//打印object的重量
					capacity-=weights.get(i);//在列表中去除已经放入container的object
					weights.remove(i--);
					//让标号i指向被remove的元素的前一个元素，这样下一次循环将遍历到被remove的元素的后一个元素
				}
				else if(weights.get(i)>10){//如果存在单个的container无法容纳的object
					overflow=true;//将标志位置为true
					weights.remove(i--);//将object移除
				}
			}
			if(fitIn) {
				//完成对objects的一次遍历后，检查container中是否放入了objects，如果放入了objects
				System.out.print('\n');//打印回车，完成一行信息的输出
			}
		}
		if(overflow) {//如果存在无法放入container的object，打印提示信息
			System.out.println("There is(are) object(s) that can not be held by containers.");
		}
	}
}
