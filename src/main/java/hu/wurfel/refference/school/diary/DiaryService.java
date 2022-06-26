package hu.wurfel.refference.school.diary;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
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


    List<Diary> dWStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId ->
                    rContent = getDiariesByStudent(studentCrudService.getStudentByStudentId(Long.parseLong(value)));
            case Name -> rContent = getDiariesByStudents(studentCrudService.getStudentsByName(value));
            case Date -> rContent = getDiariesByStudents(studentCrudService.getStudentsByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Diary> dWDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent.add(getDiaryByDiaryid(Integer.parseInt(value)));
            case StudentId -> rContent = getDiariesByScid(Long.parseLong(value));
            case ClassId -> rContent = getDiariesByClassid(Integer.parseInt(value));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Diary> dWClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getDiariesByClassid(Integer.parseInt(value));
            case Grade -> rContent = getDiariesByClasses(classCrudService.getClassesByGrade(Short.parseShort(value)));
            case Sign -> rContent = getDiariesByClasses(classCrudService.getClassesBySign(value.strip().charAt(0)));
            case Year -> rContent = getDiariesByClasses(classCrudService.getClassesByYear(Year.parse(value)));
            case TeacherId -> rContent = getDiariesByClasses(classCrudService.getClassesByTid(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Diary> dWSubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId ->
                    rContent = getDiariesBySubject(subjectCrudService.getSubjectBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getDiariesBySubjects(subjectCrudService.getSubjectsByName(value));
            case TeacherId ->
                    rContent = getDiariesBySubjects(subjectCrudService.getSubjectsByTid(Long.parseLong(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Diary> dWMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getDiaryByMark(markCrudService.getMarkByMarkid(Long.parseLong(value))));
            case DiaryId -> rContent.add(getDiaryByDiaryid(Integer.parseInt(value)));
            case Date -> rContent = getDiariesByMarks(markCrudService.getMarksByDate(value));
            case SubjectId ->
                    rContent = getDiariesByMarks(markCrudService.getMarksBySubjectid(Integer.parseInt(value)));
            case Mark -> rContent = getDiariesByMarks(markCrudService.getMarksByMark(Byte.parseByte(value)));
            default -> rContent = null;
        }
        return rContent;
    }

    List<Diary> dWTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId ->
                    rContent = getDiariesByTeacher(teacherCrudService.getTeacherByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getDiariesByTeachers(teacherCrudService.getTeacherByName(value));
            case Date -> rContent = getDiariesByTeachers(teacherCrudService.getTeacherByBirth(value));
            default -> rContent = null;
        }
        return rContent;
    }

    protected List<Diary> getDiariesByStudent(@NotNull Student student) {
        return getDiariesByScid(student.getId());
    }

    protected List<Diary> getDiariesByStudents(@NotNull List<Student> students) {
        List<Diary> diaries = new ArrayList<>();
        for (Student student : students) {
            diaries.addAll(getDiariesByStudent(student));
        }
        return diaries;
    }

    protected List<Diary> getDiariesByClass(@NotNull Class division) {
        return getDiariesByClassid(division.getId());
    }

    protected List<Diary> getDiariesByClasses(@NotNull List<Class> classes) {
        List<Diary> diaries = new ArrayList<>();
        for (Class division : classes) {
            diaries.addAll(getDiariesByClass(division));
        }
        return diaries;
    }

    protected Diary getDiaryByMark(@NotNull Mark mark) {
        return getDiaryByDiaryid(mark.getDiaryID());
    }

    protected List<Diary> getDiariesByMarks(@NotNull List<Mark> marks) {
        List<Diary> diaries = new ArrayList<>();
        for (Mark mark : marks) {
            diaries.add(getDiaryByMark(mark));
        }
        return diaries;
    }

    protected List<Diary> getDiariesBySubject(@NotNull Subject subject) {
        List<Diary> result = new ArrayList<>();
        for (Mark mark : new ArrayList<>(markCrudService.getMarksBySubjectid(subject.getId()))) {
            result.add(getDiaryByMark(mark));
        }
        return result;
    }

    protected List<Diary> getDiariesBySubjects(@NotNull List<Subject> subjects) {
        List<Diary> diaries = new ArrayList<>();
        for (Subject subject : subjects) {
            diaries.addAll(getDiariesBySubject(subject));
        }
        return diaries;
    }

    protected List<Diary> getDiariesByTeacher(@NotNull Teacher teacher) {
        List<Diary> result = new ArrayList<>();
        for (Class division : new ArrayList<>(classCrudService.getClassesByTid(teacher.getId()))) {
            result.addAll(getDiariesByClass(division));
        }
        return result;
    }

    protected List<Diary> getDiariesByTeachers(@NotNull List<Teacher> teachers) {
        List<Diary> diaries = new ArrayList<>();
        for (Teacher teacher : teachers) {
            diaries.addAll(getDiariesByTeacher(teacher));
        }
        return diaries;
    }
}
