package customdatastructures;

import java.util.ArrayList;
import java.util.List;

public class SearchState {
	
	private List<Integer> variables = new ArrayList<Integer>();
	private int h1 = -1;
	private int h2 = -1;
	private Formula searchFormula = null;
	
	public SearchState() {
		super();
	}
	
	public SearchState(List<Integer> variables) {
		for (int i : variables ) 
		this.variables.add(i);
	}

	public SearchState(SearchState state) {
		for (int i : state.getVariables() ) 
		this.variables.add(i);
	}
	
	public SearchState(SearchState state, int child) {
		for (int i : state.getVariables() ) 
		this.variables.add(i);
		this.variables.add(child);
	}
	
	public List<Integer> getVariables() {
		return this.variables;
	}
	
	public boolean isEmpty() {
		return this.variables.isEmpty();
	}
	
	public int getLast() {
		return this.variables.get(this.variables.size()-1);
	}
	
	public int getH1(Formula formula) {
		if ( this.h1 == -1 || this.searchFormula != formula ) this.setupH1(formula);
		return this.h1;
	}
	
	public int getH2(Formula formula) {
		if ( this.h2 == -1 || this.searchFormula != formula ) this.setupH2(formula);
		return this.h2;
	}
	
	private void setupH1(Formula formula) {
		if ( this.searchFormula != formula ) this.searchFormula = formula;
		this.h1 = formula.unsatisfiedClausesByStateCount(this);
	}
	
	private void setupH2(Formula formula) {
		if ( this.searchFormula != formula ) this.searchFormula = formula;
		this.h2 = formula.unsatisfiedClausesByStateCount(this) + this.variables.size();
	}
	
	public String toString() {
		return this.variables.toString();
	}

}
