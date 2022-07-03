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

    List<DiaryDto> getSearchResponseList(EntityNames searchWith, EntityFieldNames searchBy, String value) {
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

    List<Diary> findByStudent(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case StudentId -> rContent = getByStudent(studentCrudService.getByStudentId(Long.parseLong(value)));
            case Name -> rContent = getDiariesByStudents(studentCrudService.getAllByName(value));
            case Date -> rContent = getDiariesByStudents(studentCrudService.getAllByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> findByDiary(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case DiaryId -> rContent.add(getByDiaryId(Integer.parseInt(value)));
            case StudentId -> rContent = getAllByStudentId(Long.parseLong(value));
            case ClassId -> rContent = getAllByClassId(Integer.parseInt(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> findByClass(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case ClassId -> rContent = getAllByClassId(Integer.parseInt(value));
            case Grade -> rContent = getDiariesByClasses(classCrudService.getAllByGrade(Short.parseShort(value)));
            case Sign -> rContent = getDiariesByClasses(classCrudService.getAllBySign(value.strip().charAt(0)));
            case Year -> rContent = getDiariesByClasses(classCrudService.getAllByYear(Year.parse(value)));
            case TeacherId -> rContent = getDiariesByClasses(classCrudService.getAllByTeacherId(Long.parseLong(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> findBySubject(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case SubjectId -> rContent = getBySubject(subjectCrudService.getBySubjectId(Integer.parseInt(value)));
            case Name -> rContent = getBySubjects(subjectCrudService.getAllByName(value));
            case TeacherId -> rContent = getBySubjects(subjectCrudService.getAllByTeacherId(Long.parseLong(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> findByMark(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case MarkId -> rContent.add(getByMark(markCrudService.getByMarkId(Long.parseLong(value))));
            case DiaryId -> rContent.add(getByDiaryId(Integer.parseInt(value)));
            case Date -> rContent = getDiariesByMarks(markCrudService.getAllByDate(value));
            case SubjectId -> rContent = getDiariesByMarks(markCrudService.getAllBySubjectId(Integer.parseInt(value)));
            case Mark -> rContent = getDiariesByMarks(markCrudService.getAllByMark(Byte.parseByte(value)));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    List<Diary> findByTeacher(EntityFieldNames searchBy, String value) {
        switch (searchBy) {
            case TeacherId -> rContent = getByTeacher(teacherCrudService.getByTeacherId(Long.parseLong(value)));
            case Name -> rContent = getByTeachers(teacherCrudService.getAllByName(value));
            case Date -> rContent = getByTeachers(teacherCrudService.getAllByBirth(value));
            default -> rContent = new ArrayList<>();
        }
        return rContent;
    }

    protected List<DiaryDto> modify(DiaryDto diaryDto) {
        Diary diary = getByDiaryId(diaryDto.getId());
        diary.setStudentId(diaryDto.getStudentId());
        diary.setClassId(diary.getClassId());
        List<DiaryDto> answer = new ArrayList<>();
        answer.add(getDto(save(diary)));
        return answer;
    }

    protected DiaryDto getDto(@NotNull Diary diary) {
        return new DiaryDto(diary.getId(), diary.getStudentId(), diary.getClassId());
    }

    protected List<DiaryDto> getDtoList(List<Diary> diaryList) {
        ArrayList<DiaryDto> dtoList = new ArrayList<>();
        for (Diary diary : diaryList) {
            dtoList.add(getDto(diary));
        }
        return dtoList;
    }

    protected List<Diary> getByStudent(@NotNull Student student) {
        return getAllByStudentId(student.getId());
    }

    protected List<Diary> getDiariesByStudents(@NotNull List<Student> students) {
        List<Diary> diaries = new ArrayList<>();
        for (Student student : students) {
            diaries.addAll(getByStudent(student));
        }
        return diaries;
    }

    protected List<Diary> getByClass(@NotNull Class division) {
        return getAllByClassId(division.getId());
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
        for (Mark mark : new ArrayList<>(markCrudService.getAllBySubjectId(subject.getId()))) {
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
        for (Class division : new ArrayList<>(classCrudService.getAllByTeacherId(teacher.getId()))) {
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
