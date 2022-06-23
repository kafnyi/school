package hu.wurfel.refference.school.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

    ArrayList<Diary> findAllByStudentId(Long studentId);

    ArrayList<Diary> findAllByClassID(Integer classId);

    Optional<Diary> findByStudentIdAndClassID(Long studentId, Integer classId);
}
