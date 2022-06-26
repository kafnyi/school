package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.diary.DiaryService;
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
public class ClassService extends ClassCrudService {

    private MarkCrudService markCrudService;
    private DiaryCrudService diaryCrudService;
    private StudentService studentService;
    private SubjectService subjectService;
    private DiaryService diaryService;
    private MarkService markService;
    private TeacherService teacherService;
    private ArrayList<Class> rContent;

    public ArrayList getAutomated(ClassRequest classRequest) {
        rContent = new ArrayList<>();
        switch (classRequest.getSearchWith()) {
            case Student -> cWStudent(classRequest);
            case Diary -> cWDiary(classRequest);
            case Class -> cWClass(classRequest);
            case Subject -> cWSubject(classRequest);
            case Mark -> cWMark(classRequest);
            case Teacher -> cWTeacher(classRequest);
            default -> {
            }
        }
        return rContent;
    }

    private void cWStudent(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case StudentId ->
                    rContent = getClassesByStudent(studentService.getStudentByStudentId(Long.parseLong(classRequest.getSearchValue())));
            case Name ->
                    rContent = getClassesByStudents(studentService.getStudentsByName(classRequest.getSearchValue()));
            case Date ->
                    rContent = getClassesByStudents(studentService.getStudentsByBirth(classRequest.getSearchValue()));
            default -> {
            }
        }
    }

    private void cWDiary(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case DiaryId ->
                    rContent.add(getClassByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(classRequest.getSearchValue()))));
            case StudentId ->
                    rContent = getClassesByDiaries(diaryService.getDiariesByScid(Long.parseLong(classRequest.getSearchValue())));
            case ClassId -> rContent.add(getClassByClassId(Integer.parseInt(classRequest.getSearchValue())));
            default -> {
            }
        }
    }

    private void cWClass(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case ClassId -> rContent.add(getClassByClassId(Integer.parseInt(classRequest.getSearchValue())));
            case Grade -> rContent = getClassesByGrade(Short.parseShort(classRequest.getSearchValue()));
            case Sign -> rContent = getClassesBySign(classRequest.getSearchValue().strip().charAt(0));
            case Year -> rContent = getClassesByYear(Year.parse(classRequest.getSearchValue()));
            case TeacherId -> rContent = getClassesByTid(Long.parseLong(classRequest.getSearchValue()));
            default -> {
            }
        }
    }

    private void cWSubject(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case SubjectId ->
                    rContent = getClassesBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(classRequest.getSearchValue())));
            case Name ->
                    rContent = getClassesBySubjects(subjectService.getSubjectsByName(classRequest.getSearchValue()));
            case TeacherId ->
                    rContent = getClassesBySubjects(subjectService.getSubjectsByTid(Long.parseLong(classRequest.getSearchValue())));
            default -> {
            }
        }
    }

    private void cWMark(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case MarkId ->
                    rContent.add(getClassByMark(markService.getMarkByMarkid(Long.parseLong(classRequest.getSearchValue()))));
            case DiaryId ->
                    rContent.add(getClassByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(classRequest.getSearchValue()))));
            case Date -> rContent = getClassesByMarks(markService.getMarksByDate(classRequest.getSearchValue()));
            case SubjectId ->
                    rContent = getClassesByMarks(markService.getMarksBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(classRequest.getSearchValue()))));
            case Mark ->
                    rContent = getClassesByMarks(markService.getMarksByMark(Byte.parseByte(classRequest.getSearchValue())));
            default -> {
            }
        }
    }

    private void cWTeacher(ClassRequest classRequest) {
        switch (classRequest.getSearchBy()) {
            case TeacherId ->
                    rContent = getClassesByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(classRequest.getSearchValue())));
            case Name ->
                    rContent = getClassesByTeachers(teacherService.getTeacherByName(classRequest.getSearchValue()));
            case Date ->
                    rContent = getClassesByTeachers(teacherService.getTeacherByBirth(classRequest.getSearchValue()));
            default -> {
            }
        }
    }

    protected Class getClassByDiary(@NotNull Diary diary) {
        return getClassByClassId(diary.getClassID());
    }

    protected ArrayList<Class> getClassesByDiaries(@NotNull ArrayList<Diary> diaries) {
        ArrayList<Class> classes = new ArrayList<>();
        for (Diary diary : diaries) {
            classes.add(getClassByDiary(diary));
        }
        return classes;
    }

    protected Class getClassByMark(@NotNull Mark mark) {
        return getClassByClassId((diaryCrudService.getDiaryByDiaryid(mark.getDiaryID())).getClassID());
    }

    protected ArrayList<Class> getClassesByMarks(@NotNull ArrayList<Mark> marks) {
        ArrayList<Class> classes = new ArrayList<>();
        for (Mark mark : marks) {
            classes.add(getClassByMark(mark));
        }
        return classes;
    }

    protected ArrayList<Class> getClassesByTeacher(@NotNull Teacher teacher) {
        return getClassesByTid(teacher.getId());
    }

    protected ArrayList<Class> getClassesByTeachers(@NotNull ArrayList<Teacher> teachers) {
        ArrayList<Class> classes = new ArrayList<>();
        for (Teacher teacher : teachers) {
            classes.addAll(getClassesByTeacher(teacher));
        }
        return classes;
    }

    protected ArrayList<Class> getClassesByStudent(@NotNull Student student) {
        ArrayList<Class> classes = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getDiariesByScid(student.getId()))) {
            classes.add(getClassByDiary(diary));
        }
        return classes;
    }

    protected ArrayList<Class> getClassesByStudents(@NotNull ArrayList<Student> students) {
        ArrayList<Class> classes = new ArrayList<>();
        for (Student student : students) {
            classes.addAll(getClassesByStudent(student));
        }
        return classes;
    }

    protected ArrayList<Class> getClassesBySubject(@NotNull Subject subject) {
        ArrayList<Class> classes = new ArrayList<>();
        for (Mark mark : new ArrayList<>(markCrudService.getMarksBySubjectid(subject.getId()))) {
            classes.add(getClassByMark(mark));
        }
        return classes;
    }

    protected ArrayList<Class> getClassesBySubjects(@NotNull ArrayList<Subject> subjects) {
        ArrayList<Class> classes = new ArrayList<>();
        for (Subject subject : subjects) {
            classes.addAll(getClassesBySubject(subject));
        }
        return classes;
    }


}
