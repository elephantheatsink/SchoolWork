package stddev;
import java.util.*;
public class stddev {
	static double sum;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner kb = new Scanner(System.in);
		System.out.println("xi = ");
		sum=0;
		double avg = 0;
		double xi = 0;
		double sq = 0;
		while(kb.hasNextDouble()) {
			
			xi = kb.nextDouble();
			sum = sum + (xi * xi);
			System.out.println("xi = ");
		}
		System.out.print(sum);
		

	}

}
