package hu.wurfel.refference.school.subject;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import lombok.Data;

@Data
public class SubjectRequestForSearch {
    private EntityNames searchFor;
    private EntityNames searchWith;
    private EntityFieldNames searchBy;
    private String searchValue;
    private String id;
    private String name;
    private String teacherId;
}
