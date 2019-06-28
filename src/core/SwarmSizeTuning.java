package core;

import java.io.IOException;

import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import customdatastructures.Formula;
import swarmintelligence.PSO;
import swarmintelligence.Particle;

public class SwarmSizeTuning {
	
	private int max_iter = 4000;
	private int cv_w = 1;
	private int pb_w = 1;
	private int gb_w = 1;
	private int vel_max = 20;
	
	private int swarm_size[] =  { 50,100,150,200,250,300,350,400,450,500,550,600,650,700,750,800,850,900,950,1000 };
	private int max_fmax[] =    {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,   0 };
	private int mean_fmax[] =   {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,   0 };
	
	private Dataset dataset = Dataset.UF75;
	private String files[] = { "uf75-0100.cnf", "uf75-02.cnf", "uf75-03.cnf", "uf75-04.cnf", "uf75-05.cnf",
							   "uf75-06.cnf", "uf75-07.cnf", "uf75-08.cnf", "uf75-09.cnf", "uf75-010.cnf"
							 };
	
	public void experiment() throws IOException {
		
		for ( int i = 0 ; i < swarm_size.length ; i++ ) {
			
			int max_f = 0;
			int mean_f = 0;
			
			for ( int j = 0 ; j < 10; j++ ) {
				
				Formula formula = new CnfReader(""+dataset.get_path()+"/"+files[j]).readFormula();
				PSO test = new PSO(dataset,files[j], swarm_size[i], max_iter, vel_max, cv_w, pb_w, gb_w );
				Particle best = test.solve();
				
				int current_f = best.fitness(formula);
				
				if ( current_f > max_f )
					max_f = current_f ;
				
				mean_f += current_f;
			}
			max_fmax[i] = max_f ;
			mean_fmax[i] = mean_f/10;
			
			System.out.println(swarm_size[i]+", "+max_fmax[i]+", "+mean_fmax[i]);
		}
	}
	
	public void show_results() {
		
		for (int i = 0; i < swarm_size.length; i++ ) 
			System.out.println(swarm_size[i]+", "+max_fmax[i]+", "+mean_fmax[i]);
	}

}
