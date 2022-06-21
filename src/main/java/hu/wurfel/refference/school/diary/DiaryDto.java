package hu.wurfel.refference.school.diary;

import lombok.Data;

import java.io.Serializable;

@Data
public class DiaryDto implements Serializable {
    private final Integer dId;
    private final Long sId;
    private final Integer cId;
}
