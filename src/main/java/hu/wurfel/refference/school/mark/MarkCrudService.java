package hu.wurfel.refference.school.mark;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MarkCrudService {

    private final MarkRepository markRepository;

    public MarkCrudService(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    public List<Mark> getAll() {
        return markRepository.findAll();
    }

    public Mark getByMarkId(Long markId) {
        return markRepository.findById(markId).get();
    }

    public List<Mark> getAllByDiaryId(Integer diaryId) {
        return markRepository.findAllByDiaryID(diaryId);
    }

    public List<Mark> getAllByDate(String date) {
        return markRepository.findAllByDate(date);
    }

    public List<Mark> getAllBySubjectId(Integer subjectId) {
        return markRepository.findAllBySubjectID(subjectId);
    }

    public List<Mark> getAllByMark(byte mark) {
        return markRepository.findAllByMark(mark);
    }

    public List<Mark> getAllByDiaryIdAndDate(Integer diaryId, String date) {
        return markRepository.findAllByDiaryIDAndDate(diaryId, date);
    }

    public List<Mark> getAllByDiaryIdAndSubjectId(Integer diaryId, Integer subjectId) {
        return markRepository.findAllByDiaryIDAndSubjectID(diaryId, subjectId);
    }

    public Mark getByDiaryIdAndDateAndSubjectId(Integer diaryId, String date, Integer subjectId) {
        return markRepository.findByDiaryIDAndDateAndSubjectID(diaryId, date, subjectId).get();
    }

    public Mark save(@NotNull Mark mark) {
        markRepository.save(mark);
        return getByMarkId(mark.getId());
    }

    public List<Mark> save(Long markId, Integer diaryId, String date, Integer subjectId, byte mark) {
        ArrayList<Mark> saved = new ArrayList<>();
        saved.add(save(new Mark(markId, diaryId, date, subjectId, mark)));
        return saved;
    }

    public Mark setDiaryId(@NotNull Mark mark, Integer did) {
        mark.setDiaryID(did);
        return save(mark);
    }

    public Mark setDate(@NotNull Mark mark, String date) {
        mark.setDate(date);
        return save(mark);
    }

    public Mark setSubjectId(@NotNull Mark mark, Integer subjectId) {
        mark.setSubjectID(subjectId);
        return save(mark);
    }

    public Mark setMark(@NotNull Mark markEntity, byte mark) {
        markEntity.setMark(mark);
        return save(markEntity);
    }

    public void delete(@NotNull Mark mark) {
        markRepository.delete(mark);
    }
}
