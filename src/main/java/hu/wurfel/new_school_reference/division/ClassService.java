package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.diary.Diary;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ClassService extends CrudService<Class, ClassRepository> {

	@Autowired
	public ClassService(ClassRepository repository) {
		super(repository);
	}

	public List<Class> findAllByGrade(short grade){
		return repository.findAllByGrade(grade);
	}

	public List<Class> findAllBySign(char sign){
		return repository.findAllBySign(sign);
	}

}
