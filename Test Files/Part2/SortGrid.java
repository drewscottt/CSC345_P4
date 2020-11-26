import java.util.Arrays;
public class SortGrid {

	public static void sort(Grid g) {
		quickSort(g, 0, 0, g.size() - 1, g.size() - 1);
	}

	// quick sort
	public static void quickSort(Grid g, int firstRow, int firstCol, int lastRow, int lastCol) {
		if (firstRow != lastRow || firstCol != lastCol) {		
			int pivotRow = firstRow;
			int pivotCol = firstCol;
			Loc lowestPivot = g.getLoc(pivotRow, pivotCol);	
			int pivotValue = lowestPivot.getIntVal();

			int numEqToPivot = 0;

			int[] nextLoc = nextLoc(g, pivotRow, pivotCol, lastRow, lastCol);
			
			// moves pivot to proper location
			while (nextLoc != null) {
				int compareRow = nextLoc[0];
				int compareCol = nextLoc[1];

				Loc compare = g.getLoc(compareRow, compareCol);
				int compareValue = compare.getIntVal();

				if (compareValue < pivotValue) {
					// compare value needs to move the the "left" of the pivot
					// so swap it down to the pivot
					int[] locBeforePivot;
					if (numEqToPivot > 0) {
						locBeforePivot = computeLeftOfPivot(g, pivotRow, pivotCol, firstRow, firstCol, numEqToPivot-1);
					} else {
						locBeforePivot = new int[] {pivotRow, pivotCol};
					}

					swapDownTo(g, locBeforePivot[0], locBeforePivot[1], compareRow, compareCol);
					// need to update pivot location since we swapped it
					int[] nextPivotLoc = nextLoc(g, pivotRow, pivotCol, lastRow, lastCol);
					if (nextPivotLoc != null) {
						pivotRow = nextPivotLoc[0];
						pivotCol = nextPivotLoc[1];
						
						// update next comparison location
						nextLoc = nextLoc(g, compareRow, compareCol, lastRow, lastCol);
					} else {
						// pivot reached the end of the range, so we break
						break;
					}
				} else if (compareValue == pivotValue) {
					// compare value = pivot value, so need to swap it down to the
					// right of the pivot, so update pivot location and swap it down
					int[] nextPivotLoc = nextLoc(g, pivotRow, pivotCol, lastRow, lastCol);
					pivotRow = nextPivotLoc[0];
					pivotCol = nextPivotLoc[1];

					swapDownTo(g, pivotRow, pivotCol, compareRow, compareCol);
					// update next comparison location
					nextLoc = nextLoc(g, compareRow, compareCol, lastRow, lastCol);

					numEqToPivot++;
				} else {
					nextLoc = nextLoc(g, compareRow, compareCol, lastRow, lastCol);
				}
			}
		
			// gets the new bounds for the next recursive calls
			int[] locAfterPivot = nextLoc(g, pivotRow, pivotCol, lastRow, lastCol);
			int[] locBeforePivot = computeLeftOfPivot(g, pivotRow, pivotCol, firstRow, firstCol, numEqToPivot);
			
			// recurses on the two halves
			if (locBeforePivot != null) {
				quickSort(g, firstRow, firstCol, locBeforePivot[0], locBeforePivot[1]);
			}

			if (locAfterPivot != null) {
				quickSort(g, locAfterPivot[0], locAfterPivot[1], lastRow, lastCol);	
			}
		}
	}

	private static int[] computeLeftOfPivot(Grid g, int pivotRow, int pivotCol, int lowerRow, int lowerCol, int numInPivot) {
		int[] locBeforePivot = prevLoc(g, pivotRow, pivotCol, lowerRow, lowerCol);
                for (int i = 0; i < numInPivot; i++) {
	                locBeforePivot = prevLoc(g, locBeforePivot[0], locBeforePivot[1], lowerRow, lowerCol);
                }
		
		return locBeforePivot;
	}


	private static void swapDownTo(Grid g, int finalRow, int finalCol, int curRow, int curCol) {
		int[] prevLoc = prevLoc(g, curRow, curCol, finalRow, finalCol);
		if (prevLoc != null) {
			int swapRow = prevLoc[0];
			int swapCol = prevLoc[1];

			while (finalRow != swapRow || finalCol != swapCol) {
				g.swap(curRow, curCol, swapRow, swapCol);
				
				curRow = swapRow;
				curCol = swapCol;

				prevLoc = prevLoc(g, curRow, curCol, finalRow, finalCol);
				swapRow = prevLoc[0];
				swapCol = prevLoc[1];
			}
			g.swap(curRow, curCol, swapRow, swapCol); 
		}
	}

	private static int[] prevLoc(Grid g, int row, int col, int lowerRow, int lowerCol) {
		int[] loc;
		if (col == 0) {
			if (row > 0) {
				loc = new int[] {row-1, g.size() - 1};
			} else {
				return null;
			} 
		} else {
			loc = new int[] {row, col-1};
		}
		
		// need to check bounds
		if (loc != null) {
			if (loc[0] > lowerRow) {
				return loc;
			} else if (loc[0] == lowerRow) {
				if (loc[1] >= lowerCol) {
					return loc;
				}
			}
		}

		return null;
	}

	private static int[] nextLoc(Grid g, int row, int col, int upperRow, int upperCol) {
		int[] loc;
		if (col == g.size() - 1) {
			if (row < g.size() - 1) {
				loc = new int[] {row+1, 0};
			} else {
				return null;
			}
		} else {
			loc = new int[] {row, col+1};
		}

		// need to check bounds
		if (loc != null) {
			if (loc[0] < upperRow) {
				return loc;
			} else if (loc[0] == upperRow) {
				if (loc[1] <= upperCol) {
					return loc;
				}
			}
		}

		return null;
	}
}
