package hu.wurfel.new_school_reference.subject;

import hu.wurfel.new_school_reference.base.CrudService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubjectService extends CrudService<Subject,SubjectRepository> {

	@Autowired
	public SubjectService(SubjectRepository repository) {
		super(repository);
	}

	public List<Subject> findAllByName(String name){
		return repository.findAllByName(name);
	}
}
