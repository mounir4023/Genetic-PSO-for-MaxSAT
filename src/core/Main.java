package core;

import java.io.IOException;


import blindsearch.AStar;
import blindsearch.BFS;
import blindsearch.DFS;
import cnfmanagement.CnfDirectoryFinder;
import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import customdatastructures.Formula;
import geneticalgorithm.GA;
import geneticalgorithm.Individual;
import swarmintelligence.PSO;

public class Main {
	
	public static void main(String args[]) throws IOException {
		
		
		//GA test = new GA(Dataset.UF20,"uf20-01.cnf",1000,2000,25,20);
		//GA test = new GA(Dataset.UF50,"uf50-01.cnf",1000,2000,25,20);
		//GA test = new GA(Dataset.UF75,"uf75-01.cnf",1000,2000,25,20);
		
		//PSO test = new PSO(Dataset.UF20,"uf20-01.cnf",50,10000,20,2,3,5);
		//PSO test = new PSO(Dataset.UF50,"uf50-01.cnf",50,10000,20,2,3,5);
		//PSO test = new PSO(Dataset.UF75,"uf75-01.cnf",50,10000,20,2,3,5);
		

		MutationRateTuning test = new MutationRateTuning();
		test.experiment();
		test.show_results();
		/*
		Dataset dataset = Dataset.UF75;
		Formula formula = new CnfReader(""+dataset.get_path()+"/"+"uf75-01.cnf").readFormula();
		Formula formula2 = new CnfReader(""+dataset.get_path()+"/"+"uf75-02.cnf").readFormula();
		Formula formula3 = new CnfReader(""+dataset.get_path()+"/"+"uf75-01.cnf").readFormula();
		
		Individual tmp = new Individual(dataset.get_nb_vars());
		
		System.out.println(tmp.fitness(formula));
		System.out.println(tmp.fitness(formula2));
		System.out.println(tmp.fitness(formula));
		System.out.println(tmp.fitness(formula3));
		System.out.println(tmp.fitness(formula3));
		*/
	}
}