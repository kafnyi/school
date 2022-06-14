package hu.wurfel.refference.school.services.entity.cruds;

import hu.wurfel.refference.school.model.daos.Subject;
import hu.wurfel.refference.school.repositories.SubjectRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectCrudService {
    @Autowired
    private SubjectRepository subjectRepository;

    public ArrayList<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectBySubjectId(Integer id) {
        return subjectRepository.findById(id).get();
    }

    public ArrayList<Subject> getSubjectsByName(String name) {
        return subjectRepository.findAllBySubjectName(name);
    }

    public ArrayList<Subject> getSubjectsByTid(Long tid) {
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
