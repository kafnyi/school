package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.diary.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    List<Class> findAllByGrade(short grade);

    List<Class> findAllBySign(char sign);

}
