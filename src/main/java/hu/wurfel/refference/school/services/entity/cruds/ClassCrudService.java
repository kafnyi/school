package hu.wurfel.refference.school.services.entity.cruds;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.repositories.ClassRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ClassCrudService {
	@Autowired
	private ClassRepository classRepository;

	public ArrayList<Class> getAllClasses() {
		return classRepository.findAll();
	}

	public Class getClassByClassId(Integer cid) {
		return classRepository.findById(cid).get();
	}

	public ArrayList<Class> getClassesByGrade(short grade) {
		return classRepository.findAllByGrade(grade);
	}

	public ArrayList<Class> getClassesBySign(char sign) {
		return classRepository.findAllBySign(sign);
	}

	public ArrayList<Class> getClassesByYear(Year year) {
		return classRepository.findAllByYear(year);
	}

	public ArrayList<Class> getClassesByTid(Long tid) {
		return classRepository.findAllByTid(tid);
	}

	public ArrayList<Class> getClassesByGradeAndSign(short grade, char sign) {
		return classRepository.findAllByGradeAndSign(grade, sign);
	}

	public ArrayList<Class> getClassesByGradeAndYear(short grade, Year year) {
		return classRepository.findAllByGradeAndYear(grade, year);
	}

	public ArrayList<Class> getClassesBySignAndYear(char sign, Year year) {
		return classRepository.findAllBySignAndYear(sign, year);
	}

	public Class getClassByGradeAndSignAndYear(short grade, char sign, Year year) {
		return classRepository.findByGradeAndSignAndYear(grade, sign, year).get();
	}

	public Class saveClass(@NotNull Class division) {
		classRepository.save(division);
		return getClassByClassId(division.getId());
	}

	public Class saveClass(Integer classid, short grade, char sign, Year year, Long tid) {
		return saveClass(new Class(classid, grade, sign, year, tid));
	}

	public Class setClassId(@NotNull Class division, Integer classid) {
		division.setId(classid);
		return saveClass(division);
	}

	public Class setGrade(@NotNull Class division, short grade) {
		division.setGrade(grade);
		return saveClass(division);
	}

	public Class setSign(@NotNull Class division, char sign) {
		division.setSign(sign);
		return saveClass(division);
	}

	public Class setYear(@NotNull Class division, Year year) {
		division.setYear(year);
		return saveClass(division);
	}

	public Class setTid(@NotNull Class division, Long tid) {
		division.setTid(tid);
		return saveClass(division);
	}

	public void deleteClass(@NotNull Class division) {
		classRepository.delete(division);
	}
}
