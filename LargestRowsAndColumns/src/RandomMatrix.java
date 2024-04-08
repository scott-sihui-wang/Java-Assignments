import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class RandomMatrix{
	public ArrayList<Integer> mat;//保存随机0-1矩阵
	private int length;//保存方阵size
	private ArrayList<Integer> row,col;//统计每行、每列1的个数
	public RandomMatrix(int n) {//构造函数
		mat=new ArrayList<Integer>();
		length=n;
		Random r=new Random();
		row=new ArrayList<Integer>();
		col=new ArrayList<Integer>();
		for(int i=0;i<length;i++) {
			row.add(0);
			col.add(0);
		}
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				mat.add(r.nextInt(2));//为第i+1行，第j+1列元素随机赋值
				if(mat.get(i*length+j)==1) {
					row.set(i, row.get(i)+1);//更新相应行的1的个数的统计
					col.set(j, col.get(j)+1);//更新相应列的1的个数的统计
				}
			}
		}
	}
	public void toString(String note) {//打印随机矩阵
		System.out.println(note);//打印提示语
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				System.out.print(mat.get(i*length+j));//打印第i行，第j列元素
			}
			System.out.print('\n');//每行结束打印回车
		}
	}
	public void findMax() {//找到含1最多的行与列，并打印相应信息
		int currentMaxRow=0;//检索到目前为止，1最多的行的1的个数
		int currentMaxCol=0;//检索到目前为止，1最多的列的1的个数
		ArrayList<Integer>rowMax=new ArrayList<Integer>();
		//1最多的行的行标号
		ArrayList<Integer>colMax=new ArrayList<Integer>();
		//1最多的列的列标号
		for(int i=0;i<length;i++) {
			if(row.get(i)>currentMaxRow) {//如果找到1更多的行
				currentMaxRow=row.get(i);//更新1最多的行的1的个数的值
				rowMax.clear();//将1最多的行的标号清空
				rowMax.add(i+1);//将当前行标号加入
			}
			else if(row.get(i)==currentMaxRow) {//当前行的1的个数与1最多的行的1的个数相等
				rowMax.add(i+1);//将当前行标号加入行标号列表
			}
			if(col.get(i)>currentMaxCol) {//对列进行统计，原理与行相同
				currentMaxCol=col.get(i);
				colMax.clear();
				colMax.add(i+1);
			}
			else if(col.get(i)==currentMaxCol) {
				colMax.add(i+1);
			}
		}
		System.out.print("The largest row index:");//打印信息
		for(int i=0;i<rowMax.size()-1;i++) {
			System.out.print(rowMax.get(i)+",");//除最后一个元素外，中间用逗号隔开
		}
		System.out.println(rowMax.get(rowMax.size()-1));//单独打印最后一个元素
		System.out.print("The largest column index:");
		for(int i=0;i<colMax.size()-1;i++) {
			System.out.print(colMax.get(i)+",");
		}
		System.out.println(colMax.get(colMax.size()-1));
	}
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.print("Enter the array size n:");
		int l=scan.nextInt();
		RandomMatrix m=new RandomMatrix(l);
		m.toString("The random array is");
		m.findMax();
	}
}
