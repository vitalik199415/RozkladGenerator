package pojo;

import tools.ELevelSubject;

public class Subject{
	
	private String 			name; 		// Назва предмету пр: "Розробка програмного забезпечення"
	private String 			short_name; // Скорочена назва пр: "РПЗ"
	private int 			count_hour; // К-сть викладацьких годин = 2*к-сть пар
	private Teacher 		teacher;	// Викладач що веде пару
	private ELevelSubject 	level;		// Рівень складності предмету
	
}
