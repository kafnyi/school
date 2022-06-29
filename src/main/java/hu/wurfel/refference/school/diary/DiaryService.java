package hu.wurfel.refference.school.diary;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
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
public class DiaryService extends DiaryCrudService {

    private final ClassCrudService classCrudService;
    private final MarkCrudService markCrudService;
    private final StudentCrudService studentCrudService;
    private final TeacherCrudService teacherCrudService;
    private final SubjectCrudService subjectCrudService;
    private List<Diary> rContent;

    public DiaryService(DiaryRepository diaryRepository, ClassCrudService classCrudService, MarkCrudService markCrudService, StudentCrudService studentCrudService, TeacherCrudService teacherCrudService, SubjectCrudService subjectCrudService) {
        super(diaryRepository);
        this.classCrudService = classCrudService;
        this.markCrudService = markCrudService;
        this.studentCrudService = studentCrudService;
        this.teacherCrudService = teacherCrudService;
        this.subjectCrudService = subjectCrudService;
    }

    List<Diary> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

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
                return new ArrayList<>();
            }
        }
    }

    List<Diary> searchWithStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId -> rContent = getByStudent(studentCrudService.getByStudentId(Long.parseLong(value)));
            case Name -> rContent = getDiariesByStudents(studentCrudService.getByName(value));
            case Date -> rContent = getDiariesByStudents(studentCrudService.getByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> searchWithDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent.add(getByDiaryId(Integer.parseInt(value)));
            case StudentId -> rContent = getByStudentId(Long.parseLong(value));
            case ClassId -> rContent = getByClassId(Integer.parseInt(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> searchWithClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getByClassId(Integer.parseInt(value));
            case Grade -> rContent = getDiariesByClasses(classCrudService.getByGrade(Short.parseShort(value)));
            case Sign -> rContent = getDiariesByClasses(classCrudService.getBySign(value.strip().charAt(0)));
            case Year -> rContent = getDiariesByClasses(classCrudService.getByYear(Year.parse(value)));
            case TeacherId -> rContent = getDiariesByClasses(classCrudService.getByTeacherId(Long.parseLong(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> searchWithSubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId -> rContent = getBySubject(subjectCrudService.getBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getBySubjects(subjectCrudService.getByName(value));
            case TeacherId -> rContent = getBySubjects(subjectCrudService.getByTeacherId(Long.parseLong(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> searchWithMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getByMark(markCrudService.getByMarkId(Long.parseLong(value))));
            case DiaryId -> rContent.add(getByDiaryId(Integer.parseInt(value)));
            case Date -> rContent = getDiariesByMarks(markCrudService.getByDate(value));
            case SubjectId -> rContent = getDiariesByMarks(markCrudService.getBySubjectid(Integer.parseInt(value)));
            case Mark -> rContent = getDiariesByMarks(markCrudService.getByMark(Byte.parseByte(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> searchWithTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId -> rContent = getByTeacher(teacherCrudService.getByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getByTeachers(teacherCrudService.getByName(value));
            case Date -> rContent = getByTeachers(teacherCrudService.getByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    protected List<Diary> getByStudent(@NotNull Student student) {
        return getByStudentId(student.getId());
    }

    protected List<Diary> getDiariesByStudents(@NotNull List<Student> students) {
        List<Diary> diaries = new ArrayList<>();
        for (Student student : students) {
            diaries.addAll(getByStudent(student));
        }
        return diaries;
    }

    protected List<Diary> getByClass(@NotNull Class division) {
        return getByClassId(division.getId());
    }

    protected List<Diary> getDiariesByClasses(@NotNull List<Class> classes) {
        List<Diary> diaries = new ArrayList<>();
        for (Class division : classes) {
            diaries.addAll(getByClass(division));
        }
        return diaries;
    }

    protected Diary getByMark(@NotNull Mark mark) {
        return getByDiaryId(mark.getDiaryID());
    }

    protected List<Diary> getDiariesByMarks(@NotNull List<Mark> marks) {
        List<Diary> diaries = new ArrayList<>();
        for (Mark mark : marks) {
            diaries.add(getByMark(mark));
        }
        return diaries;
    }

    protected List<Diary> getBySubject(@NotNull Subject subject) {
        List<Diary> result = new ArrayList<>();
        for (Mark mark : new ArrayList<>(markCrudService.getBySubjectid(subject.getId()))) {
            result.add(getByMark(mark));
        }
        return result;
    }

    protected List<Diary> getBySubjects(@NotNull List<Subject> subjects) {
        List<Diary> diaries = new ArrayList<>();
        for (Subject subject : subjects) {
            diaries.addAll(getBySubject(subject));
        }
        return diaries;
    }

    protected List<Diary> getByTeacher(@NotNull Teacher teacher) {
        List<Diary> result = new ArrayList<>();
        for (Class division : new ArrayList<>(classCrudService.getByTeacherId(teacher.getId()))) {
            result.addAll(getByClass(division));
        }
        return result;
    }

    protected List<Diary> getByTeachers(@NotNull List<Teacher> teachers) {
        List<Diary> diaries = new ArrayList<>();
        for (Teacher teacher : teachers) {
            diaries.addAll(getByTeacher(teacher));
        }
        return diaries;
    }
}
