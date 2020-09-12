
public abstract class Rule{
	
	private int ruleNum;
	
	protected Rule(int ruleNum) {
		
		this.ruleNum = ruleNum;
		
	}
	
	public int getRuleNum() {
		
		return ruleNum;
		
	}
	
	public Generation evolve(Generation gen, BoundaryConditions bc) {
		
		Cell[] cells  = new EvolvedCell[gen.size()];
		
		for(int i = 0; i < gen.size(); i++) {
			cells[i] = evolve(getNeighborhood(i, gen, bc));
		}
		
		return new Generation(cells);
	}
	
	public abstract int getNumSubrules();
	
	public abstract Cell[] getNeighborhood(int cellIdx, Generation gen, BoundaryConditions bc);
	
	public abstract EvolvedCell evolve(Cell[] neighborhood);
	
	public abstract String toString();
	
	
}
