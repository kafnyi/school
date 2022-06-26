package hu.wurfel.refference.school.subject;

import hu.wurfel.refference.school.base.enums.EntityNaming;
import hu.wurfel.refference.school.base.enums.SearchByFields;
import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectRequestForSearch implements Serializable {
    private EntityNaming searchFor;
    private EntityNaming searchWith;
    private SearchByFields searchBy;
    private String searchValue;
    private String id;
    private String name;
    private String teacherId;
}
