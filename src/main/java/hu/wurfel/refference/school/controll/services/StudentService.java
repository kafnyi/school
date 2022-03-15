package hu.wurfel.refference.school.controll.services;

import hu.wurfel.refference.school.model.daos.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentService extends List<Student> {
	Student FindByID(int ID);
	Student FindByName(String Name);
	Student FindByDiary(int Diary);
}
