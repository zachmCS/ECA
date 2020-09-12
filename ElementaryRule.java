
public class ElementaryRule extends Rule {

	private CellState[] ruleSet = new CellState[10];

	public ElementaryRule(int ruleNum) throws InvalidRuleNumException {

		super(exceptionThrower(ruleNum));
		ruleSet = genRuleSet(ruleNum);

	}

	private CellState[] genRuleSet(int ruleNum) {
		String binary = Integer.toBinaryString(ruleNum);
		if (binary.length() != 8) {
			int leadingZeroes = 8 - binary.length();
			for (int i = 0; i < leadingZeroes; i++) {
				binary = "0" + binary;
			}
		}
		CellState[] ruleSet = new CellState[8];
		for (int i = 0; i < ruleSet.length; i++) {
			if (binary.charAt(i) == '0') {
				ruleSet[i] = CellState.OFF;
			} else {
				ruleSet[i] = CellState.ON;
			}
		}
		return ruleSet;
	}

	@Override
	public int getNumSubrules() {
		return 8;
	}

	@Override
	public EvolvedCell evolve(Cell[] neighborhood) {
		if (neighborhood[0].getState() == CellState.ON && neighborhood[1].getState() == CellState.ON
				&& neighborhood[2].getState() == CellState.ON) {
			// subrule 7, counts down from here
			return new EvolvedCell(ruleSet[0], 7);
		} else if (neighborhood[0].getState() == CellState.ON && neighborhood[1].getState() == CellState.ON
				&& neighborhood[2].getState() == CellState.OFF) {
			return new EvolvedCell(ruleSet[1], 6);
		} else if (neighborhood[0].getState() == CellState.ON && neighborhood[1].getState() == CellState.OFF
				&& neighborhood[2].getState() == CellState.ON) {
			return new EvolvedCell(ruleSet[2], 5);
		} else if (neighborhood[0].getState() == CellState.ON && neighborhood[1].getState() == CellState.OFF
				&& neighborhood[2].getState() == CellState.OFF) {
			return new EvolvedCell(ruleSet[3], 4);
		} else if (neighborhood[0].getState() == CellState.OFF && neighborhood[1].getState() == CellState.ON
				&& neighborhood[2].getState() == CellState.ON) {
			return new EvolvedCell(ruleSet[4], 3);
		} else if (neighborhood[0].getState() == CellState.OFF && neighborhood[1].getState() == CellState.ON
				&& neighborhood[2].getState() == CellState.OFF) {
			return new EvolvedCell(ruleSet[5], 2);
		} else if (neighborhood[0].getState() == CellState.OFF && neighborhood[1].getState() == CellState.OFF
				&& neighborhood[2].getState() == CellState.ON) {
			return new EvolvedCell(ruleSet[6], 1);
		} else {
			return new EvolvedCell(ruleSet[7], 0);
		}
	}

	@Override
	public String toString() {
		String automaton = "OOO OO. O.O O.. .OO .O. ..O ...\n" + " " + ruleSet[0].toString();
		
		for(int i = 1; i < ruleSet.length; i++) {
			automaton = automaton + "   " +  ruleSet[i].toString();
		}
		automaton = automaton + " ";
		return automaton;
	}
	// two line representation

	private static int exceptionThrower(int ruleNum) throws InvalidRuleNumException {
		if (ruleNum < 0 || ruleNum > 255) {
			throw new InvalidRuleNumException();
		} else {
			return ruleNum;
		}
	}

	@Override
	public Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc) {
		Cell[] neighborhood = new Cell[3];

		neighborhood[0] = bc.getNeighbor(cellIdx, -1, gen);
		neighborhood[1] = gen.getCell(cellIdx);
		neighborhood[2] = bc.getNeighbor(cellIdx, 1, gen);

		return neighborhood;

	}
}
