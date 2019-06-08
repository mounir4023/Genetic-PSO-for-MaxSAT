package cnfmanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import customdatastructures.Clause;
import customdatastructures.Formula;

public class CnfReader {

	/* given a path of a cnf file, this object turns it into a CNF Formula */
	
	private String path;

	public CnfReader(String path) {
		super();
		this.path = path;
	}

	public Formula readFormula() throws IOException {

		List<Clause> clauses = new ArrayList<Clause>();

		BufferedReader reader = new BufferedReader(new FileReader(this.path));
		String line = reader.readLine();

		while (line.equals("") == false) { 

			if (line.charAt(0) != 'c' && line.charAt(0) != 'p' && line.charAt(0) != '0' && line.charAt(0) != '%') {
				
				String[] tmp = line.split("\\s+");
				String[] splited;
				if(tmp[0].equals("")) {
					splited = Arrays.copyOfRange(tmp,1, tmp.length - 1);					
				}
				else {
					splited = Arrays.copyOfRange(tmp,0, tmp.length - 1);					
				}

				Clause clause = new Clause();
				for ( String l : splited) {
					clause.addLiteral(Integer.parseInt(l));
				}
				
				clauses.add(clause);
			}
			
			line = reader.readLine();
		}

		reader.close();
		
		return new Formula(clauses);
	}

}
