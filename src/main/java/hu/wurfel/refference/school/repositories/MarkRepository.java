package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Mark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
	ArrayList<Mark> findAll();

	Optional<Mark> findByMarkID(Long markId);

	ArrayList<Mark> findAllByDiaryID(Integer diaryId);

	ArrayList<Mark> findAllByDate(Date date);

	ArrayList<Mark> findAllBySubjectID(Integer subjectId);

	ArrayList<Mark> findAllByMark(byte mark);

	ArrayList<Mark> findAllByDiaryIDAndSubjectID(Integer diaryId, Integer subjectId);

	ArrayList<Mark> findAllByDiaryIDAndDate(Integer diaryId, Date date);

	Optional<Mark> findByDiaryIDAndDateAndSubjectID(Integer diaryId, Date date, Integer subjectId);
}
