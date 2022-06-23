package hu.wurfel.refference.school.mark;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MarkCrudService {
    @Autowired
    private MarkRepository markRepository;

    public ArrayList<Mark> getAllMarks() {
        return new ArrayList<Mark>(markRepository.findAll());
    }

    public Mark getMarkByMarkid(Long mid) {
        return markRepository.findById(mid).get();
    }

    public ArrayList<Mark> getMarksByDiaryid(Integer did) {
        return markRepository.findAllByDiaryID(did);
    }

    public ArrayList<Mark> getMarksByDate(String date) {
        return markRepository.findAllByDate(date);
    }

    public ArrayList<Mark> getMarksBySubjectid(Integer sid) {
        return markRepository.findAllBySubjectID(sid);
    }

    public ArrayList<Mark> getMarksByMark(byte mark) {
        return markRepository.findAllByMark(mark);
    }

    public ArrayList<Mark> getMarksByDiaryidAndDate(Integer did, String date) {
        return markRepository.findAllByDiaryIDAndDate(did, date);
    }

    public ArrayList<Mark> getMarksByDiaryidAndSubjectid(Integer did, Integer sid) {
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
