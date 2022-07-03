package hu.wurfel.refference.school.mark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

	List<Mark> findAllByDiaryId(Integer diaryId);

	List<Mark> findAllByDate(String date);

	List<Mark> findAllBySubjectId(Integer subjectId);

	List<Mark> findAllByMark(byte mark);

	List<Mark> findAllByDiaryIdAndSubjectId(Integer diaryId, Integer subjectId);

	List<Mark> findAllByDiaryIdAndDate(Integer diaryId, String date);

	Optional<Mark> findByDiaryIdAndDateAndSubjectId(Integer diaryId, String date, Integer subjectId);
}
