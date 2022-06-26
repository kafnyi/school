package hu.wurfel.refference.school.subject;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
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
import java.util.List;

@Service
public class SubjectService extends SubjectCrudService {

    private final DiaryCrudService diaryCrudService;
    private final MarkCrudService markCrudService;
    private final DiaryService diaryService;
    private final ClassService classService;
    private final MarkService markService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private List<Subject> rContent;

    public SubjectService(DiaryCrudService diaryCrudService, MarkCrudService markCrudService, DiaryService diaryService, ClassService classService, MarkService markService, StudentService studentService, TeacherService teacherService, SubjectRepository subjectRepository) {
        super(subjectRepository);
        this.diaryCrudService = diaryCrudService;
        this.markCrudService = markCrudService;
        this.diaryService = diaryService;
        this.classService = classService;
        this.markService = markService;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    List<Subject> sjWStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId ->
                    rContent = getSubjectsByStudent(studentService.getStudentByStudentId(Long.parseLong(value)));
            case Name -> rContent = getSubjectsByStudents(studentService.getStudentsByName(value));
            case Date -> rContent = getSubjectsByStudents(studentService.getStudentsByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> sjWDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent = getSubjectsByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(value)));
            case StudentId -> rContent = getSubjectsByDiaries(diaryService.getDiariesByScid(Long.parseLong(value)));
            case ClassId -> rContent = getSubjectsByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> sjWClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getSubjectsByClass(classService.getClassByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getSubjectsByClasses(classService.getClassesByGrade(Short.parseShort(value)));
            case Sign -> rContent = getSubjectsByClasses(classService.getClassesBySign(value.strip().charAt(0)));
            case Year -> rContent = getSubjectsByClasses(classService.getClassesByYear(Year.parse(value)));
            case TeacherId -> rContent = getSubjectsByClasses(classService.getClassesByTid(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> sjWSubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId -> rContent.add(getSubjectBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getSubjectsByName(value);
            case TeacherId -> rContent = getSubjectsByTid(Long.parseLong(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> sjWMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getSubjectByMark(markService.getMarkByMarkid(Long.parseLong(value))));
            case DiaryId -> rContent = getSubjectsByMarks(markService.getMarksByDiaryid(Integer.parseInt(value)));
            case Date -> rContent = getSubjectsByMarks(markService.getMarksByDate(value));
            case SubjectId -> rContent.add(getSubjectBySubjectId(Integer.parseInt(value)));
            case Mark -> rContent = getSubjectsByMarks(markService.getMarksByMark(Byte.parseByte(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> sjWTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId ->
                    rContent = getSubjectsByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getSubjectsByTeachers(teacherService.getTeacherByName(value));
            case Date -> rContent = getSubjectsByTeachers(teacherService.getTeacherByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    protected Subject getSubjectByMark(@NotNull Mark mark) {
        return getSubjectBySubjectId(mark.getSubjectID());
    }

    protected List<Subject> getSubjectsByMarks(@NotNull List<Mark> marks) {
        List<Subject> subjects = new ArrayList<>();
        for (Mark mark : marks) {
            subjects.add(getSubjectByMark(mark));
        }
        return subjects;
    }

    protected List<Subject> getSubjectsByTeacher(@NotNull Teacher teacher) {
        return getSubjectsByTid(teacher.getId());
    }

    protected List<Subject> getSubjectsByTeachers(@NotNull List<Teacher> teachers) {
        List<Subject> subjects = new ArrayList<>();
        for (Teacher teacher : teachers) {
            subjects.addAll(getSubjectsByTeacher(teacher));
        }
        return subjects;
    }

    protected List<Subject> getSubjectsByDiary(@NotNull Diary diary) {
        List<Subject> subjects = new ArrayList<>();
        for (Mark mark : new ArrayList<>(markCrudService.getMarksByDiaryid(diary.getId()))) {
            subjects.add(getSubjectByMark(mark));
        }
        return subjects;
    }

    protected List<Subject> getSubjectsByDiaries(@NotNull List<Diary> diaries) {
        List<Subject> subjects = new ArrayList<>();
        for (Diary diary : diaries) {
            subjects.addAll(getSubjectsByDiary(diary));
        }
        return subjects;
    }

    protected List<Subject> getSubjectsByStudent(@NotNull Student student) {
        List<Subject> subjects = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getDiariesByScid(student.getId()))) {
            subjects.addAll(getSubjectsByDiary(diary));
        }
        return subjects;
    }

    protected List<Subject> getSubjectsByStudents(@NotNull List<Student> students) {
        List<Subject> subjects = new ArrayList<>();
        for (Student student : students) {
            subjects.addAll(getSubjectsByStudent(student));
        }
        return subjects;
    }


    protected List<Subject> getSubjectsByClass(@NotNull Class division) {
        List<Subject> subjects = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getDiariesByClassid(division.getId()))) {
            subjects.addAll(getSubjectsByDiary(diary));
        }
        return subjects;
    }

    protected List<Subject> getSubjectsByClasses(@NotNull List<Class> classes) {
        List<Subject> subjects = new ArrayList<>();
        for (Class division : classes) {
            subjects.addAll(getSubjectsByClass(division));
        }
        return subjects;
    }

}
