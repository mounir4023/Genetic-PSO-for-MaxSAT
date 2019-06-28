package core;

import java.io.IOException;

import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import customdatastructures.Formula;
import swarmintelligence.PSO;
import swarmintelligence.Particle;

public class PBWTuning {
	
	private int max_iter = 4000;
	private int swarm_size = 600;
	private double cv_w = 6.5;
	private double gb_w = 1;
	private int vel_max = 20;
	
	private double pb_w[] =      { 0.5,1.0,1.5,2.0,2.5,3.0,3.5,4.0,4.5,5.0,5.5,6.0,6.5,7.0,7.5,8.0,8.5,9.0,9.5,10.0 };
	private int max_fmax[] =  {   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,   0 };
	private int mean_fmax[] = {   0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,   0 };
	
	private Dataset dataset = Dataset.UF75;
	private String files[] = { "uf75-0100.cnf", "uf75-02.cnf", "uf75-03.cnf", "uf75-04.cnf", "uf75-05.cnf",
							   "uf75-06.cnf", "uf75-07.cnf", "uf75-08.cnf", "uf75-09.cnf", "uf75-010.cnf"
							 };
	
	public void experiment() throws IOException {
		
		for ( int i = 0 ; i < pb_w.length ; i++ ) {
			
			int max_f = 0;
			int mean_f = 0;
			
			for ( int j = 0 ; j < 10; j++ ) {
				
				Formula formula = new CnfReader(""+dataset.get_path()+"/"+files[j]).readFormula();
				PSO test = new PSO(dataset,files[j], swarm_size, max_iter, vel_max, cv_w, pb_w[i], gb_w );
				Particle best = test.solve();
				
				int current_f = best.fitness(formula);
				
				if ( current_f > max_f )
					max_f = current_f ;
				
				mean_f += current_f;
			}
			max_fmax[i] = max_f ;
			mean_fmax[i] = mean_f/10;
			
			System.out.println(pb_w[i]+", "+max_fmax[i]+", "+mean_fmax[i]);
		}
	}
	
	public void show_results() {
		
		for (int i = 0; i < pb_w.length; i++ ) 
			System.out.println(pb_w[i]+", "+max_fmax[i]+", "+mean_fmax[i]);
	}

}
