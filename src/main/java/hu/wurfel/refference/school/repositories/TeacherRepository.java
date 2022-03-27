package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

	Iterable<Teacher> FindAll();

	Teacher FindById(Long Id);

	Teacher FindByName(String Name);

	Teacher FindByDiary(int Diary);


}
