import java.util.LinkedList;

public class Automaton{
	
	private Rule rule;
	private LinkedList<Generation> generations = new LinkedList<>();
	private BoundaryConditions bc;
	
	public Automaton(Rule rule, Generation init, BoundaryConditions bc) {
		
		this.rule = rule;
		generations.add(init);
		this.bc = bc;
		
	}
	
	public Rule getRule() {
		return rule;
	}
	
	public Generation getGeneration(int stepNum) throws InvalidStepNumException {
		
		if(stepNum < 0) {
			throw new InvalidStepNumException();
		}
		
		if(generations.size() - 1 < stepNum) {
			evolve(stepNum - getTotalSteps());
			return generations.get(getTotalSteps());
		}
		return generations.get(stepNum);
	}
	
	public BoundaryConditions getBoundaryConditions() {
		return bc;
	}
	
	public void evolve (int numSteps) throws InvalidStepNumException {
		if(numSteps < 0) {
			throw new InvalidStepNumException();
		} else {
			for(int count = 0; count < numSteps; count++) {
				generations.add(rule.evolve(generations.get(getTotalSteps()), bc));
			}
		}
	}
	
	public int getTotalSteps() {
		return generations.size() - 1;
	}
	
	public String toString() {
		return generations.get(getTotalSteps()).toString();
	}
	
	public String getHistory() {
		String automaton = "";
		
		for(int i = 0; i < generations.size(); i++) {
			if(i < getTotalSteps()) {
				automaton = automaton + generations.get(i).toString() + '\n';
			} else {
				automaton = automaton + generations.get(i).toString();
			}
		}
		
		return automaton;
	}
	
	
}