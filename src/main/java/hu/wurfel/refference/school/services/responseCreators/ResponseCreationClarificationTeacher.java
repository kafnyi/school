package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.model.Request;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ResponseCreationClarificationTeacher extends ResponseCreationClarificationTemplate {

    public ArrayList create(Request request) {
        rContent = new ArrayList<>();
        switch (request.getRWith()) {
            case Student -> {
                tWStudent(request);
            }
            case Diary -> {
                tWDiary(request);
            }
            case Class -> {
                tWClass(request);
            }
            case Subject -> {
                tWSubject(request);
            }
            case Mark -> {
                tWMark(request);
            }
            case Teacher -> {
                tWTeacher(request);
            }
            default -> {
            }
        }
        return rContent;
    }

    private void tWStudent(Request request) {
        switch (request.getRBy()) {
            case StudentId -> {
                rContent = teacherService.getTeachersByStudent(studentService.getStudentByStudentId(Long.parseLong(request.getRValue())));
            }
            case Name -> {
                rContent = teacherService.getTeachersByStudents(studentService.getStudentsByName(request.getRValue()));
            }
            case Date -> {
                rContent = teacherService.getTeachersByStudents(studentService.getStudentsByBirth(request.getRValue()));
            }
            default -> {
            }
        }
    }

    private void tWDiary(Request request) {
        switch (request.getRBy()) {
            case DiaryId -> {
                rContent = teacherService.getTeachersByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getRValue())));
            }
            case StudentId -> {
                rContent = teacherService.getTeachersByDiaries(diaryService.getDiariesByScid(Long.parseLong(request.getRValue())));
            }
            case ClassId -> {
                rContent = teacherService.getTeachersByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(request.getRValue())));
            }
            default -> {
            }
        }
    }

    private void tWClass(Request request) {
        switch (request.getRBy()) {
            case ClassId -> {
                rContent.add(teacherService.getTeacherByClass(classService.getClassByClassId(Integer.parseInt(request.getRValue()))));
            }
            case Grade -> {
                rContent = teacherService.getTeachersByClasses(classService.getClassesByGrade(Short.parseShort(request.getRValue())));
            }
            case Sign -> {
                rContent = teacherService.getTeachersByClasses(classService.getClassesBySign(request.getRValue().strip().charAt(0)));
            }
            case Year -> {
                rContent = teacherService.getTeachersByClasses(classService.getClassesByYear(Year.parse(request.getRValue())));
            }
            case TeacherId -> {
                rContent.add(teacherService.getTeacherByTeacherId(Long.parseLong(request.getRValue())));
            }
            default -> {
            }
        }
    }

    private void tWSubject(Request request) {
        switch (request.getRBy()) {
            case SubjectId -> {
                rContent.add(teacherService.getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue()))));
            }
            case Name -> {
                rContent = teacherService.getTeacherBySubjects(subjectService.getSubjectsByName(request.getRValue()));
            }
            case TeacherId -> {
                rContent.add(teacherService.getTeacherByTeacherId(Long.parseLong(request.getRValue())));
            }
            default -> {
            }
        }
    }

    private void tWMark(Request request) {
        switch (request.getRBy()) {
            case MarkId -> {
                rContent.add(teacherService.getTeacherByMark(markService.getMarkByMarkid(Long.parseLong(request.getRValue()))));
            }
            case DiaryId -> {
                rContent = teacherService.getTeachersByMarks(markService.getMarksByDiaryid(Integer.parseInt(request.getRValue())));
            }
            case Date -> {
                rContent = teacherService.getTeachersByMarks(markService.getMarksByDate(request.getRValue()));
            }
            case SubjectId -> {
                rContent.add(teacherService.getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue()))));
            }
            case Mark -> {
                rContent = teacherService.getTeachersByMarks(markService.getMarksByMark(Byte.parseByte(request.getRValue())));
            }
            default -> {
            }
        }
    }

    private void tWTeacher(Request request) {
        switch (request.getRBy()) {
            case TeacherId -> {
                rContent.add(teacherService.getTeacherByTeacherId(Long.parseLong(request.getRValue())));
            }
            case Name -> {
                rContent = teacherService.getTeacherByName(request.getRValue());
            }
            case Date -> {
                rContent = teacherService.getTeacherByBirth(request.getRValue());
            }
            default -> {
            }
        }
    }
}
