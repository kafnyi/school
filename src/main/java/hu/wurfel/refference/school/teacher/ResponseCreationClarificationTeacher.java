package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.base.ResponseCreationClarificationTemplate;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ResponseCreationClarificationTeacher extends ResponseCreationClarificationTemplate {

    public ArrayList create(TeacherRequest teacherRequest) {
        rContent = new ArrayList<>();
        switch (teacherRequest.getSearchWith()) {
            case Student -> {
                tWStudent(teacherRequest);
            }
            case Diary -> {
                tWDiary(teacherRequest);
            }
            case Class -> {
                tWClass(teacherRequest);
            }
            case Subject -> {
                tWSubject(teacherRequest);
            }
            case Mark -> {
                tWMark(teacherRequest);
            }
            case Teacher -> {
                tWTeacher(teacherRequest);
            }
            default -> {
            }
        }
        return rContent;
    }

    private void tWStudent(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case StudentId -> {
                rContent = teacherService.getTeachersByStudent(studentService.getStudentByStudentId(Long.parseLong(teacherRequest.getSearchValue())));
            }
            case Name -> {
                rContent = teacherService.getTeachersByStudents(studentService.getStudentsByName(teacherRequest.getSearchValue()));
            }
            case Date -> {
                rContent = teacherService.getTeachersByStudents(studentService.getStudentsByBirth(teacherRequest.getSearchValue()));
            }
            default -> {
            }
        }
    }

    private void tWDiary(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case DiaryId -> {
                rContent = teacherService.getTeachersByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(teacherRequest.getSearchValue())));
            }
            case StudentId -> {
                rContent = teacherService.getTeachersByDiaries(diaryService.getDiariesByScid(Long.parseLong(teacherRequest.getSearchValue())));
            }
            case ClassId -> {
                rContent = teacherService.getTeachersByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(teacherRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void tWClass(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case ClassId -> {
                rContent.add(teacherService.getTeacherByClass(classService.getClassByClassId(Integer.parseInt(teacherRequest.getSearchValue()))));
            }
            case Grade -> {
                rContent = teacherService.getTeachersByClasses(classService.getClassesByGrade(Short.parseShort(teacherRequest.getSearchValue())));
            }
            case Sign -> {
                rContent = teacherService.getTeachersByClasses(classService.getClassesBySign(teacherRequest.getSearchValue().strip().charAt(0)));
            }
            case Year -> {
                rContent = teacherService.getTeachersByClasses(classService.getClassesByYear(Year.parse(teacherRequest.getSearchValue())));
            }
            case TeacherId -> {
                rContent.add(teacherService.getTeacherByTeacherId(Long.parseLong(teacherRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void tWSubject(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case SubjectId -> {
                rContent.add(teacherService.getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(teacherRequest.getSearchValue()))));
            }
            case Name -> {
                rContent = teacherService.getTeacherBySubjects(subjectService.getSubjectsByName(teacherRequest.getSearchValue()));
            }
            case TeacherId -> {
                rContent.add(teacherService.getTeacherByTeacherId(Long.parseLong(teacherRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void tWMark(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case MarkId -> {
                rContent.add(teacherService.getTeacherByMark(markService.getMarkByMarkid(Long.parseLong(teacherRequest.getSearchValue()))));
            }
            case DiaryId -> {
                rContent = teacherService.getTeachersByMarks(markService.getMarksByDiaryid(Integer.parseInt(teacherRequest.getSearchValue())));
            }
            case Date -> {
                rContent = teacherService.getTeachersByMarks(markService.getMarksByDate(teacherRequest.getSearchValue()));
            }
            case SubjectId -> {
                rContent.add(teacherService.getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(teacherRequest.getSearchValue()))));
            }
            case Mark -> {
                rContent = teacherService.getTeachersByMarks(markService.getMarksByMark(Byte.parseByte(teacherRequest.getSearchValue())));
            }
            default -> {
            }
        }
    }

    private void tWTeacher(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case TeacherId -> {
                rContent.add(teacherService.getTeacherByTeacherId(Long.parseLong(teacherRequest.getSearchValue())));
            }
            case Name -> {
                rContent = teacherService.getTeacherByName(teacherRequest.getSearchValue());
            }
            case Date -> {
                rContent = teacherService.getTeacherByBirth(teacherRequest.getSearchValue());
            }
            default -> {
            }
        }
    }
}
