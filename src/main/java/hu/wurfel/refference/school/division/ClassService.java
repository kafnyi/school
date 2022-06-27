package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
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
public class ClassService extends ClassCrudService {

    private final MarkCrudService markCrudService;
    private final DiaryCrudService diaryCrudService;
    private final StudentCrudService studentCrudService;
    private final SubjectCrudService subjectCrudService;
    private final TeacherCrudService teacherCrudService;
    private List<Class> rContent;

    public ClassService(ClassRepository classRepository, MarkCrudService markCrudService, DiaryCrudService diaryCrudService, StudentCrudService studentCrudService, SubjectCrudService subjectCrudService, TeacherCrudService teacherCrudService) {
        super(classRepository);
        this.markCrudService = markCrudService;
        this.diaryCrudService = diaryCrudService;
        this.studentCrudService = studentCrudService;
        this.subjectCrudService = subjectCrudService;
        this.teacherCrudService = teacherCrudService;
    }


    List<Class> cWStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId ->
                    rContent = getClassesByStudent(studentCrudService.getStudentByStudentId(Long.parseLong(value)));
            case Name -> rContent = getClassesByStudents(studentCrudService.getStudentsByName(value));
            case Date -> rContent = getClassesByStudents(studentCrudService.getStudentsByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> cWDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent.add(getClassByDiary(diaryCrudService.getByDiaryId(Integer.parseInt(value))));
            case StudentId -> rContent = getClassesByDiaries(diaryCrudService.getByScid(Long.parseLong(value)));
            case ClassId -> rContent.add(getClassByClassId(Integer.parseInt(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> cWClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent.add(getClassByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getClassesByGrade(Short.parseShort(value));
            case Sign -> rContent = getClassesBySign(value.strip().charAt(0));
            case Year -> rContent = getClassesByYear(Year.parse(value));
            case TeacherId -> rContent = getClassesByTid(Long.parseLong(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> cWSubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId ->
                    rContent = getClassesBySubject(subjectCrudService.getSubjectBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getClassesBySubjects(subjectCrudService.getSubjectsByName(value));
            case TeacherId ->
                    rContent = getClassesBySubjects(subjectCrudService.getSubjectsByTid(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> cWMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getClassByMark(markCrudService.getMarkByMarkid(Long.parseLong(value))));
            case DiaryId -> rContent.add(getClassByDiary(diaryCrudService.getByDiaryId(Integer.parseInt(value))));
            case Date -> rContent = getClassesByMarks(markCrudService.getMarksByDate(value));
            case SubjectId ->
                    rContent = getClassesByMarks(markCrudService.getMarksBySubjectid(Integer.parseInt(value)));
            case Mark -> rContent = getClassesByMarks(markCrudService.getMarksByMark(Byte.parseByte(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> cWTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId ->
                    rContent = getClassesByTeacher(teacherCrudService.getTeacherByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getClassesByTeachers(teacherCrudService.getTeacherByName(value));
            case Date -> rContent = getClassesByTeachers(teacherCrudService.getTeacherByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    protected Class getClassByDiary(@NotNull Diary diary) {
        return getClassByClassId(diary.getClassID());
    }

    protected List<Class> getClassesByDiaries(@NotNull List<Diary> diaries) {
        List<Class> classes = new ArrayList<>();
        for (Diary diary : diaries) {
            classes.add(getClassByDiary(diary));
        }
        return classes;
    }

    protected Class getClassByMark(@NotNull Mark mark) {
        return getClassByClassId((diaryCrudService.getByDiaryId(mark.getDiaryID())).getClassID());
    }

    protected List<Class> getClassesByMarks(@NotNull List<Mark> marks) {
        List<Class> classes = new ArrayList<>();
        for (Mark mark : marks) {
            classes.add(getClassByMark(mark));
        }
        return classes;
    }

    protected List<Class> getClassesByTeacher(@NotNull Teacher teacher) {
        return getClassesByTid(teacher.getId());
    }

    protected List<Class> getClassesByTeachers(@NotNull List<Teacher> teachers) {
        List<Class> classes = new ArrayList<>();
        for (Teacher teacher : teachers) {
            classes.addAll(getClassesByTeacher(teacher));
        }
        return classes;
    }

    protected List<Class> getClassesByStudent(@NotNull Student student) {
        List<Class> classes = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getByScid(student.getId()))) {
            classes.add(getClassByDiary(diary));
        }
        return classes;
    }

    protected List<Class> getClassesByStudents(@NotNull List<Student> students) {
        List<Class> classes = new ArrayList<>();
        for (Student student : students) {
            classes.addAll(getClassesByStudent(student));
        }
        return classes;
    }

    protected List<Class> getClassesBySubject(@NotNull Subject subject) {
        List<Class> classes = new ArrayList<>();
        for (Mark mark : new ArrayList<>(markCrudService.getMarksBySubjectid(subject.getId()))) {
            classes.add(getClassByMark(mark));
        }
        return classes;
    }

    protected List<Class> getClassesBySubjects(@NotNull List<Subject> subjects) {
        List<Class> classes = new ArrayList<>();
        for (Subject subject : subjects) {
            classes.addAll(getClassesBySubject(subject));
        }
        return classes;
    }


}
