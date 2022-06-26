package hu.wurfel.refference.school.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

    List<Subject> findAllBySubjectName(String subjectName);

    List<Subject> findAllByTid(Long tId);

    Optional<Subject> findBySubjectNameAndTid(String subjectName, Long tId);
}
