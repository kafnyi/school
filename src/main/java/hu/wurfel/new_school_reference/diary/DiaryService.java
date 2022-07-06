package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.division.Class;
import hu.wurfel.new_school_reference.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DiaryService extends CrudService<Diary, DiaryRepository> {

@Autowired
	public DiaryService(DiaryRepository repository) {
		super(repository);
	}

	public List<Diary> findAllByStart(Date date){
	return repository.findAllByStart(date);
	}

	public List<Diary> findAllByEnd(Date date){
	return repository.findAllByEnd(date);
	}

	public List<Diary> findAllClass(Class division){
	return repository.findAllByDivision(division);
	}

	public List<Diary> findAllByTeacher(Teacher teacher){
	return repository.findAllByHeadTeacher(teacher);
	}




	public List<Diary> getList(Diary diary){
	List<Diary> diaryList = new ArrayList<>();
	diaryList.add(diary);
	return diaryList;
	}

}