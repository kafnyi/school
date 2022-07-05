package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.division.Class;
import hu.wurfel.new_school_reference.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary,Long> {

    List<Diary> findAllByDivision(Class division);

    List<Diary> findAllByHeadTeacher(Teacher teacher);

    List<Diary> findAllByStart(Date date);

    List<Diary> findAllByEnd(Date date);


}
