public class PuzzleTest1 {
    public static void main(String[] args) {
	Grid g = new Grid("puzzle1_test.txt", true);
	Puzzle p = new Puzzle(g);
	String[] words = new String []
	    {"RED", "ORANGE", "YELLOW",
	     "GREEN", "BLUE", "PURPLE",
	     "VIOLET", "PINK", "WHITE",
	     "BLACK", "BROWN", "GRAY",
	     "LILAC", "TEAL", "FUCHSIA"};
	int[] startX = new int[] 
	    {1, 4, 12, 6, 10, 9, 3, 4, 6,
	     10, 0, 10, 9, 3, 8};
	int[] startY = new int[] 
	    {14, 2, 6, 1, 9, 7, 8, 13, 12,
	     9, 11, 12, 9, 3, 0};
	int[] endX = new int[] 
	    {3, 9, 7, 2, 7, 9, 3, 4, 2, 10,
	     4, 13, 5, 0, 2};
	int[] endY = new int[]
	    {14, 2, 11, 5, 9, 2, 3, 10, 12,
	     5, 11, 9, 5, 6, 0};
	
	for(int i = 0; i < words.length; i++) 
	    findWord(p, words[i], startX[i], 
			      startY[i], endX[i], endY[i]);	
	int c = g.getAccessCount();
	if(c <= 686) 
	    System.out.println("Access count looks good!");
	else
	    System.out.println("Access count (" + c + ") is higher than the threshold (686).");
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
