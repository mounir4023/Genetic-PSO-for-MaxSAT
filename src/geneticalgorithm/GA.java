package geneticalgorithm;

import java.io.IOException;

import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import customdatastructures.Formula;

public class GA {
	
	private Dataset dataset;
	private Formula formula;
	private Population population;
	private int pop_size = 100;
	private int max_iter = 50; 
	private int crossover_rate = 30; 
	private int mutation_rate = 10;
	
	public GA () throws IOException {
		this.dataset = Dataset.UF20;
		this.formula = new CnfReader(Dataset.UF20.get_path()+"/"+"uf20-01000.cnf").readFormula();
		this.population = new Population(this);
		this.pop_size = 100;
		this.max_iter = 50; 
		this.crossover_rate = 50; 
		this.mutation_rate = 20; 
	}
	
	public void solve() {
		
		int iteration = 0;
		Individual solution = null;
		
		while ( iteration < max_iter && solution == null ) {
			
			Population new_borns = new Population(this);
			
			for ( int crossover_count = 0 ; crossover_count < crossover_rate; crossover_count++ ) {
				
				Individual parent1 = population.anybody();
				Individual parent2 = population.anybody();
				
				Individual child1 = new Individual(parent1,parent2);
				Individual child2 = new Individual(parent2,parent1);
				
				new_borns.register(child1);
				new_borns.register(child2);
			}
			
			this.population.merge(new_borns);
			
			Individual best = this.population.get_list().get(0);
			System.out.println(best);
			System.out.println(best.fitness(formula));
			
			for ( int mutation_count = 0 ; mutation_count < mutation_rate; mutation_count++ ) {
				
				
			}
		}
	}
	
	public int get_nb_vars() {
		return this.dataset.get_nb_vars();
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
