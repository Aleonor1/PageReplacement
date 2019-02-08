package bankersalgorithm;

import java.util.Scanner;

public class Bankers {
	private int need[][], allocate[][], max[][], avail[][], np, nr;

	private void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter no. of processes: ");
		np = sc.nextInt();
		nr = 1; 
		need = new int[np][nr]; 
		max = new int[np][nr];
		allocate = new int[np][nr];
		avail = new int[1][nr];

		System.out.println("Enter allocation matrix -->");
		for (int i = 0; i < np; i++)
			for (int j = 0; j < nr; j++)
				allocate[i][j] = sc.nextInt(); // allocation matrix

		System.out.println("Enter max matrix -->");
		for (int i = 0; i < np; i++)
			for (int j = 0; j < nr; j++)
				max[i][j] = sc.nextInt(); // max matrix

		System.out.println("Enter available matrix -->");
		for (int j = 0; j < nr; j++)
			avail[0][j] = 0;

		sc.close();
	}

	private int[][] calc_need() {
		for (int i = 0; i < np; i++)
			for (int j = 0; j < nr; j++) // calculating need matrix
				need[i][j] = max[i][j] - allocate[i][j];

		return need;
	}

	private boolean check(int i) {
		// checking if all resources for ith process can be allocated
		for (int j = 0; j < nr; j++)
			if (avail[0][j] < need[i][j])
				return false;

		return true;
	}

	public void isSafe(int a) {
		//input();
		avail[0][0] = a;
		int aux = avail[0][0];
		calc_need();
		boolean done[] = new boolean[np];
		int j = 0;

		while (j < np) {
			boolean allocated = false;
			for (int i = 0; i < np; i++)
				if (!done[i] && check(i)) {
					for (int k = 0; k < nr; k++)
						avail[0][k] = avail[0][k] - need[i][k] + max[i][k];
					System.out.println("Allocated process : " + i);
					allocated = done[i] = true;
					j++;
				}
			if (!allocated)
				break; // if no allocation
		}
		if (j == np) // if all processes are allocated
		{
			System.out.println("\nSafely allocated");
			System.out.println(aux);
			System.exit(0);
		} else
			System.out.println("All proceess cant be allocated safely");
	}

	public static void main(String[] args) {
		Bankers a = new Bankers();
		a.input();
		for (int i=0;i<10000;i++) {
			a.isSafe(i);
		}
	}
}