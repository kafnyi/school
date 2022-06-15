package hu.wurfel.refference.school.model;

import hu.wurfel.refference.school.model.enums.EntityNaming;
import lombok.Data;

@Data
public class AddRequest {
    EntityNaming type;
    String id;
    String studentID;
    String diaryID;
    String subjectID;
    String markID;
    String teacherID;
    String classID;
    String name;
    String date;
    String year;
    String grade;
    String sign;
    String mark;
}
