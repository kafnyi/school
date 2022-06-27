package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
import hu.wurfel.refference.school.student.Student;
import hu.wurfel.refference.school.student.StudentCrudService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectCrudService;
import hu.wurfel.refference.school.teacher.Teacher;
import hu.wurfel.refference.school.teacher.TeacherCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarkService extends MarkCrudService {

    private final DiaryCrudService diaryCrudService;
    private final SubjectCrudService subjectCrudService;
    private final StudentCrudService studentCrudService;
    private final ClassCrudService classCrudService;
    private final TeacherCrudService teacherCrudService;
    private List<Mark> rContent;

    public MarkService(MarkRepository markRepository, DiaryCrudService diaryCrudService, SubjectCrudService subjectCrudService, StudentCrudService studentCrudService, ClassCrudService classCrudService, TeacherCrudService teacherCrudService) {
        super(markRepository);
        this.diaryCrudService = diaryCrudService;
        this.subjectCrudService = subjectCrudService;
        this.studentCrudService = studentCrudService;
        this.classCrudService = classCrudService;
        this.teacherCrudService = teacherCrudService;
    }


    List<Mark> mWStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId ->
                    rContent = getMarksByStudent(studentCrudService.getStudentByStudentId(Long.parseLong(value)));
            case Name -> rContent = getMarksByStudents(studentCrudService.getStudentsByName(value));
            case Date -> rContent = getMarksByStudents(studentCrudService.getStudentsByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> mWDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent = getMarksByDiary(diaryCrudService.getByDiaryId(Integer.parseInt(value)));
            case StudentId -> rContent = getMarksByDiaries(diaryCrudService.getByScid(Long.parseLong(value)));
            case ClassId -> rContent = getMarksByDiaries(diaryCrudService.getByClassid(Integer.parseInt(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> mWClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getMarksByClass(classCrudService.getByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getMarksByClasses(classCrudService.getByGrade(Short.parseShort(value)));
            case Sign -> rContent = getMarksByClasses(classCrudService.getBySign(value.strip().charAt(0)));
            case Year -> rContent = getMarksByClasses(classCrudService.getByYear(Year.parse(value)));
            case TeacherId -> rContent = getMarksByClasses(classCrudService.getByTid(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> mWSubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId ->
                    rContent = getMarksBySubject(subjectCrudService.getSubjectBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getMarksBySubjects(subjectCrudService.getSubjectsByName(value));
            case TeacherId -> rContent = getMarksBySubjects(subjectCrudService.getSubjectsByTid(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> mWMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getMarkByMarkid(Long.parseLong(value)));
            case DiaryId -> rContent = getMarksByDiaryid(Integer.parseInt(value));
            case Date -> rContent = getMarksByDate(value);
            case SubjectId -> rContent = getMarksBySubjectid(Integer.parseInt(value));
            case Mark -> rContent = getMarksByMark(Byte.parseByte(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> mWTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId ->
                    rContent = getMarksByTeacher(teacherCrudService.getTeacherByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getMarksByTeachers(teacherCrudService.getTeacherByName(value));
            case Date -> rContent = getMarksByTeachers(teacherCrudService.getTeacherByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    protected List<Mark> getMarksByDiary(@NotNull Diary diary) {
        return getMarksByDiaryid(diary.getId());
    }

    protected List<Mark> getMarksByDiaries(@NotNull List<Diary> diaries) {
        List<Mark> marks = new ArrayList<>();
        for (Diary diary : diaries) {
            marks.addAll(getMarksByDiary(diary));
        }
        return marks;
    }

    protected List<Mark> getMarksByStudent(@NotNull Student student) {
        List<Mark> marks = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getByScid(student.getId()))) {
            marks.addAll(getMarksByDiary(diary));
        }
        return marks;
    }

    protected List<Mark> getMarksByStudents(@NotNull List<Student> students) {
        List<Mark> marks = new ArrayList<>();
        for (Student student : students) {
            marks.addAll(getMarksByStudent(student));
        }
        return marks;
    }

    protected List<Mark> getMarksByClass(@NotNull Class division) {
        List<Mark> marks = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getByClassid(division.getId()))) {
            marks.addAll(getMarksByDiary(diary));
        }
        return marks;
    }

    protected List<Mark> getMarksByClasses(@NotNull List<Class> classes) {
        List<Mark> marks = new ArrayList<>();
        for (Class division : classes) {
            marks.addAll(getMarksByClass(division));
        }
        return marks;
    }

    public List<Mark> getMarksBySubject(@NotNull Subject subject) {
        return getMarksBySubjectid(subject.getId());
    }

    protected List<Mark> getMarksBySubjects(@NotNull List<Subject> subjects) {
        List<Mark> marks = new ArrayList<>();
        for (Subject subject : subjects) {
            marks.addAll(getMarksBySubject(subject));
        }
        return marks;
    }

    protected List<Mark> getMarksByTeacher(@NotNull Teacher teacher) {
        List<Mark> marks = new ArrayList<>();
        for (Subject subject : new ArrayList<>(subjectCrudService.getSubjectsByTid(teacher.getId()))) {
            marks.addAll(getMarksBySubject(subject));
        }
        return marks;
    }

    protected List<Mark> getMarksByTeachers(@NotNull List<Teacher> teachers) {
        List<Mark> marks = new ArrayList<>();
        for (Teacher teacher : teachers) {
            marks.addAll(getMarksByTeacher(teacher));
        }
        return marks;
    }
}
