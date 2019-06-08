package blindsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import cnfmanagement.CnfReader;
import customdatastructures.Formula;
import customdatastructures.SearchState;

public class DFS {
	
	/* this class applies the Depth First Search to find a solution satisfying a Formula */ 
	
	private Formula formula;
	private int nb_vars;
	
	public DFS( String file , int nb_vars) throws IOException {
		CnfReader reader = new CnfReader(file);
		formula = reader.readFormula();
		this.nb_vars = nb_vars;
	}
	
	public void search () {
		
		ArrayList<Integer> randomSequence = new ArrayList<Integer>();
		for ( int var=1; var<=nb_vars; var++ ) {
			randomSequence.add(var);
		}
		Collections.shuffle(randomSequence);
		
		SearchState solFound = null;
		Stack<SearchState> open = new Stack<SearchState>();
		//ArrayList<SearchState> closed = new ArrayList<SearchState>();
		
		open.push(new SearchState ());
		int index = 0 ;
		
		System.out.println(randomSequence);
		System.out.println("formula size: "+ formula.getClauses().size());
		System.out.println("==========================");
		System.out.println("   Starting DFS search");
		System.out.println("==========================");
		
		while ( ! open.isEmpty() && (solFound == null) ) {
			
			SearchState currentState = (SearchState)open.pop();
			
			boolean satisfied = this.formula.satisfiedByState(currentState);
			if ( satisfied ) solFound = currentState;
			
			if (currentState.getVariables().isEmpty()) index=-1;
			else index = randomSequence.indexOf(Math.abs(currentState.getLast()));

			if ( index != (nb_vars-1) ) {
				open.push(new SearchState(currentState,randomSequence.get(index+1)));
				open.push(new SearchState(currentState,-randomSequence.get(index+1)));
			}
			
			System.out.println("\ntested state: "+currentState);
			System.out.println("test results: "+satisfied);
			System.out.println("\n==============================");
		}
		
		if ( solFound != null ) {
			System.out.println("      DFS Search finished");
			System.out.println("==============================");
			System.out.println("\nsorted sequence:");
			System.out.println(randomSequence);
			System.out.println("\nsolution found:");
			System.out.println(solFound);
		}
		else System.out.println("Solution not found");

	}
}
