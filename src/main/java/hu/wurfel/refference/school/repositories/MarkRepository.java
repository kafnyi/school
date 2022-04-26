package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Mark;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

public interface MarkRepository extends CrudRepository<Mark, Long> {
	ArrayList<Mark> findAll();

	Optional<Mark> findById(Long markId);

	ArrayList<Mark> findAllByDiaryID(Integer diaryId);

	ArrayList<Mark> findAllByDate(Date date);

	ArrayList<Mark> findAllBySubjectID(Integer subjectId);

	ArrayList<Mark> findAllByMark(byte mark);

	ArrayList<Mark> findAllByDiaryIDAndSubjectID(Integer diaryId, Integer subjectId);

	ArrayList<Mark> findAllByDiaryIDAndDate(Integer diaryId, Date date);

	Optional<Mark> findByDiaryIDAndDateAndSubjectID(Integer diaryId, Date date, Integer subjectId);
}
