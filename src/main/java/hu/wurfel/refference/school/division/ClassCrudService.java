package hu.wurfel.refference.school.division;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassCrudService {

    private final ClassRepository classRepository;

    public ClassCrudService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }


    public List<Class> getAll() {
        return (classRepository.findAll());
    }

    public Class getById(Integer classId) {
        return classRepository.findById(classId).get();
    }

    public List<Class> getAllByGrade(short grade) {
        return classRepository.findAllByGrade(grade);
    }

    public List<Class> getAllBySign(char sign) {
        return classRepository.findAllBySign(sign);
    }

    public List<Class> getAllByYear(Year year) {
        return classRepository.findAllByYear(year);
    }

    public List<Class> getAllByTeacherId(Long teacherId) {
        return classRepository.findAllByTeacherId(teacherId);
    }

    public List<Class> getAllByGradeAndSign(short grade, char sign) {
        return classRepository.findAllByGradeAndSign(grade, sign);
    }

    public List<Class> getAllByGradeAndYear(short grade, Year year) {
        return classRepository.findAllByGradeAndYear(grade, year);
    }

    public List<Class> getAllBySignAndYear(char sign, Year year) {
        return classRepository.findAllBySignAndYear(sign, year);
    }

    public Class getByGradeAndSignAndYear(short grade, char sign, Year year) {
        return classRepository.findByGradeAndSignAndYear(grade, sign, year).get();
    }

    public Class save(@NotNull Class division) {
        classRepository.save(division);
        return getById(division.getId());
    }

    public List<Class> save(Integer classId, short grade, char sign, Year year, Long tid) {
        ArrayList<Class> saved = new ArrayList<>();
        saved.add(save(new Class(classId, grade, sign, year, tid)));
        return saved;
    }

    public Class setGrade(@NotNull Class division, short grade) {
        division.setGrade(grade);
        return save(division);
    }

    public Class setSign(@NotNull Class division, char sign) {
        division.setSign(sign);
        return save(division);
    }

    public Class setYear(@NotNull Class division, Year year) {
        division.setYear(year);
        return save(division);
    }

    public Class setTeacherId(@NotNull Class division, Long teacherId) {
        division.setTeacherId(teacherId);
        return save(division);
    }

    public void delete(@NotNull Class division) {
        classRepository.delete(division);
    }
}
