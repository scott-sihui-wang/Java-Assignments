import java.util.ArrayList;
public class MinStack {
	private ArrayList<Comparable> list=new ArrayList<Comparable>();
	private ArrayList<Comparable> minList=new ArrayList<Comparable>();
	public boolean isEmpty() {
		return list.isEmpty();
	}
	public int getSize() {
		return list.size();
	}
	public Comparable peak() {
		return list.get(getSize()-1);
	}
	public Comparable pop() {
		Comparable o=list.get(getSize()-1);
		list.remove(getSize()-1);
		minList.remove(minList.size()-1);
		return o;
	}
	public void push(Comparable o) {
		list.add(o);
		if(minList.isEmpty()) {
			minList.add(o);
		}
		else if(o.compareTo(minList.get(minList.size()-1))<0){
			minList.add(o);
		}
		else {
			minList.add(minList.get(minList.size()-1));
		}
	}
	public Comparable getMin() {
		return minList.get(minList.size()-1);
	}
	public String toString() {
		return "stack: "+list.toString();
	}
	public static void main(String args[]) {
		MinStack minStack=new MinStack();
		minStack.push(-2);
		minStack.push(0);
		minStack.push(-3);
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.peak());
		System.out.println(minStack.getMin());
	}
}
