package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

	Iterable<Student> FindAll();

	Student FindById(Long Id);

	Student FindByName(String Name);

	Student FindByDiary(int Diary);

}
