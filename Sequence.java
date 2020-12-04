import java.util.Stack;

public class Sequence {
	private Grid grid;
	private Stack<Loc> path;

	public Sequence(Grid grid) {
		this.grid = grid;
	}

	public void getSeq(int i, int j, int val) {
		Stack<Loc> curPath = new Stack<Loc>();
		
		Loc firstLoc = this.grid.getLoc(i, j);
		if (firstLoc != null) {
			curPath.push(firstLoc);
			int firstVal = firstLoc.getIntVal();
			if (firstVal == val) {
				this.path = curPath;
			} else if (firstVal > val) {
				this.path = new Stack<Loc>();
			} else {
				this.path = getSeqHelp(i, j, val, curPath, firstVal, firstVal);
			}
		} else {
			this.path = new Stack<Loc>();
		}
	}

	private Stack<Loc> getSeqHelp(int row, int col, int targetVal, Stack<Loc> curPath, int prevVal, int firstVal) {
		Loc thisLoc = this.grid.getLoc(row, col);
		if (thisLoc.getIntVal() != targetVal) {
			Loc aboveLoc = this.grid.getLoc(row-1, col);
			if (aboveLoc != null) {
				int aboveVal = aboveLoc.getIntVal();
				if (aboveVal == prevVal + 1) {
					curPath.push(aboveLoc);
					Stack<Loc> recurPath = getSeqHelp(row-1, col, targetVal, curPath, aboveVal, firstVal);
					if (recurPath.size() == targetVal - firstVal + 1) {
						// path is good
						return recurPath;
					}
					curPath.pop();
				}
			}


			Loc rightLoc = this.grid.getLoc(row, col+1);
			if (rightLoc != null) {
				int rightVal = rightLoc.getIntVal();
				if (rightVal == prevVal + 1) {
					curPath.push(rightLoc);
					Stack<Loc> recurPath = getSeqHelp(row, col+1, targetVal, curPath, rightVal, firstVal);
					if (recurPath.size() == targetVal - firstVal + 1) {
						// path is good
						return recurPath;
					}
					curPath.pop();
				}
			}

			Loc belowLoc = this.grid.getLoc(row+1, col);
			if (belowLoc != null) {
				int belowVal = belowLoc.getIntVal();
				if (belowVal == prevVal + 1) {
					curPath.push(belowLoc);
					Stack<Loc> recurPath = getSeqHelp(row+1, col, targetVal, curPath, belowVal, firstVal);
					if (recurPath.size() == targetVal - firstVal + 1) {
						// path is good
						return recurPath;
					}
					curPath.pop();
				}
			}

			
			Loc leftLoc = this.grid.getLoc(row, col-1);
			if (leftLoc != null) {
				int leftVal = leftLoc.getIntVal();
				if (leftVal == prevVal + 1) {
					curPath.push(leftLoc);
					Stack<Loc> recurPath = getSeqHelp(row, col-1, targetVal, curPath, leftVal, firstVal);
					if (recurPath.size() == targetVal - firstVal + 1) {
						// path is good
						return recurPath;
					}
					curPath.pop();
				}
			}

			// no adjacent spaces are the increment, so no path
			return new Stack<Loc>();
		}

		// thisLoc.val == val, so the path is complete
		return curPath;
	}

	public String toString() {
		String ret = "";
		if (this.path == null) {
			return ret;
		} else {
			while (!path.isEmpty()) {
				ret = path.pop().toString() + ret;
			}
		
			return ret;
		}
	}
}
