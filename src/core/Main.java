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
		
		
		GA test = new GA();
		test.solve();
		
		//System.out.println(Dataset.UF20.get_path());
		
		//CnfDirectoryFinder cdf = new CnfDirectoryFinder(Dataset.UF20.get_path());
		//cdf.listFiles();
					
		
		// PART 1 TEST 
		//CnfDirectoryFinder finder = new CnfDirectoryFinder("/home/mounir4023/Developpement/testing/java/SATSolver/materials/Dataset1/");
		//finder.collectCnfFiles();
		
		//System.out.println(finder.getReaders().get(100).readFormula());
		
		//for ( CnfReader r : finder.getReaders())
			//System.out.println(finder.getReaders().indexOf(r)+" "+r.readFormula().getClauses().size());
		
		
		// PART 2 TEST
		/*
		  Experiment e = new Experiment("/home/mounir4023/Developpement/testing/java/SATSolver/materials/Dataset1/",20,91,3);
		  e.tryRandSolution();
		 */
		 
		/*
		for ( int i =0; i<1000; i++) {
			e.tryRandSolution();
		}
		*/
		
		// PART 3 TEST
		//DFS DFSTest = new DFS("/home/mounir4023/Developpement/testing/java/SATSolver/materials/Dataset1/uf20-01.cnf",20);
		//DFSTest.search();
		
		//BFS BFSTest = new BFS("/home/mounir4023/Developpement/testing/java/SATSolver/materials/Dataset1/uf20-01.cnf",20);
		//BFSTest.search();
		
		//AStar AStarTest = new AStar("/home/mounir4023/Developpement/testing/java/SATSolver/materials/Dataset1/uf20-01.cnf",20);
		//AStarTest.search();
		
	}

}
