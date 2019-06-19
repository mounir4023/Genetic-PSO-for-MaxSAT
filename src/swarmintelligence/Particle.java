package swarmintelligence;

import java.util.ArrayList;
import java.util.Random;

import customdatastructures.Formula;

public class Particle {
	
	private ArrayList<Integer> position;
	private Particle p_best;
	private int velocity;
	private int dimension;

	// constructor to generate random position and velocity [1,5]
	public Particle( int dimension ) {
		
		this.position = new ArrayList<Integer>();
		this.dimension = dimension;
		Random generator = new Random();
		this.velocity = generator.nextInt(4)+1;
		
		for ( int i = 0; i < this.dimension ; i++ ) 
			position.add((int)(Math.round( Math.random() )));
		
		this.p_best = new Particle(this.copy_position());
	}
	
	// generates particle with given position for saving purposes 
	public Particle ( ArrayList<Integer> position ) {
		
		for ( int i = 0; i < this.dimension ; i++ )
			this.position.add(position.get(i));
	}
	
	// fitness function towards a given formula
	public int fitness( Formula formula ) { 
		return formula.satisfiedClausesBySolutionCount(this.position);
	}
	
	// calculate V(t+1) using V(t) & p_best & g_best 
	public void update_velocity( PSO configuration , Particle g_best) {
		
		int pb_d = this.distance_to(this.p_best, configuration.get_formula());
		int gb_d = this.distance_to(g_best, configuration.get_formula());
		
		double pb_c = Math.random();
		double gb_c = Math.random();
		
		double cv_w = configuration.get_cv_w();
		double pb_w = configuration.get_pb_w();
		double gb_w = configuration.get_gb_w();
		
		this.velocity = (int) Math.round( cv_w * this.velocity + pb_w * pb_c * pb_d + gb_w * gb_c * gb_d ) ;
		
		if ( this.velocity > configuration.get_max_vel() )
			this.velocity = (int) Math.round( Math.random() * 5) + 5;
	}
	
	// position at X(t+1) using V(t+1)
	public void move( Formula formula) {
		
		for (int i = 0; i < this.velocity; i++) {
			
			Random generator = new Random();
			int mutated = generator.nextInt(this.dimension);
			this.position.set(mutated, (this.position.get(mutated) +1) % 2);
		}
		
		if ( this.better_than(this.p_best, formula) )
			this.p_best = new Particle(this.copy_position());
	}
	
	// compare current position to a given position
	public boolean better_than ( Particle position, Formula formula ) {
		return this.fitness(formula) >= position.fitness(formula);
	}
	
	// calculates the "Hamming" distance towards a given position
	public int distance_to( Particle position, Formula formula ) {
		
		int diff_count = 0;
		
		for ( int i = 0; i < this.dimension; i++)
			if ( this.position.get(i) != position.get_position().get(i) )
				diff_count++;
		
		return diff_count;
	}
	
	// return a copy of the current position
	public ArrayList<Integer> copy_position() {
		
		ArrayList<Integer> copy = new ArrayList<Integer>();
		
		for ( int i = 0; i < this.dimension; i ++)
			copy.add(i, this.position.get(i));
		
		return copy;
	}
	
	public ArrayList<Integer> get_position() {
		return this.position;
	}
}
