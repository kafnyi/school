package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DiaryService extends CrudService<Diary, DiaryRepository> {

@Autowired
	public DiaryService(DiaryRepository repository) {
		super(repository);
	}

}