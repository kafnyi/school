package hu.wurfel.refference.school.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {

	List<Diary> findAllByStudentId(Long studentId);

	List<Diary> findAllByClassID(Integer classId);

    Optional<Diary> findByStudentIdAndClassID(Long studentId, Integer classId);
}
