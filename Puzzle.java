import java.util.HashMap;
import java.util.Arrays;
public class Puzzle {

	private Grid g;

	public Puzzle(Grid grid) {
		this.g = grid;
	}

	public Loc[] search(String word) {
		Loc[] rowLoc = this.searchRows(word);
		if (rowLoc[1] != null)
			return rowLoc;
	
		Loc[] colLoc = this.searchCols(word);
		if (colLoc[1] != null)
			return colLoc;	

		Loc[] leftDiagLoc = this.searchLeftDiags(word);
		if (leftDiagLoc[1] != null)
			return leftDiagLoc;
		
		Loc[] rightDiagLoc = this.searchRightDiags(word);
		if (rightDiagLoc[1] != null) 
			return rightDiagLoc;
		
		return null;
	}

	private Loc[] searchRightDiags(String word) {
		Loc[] loc = new Loc[2];

		int maxDiff = g.size() - word.length();
                for (int diff = -maxDiff; diff <= maxDiff; diff++) {
                       	int firstRow;
			int firstCol;
			int minRowCol = 0;

			if (diff < 0) {
				firstRow = g.size() + diff - 1;
				firstCol = 0;
			} else {
				firstRow = g.size() - 1;
				firstCol = diff;
			}
			
			String rowString = "";
			for (int i = 0; i < g.size() - Math.abs(diff); i++) {
				rowString += g.getVal(firstRow - i, firstCol + i); 
			}			

			int startIndPN = boyerMoore(rowString, word);
                        if (startIndPN != Integer.MIN_VALUE) {
				int startInd = Math.abs(startIndPN);
				Loc loc1 = g.getLoc(firstRow - startInd, firstCol + startInd);
				Loc loc2 = g.getLoc(firstRow - startInd - word.length() + 1, firstCol + startInd + word.length() - 1);

				if (startIndPN >= 0) {
					loc[0] = loc1;
					loc[1] = loc2;
				} else {
					loc[0] = loc2;
					loc[1] = loc1;
				}
			        break;
                        }
        	}

		return loc;
	}
 
	private Loc[] searchLeftDiags(String word) {
		Loc[] loc = new Loc[2];

		int maxDiff = g.size() - word.length();
                for (int diff = -maxDiff; diff <= maxDiff; diff++) {
                       	int firstRow;
			int firstCol;
			int minRowCol = 0;

			if (diff < 0) {
				firstRow = -diff;
				firstCol = 0;
			} else {
				firstRow = 0;
				firstCol = diff;
			}
			
			String rowString = "";
			for (int i = 0; i < g.size() - Math.abs(diff); i++) {
				rowString += g.getVal(firstRow + i, firstCol + i); 
			}			

			int startIndPN = boyerMoore(rowString, word);
                        if (startIndPN != Integer.MIN_VALUE) {
				int startInd = Math.abs(startIndPN);
				Loc loc1 = g.getLoc(firstRow + startInd, firstCol + startInd);
				Loc loc2 = g.getLoc(firstRow + startInd + word.length() - 1, firstCol + startInd + word.length() - 1);

				if (startIndPN >= 0) {
					loc[0] = loc1;
					loc[1] = loc2;
				} else {
					loc[0] = loc2;
					loc[1] = loc1;
				}

                                break;
                        }
        	}

		return loc;
	}


	private Loc[] searchCols( String word) {
		Loc[] loc = new Loc[2];
	
		for (int col = 0; col < g.size(); col++) {
			String rowString = "";
			for (int row = 0; row < g.size(); row++) {
				rowString += g.getVal(row, col);
			}		
			
			int startIndPN = boyerMoore(rowString, word);
			if (startIndPN != Integer.MIN_VALUE) {
				int startInd = Math.abs(startIndPN);
				Loc loc1 = g.getLoc(startInd, col);
				Loc loc2 = g.getLoc(startInd + word.length() - 1, col);
				if (startIndPN >= 0) {
					loc[0] = loc1;
					loc[1] = loc2;
				} else {
					loc[0] = loc2;
					loc[1] = loc1;
				}
				
				break;
			}
		}

		return loc;
        }

	private Loc[] searchRows(String word) {
		Loc[] loc = new Loc[2];
	
		for (int row = 0; row < g.size(); row++) {
			String rowString = "";
			for (int col = 0; col < g.size(); col++) {
				rowString += g.getVal(row, col);
			}		
	
			int startIndPN = boyerMoore(rowString, word);
			if (startIndPN != Integer.MIN_VALUE) {
				int startInd = Math.abs(startIndPN);
				Loc loc1 = g.getLoc(row, startInd);
				Loc loc2 = g.getLoc(row, startInd + word.length() - 1);

				if (startIndPN >= 0) {
					loc[0] = loc1;
					loc[1] = loc2;
				} else {
					loc[0] = loc2;
					loc[1] = loc1;
				}
				break;
			}
		}

		return loc;
	}

	private int boyerMoore(String line, String pattern) {
		int forward = boyerMooreHelp(line, pattern);
		if (forward != -1)
			return forward;
	
		String reversePattern = "";
		for (int i = pattern.length() - 1; i >= 0; i--)
			reversePattern += Character.toString(pattern.charAt(i));

		int reverse = boyerMooreHelp(line, reversePattern);
		if (reverse == -1) {
			return Integer.MIN_VALUE;
		} else {
			return -reverse;
		}
	}

	private int boyerMooreHelp(String line, String pattern) {
		HashMap<String, Integer> lastIndexes = this.getLastLocs(pattern);
		
		int i = pattern.length() - 1;
		int j = pattern.length() - 1;
		
		while (i < line.length()) {
			if (line.charAt(i) == pattern.charAt(j)) {
				if (j == 0) {
					return i;
				} else {
					i--;
					j--;
				}
			} else {
				int lastInd = lastIndexes.get(Character.toString(line.charAt(i)));
				i = i + pattern.length() - Math.min(j, 1 + lastInd);
				j = pattern.length() - 1;
			}
		}

		return -1;
	}  

	private HashMap<String, Integer> getLastLocs(String pattern) {
		HashMap<String, Integer> lastIndexes = new HashMap<String, Integer>();
		for (int i = 0; i < 26; i++) {
			char c = (char) (i+65);
			String s = Character.toString(c);

			lastIndexes.put(s, -1);
		}

		for (int i = 0; i < pattern.length(); i++) {
			String s = Character.toString(pattern.charAt(i));

			lastIndexes.put(s, i);
		}

		return lastIndexes;
	}
}
