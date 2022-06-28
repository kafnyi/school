package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
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

    List<Class> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

        switch (searchWith) {
            case Student -> {
                return searchWithStudent(searchBy, value);
            }
            case Diary -> {
                return searchWithDiary(searchBy, value);
            }
            case Class -> {
                return searchWithClass(searchBy, value);
            }
            case Subject -> {
                return searchWithSubject(searchBy, value);
            }
            case Mark -> {
                return searchWithMark(searchBy, value);
            }
            case Teacher -> {
                return searchWithTeacher(searchBy, value);
            }
            default -> {
                return null;
            }
        }
    }

    List<Class> searchWithStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId -> rContent = getByStudent(studentCrudService.getByStudentId(Long.parseLong(value)));
            case Name -> rContent = getByStudents(studentCrudService.getByName(value));
            case Date -> rContent = getByStudents(studentCrudService.getByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> searchWithDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent.add(getByDiary(diaryCrudService.getByDiaryId(Integer.parseInt(value))));
            case StudentId -> rContent = getClassesByDiaries(diaryCrudService.getByStudentId(Long.parseLong(value)));
            case ClassId -> rContent.add(getByClassId(Integer.parseInt(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> searchWithClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent.add(getByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getByGrade(Short.parseShort(value));
            case Sign -> rContent = getBySign(value.strip().charAt(0));
            case Year -> rContent = getByYear(Year.parse(value));
            case TeacherId -> rContent = getByTeacherId(Long.parseLong(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> searchWithSubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId -> rContent = getBySubject(subjectCrudService.getBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getBySubjects(subjectCrudService.getByName(value));
            case TeacherId -> rContent = getBySubjects(subjectCrudService.getByTeacherId(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> searchWithMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getByMark(markCrudService.getByMarkId(Long.parseLong(value))));
            case DiaryId -> rContent.add(getByDiary(diaryCrudService.getByDiaryId(Integer.parseInt(value))));
            case Date -> rContent = getClassesByMarks(markCrudService.getByDate(value));
            case SubjectId -> rContent = getClassesByMarks(markCrudService.getBySubjectid(Integer.parseInt(value)));
            case Mark -> rContent = getClassesByMarks(markCrudService.getByMark(Byte.parseByte(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Class> searchWithTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId -> rContent = getByTeacher(teacherCrudService.getByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getByTeachers(teacherCrudService.getByName(value));
            case Date -> rContent = getByTeachers(teacherCrudService.getByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    protected Class getByDiary(@NotNull Diary diary) {
        return getByClassId(diary.getClassID());
    }

    protected List<Class> getClassesByDiaries(@NotNull List<Diary> diaries) {
        List<Class> classes = new ArrayList<>();
        for (Diary diary : diaries) {
            classes.add(getByDiary(diary));
        }
        return classes;
    }

    protected Class getByMark(@NotNull Mark mark) {
        return getByClassId((diaryCrudService.getByDiaryId(mark.getDiaryID())).getClassID());
    }

    protected List<Class> getClassesByMarks(@NotNull List<Mark> marks) {
        List<Class> classes = new ArrayList<>();
        for (Mark mark : marks) {
            classes.add(getByMark(mark));
        }
        return classes;
    }

    protected List<Class> getByTeacher(@NotNull Teacher teacher) {
        return getByTeacherId(teacher.getId());
    }

    protected List<Class> getByTeachers(@NotNull List<Teacher> teachers) {
        List<Class> classes = new ArrayList<>();
        for (Teacher teacher : teachers) {
            classes.addAll(getByTeacher(teacher));
        }
        return classes;
    }

    protected List<Class> getByStudent(@NotNull Student student) {
        List<Class> classes = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getByStudentId(student.getId()))) {
            classes.add(getByDiary(diary));
        }
        return classes;
    }

    protected List<Class> getByStudents(@NotNull List<Student> students) {
        List<Class> classes = new ArrayList<>();
        for (Student student : students) {
            classes.addAll(getByStudent(student));
        }
        return classes;
    }

    protected List<Class> getBySubject(@NotNull Subject subject) {
        List<Class> classes = new ArrayList<>();
        for (Mark mark : new ArrayList<>(markCrudService.getBySubjectid(subject.getId()))) {
            classes.add(getByMark(mark));
        }
        return classes;
    }

    protected List<Class> getBySubjects(@NotNull List<Subject> subjects) {
        List<Class> classes = new ArrayList<>();
        for (Subject subject : subjects) {
            classes.addAll(getBySubject(subject));
        }
        return classes;
    }


}
