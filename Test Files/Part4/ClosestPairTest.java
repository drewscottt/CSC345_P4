import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClosestPairTest {
    private static Grid grid;
    private static ClosestPair cp;
    private static String fileName;
    private static BufferedReader br;
    private static int testNum;
    private static double ptsPoss;

    public static void main(String[] args) {
	//Tests 1-4 use a basic grid
	fileName = "basic_grid_3.txt";
	double score = 0.0;

	//Test 1
	set_cp();
	testNum = 1;
	ptsPoss = 1.0;
	score += test(0, 0, 4, 3);
	score += testAccessCount(86);
	//Test 2
	set_cp();
	testNum++;
	score += test(1, 4, 3, 0);
	score += testAccessCount(126);
	//Test 3
	set_cp();
	testNum++;
	score += test(6, 4, -1, -1);
	score += testAccessCount(169);
	//Test 4
	set_cp();
	testNum++;
	score += test(1, 0, 2, 5);
	score += testAccessCount(73);

	//Tests 5-9 use large grid
	fileName = "large_grid_3.txt";
	ptsPoss = 1.5;
	//Test 5
	set_cp();
	testNum++;
	score += test(0, 0, 3, 2);
	score += testAccessCount(43);

	//Test 6
	set_cp();
	testNum++;
	score += test(10, 39, 1, 37);
	score += testAccessCount(736);

	//Test 7
	set_cp();
	testNum++;
	score += test(49, 0, -1, -1);
	score += testAccessCount(9801);

	//Test 8
	set_cp();
	testNum++;
	score += test(29, 18, 25, 17);
	score += testAccessCount(105);

	//Test 9
	set_cp();
	testNum++;
	ptsPoss = 1.0;
	score += test(-1, -1, -1, -1);
	score += testAccessCount(0);
    }

    public static void set_cp() {
	grid = new Grid(fileName, false);
	cp = new ClosestPair(grid);
    }

    public static double test(int x_i, int y_i, int x_o, int y_o) {
	System.out.println("\n***** BEGIN TEST " + testNum + "*****");
	Loc loc = cp.search(x_i, y_i); 
	if(x_o < 0 || y_o < 0) { 
	    if(loc == null) {
		printMsg(true, x_o, y_o, 0, 0);
		return ptsPoss;
	    }
	    else { 
		printMsg(false, x_o, y_o, loc.row, loc.col);
	    }
	} else {
	    if(loc != null && loc.row == x_o && loc.col == y_o) {
		printMsg(true, x_o, y_o, loc.row, loc.col);
		return ptsPoss;
	    } else {
		printMsg(false, x_o, y_o, loc.row, loc.col);
	    }
	}
	return 0.0;
    }

    private static double testAccessCount(int threshold) {
	System.out.println("Checking access count...");
	int c = grid.getAccessCount();
	if(c < 0) {
	    System.out.println("Access count < 0? Something isn't right...");
	    return 0.0;
	} if(c > threshold) {
	    System.out.println("Access count (" + c + ") is higher than the threshold (" + threshold + ").");
	    return 0.0;
	}
	System.out.println("Access count looks good!");
	return 1.0;
    }

    private static void printMsg(boolean passed, int xx, int xy, int ax, int ay) {
	if(passed)
	    System.out.println("Test " + testNum + " passed accuracy check!");
	else {
	    System.out.println("Test " + testNum + " failed accuracy check!");
	    System.out.println("Expected: (" + xx + ", " + xy + ")");
	    System.out.println("Actual: (" + ax + ", " + ay + ")");
	}
    }
}
    
	
