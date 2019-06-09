package cnfmanagement;

public enum Dataset {
	
	UF20("uf20-91",20,91),
	UF50("uf50-218",50,218),
	UUF50("uuf50-218",50,218),
	UF75("uf75-325",75,325),
	UUF75("uuf75-325",75,325);
	
	private String path;
	private int nb_vars;
	private int nb_clauses;
	
	private Dataset(String directory,int nb_vars, int nb_clauses) {
		String os_path = System.getProperty("user.dir");
		this.path = os_path+"/src/datasets/"+directory;
		this.nb_vars = nb_vars;
		this.nb_clauses = nb_clauses;
	}
	
	public String get_path() {
		return this.path;
	}
	
	public int get_nb_vars() {
		return this.nb_vars;
	}
	
	public int get_nb_clauses() {
		return this.nb_clauses;
	}
}
