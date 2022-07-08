package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.division.Class;
import hu.wurfel.new_school_reference.division.ClassDto;
import hu.wurfel.new_school_reference.division.ClassService;
import hu.wurfel.new_school_reference.teacher.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.JdbcAggregateOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DiaryService extends CrudService<Diary, DiaryRepository> {

	private ClassService classService;

	@Autowired
	public DiaryService(DiaryRepository repository) {
		super(repository);
	}

	public List<Diary> findAllByStart(Date date){
		return repo.findAllByStart(date);
	}

	public List<Diary> findAllByEnd(Date date){
		return repo.findAllByEnd(date);
	}

	public List<Diary> findAllByClass(Class division){
		return repo.findAllByDivision(division);
	}

	public List<Diary> findAllByTeacher(Teacher teacher){
		return repo.findAllByHeadTeacher(teacher);
	}


	public List<Diary> getList(Diary diary) {
		List<Diary> diaryList = new ArrayList<>();
		diaryList.add(diary);
		return diaryList;
	}

	public List<?> findAllByClass(ClassDto classDto) {
		Class division = this.classService.findById(classDto.getId());
		return null;
	}
}