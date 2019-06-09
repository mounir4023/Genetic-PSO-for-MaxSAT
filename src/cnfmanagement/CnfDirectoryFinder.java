package cnfmanagement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CnfDirectoryFinder {
	
	/* given a directory , this objects looks for all cnf files inside it, and returns a list of CnfReaders one for each file */
	
	private String path;
	private List<CnfReader> readers;
	
	public CnfDirectoryFinder(String path) {
		super();
		this.path = path;
		this.collectCnfFiles();
	}
	
	public void collectCnfFiles() {
		
		ArrayList<CnfReader> list = new ArrayList<CnfReader>();
		
		File folder = new File(this.path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile() && listOfFiles[i].toString().endsWith(".cnf")) {
		    list.add(new CnfReader(this.path+listOfFiles[i].getName()));
		  } 
		}
		
		this.readers = list;
	}
	
	public void listFiles() {
		
		File folder = new File(this.path);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile() && listOfFiles[i].toString().endsWith(".cnf")) {
		    System.out.println(listOfFiles[i].getName());
		  } 
		}
	}
	
	public List<CnfReader> getReaders() {
		return this.readers;
	}

}
