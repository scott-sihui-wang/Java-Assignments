import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class RandomMatrix{
	public ArrayList<Integer> mat;//�������0-1����
	private int length;//���淽��size
	private ArrayList<Integer> row,col;//ͳ��ÿ�С�ÿ��1�ĸ���
	public RandomMatrix(int n) {//���캯��
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
				mat.add(r.nextInt(2));//Ϊ��i+1�У���j+1��Ԫ�������ֵ
				if(mat.get(i*length+j)==1) {
					row.set(i, row.get(i)+1);//������Ӧ�е�1�ĸ�����ͳ��
					col.set(j, col.get(j)+1);//������Ӧ�е�1�ĸ�����ͳ��
				}
			}
		}
	}
	public void toString(String note) {//��ӡ�������
		System.out.println(note);//��ӡ��ʾ��
		for(int i=0;i<length;i++) {
			for(int j=0;j<length;j++) {
				System.out.print(mat.get(i*length+j));//��ӡ��i�У���j��Ԫ��
			}
			System.out.print('\n');//ÿ�н�����ӡ�س�
		}
	}
	public void findMax() {//�ҵ���1���������У�����ӡ��Ӧ��Ϣ
		int currentMaxRow=0;//������ĿǰΪֹ��1�����е�1�ĸ���
		int currentMaxCol=0;//������ĿǰΪֹ��1�����е�1�ĸ���
		ArrayList<Integer>rowMax=new ArrayList<Integer>();
		//1�����е��б��
		ArrayList<Integer>colMax=new ArrayList<Integer>();
		//1�����е��б��
		for(int i=0;i<length;i++) {
			if(row.get(i)>currentMaxRow) {//����ҵ�1�������
				currentMaxRow=row.get(i);//����1�����е�1�ĸ�����ֵ
				rowMax.clear();//��1�����еı�����
				rowMax.add(i+1);//����ǰ�б�ż���
			}
			else if(row.get(i)==currentMaxRow) {//��ǰ�е�1�ĸ�����1�����е�1�ĸ������
				rowMax.add(i+1);//����ǰ�б�ż����б���б�
			}
			if(col.get(i)>currentMaxCol) {//���н���ͳ�ƣ�ԭ��������ͬ
				currentMaxCol=col.get(i);
				colMax.clear();
				colMax.add(i+1);
			}
			else if(col.get(i)==currentMaxCol) {
				colMax.add(i+1);
			}
		}
		System.out.print("The largest row index:");//��ӡ��Ϣ
		for(int i=0;i<rowMax.size()-1;i++) {
			System.out.print(rowMax.get(i)+",");//�����һ��Ԫ���⣬�м��ö��Ÿ���
		}
		System.out.println(rowMax.get(rowMax.size()-1));//������ӡ���һ��Ԫ��
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
