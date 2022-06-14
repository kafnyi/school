package hu.wurfel.refference.school.model.dtos;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MarkDto implements Serializable {
    private final Long mId;
    private final Integer dId;
    private final Date date;
    private final Integer sjId;
    private final byte mark;
}
