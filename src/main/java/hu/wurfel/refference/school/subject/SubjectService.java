package hu.wurfel.refference.school.subject;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.student.Student;
import hu.wurfel.refference.school.student.StudentCrudService;
import hu.wurfel.refference.school.teacher.Teacher;
import hu.wurfel.refference.school.teacher.TeacherCrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService extends SubjectCrudService {

    private final DiaryCrudService diaryCrudService;
    private final MarkCrudService markCrudService;
    private final ClassCrudService classCrudService;
    private final StudentCrudService studentCrudService;
    private final TeacherCrudService teacherCrudService;
    private List<Subject> rContent;

    public SubjectService(SubjectRepository subjectRepository, DiaryCrudService diaryCrudService, MarkCrudService markCrudService, ClassCrudService classCrudService, StudentCrudService studentCrudService, TeacherCrudService teacherCrudService) {
        super(subjectRepository);
        this.diaryCrudService = diaryCrudService;
        this.markCrudService = markCrudService;
        this.classCrudService = classCrudService;
        this.studentCrudService = studentCrudService;
        this.teacherCrudService = teacherCrudService;
    }


    List<Subject> sjWStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId ->
                    rContent = getSubjectsByStudent(studentCrudService.getStudentByStudentId(Long.parseLong(value)));
            case Name -> rContent = getSubjectsByStudents(studentCrudService.getStudentsByName(value));
            case Date -> rContent = getSubjectsByStudents(studentCrudService.getStudentsByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> sjWDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent = getSubjectsByDiary(diaryCrudService.getByDiaryId(Integer.parseInt(value)));
            case StudentId -> rContent = getSubjectsByDiaries(diaryCrudService.getByScid(Long.parseLong(value)));
            case ClassId -> rContent = getSubjectsByDiaries(diaryCrudService.getByClassid(Integer.parseInt(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> sjWClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getSubjectsByClass(classCrudService.getByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getSubjectsByClasses(classCrudService.getByGrade(Short.parseShort(value)));
            case Sign -> rContent = getSubjectsByClasses(classCrudService.getBySign(value.strip().charAt(0)));
            case Year -> rContent = getSubjectsByClasses(classCrudService.getByYear(Year.parse(value)));
            case TeacherId -> rContent = getSubjectsByClasses(classCrudService.getByTid(Long.parseLong(value)));
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
            case MarkId -> rContent.add(getSubjectByMark(markCrudService.getByMarkid(Long.parseLong(value))));
            case DiaryId -> rContent = getSubjectsByMarks(markCrudService.getByDiaryid(Integer.parseInt(value)));
            case Date -> rContent = getSubjectsByMarks(markCrudService.getByDate(value));
            case SubjectId -> rContent.add(getSubjectBySubjectId(Integer.parseInt(value)));
            case Mark -> rContent = getSubjectsByMarks(markCrudService.getByMark(Byte.parseByte(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> sjWTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId ->
                    rContent = getSubjectsByTeacher(teacherCrudService.getTeacherByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getSubjectsByTeachers(teacherCrudService.getTeacherByName(value));
            case Date -> rContent = getSubjectsByTeachers(teacherCrudService.getTeacherByBirth(value));
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
        for (Mark mark : new ArrayList<>(markCrudService.getByDiaryid(diary.getId()))) {
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
        for (Diary diary : new ArrayList<>(diaryCrudService.getByScid(student.getId()))) {
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
        for (Diary diary : new ArrayList<>(diaryCrudService.getByClassid(division.getId()))) {
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
