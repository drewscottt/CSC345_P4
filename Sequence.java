public class Sequence {
	private Grid grid;
	private Stack<Loc> curPath;
	private Stack<Loc> path;

	public Sequence(Grid grid) {
		this.grid = grid;
	}

	public void setSeq(int i, int j, int val) {
		int locVal = this.grid.getIntVal(i, j);		
		if (locVal == val) {
			this.path = this.curPath;
		} else {
			if (this.path != null) {
				// above
				if (this.isVal(i-1, j, locVal+1)) {
							
				} else {
					
				}

				// right
				if (this.isVal(i, j+1, locVal+1)) {

				} else {

				}

				// below
				if (this.isVal(i+1, j, locVal+1)) {

				} else {

				} 

				// left
				if (this.isVal(i, j-1, locVal+1)) {

				} else {
					// none are the correct val
				} 
			}
		}
	}

	private boolean isVal(int i, int j, int val) {
		return this.grid.getIntVal(i, j) == val;
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
