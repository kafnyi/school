package hu.wurfel.refference.school.controll;

import hu.wurfel.refference.school.daos.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentService extends List<Student> {
	Student FindByID(int ID);
	Student FindByName(String Name);
	Student FindByDiary(int Diary);
}
