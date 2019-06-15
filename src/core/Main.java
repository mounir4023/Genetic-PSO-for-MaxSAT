package core;

import java.io.IOException;


import blindsearch.AStar;
import blindsearch.BFS;
import blindsearch.DFS;
import cnfmanagement.CnfDirectoryFinder;
import cnfmanagement.CnfReader;
import cnfmanagement.Dataset;
import geneticalgorithm.GA;

public class Main {
	
	public static void main(String args[]) throws IOException {
		
		
		//GA test = new GA(Dataset.UF20,"uf20-0100.cnf",1000,1000,20,20);
		//GA test = new GA(Dataset.UF50,"uf50-0100.cnf",1000,1000,20,20);
		GA test = new GA(Dataset.UF75,"uf75-0100.cnf",1000,1000,20,20);
		test.solve();
		
		//System.out.println(Dataset.UF20.get_path());
		
		//CnfDirectoryFinder cdf = new CnfDirectoryFinder(Dataset.UF20.get_path());
		//cdf.listFiles();
					
		//AStar AStarTest = new AStar(Dataset.UF50.get_path()+"/uf50-01.cnf",50);
		//AStarTest.search();
		
	}

}
