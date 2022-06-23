package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.model.SearchRequest;
import hu.wurfel.refference.school.services.requestServices.ResponseCreationClarificationTemplate;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ResponseCreationClarificationMark extends ResponseCreationClarificationTemplate {

    public ArrayList create(MarkRequest markRequest) {
        rContent = new ArrayList<>();
        switch (markRequest.getSearchWith()) {
            case Student -> {
                mWStudent(markRequest);
            }
            case Diary -> {
                mWDiary(markRequest);
            }
            case Class -> {
                mWClass(markRequest);
            }
            case Subject -> {
                mWSubject(markRequest);
            }
            case Mark -> {
                mWMark(markRequest);
            }
            case Teacher -> {
                mWTeacher(markRequest);
            }
            default -> {
            }
        }
        return rContent;
    }

    private void mWStudent(MarkRequest markRequest) {
        switch (markRequest.getSearchBy()) {
            case StudentId -> {
                rContent = markService.getMarksByStudent(studentService.getStudentByStudentId(Long.parseLong(markRequest.getSearchValue())));
            }
            case Name -> {
                rContent = markService.getMarksByStudents(studentService.getStudentsByName(markRequest.getSearchValue()));
            }
            case Date -> {
                rContent = markService.getMarksByStudents(studentService.getStudentsByBirth(markRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }

    private void mWDiary(MarkRequest markRequest) {
        switch (markRequest.getSearchBy()) {
            case DiaryId -> {
                rContent = markService.getMarksByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(markRequest.getSearchValue())));
            }
            case StudentId -> {
                rContent = markService.getMarksByDiaries(diaryService.getDiariesByScid(Long.parseLong(markRequest.getSearchValue())));
            }
            case ClassId -> {
                rContent = markService.getMarksByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(markRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void mWClass(MarkRequest markRequest) {
        switch (markRequest.getSearchBy()) {
            case ClassId -> {
                rContent = markService.getMarksByClass(classService.getClassByClassId(Integer.parseInt(markRequest.getSearchValue())));
            }
            case Grade -> {
                rContent = markService.getMarksByClasses(classService.getClassesByGrade(Short.parseShort(markRequest.getSearchValue())));
            }
            case Sign -> {
                rContent = markService.getMarksByClasses(classService.getClassesBySign(markRequest.getSearchValue().strip().charAt(0)));
            }
            case Year -> {
                rContent = markService.getMarksByClasses(classService.getClassesByYear(Year.parse(markRequest.getSearchValue())));
            }
            case TeacherId -> {
                rContent = markService.getMarksByClasses(classService.getClassesByTid(Long.parseLong(markRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void mWSubject(MarkRequest markRequest) {
        switch (markRequest.getSearchBy()) {
            case SubjectId -> {
                rContent = markService.getMarksBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(markRequest.getSearchValue())));
            }
            case Name -> {
                rContent = markService.getMarksBySubjects(subjectService.getSubjectsByName(markRequest.getSearchValue()));
            }
            case TeacherId -> {
                rContent = markService.getMarksBySubjects(subjectService.getSubjectsByTid(Long.parseLong(markRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void mWMark(MarkRequest markRequest) {
        switch (markRequest.getSearchBy()) {
            case MarkId -> {
                rContent.add(markService.getMarkByMarkid(Long.parseLong(markRequest.getSearchValue())));
            }
            case DiaryId -> {
                rContent = markService.getMarksByDiaryid(Integer.parseInt(markRequest.getSearchValue()));
            }
            case Date -> {
                rContent = markService.getMarksByDate(markRequest.getSearchValue());
            }
            case SubjectId -> {
                rContent = markService.getMarksBySubjectid(Integer.parseInt(markRequest.getSearchValue()));
            }
            case Mark -> {
                rContent = markService.getMarksByMark(Byte.parseByte(markRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }

    private void mWTeacher(MarkRequest markRequest) {
        switch (markRequest.getSearchBy()) {
            case TeacherId -> {
                rContent = markService.getMarksByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(markRequest.getSearchValue())));
            }
            case Name -> {
                rContent = markService.getMarksByTeachers(teacherService.getTeacherByName(markRequest.getSearchValue()));
            }
            case Date -> {
                rContent = markService.getMarksByTeachers(teacherService.getTeacherByBirth(markRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }
}
