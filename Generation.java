public class Generation {

	private Cell[] cells;

	public Generation(CellState[] states) {
		cells = new Cell[states.length];

		for (int i = 0; i < cells.length; i++) {
			cells[i] = new Cell(states[i]);
		}
	}

	public Generation(String states) {
		cells = new Cell[states.length()];

		for (int i = 0; i < cells.length; i++) {
			if (states.charAt(i) == 'O' || states.charAt(i) == '.') {
				cells[i] = new Cell(CellState.getState(states.charAt(i)));
			} else {
				throw new IllegalArgumentException();
			}
		}
	}
	
	public Generation(Cell[] cells) {
		this.cells = cells.clone();
		//TODO figure this out
	}
	
	public int size() {
		return cells.length;
	}
	
	public Cell getCell(int idx) {
		return cells[idx];
	}
	
	public String toString() {
		String concat = "";
		
		for(int i = 0; i < cells.length; i++) {
			concat = concat + cells[i].toString();
		}
		return concat;
	}
}