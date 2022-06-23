package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityNaming;
import hu.wurfel.refference.school.base.enums.SearchByFields;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class ClassRequest implements Serializable {
    private final EntityNaming searchFor;
    private final EntityNaming searchWith;
    private final SearchByFields searchBy;
    private final String searchValue;
    private final String id;
    private final String grade;
    private final String sign;
    private final String year;
    private final String teacherId;
}
