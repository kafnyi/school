package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.base.enums.EntityNaming;
import hu.wurfel.refference.school.base.enums.SearchByFields;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class StudentRequest implements Serializable {
    private final EntityNaming searchFor;
    private final EntityNaming searchWith;
    private final SearchByFields searchBy;
    private final String searchValue;
    private final String id;
    private final String name;
    private final String date;
}
