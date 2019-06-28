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
		
		
		//GA test = new GA(Dataset.UF20,"uf20-01.cnf",150,1500,10,25);
		//GA test = new GA(Dataset.UF50,"uf50-01.cnf",150,1500,10,25);
		GA test = new GA(Dataset.UF75,"uf75-01.cnf",150,1500,10,25);
		test.solve();
		
		//PSO test2 = new PSO(Dataset.UF20,"uf20-01.cnf",600,4000,20,6.5,2,3.5);
		//PSO test2 = new PSO(Dataset.UF50,"uf50-01.cnf",600,4000,20,6.5,2,3.5);
		PSO test2 = new PSO(Dataset.UF75,"uf75-01.cnf",600,4000,20,6.5,2,3.5);
		test2.solve();

		//GBWTuning test = new GBWTuning();
		//test.experiment();
	}
}