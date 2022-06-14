package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.model.Request;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ResponseCreationClarificationDiary extends ResponseCreationClarificationTemplate {

    public ArrayList create(Request request) {
        rContent = new ArrayList<>();
        switch (request.getRWith()) {
            case Student -> {
                dWStudent(request);
            }
            case Diary -> {
                dWDiary(request);
            }
            case Class -> {
                dWClass(request);
            }
            case Subject -> {
                dWSubject(request);
            }
            case Mark -> {
                dWMark(request);
            }
            case Teacher -> {
                dWTeacher(request);
            }
            default -> {
            }
        }
        return rContent;
    }

    private void dWStudent(Request request) {
        switch (request.getRBy()) {
            case StudentId -> {
                rContent = diaryService.getDiariesByStudent(studentService.getStudentByStudentId(Long.parseLong(request.getRValue())));
            }
            case Name -> {
                rContent = diaryService.getDiariesByStudents(studentService.getStudentsByName(request.getRValue()));
            }
            case Date -> {
                rContent = diaryService.getDiariesByStudents(studentService.getStudentsByBirth(request.getRValue()));
            }
            default -> {
            }
        }
    }

    private void dWDiary(Request request) {
        switch (request.getRBy()) {
            case DiaryId -> {
                rContent.add(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getRValue())));
            }
            case StudentId -> {
                rContent = diaryService.getDiariesByScid(Long.parseLong(request.getRValue()));
            }
            case ClassId -> {
                rContent = diaryService.getDiariesByClassid(Integer.parseInt(request.getRValue()));
            }
            default -> {
            }
        }
    }

    private void dWClass(Request request) {
        switch (request.getRBy()) {
            case ClassId -> {
                rContent = diaryService.getDiariesByClassid(Integer.parseInt(request.getRValue()));
            }
            case Grade -> {
                rContent = diaryService.getDiariesByClasses(classService.getClassesByGrade(Short.parseShort(request.getRValue())));
            }
            case Sign -> {
                rContent = diaryService.getDiariesByClasses(classService.getClassesBySign(request.getRValue().strip().charAt(0)));
            }
            case Year -> {
                rContent = diaryService.getDiariesByClasses(classService.getClassesByYear(Year.parse(request.getRValue())));
            }
            case TeacherId -> {
                rContent = diaryService.getDiariesByClasses(classService.getClassesByTid(Long.parseLong(request.getRValue())));
            }
            default -> {
            }
        }
    }

    private void dWSubject(Request request) {
        switch (request.getRBy()) {
            case SubjectId -> {
                rContent = diaryService.getDiariesBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue())));
            }
            case Name -> {
                rContent = diaryService.getDiariesBySubjects(subjectService.getSubjectsByName(request.getRValue()));
            }
            case TeacherId -> {
                rContent = diaryService.getDiariesBySubjects(subjectService.getSubjectsByTid(Long.parseLong(request.getRValue())));
            }
            default -> {
            }
        }
    }

    private void dWMark(Request request) {
        switch (request.getRBy()) {
            case MarkId -> {
                rContent.add(diaryService.getDiaryByMark(markService.getMarkByMarkid(Long.parseLong(request.getRValue()))));
            }
            case DiaryId -> {
                rContent.add(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getRValue())));
            }
            case Date -> {
                rContent = diaryService.getDiariesByMarks(markService.getMarksByDate(request.getRValue()));
            }
            case SubjectId -> {
                rContent = diaryService.getDiariesByMarks(markService.getMarksBySubjectid(Integer.parseInt(request.getRValue())));
            }
            case Mark -> {
                rContent = diaryService.getDiariesByMarks(markService.getMarksByMark(Byte.parseByte(request.getRValue())));
            }
            default -> {
            }
        }
    }

    private void dWTeacher(Request request) {
        switch (request.getRBy()) {
            case TeacherId -> {
                rContent = diaryService.getDiariesByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(request.getRValue())));
            }
            case Name -> {
                rContent = diaryService.getDiariesByTeachers(teacherService.getTeacherByName(request.getRValue()));
            }
            case Date -> {
                rContent = diaryService.getDiariesByTeachers(teacherService.getTeacherByBirth(request.getRValue()));
            }
            default -> {
            }
        }
    }
}
