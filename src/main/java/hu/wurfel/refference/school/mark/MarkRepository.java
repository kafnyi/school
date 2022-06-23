package hu.wurfel.refference.school.mark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {

    ArrayList<Mark> findAllByDiaryID(Integer diaryId);

    ArrayList<Mark> findAllByDate(String date);

    ArrayList<Mark> findAllBySubjectID(Integer subjectId);

    ArrayList<Mark> findAllByMark(byte mark);

    ArrayList<Mark> findAllByDiaryIDAndSubjectID(Integer diaryId, Integer subjectId);

    ArrayList<Mark> findAllByDiaryIDAndDate(Integer diaryId, String date);

    Optional<Mark> findByDiaryIDAndDateAndSubjectID(Integer diaryId, String date, Integer subjectId);
}
