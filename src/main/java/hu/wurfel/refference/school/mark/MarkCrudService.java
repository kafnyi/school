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
        return new ArrayList<>(markRepository.findAll());
    }

    public Mark getByMarkid(Long mid) {
        return markRepository.findById(mid).get();
    }

    public List<Mark> getByDiaryid(Integer did) {
        return markRepository.findAllByDiaryID(did);
    }

    public List<Mark> getByDate(String date) {
        return markRepository.findAllByDate(date);
    }

    public List<Mark> getBySubjectid(Integer sid) {
        return markRepository.findAllBySubjectID(sid);
    }

    public List<Mark> getByMark(byte mark) {
        return markRepository.findAllByMark(mark);
    }

    public List<Mark> getByDiaryidAndDate(Integer did, String date) {
        return markRepository.findAllByDiaryIDAndDate(did, date);
    }

    public List<Mark> getByDiaryidAndSubjectid(Integer did, Integer sid) {
        return markRepository.findAllByDiaryIDAndSubjectID(did, sid);
    }

    public Mark getByDiaryidAndDateAndSubjectid(Integer did, String date, Integer sid) {
        return markRepository.findByDiaryIDAndDateAndSubjectID(did, date, sid).get();
    }

    public Mark save(@NotNull Mark mark) {
        markRepository.save(mark);
        return getByMarkid(mark.getId());
    }

    public Mark save(Long mid, Integer did, String date, Integer sid, byte mark) {
        return save(new Mark(mid, did, date, sid, mark));
    }

    public Mark setId(@NotNull Mark mark, Long mid) {
        mark.setId(mid);
        return save(mark);
    }

    public Mark setDiaryid(@NotNull Mark mark, Integer did) {
        mark.setDiaryID(did);
        return save(mark);
    }

    public Mark setDate(@NotNull Mark mark, String date) {
        mark.setDate(date);
        return save(mark);
    }

    public Mark setSubjectid(@NotNull Mark mark, Integer sid) {
        mark.setSubjectID(sid);
        return save(mark);
    }

    public Mark setMark(@NotNull Mark mark, byte m) {
        mark.setMark(m);
        return save(mark);
    }

    public void delete(@NotNull Mark mark) {
        markRepository.delete(mark);
    }
}
