package hu.wurfel.refference.school.services.requestServices;

import hu.wurfel.refference.school.diary.DiaryService;
import hu.wurfel.refference.school.division.ClassService;
import hu.wurfel.refference.school.mark.MarkService;
import hu.wurfel.refference.school.student.StudentService;
import hu.wurfel.refference.school.subject.SubjectService;
import hu.wurfel.refference.school.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ResponseCreationClarificationTemplate {

	@Autowired
	protected StudentService studentService;

	@Autowired
	protected DiaryService diaryService;

	@Autowired
	protected ClassService classService;

	@Autowired
	protected SubjectService subjectService;

	@Autowired
	protected MarkService markService;

	@Autowired
	protected TeacherService teacherService;

	protected ArrayList rContent;

}
