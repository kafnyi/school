package hu.wurfel.refference.school.subject;

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

    public List<Subject> getAutomated(SubjectRequestForSearch request) {
        rContent = new ArrayList<>();
        switch (request.getSearchWith()) {
            case Student -> sjWStudent(request);
            case Diary -> sjWDiary(request);
            case Class -> sjWClass(request);
            case Subject -> sjWSubject(request);
            case Mark -> sjWMark(request);
            case Teacher -> sjWTeacher(request);
            default -> {
            }
        }
        return rContent;
    }

    private void sjWStudent(SubjectRequestForSearch request) {
        switch (request.getSearchBy()) {
            case StudentId ->
                    rContent = getSubjectsByStudent(studentService.getStudentByStudentId(Long.parseLong(request.getSearchValue())));
            case Name -> rContent = getSubjectsByStudents(studentService.getStudentsByName(request.getSearchValue()));
            case Date -> rContent = getSubjectsByStudents(studentService.getStudentsByBirth(request.getSearchValue()));
            default -> {
            }
        }
    }

    private void sjWDiary(SubjectRequestForSearch request) {
        switch (request.getSearchBy()) {
            case DiaryId ->
                    rContent = getSubjectsByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getSearchValue())));
            case StudentId ->
                    rContent = getSubjectsByDiaries(diaryService.getDiariesByScid(Long.parseLong(request.getSearchValue())));
            case ClassId ->
                    rContent = getSubjectsByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(request.getSearchValue())));
            default -> {
            }
        }
    }

    private void sjWClass(SubjectRequestForSearch request) {
        switch (request.getSearchBy()) {
            case ClassId ->
                    rContent = getSubjectsByClass(classService.getClassByClassId(Integer.parseInt(request.getSearchValue())));
            case Grade ->
                    rContent = getSubjectsByClasses(classService.getClassesByGrade(Short.parseShort(request.getSearchValue())));
            case Sign ->
                    rContent = getSubjectsByClasses(classService.getClassesBySign(request.getSearchValue().strip().charAt(0)));
            case Year ->
                    rContent = getSubjectsByClasses(classService.getClassesByYear(Year.parse(request.getSearchValue())));
            case TeacherId ->
                    rContent = getSubjectsByClasses(classService.getClassesByTid(Long.parseLong(request.getSearchValue())));
            default -> {
            }
        }
    }

    private void sjWSubject(SubjectRequestForSearch request) {
        switch (request.getSearchBy()) {
            case SubjectId -> rContent.add(getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
            case Name -> rContent = getSubjectsByName(request.getSearchValue());
            case TeacherId -> rContent = getSubjectsByTid(Long.parseLong(request.getSearchValue()));

            default -> {
            }
        }
    }

    private void sjWMark(SubjectRequestForSearch request) {
        switch (request.getSearchBy()) {
            case MarkId ->
                    rContent.add(getSubjectByMark(markService.getMarkByMarkid(Long.parseLong(request.getSearchValue()))));
            case DiaryId ->
                    rContent = getSubjectsByMarks(markService.getMarksByDiaryid(Integer.parseInt(request.getSearchValue())));
            case Date -> rContent = getSubjectsByMarks(markService.getMarksByDate(request.getSearchValue()));
            case SubjectId -> rContent.add(getSubjectBySubjectId(Integer.parseInt(request.getSearchValue())));
            case Mark ->
                    rContent = getSubjectsByMarks(markService.getMarksByMark(Byte.parseByte(request.getSearchValue())));
            default -> {
            }
        }
    }

    private void sjWTeacher(SubjectRequestForSearch request) {
        switch (request.getSearchBy()) {
            case TeacherId ->
                    rContent = getSubjectsByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(request.getSearchValue())));
            case Name -> rContent = getSubjectsByTeachers(teacherService.getTeacherByName(request.getSearchValue()));
            case Date -> rContent = getSubjectsByTeachers(teacherService.getTeacherByBirth(request.getSearchValue()));
            default -> {
            }
        }
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
