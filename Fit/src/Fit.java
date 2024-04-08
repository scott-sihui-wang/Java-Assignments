import java.util.ArrayList;
import java.util.Scanner;
public class Fit {
	public static void main(String args[]) {
		Scanner scan=new Scanner(System.in);
		System.out.print("Enter the number of objects:");//����objects����
		int num=scan.nextInt();
		System.out.print("Enter the weights of the objects:");//����ÿ��object������
		ArrayList<Integer>weights=new ArrayList<Integer>();
		for(int i=0;i<num;i++) {
			weights.add(scan.nextInt());
		}
		int id=1;//Container���
		boolean overflow=false;//�Ƿ������������10��object
		while(!weights.isEmpty()) {//��object�б��յ������
			int capacity=10;//container��ʼ����Ϊ10
			boolean fitIn=false;//��δ�ҵ���һ�����Է���container��object
			for(int i=0;i<weights.size();i++) {//����objects
				if(weights.get(i)<=capacity) {//�ҵ����Է���container��object
					if(!fitIn) {//��object�ǵ�һ�������container��object
						System.out.print("Container "+id+" contains objects with weight ");//��ӡ������Ϣ
						fitIn=true;//������ǰcontainer�����ٴ���һ��object����ǰcontainer��������Ϣ�Ѿ���ӡ����
						id++;//ָ����һ������ӡ��container
					}
					System.out.print(weights.get(i)+" ");//��ӡobject������
					capacity-=weights.get(i);//���б���ȥ���Ѿ�����container��object
					weights.remove(i--);
					//�ñ��iָ��remove��Ԫ�ص�ǰһ��Ԫ�أ�������һ��ѭ������������remove��Ԫ�صĺ�һ��Ԫ��
				}
				else if(weights.get(i)>10){//������ڵ�����container�޷����ɵ�object
					overflow=true;//����־λ��Ϊtrue
					weights.remove(i--);//��object�Ƴ�
				}
			}
			if(fitIn) {
				//��ɶ�objects��һ�α����󣬼��container���Ƿ������objects�����������objects
				System.out.print('\n');//��ӡ�س������һ����Ϣ�����
			}
		}
		if(overflow) {//��������޷�����container��object����ӡ��ʾ��Ϣ
			System.out.println("There is(are) object(s) that can not be held by containers.");
		}
	}
}
