public class PuzzleTest2 {
    public static void main(String[] args) {
	Grid g = new Grid("puzzle2_test.txt", true);
	Puzzle p = new Puzzle(g);
	String[] words = new String []
	    {"APPLE", "BANANA", "CANTALOUPE", "DURIAN",
	     "BLUEBERRY", "KIWI", "LYCHEE", "MANGO",
	     "MULBERRY", "ORANGE", "PEACH", "PEAR",
	     "QUINCE", "RASPBERRY", "STRAWBERRY", 
	     "TANGERINE", "WATERMELON", "PINEAPPLE",
	     "KUMQUAT", "LOQUAT", "PAPAYA", "POMEGRANATE",
	     "CHERRY", "BLACKBERRY", "CHIRIMOYA"};
	int[] startX = new int[] 
	    {1, 2, 1, 21, 23, 22, 6, 22, 12, 11, 21,
	     21, 5, 6, 12, 23, 7, 1, 14, 8, 3, 24,
	     7, 10, 14};
	int[] startY = new int[] 
	    {16, 2, 6, 8, 9, 23, 24, 11, 3, 9, 23,
	     16, 20, 14, 12, 0, 0, 20, 1, 18, 24,
	     24, 11, 1, 21};
	int[] endX = new int[] 
	    {1, 2, 10, 21, 15, 22, 11, 18, 12, 16,
	     17, 18, 10, 14, 12, 15, 7, 1, 8, 8,
	     3, 14, 2, 19, 14};
	int[] endY = new int[]
	    {12, 7, 15, 13, 9, 20, 24, 15, 10, 4,
	     19, 19, 15, 14, 21, 8, 9, 12, 7, 23,
	     19, 24, 16, 1, 13};
	
	for(int i = 0; i < words.length; i++) 
	    findWord(p, words[i], startX[i], 
			      startY[i], endX[i], endY[i]);
	int c = g.getAccessCount();
	if(c <= 2920) {
	    System.out.println("Access count looks good!");
	} else {
	    System.out.println("Access count (" + c + ") is higher than the threshold (2920).");
	}
    }
    
    private static void findWord(Puzzle p, String word, int startX,
				int startY, int endX, int endY) {
	Loc[] search = p.search(word);
	if(search == null) {
	    System.out.println("Did not find " +  word + ".");
	    return;
	}
	if(startX == search[0].row && startY == search[0].col &&
	   endX == search[1].row && endY == search[1].col) {
	    System.out.println("Found " + word + "!");
	} else {
	    System.out.println("Did not find " + word + ".");
	}
    }
}
