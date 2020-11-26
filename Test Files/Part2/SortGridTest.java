import java.util.*;
import java.io.*;

public class SortGridTest {
    private static int[] c1 = new int[]{323, 1878, 9896, 49160, 235144, 386726};
    private static int[] c2 = new int[]{571, 8944, 157442, 2526352, 40397134, 98631144};
    private static int[] c3 = new int[]{663, 10178, 160772, 2563148, 40972638, 100019980};
    
    public static void main(String[] args) {
	double score = 0;
	
	for(int testNum = 1; testNum <= 6; testNum++) 
	    score += test(testNum);
	System.out.println("SortGrid Total: " + score);
    }

    private static double test(int testNum) {
	double score = 0.0;
	System.out.println("***** BEGIN TEST " + testNum + "*****");
	Grid grid = new Grid("testGrid" + testNum + ".txt", false);
	SortGrid.sort(grid);
	score += checkAccuracy(grid);
	score += checkAccessCount(grid.getAccessCount(), c1[testNum-1], c2[testNum-1], c3[testNum-1]);
	return score;
    }
	
    private static double checkAccessCount(int c, int c1, int c2, int c3) {
	System.out.println("Checking access count...");
	double score = 0.0;
	System.out.println(c + "    "  + c1 + "    "  + c2);
	if(c <= 0) {
	    System.out.println("Access count <= 0? Something isn't right...");
	    return 0;
	}    
	if(c <= c1) {
	    System.out.println("Access count is below the first cutoff!");
	    return 1.5;
	}
	else if(c <= c2) {
	    System.out.println("Access count is below the second cutoff.");
	    score = 1.0;
	}
	else if(c <= c3) {
	    System.out.println("Access count is below the third cutoff.");
	    score = 0.5;
	}
	else {
	    System.out.println("Access count is too high...");
	    score = 0.0;
	}
	return score;
    }

    private static double checkAccuracy(Grid grid) {
	//System.out.println(grid);
	if(grid.isSorted()) {
	    System.out.println("The grid is sorted!");
	    return 1.0;
	}
	System.out.println("The grid is not sorted. Use the toString method to print the grid before and after sorting.");
	return 0.0;
    }
}
 
