package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.Naming;
import hu.wurfel.refference.school.model.StudentSearchDto;

public class Validate {
	public static Naming StudentSearchDTO(StudentSearchDto S) {
		if (S.getID() == 0) {
			if (S.getName().trim().equals("")) {
				if (S.getDate().trim().equals("")) {
					if (S.getDiary() == 0) {
						return Naming.Empty;
					} else return Naming.Diary;
				} else return Naming.Birth;
			} else return Naming.Name;
		} else return Naming.ID;
	}
}
