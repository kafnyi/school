package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.base.enums.EntityNaming;
import hu.wurfel.refference.school.base.enums.SearchByFields;
import lombok.Data;

import java.io.Serializable;

@Data
public class MarkRequestForSearch implements Serializable {
    private EntityNaming searchFor;
    private EntityNaming searchWith;
    private SearchByFields searchBy;
    private String searchValue;
    private String id;
    private String subjectId;
    private String date;
    private String diaryId;
    private String mark;
}
