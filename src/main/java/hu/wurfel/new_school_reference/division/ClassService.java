package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassService extends CrudService<Class, ClassRepository> {

	@Autowired
	public ClassService(ClassRepository repository) {
		super(repository);
	}

	public List<Class> findAllByGrade(short grade){
		return repo.findAllByGrade(grade);
	}

	public List<Class> findAllBySign(char sign){
		return repo.findAllBySign(sign);
	}

}
