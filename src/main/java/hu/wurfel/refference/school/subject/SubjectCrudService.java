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

    public List<Subject> getAllSubjects() {
        return new ArrayList<>(subjectRepository.findAll());
    }

    public Subject getSubjectBySubjectId(Integer id) {
        return subjectRepository.findById(id).get();
    }

    public List<Subject> getSubjectsByName(String name) {
        return subjectRepository.findAllBySubjectName(name);
    }

    public List<Subject> getSubjectsByTid(Long tid) {
        return subjectRepository.findAllByTid(tid);
    }

    public Subject getSubjectByNameAndTid(String name, Long tid) {
        return subjectRepository.findBySubjectNameAndTid(name, tid).get();
    }

    public Subject saveSubject(@NotNull Subject subject) {
        subjectRepository.save(subject);
        return getSubjectBySubjectId(subject.getId());
    }

    public Subject saveSubject(Integer id, String name, Long tid) {
        return saveSubject(new Subject(id, name, tid));
    }

    public Subject setSubjectId(@NotNull Subject subject, Integer id) {
        subject.setId(id);
        return saveSubject(subject);
    }

    public Subject setSubjectName(@NotNull Subject subject, String name) {
        subject.setSubjectName(name);
        return saveSubject(subject);
    }

    public Subject setSubjectTid(@NotNull Subject subject, Long tid) {
        subject.setTid(tid);
        return saveSubject(subject);
    }

    public void delete(@NotNull Subject subject) {
        subjectRepository.delete(subject);
    }
}
