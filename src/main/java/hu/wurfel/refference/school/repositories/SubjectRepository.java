package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Integer> {
    ArrayList<Subject> findAll();

    Optional<Subject> findById(Integer subjectId);

    ArrayList<Subject> findAllBySubjectName(String subjectName);

    ArrayList<Subject> findAllByTid(Long tId);

    Optional<Subject> findBySubjectNameAndTid(String subjectName, Long tId);
}
