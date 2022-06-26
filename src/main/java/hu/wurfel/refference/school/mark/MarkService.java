package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.diary.DiaryService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassService;
import hu.wurfel.refference.school.student.Student;
import hu.wurfel.refference.school.student.StudentService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectCrudService;
import hu.wurfel.refference.school.subject.SubjectService;
import hu.wurfel.refference.school.teacher.Teacher;
import hu.wurfel.refference.school.teacher.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class MarkService extends MarkCrudService {

    private final DiaryCrudService diaryCrudService;
    private final SubjectCrudService subjectCrudService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final DiaryService diaryService;
    private final ClassService classService;
    private final TeacherService teacherService;
    private List<Mark> rContent;

    public MarkService(MarkRepository markRepository, DiaryCrudService diaryCrudService, SubjectCrudService subjectCrudService, StudentService studentService, SubjectService subjectService, DiaryService diaryService, ClassService classService, TeacherService teacherService) {
        super(markRepository);
        this.diaryCrudService = diaryCrudService;
        this.subjectCrudService = subjectCrudService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.diaryService = diaryService;
        this.classService = classService;
        this.teacherService = teacherService;
    }

    List<Mark> mWStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId -> rContent = getMarksByStudent(studentService.getStudentByStudentId(Long.parseLong(value)));
            case Name -> rContent = getMarksByStudents(studentService.getStudentsByName(value));
            case Date -> rContent = getMarksByStudents(studentService.getStudentsByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> mWDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent = getMarksByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(value)));
            case StudentId -> rContent = getMarksByDiaries(diaryService.getDiariesByScid(Long.parseLong(value)));
            case ClassId -> rContent = getMarksByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> mWClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getMarksByClass(classService.getClassByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getMarksByClasses(classService.getClassesByGrade(Short.parseShort(value)));
            case Sign -> rContent = getMarksByClasses(classService.getClassesBySign(value.strip().charAt(0)));
            case Year -> rContent = getMarksByClasses(classService.getClassesByYear(Year.parse(value)));
            case TeacherId -> rContent = getMarksByClasses(classService.getClassesByTid(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Mark> mWSubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId ->
                    rContent = getMarksBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getMarksBySubjects(subjectService.getSubjectsByName(value));
            case TeacherId -> rContent = getMarksBySubjects(subjectService.getSubjectsByTid(Long.parseLong(value)));
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
            case TeacherId -> rContent = getMarksByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getMarksByTeachers(teacherService.getTeacherByName(value));
            case Date -> rContent = getMarksByTeachers(teacherService.getTeacherByBirth(value));
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
        for (Diary diary : new ArrayList<>(diaryCrudService.getDiariesByScid(student.getId()))) {
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
        for (Diary diary : new ArrayList<>(diaryCrudService.getDiariesByClassid(division.getId()))) {
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
