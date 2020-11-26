public class Part1Test {
    public static void main(String[] args) {
	//Test 1
	int testNum = 1;
	int gridNum = 1;
	long exp = 448L;
	int accessCount = 288;
	int m = 3;
	runTest(testNum, gridNum, m, exp, accessCount);
	//Test 2
	testNum++;
	exp = 7840L;
	accessCount = 192;
	m = 5;
	runTest(testNum, gridNum, m, exp, accessCount);
	//Test 3
	testNum++;
	gridNum++;
	m = 4;
	exp = 4536L;
	accessCount = 1344;
	runTest(testNum, gridNum, m, exp, accessCount);
	//Test 4
	testNum++;
	m = 8;
	exp = 3528000L;
	accessCount = 1034;
	runTest(testNum, gridNum, m, exp, accessCount);
	//Test 5                                                             
	testNum++;
	gridNum++;
	m = 4;
	exp = 5184L;
	accessCount = 6435;
	runTest(testNum, gridNum, m, exp, accessCount);
	//Test 6                                                                 
	testNum++;
        m = 9;
        exp = 26127360L;
	accessCount = 6246;
        runTest(testNum, gridNum, m, exp, accessCount);
	//Test 7                                                                    
	testNum++;
        gridNum++;
        m = 5;
        exp = 52488L;
	accessCount = 27854;
        runTest(testNum, gridNum, m, exp, accessCount);
        //Test 8                                                                    
	testNum++;
        m = 10;
        exp = 694416240L;
	accessCount = 30033;
        runTest(testNum, gridNum, m, exp, accessCount);
	//Test 9                                                                  
	testNum++;
        gridNum++;
        m = 6;
        exp = 419904L;
	accessCount = 119008;
        runTest(testNum, gridNum, m, exp, accessCount);
        //Test 10
	testNum++;
        m = 11;
        exp = 3809369088L;
	accessCount = 135288;
        runTest(testNum, gridNum, m, exp, accessCount);
    }

    private static void runTest(int testNum, int gridNum, int m, long exp, int acount) {
	System.out.println("***** BEGIN TEST " + testNum + " *****");
	String fn = "p1testGrid" + gridNum + ".txt";
	Grid G = new Grid(fn, false);
	long max = Part1.getMaxProd(G, m);
	printMsg1(testNum, max, exp);
	int accessCount = G.getAccessCount();
	printMsg2(testNum, accessCount, acount);
    }

    private static void printMsg1(int testNum, long act, long exp) {
	if(act == exp) {
	    System.out.println("Test " + testNum + " passed!\n");
	} else {
	    System.out.println("Test " + testNum + " failed.");
	    System.out.println("Expected: " + exp);
	    System.out.println("Actual: " + act + "\n");
	}
    }

    private static void printMsg2(int testNum, long act, long exp) {
	if(act <= exp) {
	    System.out.println("Test " + testNum + " runtime test passed!\n");
	} else {
	    System.out.println("Test " + testNum + " runtime test failed.");
	    System.out.println("Access count should be <= " + exp);
	    System.out.println("Your access count: " + act);
	}
    }
}
