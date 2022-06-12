package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.services.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ResponseCreationClarificationTemplate {

	@Autowired
	StudentService studentService;

	@Autowired
	DiaryService diaryService;

	@Autowired
	ClassService classService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	MarkService markService;

	@Autowired
	TeacherService teacherService;

	ArrayList rContent;

}
