package hu.wurfel.new_school_reference.division;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {

    List<Class> findAllByGradeAndDeletedIsFalse(short grade);

    List<Class> findAllBySignAndDeletedIsFalse(char sign);

    Class findByGradeAndSignAndDeletedIsFalse(short grade , char sign);

}
