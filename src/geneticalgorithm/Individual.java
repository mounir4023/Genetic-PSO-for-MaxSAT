package geneticalgorithm;

import java.util.ArrayList;
import java.util.Random;

import customdatastructures.Formula;

public class Individual {

	private ArrayList<Integer> chromosome;
	private int genes_count;
	
	// constructor to  generate random chromosome
	public Individual(int genes_count) { 
		
		this.chromosome = new ArrayList<Integer>();
		this.genes_count = genes_count;
		
		for ( int i = 0; i < this.genes_count ; i++ ) {
			chromosome.add((int)(Math.round( Math.random() )));
		} 
	}
	
	// constructor to generate by crossover
	public Individual(Individual parent1, Individual parent2) {
		
		this.chromosome = new ArrayList<Integer>();
		this.genes_count = Math.min(parent1.get_genes_count(), parent2.get_genes_count());
		
		int gene_index = 0;
		
		while ( gene_index < this.genes_count/2 + 1 ) {
			this.chromosome.add(parent1.get_gene(gene_index));
			gene_index++;
		}
		
		while ( gene_index < this.genes_count ) {
			this.chromosome.add(parent2.get_gene(gene_index));
			gene_index++;
		}
	}
	
	// creates a duplicate copy of this individual
	public Individual clone() {
		
		Individual copy = new Individual(this.genes_count);
		for ( int i = 0; i< this.genes_count; i++) {
			copy.get_chromosome().set(i,this.chromosome.get(i));
		}
		
		return copy;
	}
	
	// randomly swap 0 and 1, between 1 and 10 genes
	public void mutate () {
		
		Random generator = new Random();
		int nb_mutations = generator.nextInt(10) +1 ;
		
		for ( int i=0; i< nb_mutations ; i++) {
			int mutated_gene = generator.nextInt(this.chromosome.size());
			int new_gene = this.chromosome.remove(mutated_gene);
			new_gene = ( new_gene +1 ) % 2 ;
			this.chromosome.add(mutated_gene, new_gene);
		}
	}
	
	// fitness function towards a given formula
	public int fitness( Formula formula ) { 
		return formula.satisfiedClausesBySolutionCount(this.chromosome);
	}
	
	// tells if this has better fitness than a rival towards a given formula
	public boolean better_than( Individual rival, Formula formula) {
		return this.fitness(formula) >= rival.fitness(formula);
	}
	
	public int get_genes_count() {
		return this.genes_count;
	}
	
	public int get_gene(int index) {
		return this.chromosome.get(index);
	}
	
	public void set_gene(int index, int value) {
		this.chromosome.set(index, value);
	}
	
	public ArrayList<Integer> get_chromosome() {
		return this.chromosome;
	}
	
	public String toString() {
		return this.chromosome.toString();
	}
}
