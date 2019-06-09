package geneticalgorithm;

import java.util.ArrayList;

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
	
	// fitness function towards a given formula
	public int fitness( Formula formula ) {
		return formula.satisfiedClausesBySolutionCount(this.chromosome);
	}
	
	public int get_genes_count() {
		return this.genes_count;
	}
	
	public int get_gene(int index) {
		return this.chromosome.get(index);
	}
	
	public String toString() {
		return this.chromosome.toString();
	}
}
