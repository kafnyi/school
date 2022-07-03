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

    public List<Diary> getAll() {
        return diaryRepository.findAll();
    }

    public Diary getById(Integer Id) {
        return diaryRepository.findById(Id).get();
    }

    public List<Diary> getAllByStudentId(Long studentId) {
        return diaryRepository.findAllByStudentId(studentId);
    }

    public List<Diary> getAllByClassId(Integer classId) {
        return diaryRepository.findAllByClassID(classId);
    }

    public Diary getByStudentIdAndClassId(Long studentId, Integer classId) {
        return diaryRepository.findByStudentIdAndClassID(studentId, classId).get();
    }

    public Diary save(@NotNull Diary diary) {
        diaryRepository.save(diary);
        return getById(diary.getId());
    }

    public List<Diary> save(Integer diaryId, Long studentId, Integer classId) {
        ArrayList<Diary> saved = new ArrayList<>();
        saved.add(save(new Diary(diaryId, studentId, classId)));
        return saved;
    }

    public Diary setStudentId(@NotNull Diary diary, Long studentId) {
        diary.setStudentId(studentId);
        return save(diary);
    }

    public Diary setClassId(@NotNull Diary diary, Integer classId) {
        diary.setClassId(classId);
        return save(diary);
    }

    public void delete(@NotNull Diary diary) {
        diaryRepository.delete(diary);
    }
}
