package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.Naming;

import java.util.Date;

public class Validate {
	public static Naming entity(long ID, String Name, Date Date, int Diary) {
		if (ID == 0) {
			if (Name.trim().equals("")) {
				if (Date.toString().trim().equals("")) {
					if (Diary == 0) {
						return Naming.Empty;
					} else return Naming.Diary;
				} else return Naming.Birth;
			} else return Naming.Name;
		} else return Naming.ID;
	}
}
