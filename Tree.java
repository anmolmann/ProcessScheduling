import java.util.Comparator;

class Process{
	int pid;
	String pName;
	Process(String pName, int pid) {
		this.pid = pid;
		this.pName = pName;
	}
	public String toString() {
		return this.pName + ' ' + this.pid;
	}
}

class Priority {
	int avgTime;
	int medTime;
	Priority(int avgTime, int medTime) {
		this.avgTime = avgTime;
		this.medTime = medTime;
	}
	public int getAvgTime() {
		return this.avgTime;
	}
	public int getMedTime() {
		return this.medTime;
	}
}

class ByMed implements Comparator {
	public int compare(Object o1, Object o2) {
		Priority p1 = (Priority) o1;
		Priority p2 = (Priority) o2;
		if(p1.getMedTime() < p2.getMedTime()) return -1;
		else if(p1.getMedTime() > p2.getMedTime()) return 1;
		return 0;
	}
}

class ByAvg implements Comparator {
	public int compare(Object o1, Object o2) {
		Priority p1 = (Priority) o1;
		Priority p2 = (Priority) o2;
		if(p1.getAvgTime() < p2.getAvgTime()) return -1;
		else if(p1.getAvgTime() > p2.getAvgTime()) return 1;
		return 0;
	}
}

public class Tree {
	
	class Node {
		Process value;
		Priority key;
		Node left;
		Node right;
		Node(Process value, Priority key) {
			this.value = value;
			this.key = key;
			this.left = null;
			this.right = null;
		}
	}
	
	private Node root = null;
	
	public void insert(Process value, Priority key, Comparator c) {
		root = insert(root, value, key, c);
	}
	
	private Node insert(Node n, Process value, Priority key, Comparator c) {
		if(n == null) return new Node(value, key);
		if(c.compare(key, n.key) < 0) n.left = insert(n.left, value, key, c);
		else if(c.compare(key, n.key) > 0) n.right = insert(n.right, value, key, c);
		else n.value = value;
		return n;
	}
	
	public void print() {
		if(root == null) return;
		print(root);
	}
	
	private void print(Node n) {
		if(n == null) return;
		print(n.left);
		System.out.println(n.value);
		print(n.right);
	}
	
	public static void main(String args[]) {
		Process p1 = new Process("Process1", 1);
		Process p2 = new Process("Process2", 2);
		Process p3 = new Process("Process3", 3);
		
		Priority pt1 = new Priority(5, 1);
		Priority pt2 = new Priority(1, 3);
		Priority pt3 = new Priority(3, 2);
		
		Tree t = new Tree();
		
		Comparator c = new ByAvg();
		
		t.insert(p1, pt1, c);
		t.insert(p2, pt2, c);
		t.insert(p3, pt3, c);
		
		t.print();
	}
}
