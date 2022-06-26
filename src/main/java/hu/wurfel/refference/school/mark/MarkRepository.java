package hu.wurfel.refference.school.mark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

	List<Mark> findAllByDiaryID(Integer diaryId);

	List<Mark> findAllByDate(String date);

	List<Mark> findAllBySubjectID(Integer subjectId);

	List<Mark> findAllByMark(byte mark);

	List<Mark> findAllByDiaryIDAndSubjectID(Integer diaryId, Integer subjectId);

	List<Mark> findAllByDiaryIDAndDate(Integer diaryId, String date);

    Optional<Mark> findByDiaryIDAndDateAndSubjectID(Integer diaryId, String date, Integer subjectId);
}
