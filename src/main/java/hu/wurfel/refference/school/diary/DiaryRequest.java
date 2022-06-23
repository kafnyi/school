package hu.wurfel.refference.school.diary;

import hu.wurfel.refference.school.model.enums.EntityNaming;
import hu.wurfel.refference.school.model.enums.SearchByFields;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class DiaryRequest implements Serializable {
    private final EntityNaming searchFor;
    private final EntityNaming searchWith;
    private final SearchByFields searchBy;
    private final String searchValue;
    private final String id;
    private final String studentId;
    private final String classId;
}
