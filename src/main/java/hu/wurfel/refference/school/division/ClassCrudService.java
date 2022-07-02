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

    public Class getByClassId(Integer cid) {
        return classRepository.findById(cid).get();
    }

    public List<Class> getByGrade(short grade) {
        return classRepository.findAllByGrade(grade);
    }

    public List<Class> getBySign(char sign) {
        return classRepository.findAllBySign(sign);
    }

    public List<Class> getByYear(Year year) {
        return classRepository.findAllByYear(year);
    }

    public List<Class> getByTeacherId(Long tid) {
        return classRepository.findAllByTeacherId(tid);
    }

    public List<Class> getByGradeAndSign(short grade, char sign) {
        return classRepository.findAllByGradeAndSign(grade, sign);
    }

    public List<Class> getByGradeAndYear(short grade, Year year) {
        return classRepository.findAllByGradeAndYear(grade, year);
    }

    public List<Class> getBySignAndYear(char sign, Year year) {
        return classRepository.findAllBySignAndYear(sign, year);
    }

    public Class getByGradeAndSignAndYear(short grade, char sign, Year year) {
        return classRepository.findByGradeAndSignAndYear(grade, sign, year).get();
    }

    public Class save(@NotNull Class division) {
        classRepository.save(division);
        return getByClassId(division.getId());
    }

    public List<Class> save(Integer classid, short grade, char sign, Year year, Long tid) {
        ArrayList<Class> saved = new ArrayList<>();
        saved.add(save(new Class(classid, grade, sign, year, tid)));
        return saved;
    }

    public Class setId(@NotNull Class division, Integer classid) {
        division.setId(classid);
        return save(division);
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

    public Class setTeacherId(@NotNull Class division, Long tid) {
        division.setTeacherId(tid);
        return save(division);
    }

    public void delete(@NotNull Class division) {
        classRepository.delete(division);
    }
}
