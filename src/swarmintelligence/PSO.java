package swarmintelligence;

import java.io.IOException;
import java.util.ArrayList;

import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import customdatastructures.Formula;
import geneticalgorithm.Individual;

public class PSO {

	private Dataset dataset;
	private Formula formula;
	private Swarm swarm;
	private int swarm_size;
	private int max_iter;
	private int max_vel;
	private double cv_w;
	private double pb_w;
	private double gb_w;
	
	public PSO ( Dataset dataset,
			String file,
			int swarm_size,
			int max_iter,
			int max_vel,
			double cv_w,
			double pb_w,
			double gb_w
		) throws IOException {

		this.dataset = dataset;
		this.formula = new CnfReader(this.dataset.get_path()+"/"+file).readFormula();
		this.swarm_size = swarm_size;
		this.max_iter = max_iter; 
		this.max_vel = max_vel; 
		this.cv_w = cv_w;
		this.pb_w = pb_w;
		this.gb_w = gb_w;
		this.swarm = new Swarm(this);
		this.swarm.init_particles();
	}
	
	public Particle solve() {
		
		// initialize the algorithm
		int iteration = 0;
		Particle g_best = this.swarm.best_position();
		Particle solution = null;

		// core of the evolution
		while ( iteration < max_iter && solution == null ) { 
		
			for ( int i = 0; i < this.swarm_size; i++ ) {
				
				this.swarm.get_particle(i).update_velocity(this, g_best);
				this.swarm.get_particle(i).move(this.formula);
			}
			
			Particle iteration_best = this.swarm.best_position();
			
			if ( iteration_best.better_than(g_best, this.formula) )
				g_best = iteration_best;
			
			if ( g_best.fitness(this.formula) == this.dataset.get_nb_clauses() )
				solution = g_best;
			
			iteration ++;
			
			System.out.println("\n\n=========== Iteration: "+iteration+" ============\n");
			System.out.println("best fitness: "+g_best.fitness(formula));
		}
		
		return g_best;
	}

	public double get_cv_w() {
		return this.cv_w;
	}
	
	public double get_pb_w() {
		return this.pb_w;
	}
	
	public double get_gb_w() {
		return this.gb_w;
	}
	
	public int get_max_vel() {
		return this.max_vel;
	}
	
	public int get_swarm_size() {
		return this.swarm_size;
	}

	public Dataset get_dataset() {
		return this.dataset;
	}
	
	public Formula get_formula() {
		return this.formula;
	}
	
}
