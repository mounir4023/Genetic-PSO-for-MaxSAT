package customdatastructures;

import java.util.ArrayList;
import java.util.List;

public class Clause {
	
	/* The clause class represents a disjunction of literals */
	
	private List<Integer> literals = new ArrayList<Integer>();
	
	public Clause(List<Integer> literals) {
		for (int i : literals) 
		this.literals.add(i);
	}
	
	public Clause() {
		super();
	}

	public Clause(Clause clause) {
		
		List<Integer> clonedLiterals = new ArrayList<Integer>(clause.getLiterals().size());
		for(Integer literal: clause.getLiterals()){
			clonedLiterals.add(literal);
		}
		
		this.literals = clonedLiterals;
	}
	
	public boolean satisfiedBy ( List<Integer> solution ) {
		
		boolean satisfied = false;
		int i = 0;
		
		while ( !satisfied && i < this.literals.size() ) {
			
			int literal = this.literals.get(i);
			if( literal < 0 ) satisfied = solution.get(-(literal)-1) == 0;
			else satisfied = solution.get(literal-1) == 1;
			i++;
		}
		
		return satisfied;
	}
	
	public boolean satisfiedByLiteral ( int literal ) {
		return this.literals.contains(literal);
	}

	public List<Integer> getLiterals() {
		return literals;
	}

	public boolean containsLiteral(Integer literal) {
		return this.literals.contains(literal);
	}
	
	public void addLiteral(Integer literal) {
		this.literals.add(literal);
	}

	public void removeLiteral(Integer literal) {
		this.literals.remove(literal);
	}
	
	public boolean isUnit() {
		return this.literals.size() == 1;
	}
	
	public boolean isEmpty() {
		return this.literals.isEmpty();
	}
	
	@Override
	public String toString() {
		return literals.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((literals == null) ? 0 : literals.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clause other = (Clause) obj;
		if (literals == null) {
			if (other.literals != null)
				return false;
		} else if (!literals.equals(other.literals))
			return false;
		return true;
	}
}
