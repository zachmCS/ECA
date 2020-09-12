import java.util.HashMap;

public class TotalisticRule extends Rule {

	private HashMap<Integer, CellState> ruleSet = new HashMap<>();

	public TotalisticRule(int ruleNum) throws InvalidRuleNumException {
		super(exceptionThrower(ruleNum));
		ruleSet = genRuleSet(ruleNum);
	}

	private static int exceptionThrower(int ruleNum) throws InvalidRuleNumException {
		if (ruleNum < 0 || ruleNum > 63) {
			throw new InvalidRuleNumException();
		} else {
			return ruleNum;
		}
	}

	private HashMap<Integer, CellState> genRuleSet(int ruleNum) {

		String binary = Integer.toBinaryString(ruleNum);

		// Add leading zeroes
		if (binary.length() != 6) {
			int lZeroes = 6 - binary.length();

			for (int i = 0; i < lZeroes; i++) {
				binary = "0" + binary;
			}
		}
		CellState[] ruleSet;
		ruleSet = new CellState[6];
		for (int i = 0; i < ruleSet.length; i++) {
			if (binary.charAt(i) == '0') {
				ruleSet[i] = CellState.OFF;
			} else {
				ruleSet[i] = CellState.ON;
			}
		}

		HashMap<Integer, CellState> rules = new HashMap<>();
		rules.put(0, ruleSet[5]);
		rules.put(1, ruleSet[4]);
		rules.put(2, ruleSet[3]);
		rules.put(3, ruleSet[2]);
		rules.put(4, ruleSet[1]);
		rules.put(5, ruleSet[0]);
		return rules;
	}

	@Override
	public int getNumSubrules() {
		return 6;
	}

	@Override
	public Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc) {

		Cell[] neighborhood = new Cell[5];
		neighborhood[0] = bc.getNeighbor(cellIdx, -2, gen);
		neighborhood[1] = bc.getNeighbor(cellIdx, -1, gen);
		neighborhood[2] = gen.getCell(cellIdx);
		neighborhood[3] = bc.getNeighbor(cellIdx, 1, gen);
		neighborhood[4] = bc.getNeighbor(cellIdx, 2, gen);

		return neighborhood;
	}

	@Override
	public EvolvedCell evolve(Cell[] neighborhood) {
		// Cells that are on.
		int onCells = 0;

		for (int i = 0; i < neighborhood.length; i++) {
			if (neighborhood[i].getState() == CellState.ON) {
				onCells++;
			}
		}
		EvolvedCell evolved = new EvolvedCell(ruleSet.get(onCells), onCells);
		return evolved;

	}

	@Override
	public String toString() {
		String automaton = "5 4 3 2 1 0\n";

		for (int i = 5; i >= 0; i--) {
			if (i != 0) {
				automaton = automaton + ruleSet.get(i).toString() + " ";
			} else {
				automaton = automaton + ruleSet.get(i).toString();
			}
		}

		return automaton;

	}
}