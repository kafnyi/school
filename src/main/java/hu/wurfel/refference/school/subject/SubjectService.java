package hu.wurfel.refference.school.subject;

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
import hu.wurfel.refference.school.teacher.Teacher;
import hu.wurfel.refference.school.teacher.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class SubjectService extends SubjectCrudService {

    private DiaryCrudService diaryCrudService;
    private MarkCrudService markCrudService;
    private DiaryService diaryService;
    private ClassService classService;
    private MarkService markService;
    private StudentService studentService;
    private TeacherService teacherService;
    private ArrayList<Subject> rContent;

    public ArrayList getAutomated(SubjectRequest request) {
        rContent = new ArrayList<>();
        switch (request.getSearchWith()) {
            case Student -> sjWStudent(request);
            case Diary -> sjWDiary(request);
            case Class -> sjWClass(request);
            case Subject -> sjWSubject(request);
            case Mark -> sjWMark(request);
            case Teacher -> sjWTeacher(request);
            default -> {
            }
        }
        return rContent;
    }

    private void sjWStudent(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case StudentId ->
                    rContent = getSubjectsByStudent(studentService.getStudentByStudentId(Long.parseLong(request.getSearchValue())));
            case Name -> rContent = getSubjectsByStudents(studentService.getStudentsByName(request.getSearchValue()));
            case Date -> rContent = getSubjectsByStudents(studentService.getStudentsByBirth(request.getSearchValue()));
            default -> {
            }
        }
    }

    private void sjWDiary(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case DiaryId ->
                    rContent = getSubjectsByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getSearchValue())));
            case StudentId ->
                    rContent = getSubjectsByDiaries(diaryService.getDiariesByScid(Long.parseLong(request.getSearchValue())));
            case ClassId ->
                    rContent = getSubjectsByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(request.getSearchValue())));
            default -> {
            }
        }
    }

    private void sjWClass(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case ClassId ->
                    rContent = getSubjectsByClass(classService.getClassByClassId(Integer.parseInt(request.getSearchValue())));
            case Grade ->
                    rContent = getSubjectsByClasses(classService.getClassesByGrade(Short.parseShort(request.getSearchValue())));
            case Sign ->
                    rContent = getSubjectsByClasses(classService.getClassesBySign(request.getSearchValue().strip().charAt(0)));
            case Year ->
                    rContent = getSubjectsByClasses(classService.getClassesByYear(Year.parse(request.getSearchValue())));
            case TeacherId ->
                    rContent = getSubjectsByClasses(classService.getClassesByTid(Long.parseLong(request.getSearchValue())));
            default -> {
            }
        }
    }

    private void sjWSubject(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case SubjectId -> rContent.add(getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
            case Name -> rContent = getSubjectsByName(request.getSearchValue());
            case TeacherId -> rContent = getSubjectsByTid(Long.parseLong(request.getSearchValue()));

            default -> {
            }
        }
    }

    private void sjWMark(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case MarkId ->
                    rContent.add(getSubjectByMark(markService.getMarkByMarkid(Long.parseLong(request.getSearchValue()))));
            case DiaryId ->
                    rContent = getSubjectsByMarks(markService.getMarksByDiaryid(Integer.parseInt(request.getSearchValue())));
            case Date -> rContent = getSubjectsByMarks(markService.getMarksByDate(request.getSearchValue()));
            case SubjectId -> rContent.add(getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
            case Mark ->
                    rContent = getSubjectsByMarks(markService.getMarksByMark(Byte.parseByte(request.getSearchValue())));
            default -> {
            }
        }
    }

    private void sjWTeacher(SubjectRequest request) {
        switch (request.getSearchBy()) {
            case TeacherId ->
                    rContent = getSubjectsByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(request.getSearchValue())));
            case Name -> rContent = getSubjectsByTeachers(teacherService.getTeacherByName(request.getSearchValue()));
            case Date -> rContent = getSubjectsByTeachers(teacherService.getTeacherByBirth(request.getSearchValue()));
            default -> {
            }
        }
    }

    protected Subject getSubjectByMark(@NotNull Mark mark) {
        return getSubjectBySubjectId(mark.getSubjectID());
    }

    protected ArrayList<Subject> getSubjectsByMarks(@NotNull ArrayList<Mark> marks) {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Mark mark : marks) {
            subjects.add(getSubjectByMark(mark));
        }
        return subjects;
    }

    protected ArrayList<Subject> getSubjectsByTeacher(@NotNull Teacher teacher) {
        return getSubjectsByTid(teacher.getId());
    }

    protected ArrayList<Subject> getSubjectsByTeachers(@NotNull ArrayList<Teacher> teachers) {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Teacher teacher : teachers) {
            subjects.addAll(getSubjectsByTeacher(teacher));
        }
        return subjects;
    }

    protected ArrayList<Subject> getSubjectsByDiary(@NotNull Diary diary) {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksByDiaryid(diary.getId()))) {
            subjects.add(getSubjectByMark(mark));
        }
        return subjects;
    }

    protected ArrayList<Subject> getSubjectsByDiaries(@NotNull ArrayList<Diary> diaries) {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Diary diary : diaries) {
            subjects.addAll(getSubjectsByDiary(diary));
        }
        return subjects;
    }

    protected ArrayList<Subject> getSubjectsByStudent(@NotNull Student student) {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByScid(student.getId()))) {
            subjects.addAll(getSubjectsByDiary(diary));
        }
        return subjects;
    }

    protected ArrayList<Subject> getSubjectsByStudents(@NotNull ArrayList<Student> students) {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Student student : students) {
            subjects.addAll(getSubjectsByStudent(student));
        }
        return subjects;
    }


    protected ArrayList<Subject> getSubjectsByClass(@NotNull Class division) {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByClassid(division.getId()))) {
            subjects.addAll(getSubjectsByDiary(diary));
        }
        return subjects;
    }

    protected ArrayList<Subject> getSubjectsByClasses(@NotNull ArrayList<Class> classes) {
        ArrayList<Subject> subjects = new ArrayList<>();
        for (Class division : classes) {
            subjects.addAll(getSubjectsByClass(division));
        }
        return subjects;
    }

}
