package geneticalgorithm;

import java.util.ArrayList;
import java.util.Random;

import customdatastructures.Formula;

public class Population {

	private GA configuration;
	private ArrayList<Individual> list;
	
	public Population(GA configuration) { 
		
		this.configuration = configuration;
		this.list = new ArrayList<Individual> ();
	}
	
	public void init_population() { 
		
		for( int i=0; i< this.configuration.get_pop_size() ; i++ ) {
			
			Individual individual = new Individual(this.configuration.get_dataset().get_nb_vars());
			this.register(individual);
		}
	}
	
	public Individual anybody() {
		
		Random generator = new Random();
		int index = generator.nextInt(configuration.get_pop_size());
		return this.list.get(index);
	}
	
	public void register(Individual new_born) {
		
		Formula formula = this.configuration.get_formula();
		boolean registered = false;
		
		int nb_fitness = new_born.fitness(formula);
		int fitness_rank = 0;
		
		while(fitness_rank < this.list.size() && ! registered ) { 
			
			int compared_fitness = this.list.get(fitness_rank).fitness(formula);
			
			if (nb_fitness >= compared_fitness) {
				
				this.list.add(fitness_rank, new_born);
				registered = true;
			} else {
				fitness_rank++;
			}
		}
		
		if ( ! registered ) this.list.add(fitness_rank, new_born);	
	}
	
	public void merge(Population new_borns) { 

		int last_index = this.list.size() - 1 ; 
		
		for ( Individual individual : new_borns.get_list() ) { 
			this.register(individual);
		} 
		
		for ( int j = this.list.size()-1; j > last_index ; j-- ) {
			this.list.remove(j);
		} 
	}
	
	public ArrayList<Individual> get_list() {
		return this.list;
	}
	
	public void top_five(Formula formula) {
		System.out.println(this.list.get(0)); System.out.println(this.list.get(0).fitness(formula));
		System.out.println(this.list.get(1)); System.out.println(this.list.get(1).fitness(formula));
		System.out.println(this.list.get(2)); System.out.println(this.list.get(2).fitness(formula));
		System.out.println(this.list.get(3)); System.out.println(this.list.get(3).fitness(formula));
		System.out.println(this.list.get(4)); System.out.println(this.list.get(4).fitness(formula));
	}
	
}
