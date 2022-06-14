package hu.wurfel.refference.school.model.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiaryDto implements Serializable {
    private final Integer dId;
    private final Long sId;
    private final Integer cId;
}
