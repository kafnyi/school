package hu.wurfel.refference.school.subject;

import hu.wurfel.refference.school.base.ResponseCreationClarificationTemplate;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ResponseCreationClarificationSubject extends ResponseCreationClarificationTemplate {

    public ArrayList create(SubjectRequest request) {
        rContent = new ArrayList<>();
        switch (request.getSearchWith()) {
            case Student -> {
                sjWStudent(request);
            }
            case Diary -> {
                sjWDiary(request);
            }
            case Class -> {
                sjWClass(request);
            }
            case Subject -> {
                sjWSubject(request);
            }
            case Mark -> {
                sjWMark(request);
            }
            case Teacher -> {
                sjWTeacher(request);
            }
            default -> {
            }
        }
        return rContent;
    }

    private void sjWStudent(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case StudentId -> {
                rContent = subjectService.getSubjectsByStudent(studentService.getStudentByStudentId(Long.parseLong(request.getSearchValue())));
            }
            case Name -> {
                rContent = subjectService.getSubjectsByStudents(studentService.getStudentsByName(request.getSearchValue()));
            }
            case Date -> {
                rContent = subjectService.getSubjectsByStudents(studentService.getStudentsByBirth(request.getSearchValue()));
            }
            default -> {
            }
        }
    }

    private void sjWDiary(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case DiaryId -> {
                rContent = subjectService.getSubjectsByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getSearchValue())));
            }
            case StudentId -> {
                rContent = subjectService.getSubjectsByDiaries(diaryService.getDiariesByScid(Long.parseLong(request.getSearchValue())));
            }
            case ClassId -> {
                rContent = subjectService.getSubjectsByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(request.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void sjWClass(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case ClassId -> {
                rContent = subjectService.getSubjectsByClass(classService.getClassByClassId(Integer.parseInt(request.getSearchValue())));
            }
            case Grade -> {
                rContent = subjectService.getSubjectsByClasses(classService.getClassesByGrade(Short.parseShort(request.getSearchValue())));
            }
            case Sign -> {
                rContent = subjectService.getSubjectsByClasses(classService.getClassesBySign(request.getSearchValue().strip().charAt(0)));
            }
            case Year -> {
                rContent = subjectService.getSubjectsByClasses(classService.getClassesByYear(Year.parse(request.getSearchValue())));
            }
            case TeacherId -> {
                rContent = subjectService.getSubjectsByClasses(classService.getClassesByTid(Long.parseLong(request.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void sjWSubject(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case SubjectId -> {
                rContent.add(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
            }
            case Name -> {
                rContent = subjectService.getSubjectsByName(request.getSearchValue());
            }
            case TeacherId -> {
                rContent = subjectService.getSubjectsByTid(Long.parseLong(request.getSearchValue()));
            }
            default -> {
            }
        }
    }

    private void sjWMark(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case MarkId -> {
                rContent.add(subjectService.getSubjectByMark(markService.getMarkByMarkid(Long.parseLong(request.getSearchValue()))));
            }
            case DiaryId -> {
                rContent = subjectService.getSubjectsByMarks(markService.getMarksByDiaryid(Integer.parseInt(request.getSearchValue())));
            }
            case Date -> {
                rContent = subjectService.getSubjectsByMarks(markService.getMarksByDate(request.getSearchValue()));
            }
            case SubjectId -> {
                rContent.add(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
            }
            case Mark -> {
                rContent = subjectService.getSubjectsByMarks(markService.getMarksByMark(Byte.parseByte(request.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void sjWTeacher(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case TeacherId -> {
                rContent = subjectService.getSubjectsByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(request.getSearchValue())));
            }
            case Name -> {
                rContent = subjectService.getSubjectsByTeachers(teacherService.getTeacherByName(request.getSearchValue()));
            }
            case Date -> {
                rContent = subjectService.getSubjectsByTeachers(teacherService.getTeacherByBirth(request.getSearchValue()));
            }
            default -> {
            }
        }
    }
}
