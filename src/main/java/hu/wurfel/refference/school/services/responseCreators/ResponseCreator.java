package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.model.Request;
import hu.wurfel.refference.school.model.Response;
import hu.wurfel.refference.school.model.enums.EntityNaming;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ResponseCreator {
	Response response;
	EntityNaming rType;
	ArrayList rContent;

	public ResponseCreator() {
		this.response = new Response();
		this.rContent = new ArrayList<>();
	}


	public ArrayList create(Request request) {
		rType = request.getRFor();
		rContent = new ArrayList();
		rContent.add(rType);
		switch (request.getRFor()) {
			case Student -> {
				rContent.addAll(ResponseCreationClarificationStudent.create(request));
			}
			case Class -> {
				rContent.addAll(ResponseCreationClarificationClass.create(request));
			}
			case Diary -> {
				rContent.addAll(ResponseCreationClarificationDiary.create(request));
			}
			case Subject -> {
				rContent.addAll(ResponseCreationClarificationSubject.create(request));
			}
			case Mark -> {
				rContent.addAll(ResponseCreationClarificationMark.create(request));
			}
			case Teacher -> {
				rContent.addAll(ResponseCreationClarificationTeacher.create(request));
			}
			default -> {
			}

		}
		return ResultValidator.validate(rContent);
	}
}
