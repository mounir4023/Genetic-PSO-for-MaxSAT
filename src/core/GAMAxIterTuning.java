package core;

import java.io.IOException;

import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import customdatastructures.Formula;
import geneticalgorithm.GA;
import geneticalgorithm.Individual;

public class GAMAxIterTuning {
	
	private int pop_size = 500;
	private int crossover_rate = 25;
	private int mutation_rate = 20;
	
	private int max_iter[] =  { 500,1000,1500,2000,2500,3000,3500,4000,4500,5000,5500,6000,6500,7000,7500,8000,8500,9000,9500,10000 };
	private int max_fmax[] =  {   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,    0 };
	private int mean_fmax[] = {   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,   0,    0 };
	
	private Dataset dataset = Dataset.UF75;
	private String files[] = { "uf75-0100.cnf", "uf75-02.cnf", "uf75-03.cnf", "uf75-04.cnf", "uf75-05.cnf",
							   "uf75-06.cnf", "uf75-07.cnf", "uf75-08.cnf", "uf75-09.cnf", "uf75-010.cnf"
							 };
	
	public void experiment() throws IOException {
		
		for ( int i = 0 ; i < max_iter.length ; i++ ) {
			
			int max_f = 0;
			int mean_f = 0;
			
			for ( int j = 0 ; j < 10; j++ ) {
				
				Formula formula = new CnfReader(""+dataset.get_path()+"/"+files[j]).readFormula();
				GA test = new GA(dataset,files[j], pop_size, max_iter[i], crossover_rate, mutation_rate );
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
		
		for (int i = 0; i < max_iter.length; i++ ) 
			System.out.println(max_iter[i]+", "+max_fmax[i]+", "+mean_fmax[i]);
	}

}
