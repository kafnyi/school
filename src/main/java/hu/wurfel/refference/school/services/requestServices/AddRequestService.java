package hu.wurfel.refference.school.services.requestServices;

import hu.wurfel.refference.school.model.AddRequest;
import hu.wurfel.refference.school.model.daos.Student;
import hu.wurfel.refference.school.services.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class AddRequestService {

    @Autowired
    StudentService studentService;
    @Autowired
    DiaryService diaryService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    ClassService classService;
    @Autowired
    MarkService markService;
    @Autowired
    TeacherService teacherService;

    public ArrayList create (AddRequest addRequest){
        ArrayList response = new ArrayList();
        switch (addRequest.getType()){
            case Student -> { studentService.saveStudent(Long.parseLong(addRequest.getId()),addRequest.getName(),addRequest.getDate());
                response.add(studentService.getStudentByStudentId(Long.parseLong(addRequest.getId())));
            }
            case Diary -> { diaryService.saveDiary(Integer.parseInt(addRequest.getId()),Long.parseLong(addRequest.getStudentID()),Integer.parseInt(addRequest.getClassID()));
                response.add(diaryService.getDiaryByDiaryid(Integer.parseInt(addRequest.getId())));
            }
            case Class -> {classService.saveClass(Integer.parseInt(addRequest.getId()),Short.parseShort(addRequest.getGrade()),addRequest.getSign().strip().charAt(0), Year.parse(addRequest.getYear()),Long.parseLong(addRequest.getTeacherID()));
                response.add(classService.getClassByClassId(Integer.parseInt(addRequest.getId())));
            }

        }



        return null;
    }
}
