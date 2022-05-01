package hu.wurfel.refference.school.services.entity;

import hu.wurfel.refference.school.services.entity.cruds.TeacherCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends TeacherCrudService {
	@Autowired
	private ClassService cs;
	@Autowired
	private DiaryService ds;
	@Autowired
	private MarkService ms;
	@Autowired
	private StudentService ss;
	@Autowired
	private SubjectService sjs;


}
