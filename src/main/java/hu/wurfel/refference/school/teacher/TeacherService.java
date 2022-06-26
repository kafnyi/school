package hu.wurfel.refference.school.teacher;

import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.diary.DiaryService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.mark.MarkService;
import hu.wurfel.refference.school.student.Student;
import hu.wurfel.refference.school.student.StudentService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectCrudService;
import hu.wurfel.refference.school.subject.SubjectService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class TeacherService extends TeacherCrudService {
    private DiaryCrudService diaryCrudService;
    private MarkCrudService markCrudService;
    private SubjectCrudService subjectCrudService;
    private DiaryService diaryService;
    private ClassService classService;
    private MarkService markService;
    private StudentService studentService;
    private SubjectService subjectService;
    private ArrayList<Teacher> rContent;

    public ArrayList getAutomated(TeacherRequestForSearch teacherRequestForSearch) {
        rContent = new ArrayList<>();
        switch (teacherRequestForSearch.getSearchWith()) {
            case Student -> tWStudent(teacherRequestForSearch);
            case Diary -> tWDiary(teacherRequestForSearch);
            case Class -> tWClass(teacherRequestForSearch);
            case Subject -> tWSubject(teacherRequestForSearch);
            case Mark -> tWMark(teacherRequestForSearch);
            case Teacher -> tWTeacher(teacherRequestForSearch);
            default -> {
            }
        }
        return rContent;
    }

    private void tWStudent(TeacherRequestForSearch teacherRequestForSearch) {
        switch (teacherRequestForSearch.getSearchBy()) {
            case StudentId ->
                    rContent = getTeachersByStudent(studentService.getStudentByStudentId(Long.parseLong(teacherRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getTeachersByStudents(studentService.getStudentsByName(teacherRequestForSearch.getSearchValue()));
            case Date ->
                    rContent = getTeachersByStudents(studentService.getStudentsByBirth(teacherRequestForSearch.getSearchValue()));
            default -> {
            }
        }
    }

    private void tWDiary(TeacherRequestForSearch teacherRequestForSearch) {
        switch (teacherRequestForSearch.getSearchBy()) {
            case DiaryId ->
                    rContent = getTeachersByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(teacherRequestForSearch.getSearchValue())));
            case StudentId ->
                    rContent = getTeachersByDiaries(diaryService.getDiariesByScid(Long.parseLong(teacherRequestForSearch.getSearchValue())));
            case ClassId ->
                    rContent = getTeachersByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(teacherRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void tWClass(TeacherRequestForSearch teacherRequestForSearch) {
        switch (teacherRequestForSearch.getSearchBy()) {
            case ClassId ->
                    rContent.add(getTeacherByClass(classService.getClassByClassId(Integer.parseInt(teacherRequestForSearch.getSearchValue()))));
            case Grade ->
                    rContent = getTeachersByClasses(classService.getClassesByGrade(Short.parseShort(teacherRequestForSearch.getSearchValue())));
            case Sign ->
                    rContent = getTeachersByClasses(classService.getClassesBySign(teacherRequestForSearch.getSearchValue().strip().charAt(0)));
            case Year ->
                    rContent = getTeachersByClasses(classService.getClassesByYear(Year.parse(teacherRequestForSearch.getSearchValue())));
            case TeacherId ->
                    rContent.add(getTeacherByTeacherId(Long.parseLong(teacherRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void tWSubject(TeacherRequestForSearch teacherRequestForSearch) {
        switch (teacherRequestForSearch.getSearchBy()) {
            case SubjectId ->
                    rContent.add(getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(teacherRequestForSearch.getSearchValue()))));
            case Name ->
                    rContent = getTeacherBySubjects(subjectService.getSubjectsByName(teacherRequestForSearch.getSearchValue()));
            case TeacherId ->
                    rContent.add(getTeacherByTeacherId(Long.parseLong(teacherRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void tWMark(TeacherRequestForSearch teacherRequestForSearch) {
        switch (teacherRequestForSearch.getSearchBy()) {
            case MarkId ->
                    rContent.add(getTeacherByMark(markService.getMarkByMarkid(Long.parseLong(teacherRequestForSearch.getSearchValue()))));
            case DiaryId ->
                    rContent = getTeachersByMarks(markService.getMarksByDiaryid(Integer.parseInt(teacherRequestForSearch.getSearchValue())));
            case Date ->
                    rContent = getTeachersByMarks(markService.getMarksByDate(teacherRequestForSearch.getSearchValue()));
            case SubjectId ->
                    rContent.add(getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(teacherRequestForSearch.getSearchValue()))));
            case Mark ->
                    rContent = getTeachersByMarks(markService.getMarksByMark(Byte.parseByte(teacherRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void tWTeacher(TeacherRequestForSearch teacherRequestForSearch) {
        switch (teacherRequestForSearch.getSearchBy()) {
            case TeacherId ->
                    rContent.add(getTeacherByTeacherId(Long.parseLong(teacherRequestForSearch.getSearchValue())));
            case Name -> rContent = getTeacherByName(teacherRequestForSearch.getSearchValue());
            case Date -> rContent = getTeacherByBirth(teacherRequestForSearch.getSearchValue());
            default -> {
            }
        }
    }

    protected Teacher getTeacherByClass(@NotNull Class division) {
        return getTeacherByTeacherId(division.getTeacherId());
    }

    protected ArrayList<Teacher> getTeachersByClasses(@NotNull ArrayList<Class> classes) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Class division : classes) {
            teachers.add(getTeacherByClass(division));
        }
        return teachers;
    }

    protected Teacher getTeacherBySubject(@NotNull Subject subject) {
        return getTeacherByTeacherId(subject.getTid());
    }

    protected ArrayList<Teacher> getTeacherBySubjects(@NotNull ArrayList<Subject> subjects) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Subject subject : subjects) {
            teachers.add(getTeacherBySubject(subject));
        }
        return teachers;
    }

    protected Teacher getTeacherByMark(@NotNull Mark mark) {
        return getTeacherBySubject(subjectCrudService.getSubjectBySubjectId(mark.getSubjectID()));
    }

    protected ArrayList<Teacher> getTeachersByMarks(@NotNull ArrayList<Mark> marks) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Mark mark : marks) {
            teachers.add(getTeacherByMark(mark));
        }
        return teachers;
    }

    protected ArrayList<Teacher> getTeachersByDiary(@NotNull Diary diary) {
        ArrayList<Teacher> result = new ArrayList<>();
        for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksByDiaryid(diary.getId()))) {
            result.add(getTeacherByMark(mark));
        }
        return result;
    }

    protected ArrayList<Teacher> getTeachersByDiaries(@NotNull ArrayList<Diary> diaries) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Diary diary : diaries) {
            teachers.addAll(getTeachersByDiary(diary));
        }
        return teachers;
    }

    protected ArrayList<Teacher> getTeachersByStudents(@NotNull ArrayList<Student> students) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Student student : students) {
            teachers.addAll(getTeachersByStudent(student));
        }
        return teachers;
    }

    protected ArrayList<Teacher> getTeachersByStudent(@NotNull Student student) {
        ArrayList<Teacher> result = new ArrayList<>();
        for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByScid(student.getId()))) {
            result.addAll(getTeachersByDiary(diary));
        }
        return result;
    }
}
