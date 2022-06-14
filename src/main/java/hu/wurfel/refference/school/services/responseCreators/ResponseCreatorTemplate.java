package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.model.Response;
import hu.wurfel.refference.school.model.enums.EntityNaming;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

public class ResponseCreatorTemplate extends ResponseCreationClarificationTemplate {
    Response response;
    EntityNaming responseType;
    ArrayList responseContent;

    @Autowired
    ResponseCreationClarificationClass responseCreationClarificationClass;
    @Autowired
    ResponseCreationClarificationDiary responseCreationClarificationDiary;
    @Autowired
    ResponseCreationClarificationMark responseCreationClarificationMark;
    @Autowired
    ResponseCreationClarificationStudent responseCreationClarificationStudent;
    @Autowired
    ResponseCreationClarificationSubject responseCreationClarificationSubject;
    @Autowired
    ResponseCreationClarificationTeacher responseCreationClarificationTeacher;
}
