package hu.wurfel.new_school_reference.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByStartAndDeletedIsFalse(Date date);

    List<Diary> findAllByEndAndDeletedIsFalse(Date date);

    List<Diary> findAllByHeadTeacher_IdAndDeletedIsFalse(long id);

    List<Diary> findAllByHeadTeacher_NameAndDeletedIsFalse(String name);

    List<Diary> findAllByHeadTeacher_CardNumberAndDeletedIsFalse(long number);

    List<Diary> findAllByDivision_IdAndDeletedIsFalse(long id);

    List<Diary> findAllByDivision_GradeAndDivision_SignAndDeletedIsFalse(short grade, char sign);

    List<Diary> findAllByDivision_GradeAndDeletedIsFalse(short grade);

    List<Diary> findAllByDivision_SignAndDeletedIsFalse(char sign);


}
