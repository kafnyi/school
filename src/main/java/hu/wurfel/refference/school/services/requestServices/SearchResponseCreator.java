package hu.wurfel.refference.school.services.requestServices;

import hu.wurfel.refference.school.model.SearchRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class SearchResponseCreator extends ResponseCreatorTemplate {

    public ArrayList create(SearchRequest request) {
        responseType = request.getRFor();
        responseContent = new ArrayList();
        responseContent.add(responseType);
        switch (request.getRFor()) {
            case Student -> {
                responseContent.addAll(responseCreationClarificationStudent.create(request));
            }
            case Class -> {
                responseContent.addAll(responseCreationClarificationClass.create(request));
            }
            case Diary -> {
                responseContent.addAll(responseCreationClarificationDiary.create(request));
            }
            case Subject -> {
                responseContent.addAll(responseCreationClarificationSubject.create(request));
            }
            case Mark -> {
                responseContent.addAll(responseCreationClarificationMark.create(request));
            }
            case Teacher -> {
                responseContent.addAll(responseCreationClarificationTeacher.create(request));
            }
            default -> {
            }

        }
        return ResultValidator.validate(responseContent);
    }
}
