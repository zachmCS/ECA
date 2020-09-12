public class CircularBoundaryConditions implements BoundaryConditions {

	@Override
	public Cell getNeighbor(int cellIdx, int offset, Generation gen) {

		//Marc Hanna helped me out with this class.
		int difference = cellIdx + offset;
		int newIdx = (difference % gen.size());

		if (newIdx == 0) {
			return gen.getCell(0);
		}
		if (difference > gen.size() - 1) {
			return gen.getCell(newIdx);
		} else if (difference < 0) {
			return gen.getCell(newIdx + gen.size());
		}
		return gen.getCell(difference);
	}

}