public class AutomatonMeasurements {

	public static int hammingDistance(Generation g1, Generation g2) {
		if (g1.size() != g2.size()) {
			throw new IllegalArgumentException();
		}

		int differenceCount = 0;
		CellState cs1, cs2;
		for (int i = 0; i < g1.size(); i++) {
			cs1 = g1.getCell(i).getState();
			cs2 = g2.getCell(i).getState();

			if (!cs1.equals(cs2)) {
				differenceCount++;
			}
		}
		return differenceCount;
	}

	public static int hammingDistance(int stepNum, Automaton a) throws InvalidStepNumException {
		if (stepNum < 0) {
			throw new InvalidStepNumException();
		} else {
			return hammingDistance(a.getGeneration(stepNum - 1), a.getGeneration(stepNum));
		}
	}

	public static int[] hammingDistances(Automaton a) throws InvalidStepNumException {
		int[] distances = new int[a.getTotalSteps()];

		for (int i = 1; i <= distances.length; i++) {
			distances[i - 1] = hammingDistance(i, a);
		}
		return distances;
	}

	public static int[] subruleCount(int stepNum, Automaton a) throws InvalidStepNumException {
		Cell[] neighborhood;
		int[] returnedCount = new int[a.getRule().getNumSubrules()];
		int count = 0;

		if (stepNum < 0) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < a.getGeneration(stepNum - 1).size(); i++) {
			neighborhood = a.getRule().getNeighborhood(i, a.getGeneration(stepNum - 1), a.getBoundaryConditions());
			int tempsubRule = a.getRule().evolve(neighborhood).getSubruleNum();

			for (int o = 0; o < a.getGeneration(stepNum - 1).size(); o++) {
				neighborhood = a.getRule().getNeighborhood(o, a.getGeneration(stepNum - 1), a.getBoundaryConditions());
				int subRuleNum = a.getRule().evolve(neighborhood).getSubruleNum();

				if (subRuleNum == tempsubRule) {
					count++;
				}
			}
			returnedCount[tempsubRule] = count;
			count = 0;
		}
		return returnedCount;

	}

	public static int[][] subruleCounts(Automaton a) throws InvalidStepNumException {

		int columns = a.getRule().getNumSubrules();
		int rows = a.getTotalSteps();
		int[][] subruleCounts = new int[rows][columns];
		int[] generationCount;
		int i, o, p = 0;

		for (i = 1; i <= a.getTotalSteps(); i++) {
			for (o = 0; o < columns; o++) {
				generationCount = AutomatonMeasurements.subruleCount(i, a);
				subruleCounts[p][o] = generationCount[o];
			}

			p++;
		}
		return subruleCounts;
	}
}