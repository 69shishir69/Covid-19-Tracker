package prac1;

import java.util.Arrays;
public class Week4Q1 {
	// Function to print required answer
	public static void ClosestCoordinates(int[][] pts, int[] target, int k) {
		int n = pts.length;
		int[] distance = new int[n];
		int x1 = target[0];
		int y1 = target[1];
		for (int i = 0; i < n; i++) {
			int x2 = pts[i][0], y2 = pts[i][1];
			distance[i] = (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
			;
		}
		Arrays.sort(distance);
		// Find the k-th distance
		int distk = distance[k - 1];// the closest three coordinates from provided target coordinate
		System.out.print("{");
		for (int i = 0; i < n; i++) {
			int x2 = pts[i][0], y2 = pts[i][1];
			int dist = (int) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
			if (dist <= distk) {
				System.out.print("{" + x2 + ", " + y2 + "}");
				if (dist <= distk - 1) {
					System.out.print(",");
				}
			}
		}
		System.out.print("}");
	}
	public static void main(String[] args) {
		int[][] points = { { 2, 3 }, { 5, 8 }, { 3, 4 }, { 5, 7 } };
		int[] target = { 3, 4 };
		ClosestCoordinates(points, target, 3);
	}
}