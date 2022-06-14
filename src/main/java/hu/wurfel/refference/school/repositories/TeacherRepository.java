package hu.wurfel.refference.school.repositories;

import hu.wurfel.refference.school.model.daos.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    ArrayList<Teacher> findAll();

    Optional<Teacher> findById(Long id);

    ArrayList<Teacher> findAllByName(String name);

    ArrayList<Teacher> findAllByBirthDate(String birthDate);

    Optional<Teacher> findByNameAndBirthDate(String name, Date birthDate);
}
