import java.util.Vector;


public class SumVectorTh extends Thread{
	public Vector<Integer> a,b;
	public int inc = 1;
	public int start = 0;
	
	public SumVectorTh() {}
	
	public SumVectorTh(int i, int s, Vector<Integer> c,Vector<Integer> d) {
		a = c;
		b = d;
		this.inc = i;
		start = s;
	}
	
	public void run() {
		for(int i = start; i < a.size(); i = i+inc) {
			a.set(i, a.get(i) + b.get(i));
		}
	}
	
	
}
