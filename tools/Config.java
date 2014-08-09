package tools;

import com.sun.xml.internal.bind.api.AccessorException;

public final class Config {

	public static final String 	DRIVER_NAME = "org.postgresql.Driver";
	public static final String 	URL 		= "jdbc:postgresql://localhost:5433/DBRozkladGenerator";
	public static final String 	USER 		= "postgres";
	public static final String 	PASS 		= "root";
	
	public static final int 	COUNT_WEEKS_IN_FIRST_SEMESTR 	= 16;
	public static final int 	MAX_SUBJ_FOR_DAY 				= 6;
	public static final int 	COUT_WORKS_DAY_IN_WEEK 			= 5;
	public static final int 	GENERATION_SIZE 				= 5;
	
	
	
	private Config() throws AccessorException{
		throw new AccessorException();
		
	}

	
}
