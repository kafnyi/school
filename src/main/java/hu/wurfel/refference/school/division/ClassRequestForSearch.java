package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityNaming;
import hu.wurfel.refference.school.base.enums.SearchByFields;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClassRequestForSearch implements Serializable {
    private EntityNaming searchFor;
    private EntityNaming searchWith;
    private SearchByFields searchBy;
    private String searchValue;
    private String id;
    private String grade;
    private String sign;
    private String year;
    private String teacherId;
}
