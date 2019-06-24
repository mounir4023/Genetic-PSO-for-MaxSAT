package core;

import java.io.IOException;


import blindsearch.AStar;
import blindsearch.BFS;
import blindsearch.DFS;
import cnfmanagement.CnfDirectoryFinder;
import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import geneticalgorithm.GA;
import swarmintelligence.PSO;

public class Main {
	
	public static void main(String args[]) throws IOException {
		
		
		//GA test = new GA(Dataset.UF20,"uf20-01.cnf",1000,2000,25,20);
		//GA test = new GA(Dataset.UF50,"uf50-01.cnf",1000,2000,25,20);
		//GA test = new GA(Dataset.UF75,"uf75-01.cnf",1000,2000,25,20);
		
		//PSO test = new PSO(Dataset.UF20,"uf20-01.cnf",50,10000,20,2,3,5);
		PSO test = new PSO(Dataset.UF50,"uf50-01.cnf",50,10000,20,2,3,5);
		//PSO test = new PSO(Dataset.UF75,"uf75-01.cnf",50,10000,20,2,3,5);
		
		test.solve();
	}
}