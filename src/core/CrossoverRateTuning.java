package core;

import java.io.IOException;

import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import customdatastructures.Formula;
import geneticalgorithm.GA;
import geneticalgorithm.Individual;

public class CrossoverRateTuning {
	
	private int max_iter = 1500;
	private int pop_size = 150;
	private int mutation_rate = 20;
	
	private int crossover_rate[] =  { 5,10,15,20,25,30,35,40,45,50,55,60,65,70,75,80,85,90,95,100 };
	private int max_fmax[] =        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0 };
	private int mean_fmax[] =       { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  0 };
	
	private Dataset dataset = Dataset.UF75;
	private String files[] = { "uf75-0100.cnf", "uf75-02.cnf", "uf75-03.cnf", "uf75-04.cnf", "uf75-05.cnf",
							   "uf75-06.cnf", "uf75-07.cnf", "uf75-08.cnf", "uf75-09.cnf", "uf75-010.cnf"
							 };
	
	public void experiment() throws IOException {
		
		for ( int i = 0 ; i < crossover_rate.length ; i++ ) {
			
			int max_f = 0;
			int mean_f = 0;
			
			for ( int j = 0 ; j < 10; j++ ) {
				
				Formula formula = new CnfReader(""+dataset.get_path()+"/"+files[j]).readFormula();
				GA test = new GA(dataset,files[j], pop_size, max_iter, crossover_rate[i], mutation_rate );
				Individual best = test.solve();
				
				int current_f = best.fitness(formula);
				
				if ( current_f > max_f )
					max_f = current_f ;
				
				mean_f += current_f;
			}
			max_fmax[i] = max_f ;
			mean_fmax[i] = mean_f/10;
		}
	}
	
	public void show_results() {
		
		for (int i = 0; i < crossover_rate.length; i++ ) 
			System.out.println(crossover_rate[i]+", "+max_fmax[i]+", "+mean_fmax[i]);
	}

}
