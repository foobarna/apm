import java.util.Vector;


public class TheMain {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Vector<Integer> a = new Vector<Integer>();
		Vector<Integer> b = new Vector<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(4);
		a.add(5);
		a.add(6);
		a.add(7);
		a.add(8);
		a.add(9);
		b.add(10);
		b.add(10);
		b.add(10);
		b.add(10);
		b.add(10);
		b.add(10);
		b.add(10);
		b.add(10);
		b.add(10);
		SumVectorTh th = new SumVectorTh(2,0,a,b);
		SumVectorTh th2 = new SumVectorTh(2,1,a,b);
		th.start();
		th2.start();
		th2.join();
		th.join();
		System.out.println(a);

	}

}
