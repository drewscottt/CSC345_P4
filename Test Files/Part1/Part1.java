import java.util.Arrays;
import java.util.Collections;

public class Part1 {

	public static long getMaxProd(Grid g, int mVal) {
		long rowsProd = getMaxRowProd(g, mVal);
		long colsProd = getMaxColProd(g, mVal);		
		long leftDiagProd = getMaxLeftDiagProd(g, mVal);
		long rightDiagProd = getMaxRightDiagProd(g, mVal);
		
		return Math.max(rightDiagProd, Math.max(leftDiagProd, Math.max(colsProd, rowsProd)));
	}

	private static long getMaxRightDiagProd(Grid g, int mVal) {
                long prod = 0;
		int maxDiff = g.size() - mVal;
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
			
			long lineMaxProd = 0;

			while (minRowCol + mVal <= g.size() - Math.abs(diff)) {
				long lineCurProd = 1;
				for (int i = minRowCol; i < minRowCol + mVal; i++) {
					int row = firstRow - i;
					int col = firstCol + i;
					int thisVal = g.getIntVal(row, col);
					lineCurProd *= thisVal;
                                        if (thisVal <= 0) {
                                        	minRowCol = i + 1;
					        break;
                                        }
				}

				if (lineCurProd > 0) {
					minRowCol += mVal;
					
					long curMaxProd = lineCurProd;
                                        
					int addVal = g.getIntVal(firstRow - minRowCol, firstCol + minRowCol);
                                        
					while (addVal > 0) {
					        int maxRowCol = minRowCol - mVal;
                                                int removeVal = g.getIntVal(firstRow - maxRowCol, firstCol + maxRowCol);
                                                lineCurProd = (lineCurProd / removeVal ) * addVal;

                                                if (removeVal < addVal) {
                                                        curMaxProd = Math.max(curMaxProd, lineCurProd);
                                                }

                                                minRowCol++;
                                                addVal = g.getIntVal(firstRow - minRowCol, firstCol + minRowCol);
                                        }

                                        lineCurProd = curMaxProd;
				}
				lineMaxProd = Math.max(lineMaxProd, lineCurProd);
			}

			prod = Math.max(prod, lineMaxProd);
		}

		return prod;
	}
 
	private static long getMaxLeftDiagProd(Grid g, int mVal) {
                long prod = 0;
		int maxDiff = g.size() - mVal;
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
			
			long lineMaxProd = 0;

			while (minRowCol + mVal <= maxDiff) {
				long lineCurProd = 1;
				for (int i = minRowCol; i < minRowCol + mVal; i++) {
					int row = firstRow + i;
					int col = firstCol + i;

					int thisVal = g.getIntVal(row, col);
					lineCurProd *= thisVal;
                                        if (thisVal <= 0) {
                                        	minRowCol = i + 1;
					        break;
                                        }
				}

				if (lineCurProd > 0) {
					minRowCol += mVal;
					
					long curMaxProd = lineCurProd;
                                        
					int addVal = g.getIntVal(firstRow + minRowCol, firstCol + minRowCol);
                                        
					while (addVal > 0) {
					        int maxRowCol = minRowCol - mVal;
                                                int removeVal = g.getIntVal(firstRow + maxRowCol, firstCol + maxRowCol);
                                                lineCurProd = (lineCurProd / removeVal ) * addVal;

                                                if (removeVal < addVal) {
                                                        curMaxProd = Math.max(curMaxProd, lineCurProd);
                                                }

                                                minRowCol++;
                                                addVal = g.getIntVal(firstRow + minRowCol, firstCol + minRowCol);
                                        }

                                        lineCurProd = curMaxProd;
				}

				lineMaxProd = Math.max(lineMaxProd, lineCurProd);
			}

			prod = Math.max(prod, lineMaxProd);
		}

		return prod;
        }


	private static long getMaxColProd(Grid g, int mVal) {
                long prod = 0;

                for (int col = 0; col < g.size(); col++) {
                        long colMaxProd = 0;
                        int startInd = 0;
                        while (startInd + mVal < g.size()) {
                                long curProd = 1;

                                for (int row = startInd; row < startInd + mVal; row++) {
                                        int thisVal = g.getIntVal(row, col);
                                        curProd *= thisVal;
                                        if (thisVal == 0) {
                                                startInd = row + 1;
                                                break;
                                        }
                                }

                                if (curProd > 0) {
                                        long curMaxProd = curProd;
                                        int addInd = startInd + mVal;
                                        int addVal = g.getIntVal(addInd, col);
                                        while (addVal > 0) {
                                                int removeInd = addInd - mVal;
                                                int removeVal = g.getIntVal(removeInd, col);

                                                curProd = (curProd / removeVal ) * addVal;

                                                if (removeVal < addVal) {
                                                        curMaxProd = Math.max(curMaxProd, curProd);
                                                }

                                                addInd++;
                                                addVal = g.getIntVal(addInd, col);
                                        }
                                        startInd = addInd + 1;

                                        curProd = curMaxProd;
                                }

                                colMaxProd = Math.max(curProd, colMaxProd);
                        }

                        prod = Math.max(prod, colMaxProd);
                }

                return prod;
        }

	private static long getMaxRowProd(Grid g, int mVal) {
		long prod = 0;
		
		for (int row = 0; row < g.size(); row++) {
			long rowMaxProd = 0; 
			int startInd = 0;
			while (startInd + mVal < g.size()) {
				long curProd = 1;
				
				for (int col = startInd; col < startInd + mVal; col++) {
					int thisVal = g.getIntVal(row, col);
					curProd *= thisVal;
					if (thisVal == 0) {
						startInd = col + 1;
						break;
					}
				}
				
				if (curProd > 0) {
					long curMaxProd = curProd;
					int addInd = startInd + mVal;
					int addVal = g.getIntVal(row, addInd);
					while (addVal > 0) {
						int removeInd = addInd - mVal;
						int removeVal = g.getIntVal(row, removeInd);
						
						curProd = (curProd / removeVal ) * addVal;
						
						if (removeVal < addVal) {
							curMaxProd = Math.max(curMaxProd, curProd);
						}

						addInd++;
						addVal = g.getIntVal(row, addInd);
					}
					startInd = addInd + 1;

					curProd = curMaxProd;
				}
				rowMaxProd = Math.max(curProd, rowMaxProd);
			}
			prod = Math.max(prod, rowMaxProd);
		}

		return prod;
	}
}
