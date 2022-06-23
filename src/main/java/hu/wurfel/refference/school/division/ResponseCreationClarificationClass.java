package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.mark.MarkRequest;
import hu.wurfel.refference.school.model.SearchRequest;
import hu.wurfel.refference.school.services.requestServices.ResponseCreationClarificationTemplate;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ResponseCreationClarificationClass extends ResponseCreationClarificationTemplate {

    public ArrayList create(ClassRequest classRequest) {
        rContent = new ArrayList<>();
        switch (classRequest.getSearchWith()) {
            case Student -> {
                cWStudent(classRequest);
            }
            case Diary -> {
                cWDiary(classRequest);
            }
            case Class -> {
                cWClass(classRequest);
            }
            case Subject -> {
                cWSubject(classRequest);
            }
            case Mark -> {
                cWMark(classRequest);
            }
            case Teacher -> {
                cWTeacher(classRequest);
            }
            default -> {
            }
        }
        return rContent;
    }

    private void cWStudent(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case StudentId -> {
                rContent.add(classService.getClassesByStudent(studentService.getStudentByStudentId(Long.parseLong(classRequest.getSearchValue()))));
            }
            case Name -> {
                rContent = classService.getClassesByStudents(studentService.getStudentsByName(classRequest.getSearchValue()));
            }
            case Date -> {
                rContent = classService.getClassesByStudents(studentService.getStudentsByBirth(classRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }

    private void cWDiary(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case DiaryId -> {
                rContent.add(classService.getClassByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(classRequest.getSearchValue()))));
            }
            case StudentId -> {
                rContent = classService.getClassesByDiaries(diaryService.getDiariesByScid(Long.parseLong(classRequest.getSearchValue())));
            }
            case ClassId -> {
                rContent.add(classService.getClassByClassId(Integer.parseInt(classRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void cWClass(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case ClassId -> {
                rContent.add(classService.getClassByClassId(Integer.parseInt(classRequest.getSearchValue())));
            }
            case Grade -> {
                rContent = classService.getClassesByGrade(Short.parseShort(classRequest.getSearchValue()));
            }
            case Sign -> {
                rContent = classService.getClassesBySign(classRequest.getSearchValue().strip().charAt(0));
            }
            case Year -> {
                rContent = classService.getClassesByYear(Year.parse(classRequest.getSearchValue()));
            }
            case TeacherId -> {
                rContent = classService.getClassesByTid(Long.parseLong(classRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }

    private void cWSubject(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case SubjectId -> {
                rContent = classService.getClassesBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(classRequest.getSearchValue())));
            }
            case Name -> {
                rContent = classService.getClassesBySubjects(subjectService.getSubjectsByName(classRequest.getSearchValue()));
            }
            case TeacherId -> {
                rContent = classService.getClassesBySubjects(subjectService.getSubjectsByTid(Long.parseLong(classRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void cWMark(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case MarkId -> {
                rContent.add(classService.getClassByMark(markService.getMarkByMarkid(Long.parseLong(classRequest.getSearchValue()))));
            }
            case DiaryId -> {
                rContent.add(classService.getClassByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(classRequest.getSearchValue()))));
            }
            case Date -> {
                rContent = classService.getClassesByMarks(markService.getMarksByDate(classRequest.getSearchValue()));
            }
            case SubjectId -> {
                rContent = classService.getClassesByMarks(markService.getMarksBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(classRequest.getSearchValue()))));
            }
            case Mark -> {
                rContent = classService.getClassesByMarks(markService.getMarksByMark(Byte.parseByte(classRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void cWTeacher(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case TeacherId -> {
                rContent = classService.getClassesByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(classRequest.getSearchValue())));
            }
            case Name -> {
                rContent = classService.getClassesByTeachers(teacherService.getTeacherByName(classRequest.getSearchValue()));
            }
            case Date -> {
                rContent = classService.getClassesByTeachers(teacherService.getTeacherByBirth(classRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }
}
