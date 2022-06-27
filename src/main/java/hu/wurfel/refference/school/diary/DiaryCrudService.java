package hu.wurfel.refference.school.diary;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiaryCrudService {

    private final DiaryRepository diaryRepository;

    public DiaryCrudService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public List<Diary> getAllDiaries() {
        return new ArrayList<>(diaryRepository.findAll());
    }

    public Diary getByDiaryid(Integer Id) {
        return diaryRepository.findById(Id).get();
    }

    public List<Diary> getByScid(Long scid) {
        return diaryRepository.findAllByStudentId(scid);
    }

    public List<Diary> getByClassid(Integer cid) {
        return diaryRepository.findAllByClassID(cid);
    }

    public Diary getByScidAndClassid(Long scid, Integer cid) {
        return diaryRepository.findByStudentIdAndClassID(scid, cid).get();
    }

    public Diary save(@NotNull Diary diary) {
        diaryRepository.save(diary);
        return getByDiaryid(diary.getId());
    }

    public Diary save(Integer did, Long scid, Integer cid) {
        return save(new Diary(did, scid, cid));
    }

    public Diary setId(@NotNull Diary diary, Integer did) {
        diary.setId(did);
        return save(diary);
    }

    public Diary setStidentId(@NotNull Diary diary, Long scid) {
        diary.setStudentId(scid);
        return save(diary);
    }

    public Diary setClassId(@NotNull Diary diary, Integer cid) {
        diary.setClassID(cid);
        return save(diary);
    }

    public void delete(@NotNull Diary diary) {
        diaryRepository.delete(diary);
    }
}
