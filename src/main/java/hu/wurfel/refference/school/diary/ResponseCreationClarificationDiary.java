package hu.wurfel.refference.school.diary;

import hu.wurfel.refference.school.model.SearchRequest;
import hu.wurfel.refference.school.services.requestServices.ResponseCreationClarificationTemplate;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ResponseCreationClarificationDiary extends ResponseCreationClarificationTemplate {

    public ArrayList create(DiaryRequest diaryRequest) {
        rContent = new ArrayList<>();
        switch (diaryRequest.getSearchWith()) {
            case Student -> {
                dWStudent(diaryRequest);
            }
            case Diary -> {
                dWDiary(diaryRequest);
            }
            case Class -> {
                dWClass(diaryRequest);
            }
            case Subject -> {
                dWSubject(diaryRequest);
            }
            case Mark -> {
                dWMark(diaryRequest);
            }
            case Teacher -> {
                dWTeacher(diaryRequest);
            }
            default -> {
            }
        }
        return rContent;
    }

    private void dWStudent(DiaryRequest diaryRequest) {
        switch (diaryRequest.getSearchBy()) {
            case StudentId -> {
                rContent = diaryService.getDiariesByStudent(studentService.getStudentByStudentId(Long.parseLong(diaryRequest.getSearchValue())));
            }
            case Name -> {
                rContent = diaryService.getDiariesByStudents(studentService.getStudentsByName(diaryRequest.getSearchValue()));
            }
            case Date -> {
                rContent = diaryService.getDiariesByStudents(studentService.getStudentsByBirth(diaryRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }

    private void dWDiary(DiaryRequest diaryRequest) {
        switch (diaryRequest.getSearchBy()) {
            case DiaryId -> {
                rContent.add(diaryService.getDiaryByDiaryid(Integer.parseInt(diaryRequest.getSearchValue())));
            }
            case StudentId -> {
                rContent = diaryService.getDiariesByScid(Long.parseLong(diaryRequest.getSearchValue()));
            }
            case ClassId -> {
                rContent = diaryService.getDiariesByClassid(Integer.parseInt(diaryRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }

    private void dWClass(DiaryRequest diaryRequest) {
        switch (diaryRequest.getSearchBy()) {
            case ClassId -> {
                rContent = diaryService.getDiariesByClassid(Integer.parseInt(diaryRequest.getSearchValue()));
            }
            case Grade -> {
                rContent = diaryService.getDiariesByClasses(classService.getClassesByGrade(Short.parseShort(diaryRequest.getSearchValue())));
            }
            case Sign -> {
                rContent = diaryService.getDiariesByClasses(classService.getClassesBySign(diaryRequest.getSearchValue().strip().charAt(0)));
            }
            case Year -> {
                rContent = diaryService.getDiariesByClasses(classService.getClassesByYear(Year.parse(diaryRequest.getSearchValue())));
            }
            case TeacherId -> {
                rContent = diaryService.getDiariesByClasses(classService.getClassesByTid(Long.parseLong(diaryRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void dWSubject(DiaryRequest diaryRequest) {
        switch (diaryRequest.getSearchBy()) {
            case SubjectId -> {
                rContent = diaryService.getDiariesBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(diaryRequest.getSearchValue())));
            }
            case Name -> {
                rContent = diaryService.getDiariesBySubjects(subjectService.getSubjectsByName(diaryRequest.getSearchValue()));
            }
            case TeacherId -> {
                rContent = diaryService.getDiariesBySubjects(subjectService.getSubjectsByTid(Long.parseLong(diaryRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void dWMark(DiaryRequest diaryRequest) {
        switch (diaryRequest.getSearchBy()) {
            case MarkId -> {
                rContent.add(diaryService.getDiaryByMark(markService.getMarkByMarkid(Long.parseLong(diaryRequest.getSearchValue()))));
            }
            case DiaryId -> {
                rContent.add(diaryService.getDiaryByDiaryid(Integer.parseInt(diaryRequest.getSearchValue())));
            }
            case Date -> {
                rContent = diaryService.getDiariesByMarks(markService.getMarksByDate(diaryRequest.getSearchValue()));
            }
            case SubjectId -> {
                rContent = diaryService.getDiariesByMarks(markService.getMarksBySubjectid(Integer.parseInt(diaryRequest.getSearchValue())));
            }
            case Mark -> {
                rContent = diaryService.getDiariesByMarks(markService.getMarksByMark(Byte.parseByte(diaryRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void dWTeacher(DiaryRequest diaryRequest) {
        switch (diaryRequest.getSearchBy()) {
            case TeacherId -> {
                rContent = diaryService.getDiariesByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(diaryRequest.getSearchValue())));
            }
            case Name -> {
                rContent = diaryService.getDiariesByTeachers(teacherService.getTeacherByName(diaryRequest.getSearchValue()));
            }
            case Date -> {
                rContent = diaryService.getDiariesByTeachers(teacherService.getTeacherByBirth(diaryRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }
}
