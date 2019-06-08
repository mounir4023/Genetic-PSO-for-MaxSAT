package geneticalgorithm;

import java.util.ArrayList;

public class GA {
	
	private ArrayList<Individual> population = new ArrayList<Individual> ();
	private int pop_size = 100;
	private int max_iter = 50;
	
	private void solve() {
		
		for ( int i =0 ; i < max_iter ; i++ ) {
			
			/* crossing */
			// for 1 to 1 < Rc < crossing rate < 100
				// select 2 parents
				// half of both parents chromosomes alternatively
				// Individual newborn = new Individual();
				// add the new born to the population and eliminate one
			
			/* mutation */
			// for 1 to 1 < Rm < mutation rate < 100
				// select an individual
				// randomly mutate one of its chromosomes
				// add the mutated individual to the population and eliminate one
		}
	}
}
