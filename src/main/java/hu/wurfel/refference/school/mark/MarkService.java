package hu.wurfel.refference.school.mark;

import hu.wurfel.refference.school.base.enums.EntityFieldNames;
import hu.wurfel.refference.school.base.enums.EntityNames;
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

    List<MarkDto> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {

        switch (searchWith) {
            case Student -> {
                return getDtoList(findByStudent(searchBy, value));
            }
            case Diary -> {
                return getDtoList(findByDiary(searchBy, value));
            }
            case Class -> {
                return getDtoList(findByClass(searchBy, value));
            }
            case Subject -> {
                return getDtoList(findBySubject(searchBy, value));
            }
            case Mark -> {
                return getDtoList(findByMark(searchBy, value));
            }
            case Teacher -> {
                return getDtoList(findByTeacher(searchBy, value));
            }
            default -> {
                return new ArrayList<>();
            }
        }
    }

    List<Mark> findByStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId -> rContent = getByStudent(studentCrudService.getById(Long.parseLong(value)));
            case Name -> rContent = getByStudents(studentCrudService.getAllByName(value));
            case Date -> rContent = getByStudents(studentCrudService.getAllByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Mark> findByDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent = getByDiary(diaryCrudService.getById(Integer.parseInt(value)));
            case StudentId -> rContent = getByDiaries(diaryCrudService.getAllByStudentId(Long.parseLong(value)));
            case ClassId -> rContent = getByDiaries(diaryCrudService.getAllByClassId(Integer.parseInt(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Mark> findByClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getByClass(classCrudService.getById(Integer.parseInt(value)));
            case Grade -> rContent = getByClasses(classCrudService.getAllByGrade(Short.parseShort(value)));
            case Sign -> rContent = getByClasses(classCrudService.getAllBySign(value.strip().charAt(0)));
            case Year -> rContent = getByClasses(classCrudService.getAllByYear(Year.parse(value)));
            case TeacherId -> rContent = getByClasses(classCrudService.getAllByTeacherId(Long.parseLong(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Mark> findBySubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId -> rContent = getBySubject(subjectCrudService.getById(Integer.parseInt(value)));
            case Name -> rContent = getBySubjects(subjectCrudService.getAllByName(value));
            case TeacherId -> rContent = getBySubjects(subjectCrudService.getAllByTeacherId(Long.parseLong(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Mark> findByMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getById(Long.parseLong(value)));
            case DiaryId -> rContent = getAllByDiaryId(Integer.parseInt(value));
            case Date -> rContent = getAllByDate(value);
            case SubjectId -> rContent = getAllBySubjectId(Integer.parseInt(value));
            case Mark -> rContent = getAllByMark(Byte.parseByte(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Mark> findByTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId -> rContent = getByTeacher(teacherCrudService.getByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getByTeachers(teacherCrudService.getAllByName(value));
            case Date -> rContent = getByTeachers(teacherCrudService.getAllByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    protected List<MarkDto> modify(MarkDto markDto) {
        Mark mark = getById(markDto.getId());
        mark.setDiaryId(markDto.getDiaryId());
        mark.setSubjectId(markDto.getSubjectId());
        mark.setDate(markDto.getDate());
        mark.setMark(markDto.getMark());
        List<MarkDto> answer = new ArrayList<>();
        answer.add(getDto(save(mark)));
        return answer;
    }

    protected MarkDto getDto(@NotNull Mark mark) {
        return new MarkDto(mark.getId(), mark.getDiaryId(), mark.getDate(), mark.getSubjectId(), mark.getMark());
    }

    protected List<MarkDto> getDtoList(List<Mark> markList) {
        ArrayList<MarkDto> dtoList = new ArrayList<>();
        for (Mark mark : markList) {
            dtoList.add(getDto(mark));
        }
        return dtoList;
    }

    protected List<Mark> getByDiary(@NotNull Diary diary) {
        return getAllByDiaryId(diary.getId());
    }

    protected List<Mark> getByDiaries(@NotNull List<Diary> diaries) {
        List<Mark> marks = new ArrayList<>();
        for (Diary diary : diaries) {
            marks.addAll(getByDiary(diary));
        }
        return marks;
    }

    protected List<Mark> getByStudent(@NotNull Student student) {
        List<Mark> marks = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getAllByStudentId(student.getId()))) {
            marks.addAll(getByDiary(diary));
        }
        return marks;
    }

    protected List<Mark> getByStudents(@NotNull List<Student> students) {
        List<Mark> marks = new ArrayList<>();
        for (Student student : students) {
            marks.addAll(getByStudent(student));
        }
        return marks;
    }

    protected List<Mark> getByClass(@NotNull Class division) {
        List<Mark> marks = new ArrayList<>();
        for (Diary diary : new ArrayList<>(diaryCrudService.getAllByClassId(division.getId()))) {
            marks.addAll(getByDiary(diary));
        }
        return marks;
    }

    protected List<Mark> getByClasses(@NotNull List<Class> classes) {
        List<Mark> marks = new ArrayList<>();
        for (Class division : classes) {
            marks.addAll(getByClass(division));
        }
        return marks;
    }

    public List<Mark> getBySubject(@NotNull Subject subject) {
        return getAllBySubjectId(subject.getId());
    }

    protected List<Mark> getBySubjects(@NotNull List<Subject> subjects) {
        List<Mark> marks = new ArrayList<>();
        for (Subject subject : subjects) {
            marks.addAll(getBySubject(subject));
        }
        return marks;
    }

    protected List<Mark> getByTeacher(@NotNull Teacher teacher) {
        List<Mark> marks = new ArrayList<>();
        for (Subject subject : new ArrayList<>(subjectCrudService.getAllByTeacherId(teacher.getId()))) {
            marks.addAll(getBySubject(subject));
        }
        return marks;
    }

    protected List<Mark> getByTeachers(@NotNull List<Teacher> teachers) {
        List<Mark> marks = new ArrayList<>();
        for (Teacher teacher : teachers) {
            marks.addAll(getByTeacher(teacher));
        }
        return marks;
    }
}
