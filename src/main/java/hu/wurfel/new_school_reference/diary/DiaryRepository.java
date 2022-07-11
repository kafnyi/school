package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByStart(Date date);

    List<Diary> findAllByEnd(Date date);

    List<Diary> findAllByHeadTeacher(long id);

    List<Diary> findAllByHeadTeacher_Id(long id);

    List<Diary> findAllByHeadTeacher_Name(String name);

    List<Diary> findAllByHeadTeacher_CardNumber(long number);

    List<Diary> findAllByDivision_Id(long id);

    List<Diary> findAllByDivision_GradeAndDivision_Sign(short grade, char sign);

    List<Diary> findAllByDivision_Grade(short grade);

    List<Diary> findAllByDivision_Sign(char sign);


}
