package hu.wurfel.refference.school.subject;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
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


    List<Subject> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

        switch (searchWith) {
            case Student -> {
                return findByStudent(searchBy, value);
            }
            case Diary -> {
                return findByDiary(searchBy, value);
            }
            case Class -> {
                return findByClass(searchBy, value);
            }
            case Subject -> {
                return findBySubject(searchBy, value);
            }
            case Mark -> {
                return findByMark(searchBy, value);
            }
            case Teacher -> {
                return findByTeacher(searchBy, value);
            }
            default -> {
                return new ArrayList<>();
            }
        }
    }

    List<Subject> findByStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId -> rContent = getByStudent(studentCrudService.getByStudentId(Long.parseLong(value)));
            case Name -> rContent = getByStudents(studentCrudService.getAllByName(value));
            case Date -> rContent = getByStudents(studentCrudService.getAllByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Subject> findByDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent = getByDiary(diaryCrudService.getByDiaryId(Integer.parseInt(value)));
            case StudentId -> rContent = getByDiaries(diaryCrudService.getAllByStudentId(Long.parseLong(value)));
            case ClassId -> rContent = getByDiaries(diaryCrudService.getAllByClassId(Integer.parseInt(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Subject> findByClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getByClass(classCrudService.getByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getByClasses(classCrudService.getAllByGrade(Short.parseShort(value)));
            case Sign -> rContent = getByClasses(classCrudService.getAllBySign(value.strip().charAt(0)));
            case Year -> rContent = getByClasses(classCrudService.getAllByYear(Year.parse(value)));
            case TeacherId -> rContent = getByClasses(classCrudService.getAllByTeacherId(Long.parseLong(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Subject> findBySubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId -> rContent.add(getBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getByName(value);
            case TeacherId -> rContent = getByTeacherId(Long.parseLong(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Subject> findByMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getByMark(markCrudService.getByMarkId(Long.parseLong(value))));
            case DiaryId -> rContent = getByMarks(markCrudService.getAllByDiaryId(Integer.parseInt(value)));
            case Date -> rContent = getByMarks(markCrudService.getAllByDate(value));
            case SubjectId -> rContent.add(getBySubjectId(Integer.parseInt(value)));
            case Mark -> rContent = getByMarks(markCrudService.getAllByMark(Byte.parseByte(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Subject> findByTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId -> rContent = getByTeacher(teacherCrudService.getByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getByTeachers(teacherCrudService.getByName(value));
            case Date -> rContent = getByTeachers(teacherCrudService.getByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    protected Subject getByMark(@NotNull Mark mark) {
        return getBySubjectId(mark.getSubjectID());
    }

    protected List<Subject> getByMarks(@NotNull List<Mark> marks) {
        List<Subject> subjects = new ArrayList<>();
        for (Mark mark : marks) {
            subjects.add(getByMark(mark));
        }
        return subjects;
    }

    protected List<Subject> getByTeacher(@NotNull Teacher teacher) {
        return getByTeacherId(teacher.getId());
    }

    protected List<Subject> getByTeachers(@NotNull List<Teacher> teachers) {
        List<Subject> subjects = new ArrayList<>();
        for (Teacher teacher : teachers) {
            subjects.addAll(getByTeacher(teacher));
        }
        return subjects;
    }

    protected List<Subject> getByDiary(@NotNull Diary diary) {
        List<Subject> subjects = new ArrayList<>();
        for (Mark mark : new ArrayList<>(markCrudService.getAllByDiaryId(diary.getId()))) {
            subjects.add(getByMark(mark));
        }
        return subjects;
    }

    protected List<Subject> getByDiaries(@NotNull List<Diary> diaries) {
        List<Subject> subjects = new ArrayList<>();
        for (Diary diary : diaries) {
            subjects.addAll(getByDiary(diary));
        }
        return subjects;
    }

    protected List<Subject> getByStudent(@NotNull Student student) {
        List<Subject> subjects = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getAllByStudentId(student.getId()))) {
            subjects.addAll(getByDiary(diary));
        }
        return subjects;
    }

    protected List<Subject> getByStudents(@NotNull List<Student> students) {
        List<Subject> subjects = new ArrayList<>();
        for (Student student : students) {
            subjects.addAll(getByStudent(student));
        }
        return subjects;
    }


    protected List<Subject> getByClass(@NotNull Class division) {
        List<Subject> subjects = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getAllByClassId(division.getId()))) {
            subjects.addAll(getByDiary(diary));
        }
        return subjects;
    }

    protected List<Subject> getByClasses(@NotNull List<Class> classes) {
        List<Subject> subjects = new ArrayList<>();
        for (Class division : classes) {
            subjects.addAll(getByClass(division));
        }
        return subjects;
    }

}
