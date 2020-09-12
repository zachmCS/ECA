public class FixedBoundaryConditions implements BoundaryConditions{

	private CellState left, right;
	CellState OFF = CellState.OFF;
	CellState ON = CellState.ON;
	
	public FixedBoundaryConditions(CellState left, CellState right) {
		this.left = left;
		this.right = right;
	}
	
	public CellState getLeftState() {
		return left;
	}
	
	public CellState getRightState() {
		return right;
	}
	//Worked with Marcos Bernier on this class.
	@Override
	public Cell getNeighbor(int cellIdx, int offset, Generation gen) {
		int difference = cellIdx + offset;
		
		if(difference < 0) {
			if(left == OFF) {
				return new Cell(OFF);
			} else {
				return new Cell(ON);
			}
		} else if(difference >= gen.size()) {
			if(right == OFF) {
				return new Cell(OFF);
			} else {
				return new Cell(ON);
			}
		} else {
			return gen.getCell(difference);
		}
		
	}
	
}