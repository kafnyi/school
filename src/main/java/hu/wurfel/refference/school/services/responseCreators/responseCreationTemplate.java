package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.services.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class responseCreationTemplate {
	@Autowired
	static
	StudentService studentService;
	@Autowired
	static
	DiaryService diaryService;
	@Autowired
	static
	ClassService classService;
	@Autowired
	static
	SubjectService subjectService;
	@Autowired
	static
	MarkService markService;

	static ArrayList rContent;

}
