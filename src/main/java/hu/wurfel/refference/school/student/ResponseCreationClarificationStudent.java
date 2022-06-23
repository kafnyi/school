package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.model.SearchRequest;
import hu.wurfel.refference.school.services.requestServices.ResponseCreationClarificationTemplate;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ResponseCreationClarificationStudent extends ResponseCreationClarificationTemplate {

    public ArrayList create(StudentRequest request) {
        rContent = new ArrayList<>();
        switch (request.getSearchWith()) {
            case Student -> {
                sWStudent(request);
            }
            case Diary -> {
                sWDiary(request);
            }
            case Class -> {
                sWClass(request);
            }
            case Subject -> {
                sWSubject(request);
            }
            case Mark -> {
                sWMark(request);
            }
            case Teacher -> {
                sWTeacher(request);
            }
            default -> {
            }
        }
        return rContent;
    }

    public void sWStudent(StudentRequest request) {
        switch (request.getSearchBy()) {
            case StudentId -> {
                rContent.add(studentService.getStudentByStudentId(Long.parseLong(request.getSearchValue())));
            }
            case Name -> {
                rContent = studentService.getStudentsByName(request.getSearchValue());
            }
            case Date -> {
                rContent = studentService.getStudentsByBirth(request.getSearchValue());
            }
            default -> {
            }
        }
    }

    private void sWDiary(StudentRequest request) {
        switch (request.getSearchBy()) {
            case DiaryId -> {
                rContent.add(studentService.getStudentByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getSearchValue()))));
            }
            case StudentId -> {
                rContent.add(studentService.getStudentByStudentId(Long.parseLong(request.getSearchValue())));
            }
            case ClassId -> {
                rContent = studentService.getStudentsByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(request.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void sWClass(StudentRequest request) {
        switch (request.getSearchBy()) {
            case ClassId -> {
                rContent = studentService.getStudentsByClass(classService.getClassByClassId(Integer.parseInt(request.getSearchValue())));
            }
            case Grade -> {
                rContent = studentService.getStudentsByClasses(classService.getClassesByGrade(Short.parseShort(request.getSearchValue())));
            }
            case Sign -> {
                rContent = studentService.getStudentsByClasses(classService.getClassesBySign(request.getSearchValue().strip().charAt(0)));
            }
            case Year -> {
                rContent = studentService.getStudentsByClasses(classService.getClassesByYear(Year.parse(request.getSearchValue())));
            }
            case TeacherId -> {
                rContent = studentService.getStudentsByClasses(classService.getClassesByTid(Long.parseLong(request.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void sWSubject(StudentRequest request) {
        switch (request.getSearchBy()) {
            case SubjectId -> {
                rContent = studentService.getStudentsBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
            }
            case Name -> {
                rContent = studentService.getStudentsBySubjects(subjectService.getSubjectsByName(request.getSearchValue()));
            }
            case TeacherId -> {
                rContent = studentService.getStudentsBySubjects(subjectService.getSubjectsByTid(Long.parseLong(request.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void sWMark(StudentRequest request) {
        switch (request.getSearchBy()) {
            case MarkId -> {
                rContent.add(studentService.getStudentByMark(markService.getMarkByMarkid(Long.parseLong(request.getSearchValue()))));
            }
            case DiaryId -> {
                rContent.add(studentService.getStudentByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getSearchValue()))));
            }
            case Date -> {
                rContent = studentService.getStudentsByMarks(markService.getMarksByDate(request.getSearchValue()));
            }
            case SubjectId -> {
                rContent = studentService.getStudentsBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
            }
            case Mark -> {
                rContent = studentService.getStudentsByMarks(markService.getMarksByMark(Byte.parseByte(request.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void sWTeacher(StudentRequest request) {
        switch (request.getSearchBy()) {
            case TeacherId -> {
                rContent = studentService.getStudentsByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(request.getSearchValue())));
            }
            case Name -> {
                rContent = studentService.getStudentsByTeachers(teacherService.getTeacherByName(request.getSearchValue()));
            }
            case Date -> {
                rContent = studentService.getStudentsByTeachers(teacherService.getTeacherByBirth(request.getSearchValue()));
            }
            default -> {
            }
        }
    }
}
