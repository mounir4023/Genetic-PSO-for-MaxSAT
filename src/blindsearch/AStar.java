package blindsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import cnfmanagement.CnfReader;
import customdatastructures.Formula;
import customdatastructures.SearchState;

public class AStar implements Comparator<SearchState> {
	
	/* this class applies the A* Search to find a solution satisfying a Formula 
	 * The chosen heuristic is defined by h1 = count(unsatisfied clauses) 
	 * the smaller h1 the better is the state */ 
	
	private Formula formula;
	private int nb_vars;
	
	public AStar( String file , int nb_vars) throws IOException {
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
		ArrayList<SearchState> open = new ArrayList<SearchState>();
		
		open.add(new SearchState ());
		int index = 0 ;
		
		System.out.println(randomSequence);
		System.out.println("formula size: "+ formula.getClauses().size());
		System.out.println("==========================");
		System.out.println("   Starting AStar1 search");
		System.out.println("==========================");
		
		while ( ! open.isEmpty() && (solFound == null) ) {
			
			SearchState currentState = (SearchState)open.remove(0);
			
			boolean satisfied = this.formula.satisfiedByState(currentState);
			if ( satisfied ) solFound = currentState;
			
			if (currentState.getVariables().isEmpty()) index=-1;
			else index = randomSequence.indexOf(Math.abs(currentState.getLast()));

			if ( index != (nb_vars-1) ) {
				/* in case sort is not optimized use this code to insert instead of comparator 
				int i = 0;
				SearchState child = new SearchState(currentState,randomSequence.get(index+1));
				while child.h1 > open.get(i).h1 i++
				open.add(i,child)
				*/
				open.add(new SearchState(currentState,randomSequence.get(index+1)));
				open.add(new SearchState(currentState,-randomSequence.get(index+1)));
				Collections.sort(open, this);
			}
			
			System.out.println("\ntested state: "+currentState);
			System.out.println("test results: "+satisfied);
			System.out.println("\n==============================");
		}
		
		if ( solFound != null ) {
			System.out.println("      ASTAR1 Search finished");
			System.out.println("==============================");
			System.out.println("\nsorted sequence:");
			System.out.println(randomSequence);
			System.out.println("\nsolution found:");
			System.out.println(solFound);
		}
		else System.out.println("Solution not found");

	}

	@Override
	public int compare(SearchState s1, SearchState s2) {
		
		int h1s1 = s1.getH1(this.formula);
		int h1s2 = s2.getH1(this.formula);
		
		if ( h1s1 == h1s2 ) return 0;
		if ( h1s1 > h1s2 )  return 1;
		return -1;
	}
}
