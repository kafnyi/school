package hu.wurfel.refference.school.diary;

import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
import hu.wurfel.refference.school.division.ClassService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.mark.MarkService;
import hu.wurfel.refference.school.student.Student;
import hu.wurfel.refference.school.student.StudentService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectService;
import hu.wurfel.refference.school.teacher.Teacher;
import hu.wurfel.refference.school.teacher.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class DiaryService extends DiaryCrudService {

    private ClassCrudService classCrudService;
    private MarkCrudService markCrudService;
    private StudentService studentService;
    private TeacherService teacherService;
    private SubjectService subjectService;
    private ClassService classService;
    private MarkService markService;
    private ArrayList<Diary> rContent;


    public ArrayList getAutomated(DiaryRequestForSearch diaryRequestForSearch) {
        rContent = new ArrayList<>();
        switch (diaryRequestForSearch.getSearchWith()) {
            case Student -> dWStudent(diaryRequestForSearch);
            case Diary -> dWDiary(diaryRequestForSearch);
            case Class -> dWClass(diaryRequestForSearch);
            case Subject -> dWSubject(diaryRequestForSearch);
            case Mark -> dWMark(diaryRequestForSearch);
            case Teacher -> dWTeacher(diaryRequestForSearch);
            default -> {
            }
        }
        return rContent;
    }

    private void dWStudent(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case StudentId ->
                    rContent = getDiariesByStudent(studentService.getStudentByStudentId(Long.parseLong(diaryRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getDiariesByStudents(studentService.getStudentsByName(diaryRequestForSearch.getSearchValue()));
            case Date ->
                    rContent = getDiariesByStudents(studentService.getStudentsByBirth(diaryRequestForSearch.getSearchValue()));
            default -> {
            }
        }
    }

    private void dWDiary(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case DiaryId -> rContent.add(getDiaryByDiaryid(Integer.parseInt(diaryRequestForSearch.getSearchValue())));
            case StudentId -> rContent = getDiariesByScid(Long.parseLong(diaryRequestForSearch.getSearchValue()));
            case ClassId -> rContent = getDiariesByClassid(Integer.parseInt(diaryRequestForSearch.getSearchValue()));
            default -> {
            }
        }
    }

    private void dWClass(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case ClassId -> rContent = getDiariesByClassid(Integer.parseInt(diaryRequestForSearch.getSearchValue()));
            case Grade ->
                    rContent = getDiariesByClasses(classService.getClassesByGrade(Short.parseShort(diaryRequestForSearch.getSearchValue())));
            case Sign ->
                    rContent = getDiariesByClasses(classService.getClassesBySign(diaryRequestForSearch.getSearchValue().strip().charAt(0)));
            case Year ->
                    rContent = getDiariesByClasses(classService.getClassesByYear(Year.parse(diaryRequestForSearch.getSearchValue())));
            case TeacherId ->
                    rContent = getDiariesByClasses(classService.getClassesByTid(Long.parseLong(diaryRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void dWSubject(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case SubjectId ->
                    rContent = getDiariesBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(diaryRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getDiariesBySubjects(subjectService.getSubjectsByName(diaryRequestForSearch.getSearchValue()));
            case TeacherId ->
                    rContent = getDiariesBySubjects(subjectService.getSubjectsByTid(Long.parseLong(diaryRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void dWMark(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case MarkId ->
                    rContent.add(getDiaryByMark(markService.getMarkByMarkid(Long.parseLong(diaryRequestForSearch.getSearchValue()))));
            case DiaryId -> rContent.add(getDiaryByDiaryid(Integer.parseInt(diaryRequestForSearch.getSearchValue())));
            case Date ->
                    rContent = getDiariesByMarks(markService.getMarksByDate(diaryRequestForSearch.getSearchValue()));
            case SubjectId ->
                    rContent = getDiariesByMarks(markService.getMarksBySubjectid(Integer.parseInt(diaryRequestForSearch.getSearchValue())));
            case Mark ->
                    rContent = getDiariesByMarks(markService.getMarksByMark(Byte.parseByte(diaryRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void dWTeacher(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case TeacherId ->
                    rContent = getDiariesByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(diaryRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getDiariesByTeachers(teacherService.getTeacherByName(diaryRequestForSearch.getSearchValue()));
            case Date ->
                    rContent = getDiariesByTeachers(teacherService.getTeacherByBirth(diaryRequestForSearch.getSearchValue()));
            default -> {
            }
        }
    }

    protected ArrayList<Diary> getDiariesByStudent(@NotNull Student student) {
        return getDiariesByScid(student.getId());
    }

    protected ArrayList<Diary> getDiariesByStudents(@NotNull ArrayList<Student> students) {
        ArrayList<Diary> diaries = new ArrayList<>();
        for (Student student : students) {
            diaries.addAll(getDiariesByStudents(students));
        }
        return diaries;
    }

    protected ArrayList<Diary> getDiariesByClass(@NotNull Class division) {
        return getDiariesByClassid(division.getId());
    }

    protected ArrayList<Diary> getDiariesByClasses(@NotNull ArrayList<Class> classes) {
        ArrayList<Diary> diaries = new ArrayList<>();
        for (Class division : classes) {
            diaries.addAll(getDiariesByClass(division));
        }
        return diaries;
    }

    protected Diary getDiaryByMark(@NotNull Mark mark) {
        return getDiaryByDiaryid(mark.getDiaryID());
    }

    protected ArrayList<Diary> getDiariesByMarks(@NotNull ArrayList<Mark> marks) {
        ArrayList<Diary> diaries = new ArrayList<>();
        for (Mark mark : marks) {
            diaries.add(getDiaryByMark(mark));
        }
        return diaries;
    }

    protected ArrayList<Diary> getDiariesBySubject(@NotNull Subject subject) {
        ArrayList<Diary> result = new ArrayList<>();
        for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksBySubjectid(subject.getId()))) {
            result.add(getDiaryByMark(mark));
        }
        return result;
    }

    protected ArrayList<Diary> getDiariesBySubjects(@NotNull ArrayList<Subject> subjects) {
        ArrayList<Diary> diaries = new ArrayList<>();
        for (Subject subject : subjects) {
            diaries.addAll(getDiariesBySubject(subject));
        }
        return diaries;
    }

    protected ArrayList<Diary> getDiariesByTeacher(@NotNull Teacher teacher) {
        ArrayList<Diary> result = new ArrayList<>();
        for (Class division : new ArrayList<Class>(classCrudService.getClassesByTid(teacher.getId()))) {
            result.addAll(getDiariesByClass(division));
        }
        return result;
    }

    protected ArrayList<Diary> getDiariesByTeachers(@NotNull ArrayList<Teacher> teachers) {
        ArrayList<Diary> diaries = new ArrayList<>();
        for (Teacher teacher : teachers) {
            diaries.addAll(getDiariesByTeacher(teacher));
        }
        return diaries;
    }
}
