import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SequenceTest {
    private static Grid grid;
    private static Sequence seq;
    private static String fileName;
    private static BufferedReader br;
    private static int testNum;
    private static double ptsPoss;

    public static void main(String[] args) {
	//Tests 1-7 use a basic grid
	fileName = "basic_grid_1.txt";
	double score = 0.0;

	//Test 1
	set_seq();
	testNum = 1;
	ptsPoss = 1.0;
	score += test(0, 0, 9);
	score += testAccessCount(56);
	//Test 2
	set_seq();
	testNum++;
	score += test(2, 6, 9);
	score += testAccessCount(57);
	//Test 3
	set_seq();
	testNum++;
	score += test(2, 6, 10);
	score += testAccessCount(84);
	//Test 4
	set_seq();
	testNum++;
	score += test(6, 6, 5);
	score += testAccessCount(4);
	//Test 5
	set_seq();
	testNum++;
	score += test(3, 4, 9);
	score += testAccessCount(1);
	//Test 6
	set_seq();
	testNum++;
	score += test(4, 0, 2);
	score += testAccessCount(1);
	//Test 7
	set_seq();
	testNum++;
	score += test(7, 7, 9);
	score += testAccessCount(0);
	//Tests 8 & 9 use large grid
	fileName = "large_grid_1.txt";

	//Test 8
	set_seq();
	testNum++;
	ptsPoss = 2.0;
	score += test(14, 23, 200);
	score += testAccessCount(343);
	//Test 9
	set_seq();
	testNum++;
	score += test(32, 0, 196);
	score += testAccessCount(81);
    }

    private static double testAccessCount(int threshold) {
	System.out.println("Checking access count...");
	int count = grid.getAccessCount();
	if(count < 0) {
	    System.out.println("Access count < 0? Something isn't right...");
	    return 0.0;
	}
	if(count <= threshold) {
	    System.out.println("Looks good!");
	    return 1.0;
	}
	System.out.println("Access count (" + count + ") is higher than the threshold (" + threshold + ")!");
	return 0.0;
    }

    public static void set_seq() {
	grid = new Grid(fileName, false);
	seq = new Sequence(grid);
    }

    public static double test(int x, int y, int v) {
	System.out.println("***** BEGIN TEST " + testNum + " *****");
	runSeq(x, y, v);
	String exp = "";
	if(testNum == 1 || testNum == 2 || testNum == 8) {
	    exp = getExpected(testNum);
	} else if(testNum == 5) {
	    exp = (new Loc(x, y, "" + v + "")).toString();
	}
	if(!seq.toString().equals(exp)) 
	    printMsg(false, exp, seq.toString());
	else {
	    printMsg(true, null, null);
	    return ptsPoss;
	}
	return 0.0;
    }

    private static String getExpected(int test) {
	String fn = "test" + test + "_output.txt";
	String line = null;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    line = br.readLine();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return line;
    }
   
    private static void printMsg(boolean passed, String exp, String act) {
	if(passed)
	    System.out.println("Test " + testNum + " passed!");
	else {
	    System.out.println("Test " + testNum + " failed!");
	    System.out.println("Expected: " + exp);
	    System.out.println("Actual: " + act);
	}
    }

    private static void runSeq(int x, int y, int v) {
	seq.getSeq(x, y, v);
    }			   
}
    
	
