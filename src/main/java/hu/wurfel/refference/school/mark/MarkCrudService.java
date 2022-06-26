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

    public List<Mark> getAllMarks() {
        return new ArrayList<>(markRepository.findAll());
    }

    public Mark getMarkByMarkid(Long mid) {
        return markRepository.findById(mid).get();
    }

    public List<Mark> getMarksByDiaryid(Integer did) {
        return markRepository.findAllByDiaryID(did);
    }

    public List<Mark> getMarksByDate(String date) {
        return markRepository.findAllByDate(date);
    }

    public List<Mark> getMarksBySubjectid(Integer sid) {
        return markRepository.findAllBySubjectID(sid);
    }

    public List<Mark> getMarksByMark(byte mark) {
        return markRepository.findAllByMark(mark);
    }

    public List<Mark> getMarksByDiaryidAndDate(Integer did, String date) {
        return markRepository.findAllByDiaryIDAndDate(did, date);
    }

    public List<Mark> getMarksByDiaryidAndSubjectid(Integer did, Integer sid) {
        return markRepository.findAllByDiaryIDAndSubjectID(did, sid);
    }

    public Mark getMarkByDiaryidAndDateAndSubjectid(Integer did, String date, Integer sid) {
        return markRepository.findByDiaryIDAndDateAndSubjectID(did, date, sid).get();
    }

    public Mark saveMark(@NotNull Mark mark) {
        markRepository.save(mark);
        return getMarkByMarkid(mark.getId());
    }

    public Mark saveMark(Long mid, Integer did, String date, Integer sid, byte mark) {
        return saveMark(new Mark(mid, did, date, sid, mark));
    }

    public Mark setMarkId(@NotNull Mark mark, Long mid) {
        mark.setId(mid);
        return saveMark(mark);
    }

    public Mark setMarkDiaryid(@NotNull Mark mark, Integer did) {
        mark.setDiaryID(did);
        return saveMark(mark);
    }

    public Mark setDate(@NotNull Mark mark, String date) {
        mark.setDate(date);
        return saveMark(mark);
    }

    public Mark setSubjectid(@NotNull Mark mark, Integer sid) {
        mark.setSubjectID(sid);
        return saveMark(mark);
    }

    public Mark setMark(@NotNull Mark mark, byte m) {
        mark.setMark(m);
        return saveMark(mark);
    }

    public void deleteMark(@NotNull Mark mark) {
        markRepository.delete(mark);
    }
}
