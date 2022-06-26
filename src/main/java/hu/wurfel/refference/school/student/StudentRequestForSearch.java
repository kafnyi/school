package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityNaming;
import hu.wurfel.refference.school.base.enums.SearchByFields;
import lombok.Data;

import java.io.Serializable;

@Data
public class StudentRequestForSearch implements Serializable {
    private EntityNaming searchFor;
    private EntityNaming searchWith;
    private SearchByFields searchBy;
    private String searchValue;
    private String id;
    private String name;
    private String date;
}
