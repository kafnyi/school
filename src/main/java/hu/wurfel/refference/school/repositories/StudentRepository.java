package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

	ArrayList<Student> findAll();

	Student findByID(long Id);

	Student findBySName(String Name);

	Student findBySBirth(String Birth);

	Student findBySDiaryID(int Diary);

}
