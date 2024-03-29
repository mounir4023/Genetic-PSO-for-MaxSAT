package geneticalgorithm;

import java.io.IOException;
import java.util.ArrayList;

import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import customdatastructures.Formula;

public class GA {
	
	private Dataset dataset;
	private Formula formula;
	private Population population;
	private int max_iter;
	private int pop_size;
	private int crossover_rate; 
	private int mutation_rate;
	
	public GA ( Dataset dataset,
				String file,
				int pop_size,
				int max_iter,
				int crossover_rate,
				int mutation_rate
			) throws IOException {
	
		this.dataset = dataset;
		this.formula = new CnfReader(this.dataset.get_path()+"/"+file).readFormula();
		this.pop_size = pop_size;
		this.max_iter = max_iter; 
		this.crossover_rate = crossover_rate; 
		this.mutation_rate = mutation_rate; 
		this.population = new Population(this);
		this.population.init_population();
	}
	
	public Individual solve() {
		
		// initialize the algorithm
		int iteration = 0;
		double stagnation_indicator = 5;
		Individual solution = null;
		Individual best = null;
		
		// core of the evolution
		while ( iteration < max_iter && solution == null ) { 
			
			// temporary store the new individuals from the crossover
			Population new_borns = new Population(this);
			
			// core of the crossover
			for ( int crossover_count = 0 ; crossover_count < pop_size * crossover_rate / 100 ; crossover_count++ ) { 
				
				Individual parent1 = population.anybody();
				Individual parent2 = population.anybody();
				
				Individual child1 = new Individual(parent1,parent2);
				Individual child2 = new Individual(parent2,parent1);
				
				new_borns.get_list().add(0,child1); 
				new_borns.get_list().add(0,child2);
			} 
			
			// add the new individuals to the populations and eliminate the worst individuals
			this.population.merge(new_borns);
			
			// store the currently best individual
			best = this.population.get_best().clone();

			// core of the mutation
			for ( int mutation_count = 0 ; mutation_count < pop_size * mutation_rate / 100; mutation_count++ ) {
				
				Individual mutated = population.anybody();
				mutated.mutate();
			}
			
			// sort the population after the mutations
			this.population.reorder();
					
			// watch over premature convergence and handle population in case
			stagnation_indicator = this.standard_deviation();
			if ( stagnation_indicator < 1 ) 
				this.population.social_disaster(); 
			
			stagnation_indicator = this.standard_deviation();
			
			// restore the best individual if it got worse due to some mutation
			if ( this.population.get_best().better_than(best, formula) )
				best = this.population.get_best().clone();
			else
				this.population.register(best);	
			
			// print iteration score
			System.out.println("\n\n=========== Iteration: "+iteration+" ============\n");
			this.population.top_five(this.formula);
			System.out.println("\nbest fitness: "+best.fitness(formula));
			//System.out.println("\nindicator: "+stagnation_indicator);
			
			// check if a solution was found, else repeat
			if (this.population.get_best().fitness(this.formula) == this.dataset.get_nb_clauses())
				solution = this.population.get_list().get(0);
			else
				iteration++;
		}
		
		return best;
	}
	
	private double standard_deviation() {
		
		double sum = 0;
		double mean = 0;
		double var = 0;
		double sd = 0;
		
		for (Individual i : this.population.get_list()) 	
			sum += i.fitness(this.formula);
		
		mean = sum / this.pop_size;
		
		for (Individual i : this.population.get_list()) 	
			var += ((i.fitness(this.formula) - mean ) * (i.fitness(this.formula) - mean ));
		
		var = var / this.pop_size;
		sd = Math.sqrt(var);
			
		return sd;
	}
	
	public int get_nb_vars() {
		return this.dataset.get_nb_vars();
	}
	
	public int get_nb_clauses() {
		return this.dataset.get_nb_clauses();
	}
	
	public int get_pop_size() {
		return this.pop_size;
	}
	
	public Dataset get_dataset() {
		return this.dataset;
	}
	
	public Formula get_formula() {
		return this.formula;
	}
}
