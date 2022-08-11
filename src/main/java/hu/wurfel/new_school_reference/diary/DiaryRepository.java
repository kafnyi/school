package hu.wurfel.new_school_reference.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findAllByStartAndDeleted(Date date, boolean deleted);

    List<Diary> findAllByEndAndDeleted(Date date, boolean deleted);

    List<Diary> findAllByHeadTeacher_IdAndDeleted(long id, boolean deleted);

    List<Diary> findAllByHeadTeacher_NameAndDeleted(String name, boolean deleted);

    List<Diary> findAllByHeadTeacher_CardNumberAndDeleted(long number, boolean deleted);

    List<Diary> findAllByDivision_IdAndDeleted(long id, boolean deleted);

    List<Diary> findAllByDivision_GradeAndDivision_SignAndDeleted(short grade, char sign, boolean deleted);

    List<Diary> findAllByDivision_GradeAndDeleted(short grade, boolean deleted);

    List<Diary> findAllByDivision_SignAndDeleted(char sign, boolean deleted);


}
