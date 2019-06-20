package swarmintelligence;

import java.util.ArrayList;

import swarmintelligence.Particle;


public class Swarm {

	private PSO configuration;
	private ArrayList<Particle> list;
	
	public Swarm(PSO configuration) { 
		
		this.configuration = configuration;
		this.list = new ArrayList<Particle> ();
	}
	
	public void init_particles() { 
		
		for( int i=0; i < this.configuration.get_swarm_size() ; i++ ) {
			
			Particle particle = new Particle(this.configuration.get_dataset().get_nb_vars());
			this.list.add(particle);
		}
	}
	
	public Particle best_position() {
		
		Particle position = new Particle(this.list.get(0).get_position());
		
		for ( int i = 0; i < this.configuration.get_swarm_size(); i++ ) {
			
			if ( this.list.get(i).better_than(position,this.configuration.get_formula()) )
				position = new Particle(this.list.get(0).get_position());
		}
		
		return position;
	}
	
	public Particle get_particle(int index) {
		return this.list.get(index);
	}
}
