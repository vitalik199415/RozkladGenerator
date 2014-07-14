package tools;

public enum ELevelSubject {
	
	ELEMENTARY(1),
	PRE_INTERMEDIATE(2),
	INTERMEDIATE(3),
	UPPER_NTERMEDIATE(4),
	ADVANCED(5);
	
	private int level;
	
	private ELevelSubject(int _level){
		this.level = _level;
	}
	

}
