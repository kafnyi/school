package hu.wurfel.refference.school.subject;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectCrudService {

    private final SubjectRepository subjectRepository;

    public SubjectCrudService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    public Subject getBySubjectId(Integer id) {
        return subjectRepository.findById(id).get();
    }

    public List<Subject> getByName(String name) {
        return subjectRepository.findAllBySubjectName(name);
    }

    public List<Subject> getByTeacherId(Long tid) {
        return subjectRepository.findAllByTid(tid);
    }

    public Subject getByNameAndTeacherId(String name, Long tid) {
        return subjectRepository.findBySubjectNameAndTid(name, tid).get();
    }

    public Subject save(@NotNull Subject subject) {
        subjectRepository.save(subject);
        return getBySubjectId(subject.getId());
    }

    public List<Subject> save(Integer id, String name, Long tid) {
        ArrayList<Subject> saved = new ArrayList<>();
        saved.add(save(new Subject(id, name, tid)));
        return saved;
    }

    public Subject setId(@NotNull Subject subject, Integer id) {
        subject.setId(id);
        return save(subject);
    }

    public Subject setSubjectName(@NotNull Subject subject, String name) {
        subject.setSubjectName(name);
        return save(subject);
    }

    public Subject setTeacherId(@NotNull Subject subject, Long tid) {
        subject.setTid(tid);
        return save(subject);
    }

    public void delete(@NotNull Subject subject) {
        subjectRepository.delete(subject);
    }
}
