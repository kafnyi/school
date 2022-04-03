package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

	Iterable<Teacher> findAll();

	Teacher findByID(long Id);

	Teacher findByTName(String Name);

	Teacher findByTBirth(Date Birth);

	Teacher findByTDiaryID(int Diary);

}
