package customdatastructures;

import java.util.ArrayList;
import java.util.List;

public class Formula {
	
	/* the formula class represents a logical formula that corresponds to a problem instance */
	
	private List<Clause> clauses = new ArrayList<Clause>();
	
	public Formula(List<Clause> clauses) {
		for ( Clause c : clauses )
		this.clauses.add(c);
	}
	
	public Formula(Formula formula) {
		
		for(Clause clause: formula.getClauses()){
			clauses.add(new Clause(clause));
		}
	}
	
	public List<Clause> getClauses() {
		return clauses;
	}
	
	private Formula unsatisfiedClausesByState ( SearchState state ) {
		
		if(state.isEmpty()) return this;
		
		Formula remainingClauses = new Formula(this);
		
		for ( int variable : state.getVariables() ) {
			
			int i = 0;
			
			Clause clause = null;
			if ( ! remainingClauses.getClauses().isEmpty() ) 
			clause = remainingClauses.getClauses().get(i);
			
			while( clause != null )	{
				
				if ( clause.satisfiedByLiteral(variable) ) 
					remainingClauses.removeClause(clause);
				else
					i++;
				
				if (i<remainingClauses.getClauses().size())
					clause=remainingClauses.getClauses().get(i);
				else
					clause=null;
			}
		}		
		return remainingClauses;
	}
	
	public int unsatisfiedClausesByStateCount ( SearchState state ) {
		return this.unsatisfiedClausesByState(state).getClauses().size();
	}
	
	public boolean satisfiedByState ( SearchState state ) { 
		return this.unsatisfiedClausesByStateCount(state) == 0;
	}
	
	public int satisfiedClausesBySolutionCount ( List<Integer> solution ) {
		
		int count = 0;
		
		for ( Clause c : this.clauses ) {
			if ( c.satisfiedBy(solution) ) count ++;
		}
		
		return count;
	}
	
	public boolean satisfiedBySolution ( List<Integer> solution ) {
		
		boolean satisfied = true ;
		int i = 0;
		
		while ( satisfied && i < this.clauses.size() ) {
			Clause c = this.clauses.get(i);
			satisfied = c.satisfiedBy(solution);
			i++;
		}
		
		return satisfied;
	}
	
	private void removeClause (Clause c ) {
		this.clauses.remove(c);
	}
	
	@Override
	public String toString() {
		for ( Clause c : clauses ) System.out.println(c.toString());
		return "";
	}

}
