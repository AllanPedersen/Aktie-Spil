import java.util.ArrayList;


public class Tester implements Runnable {

	private int upto = 300;
	private ArrayList<ServerConnector> scs = new ArrayList<ServerConnector>();
	private int sleep = 200;

	public static void main(String[] args) {
		new Tester();
	}

	public Tester() {
		new Thread(this).start();
	}

	@Override
	public void run() {
		for (int i = 0; i <= upto; i++) {
			System.out.println("Initiate: " + (i+1));
			if (i % 100 == 0 && i != 0) {
				//this.sleep = this.sleep + 100;
			}

			ServerConnector sc = new ServerConnector("Connection: " + (i+1));
			this.scs.add(sc);
			try {
				System.out.println("Sleep: " + this.sleep);
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Calculating results...");
		calculate();
		System.exit(0);
	}

	public void calculate() {
		System.out.println("+------------------------------+");
		System.out.println("| CONNECTION  |  AV. DIFF TIME |");
		System.out.println("+------------------------------+");

		long mills = 0;
		int from = 0;
		int to = 0;
		for (int i = 1; i <= upto; i++) {

			if (i == 1) {
				from = 1;
				to = 1;
				mills = scs.get(i).getDiff();
			} else { 
				if (i % 50 == 0) {
					printResult(from + " - " + to, (mills / (to-from)) + "");
					mills = scs.get(i).getDiff();
					from = i;
				} else {
					mills += scs.get(i).getDiff();
				}
			}

			to++;
		}


	}

	public void printResult(String interval, String time) {
		String placeholder = "|%12s | %14s |";
		String format = String.format(placeholder, interval, time);
		System.out.println(format);
		System.out.println("+------------------------------+");
	}

}
