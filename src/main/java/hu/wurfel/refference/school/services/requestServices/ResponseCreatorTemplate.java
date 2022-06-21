package hu.wurfel.refference.school.services.requestServices;

import hu.wurfel.refference.school.diary.ResponseCreationClarificationDiary;
import hu.wurfel.refference.school.division.ResponseCreationClarificationClass;
import hu.wurfel.refference.school.mark.ResponseCreationClarificationMark;
import hu.wurfel.refference.school.model.Response;
import hu.wurfel.refference.school.model.enums.EntityNaming;
import hu.wurfel.refference.school.student.ResponseCreationClarificationStudent;
import hu.wurfel.refference.school.subject.ResponseCreationClarificationSubject;
import hu.wurfel.refference.school.teacher.ResponseCreationClarificationTeacher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ResponseCreatorTemplate extends ResponseCreationClarificationTemplate {
	protected Response response;
	protected EntityNaming responseType;
	protected ArrayList responseContent;

	@Autowired
	protected ResponseCreationClarificationClass responseCreationClarificationClass;
	@Autowired
	protected ResponseCreationClarificationDiary responseCreationClarificationDiary;
	@Autowired
	protected ResponseCreationClarificationMark responseCreationClarificationMark;
	@Autowired
	protected ResponseCreationClarificationStudent responseCreationClarificationStudent;
	@Autowired
	protected ResponseCreationClarificationSubject responseCreationClarificationSubject;
	@Autowired
	protected ResponseCreationClarificationTeacher responseCreationClarificationTeacher;
}
