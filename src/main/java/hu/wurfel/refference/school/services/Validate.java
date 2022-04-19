package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.Naming;
import hu.wurfel.refference.school.model.StudentRequestDto;
import hu.wurfel.refference.school.model.StudentSearchDto;

public class Validate {
	public static Naming searchFor(StudentSearchDto S) {
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

	public static StudentRequestDto requestContent(StudentRequestDto ssrd) {
		if (ssrd.getID() == null || ssrd.getID().equals("")) ssrd.setID(String.valueOf(0));
		if (ssrd.getName() == null) ssrd.setName("");
		if (ssrd.getBirth() == (null)) ssrd.setBirth("");
		if (ssrd.getDiary() == (null) || ssrd.getDiary().equals("")) ssrd.setDiary(String.valueOf(0));
		return ssrd;
	}
}
