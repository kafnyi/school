package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import lombok.Data;

@Data
public class StudentRequestForSearch {
    private EntityNames searchFor;
    private EntityNames searchWith;
    private EntityFieldNames searchBy;
    private String searchValue;
    private Long id;
    private String name;
    private String date;
}
