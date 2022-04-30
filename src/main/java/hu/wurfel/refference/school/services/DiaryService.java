package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.services.cruds.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
	@Autowired
	private ClassCrudService ccs;
	@Autowired
	private DiaryCrudService dcs;
	@Autowired
	private MarkCrudService mcs;
	@Autowired
	private StudentCrudService scs;
	@Autowired
	private SubjectCrudService sjcs;
	@Autowired
	private TeacherCrudService tcs;


}
