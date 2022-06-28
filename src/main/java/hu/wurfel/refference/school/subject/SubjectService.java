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

    List<Subject> searchWithStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId -> rContent = getByStudent(studentCrudService.getByStudentId(Long.parseLong(value)));
            case Name -> rContent = getByStudents(studentCrudService.getByName(value));
            case Date -> rContent = getByStudents(studentCrudService.getByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> searchWithDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent = getByDiary(diaryCrudService.getByDiaryId(Integer.parseInt(value)));
            case StudentId -> rContent = getByDiaries(diaryCrudService.getByStudentId(Long.parseLong(value)));
            case ClassId -> rContent = getByDiaries(diaryCrudService.getByClassId(Integer.parseInt(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> searchWithClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getByClass(classCrudService.getByClassId(Integer.parseInt(value)));
            case Grade -> rContent = getByClasses(classCrudService.getByGrade(Short.parseShort(value)));
            case Sign -> rContent = getByClasses(classCrudService.getBySign(value.strip().charAt(0)));
            case Year -> rContent = getByClasses(classCrudService.getByYear(Year.parse(value)));
            case TeacherId -> rContent = getByClasses(classCrudService.getByTeacherId(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> searchWithSubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId -> rContent.add(getBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getByName(value);
            case TeacherId -> rContent = getByTeacherId(Long.parseLong(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> searchWithMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getByMark(markCrudService.getByMarkId(Long.parseLong(value))));
            case DiaryId -> rContent = getByMarks(markCrudService.getByDiaryId(Integer.parseInt(value)));
            case Date -> rContent = getByMarks(markCrudService.getByDate(value));
            case SubjectId -> rContent.add(getBySubjectId(Integer.parseInt(value)));
            case Mark -> rContent = getByMarks(markCrudService.getByMark(Byte.parseByte(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Subject> searchWithTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId -> rContent = getByTeacher(teacherCrudService.getByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getByTeachers(teacherCrudService.getByName(value));
            case Date -> rContent = getByTeachers(teacherCrudService.getByBirth(value));
            default -> rContent = null;
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
        for (Mark mark : new ArrayList<>(markCrudService.getByDiaryId(diary.getId()))) {
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
        for (Diary diary : new ArrayList<>(diaryCrudService.getByStudentId(student.getId()))) {
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
        for (Diary diary : new ArrayList<>(diaryCrudService.getByClassId(division.getId()))) {
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
