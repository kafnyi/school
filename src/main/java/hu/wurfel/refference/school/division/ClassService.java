package hu.wurfel.refference.school.division;

import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.diary.DiaryService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.mark.MarkService;
import hu.wurfel.refference.school.student.Student;
import hu.wurfel.refference.school.student.StudentService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectService;
import hu.wurfel.refference.school.teacher.Teacher;
import hu.wurfel.refference.school.teacher.TeacherService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService extends ClassCrudService {

    private final MarkCrudService markCrudService;
    private final DiaryCrudService diaryCrudService;
    private final StudentService studentService;
    private final SubjectService subjectService;
    private final DiaryService diaryService;
    private final MarkService markService;
    private final TeacherService teacherService;
    private List<Class> rContent;


    public ClassService(ClassRepository classRepository, MarkCrudService markCrudService, DiaryCrudService diaryCrudService, StudentService studentService, SubjectService subjectService, DiaryService diaryService, MarkService markService, TeacherService teacherService) {
        super(classRepository);
        this.markCrudService = markCrudService;
        this.diaryCrudService = diaryCrudService;
        this.studentService = studentService;
        this.subjectService = subjectService;
        this.diaryService = diaryService;
        this.markService = markService;
        this.teacherService = teacherService;
    }

    public List<Class> getAutomated(ClassRequestForSearch classRequestForSearch) {
        rContent = new ArrayList<>();
        switch (classRequestForSearch.getSearchWith()) {
            case Student -> cWStudent(classRequestForSearch);
            case Diary -> cWDiary(classRequestForSearch);
            case Class -> cWClass(classRequestForSearch);
            case Subject -> cWSubject(classRequestForSearch);
            case Mark -> cWMark(classRequestForSearch);
            case Teacher -> cWTeacher(classRequestForSearch);
            default -> {
            }
        }
        return rContent;
    }

    private void cWStudent(ClassRequestForSearch classRequestForSearch) {
        switch (classRequestForSearch.getSearchBy()) {
            case StudentId ->
                    rContent = getClassesByStudent(studentService.getStudentByStudentId(Long.parseLong(classRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getClassesByStudents(studentService.getStudentsByName(classRequestForSearch.getSearchValue()));
            case Date ->
                    rContent = getClassesByStudents(studentService.getStudentsByBirth(classRequestForSearch.getSearchValue()));
            default -> {
            }
        }
    }

    private void cWDiary(ClassRequestForSearch classRequestForSearch) {
        switch (classRequestForSearch.getSearchBy()) {
            case DiaryId ->
                    rContent.add(getClassByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(classRequestForSearch.getSearchValue()))));
            case StudentId ->
                    rContent = getClassesByDiaries(diaryService.getDiariesByScid(Long.parseLong(classRequestForSearch.getSearchValue())));
            case ClassId -> rContent.add(getClassByClassId(Integer.parseInt(classRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void cWClass(ClassRequestForSearch classRequestForSearch) {
        switch (classRequestForSearch.getSearchBy()) {
            case ClassId -> rContent.add(getClassByClassId(Integer.parseInt(classRequestForSearch.getSearchValue())));
            case Grade -> rContent = getClassesByGrade(Short.parseShort(classRequestForSearch.getSearchValue()));
            case Sign -> rContent = getClassesBySign(classRequestForSearch.getSearchValue().strip().charAt(0));
            case Year -> rContent = getClassesByYear(Year.parse(classRequestForSearch.getSearchValue()));
            case TeacherId -> rContent = getClassesByTid(Long.parseLong(classRequestForSearch.getSearchValue()));
            default -> {
            }
        }
    }

    private void cWSubject(ClassRequestForSearch classRequestForSearch) {
        switch (classRequestForSearch.getSearchBy()) {
            case SubjectId ->
                    rContent = getClassesBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(classRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getClassesBySubjects(subjectService.getSubjectsByName(classRequestForSearch.getSearchValue()));
            case TeacherId ->
                    rContent = getClassesBySubjects(subjectService.getSubjectsByTid(Long.parseLong(classRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void cWMark(ClassRequestForSearch classRequestForSearch) {
        switch (classRequestForSearch.getSearchBy()) {
            case MarkId ->
                    rContent.add(getClassByMark(markService.getMarkByMarkid(Long.parseLong(classRequestForSearch.getSearchValue()))));
            case DiaryId ->
                    rContent.add(getClassByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(classRequestForSearch.getSearchValue()))));
            case Date ->
                    rContent = getClassesByMarks(markService.getMarksByDate(classRequestForSearch.getSearchValue()));
            case SubjectId ->
                    rContent = getClassesByMarks(markService.getMarksBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(classRequestForSearch.getSearchValue()))));
            case Mark ->
                    rContent = getClassesByMarks(markService.getMarksByMark(Byte.parseByte(classRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void cWTeacher(ClassRequestForSearch classRequestForSearch) {
        switch (classRequestForSearch.getSearchBy()) {
            case TeacherId ->
                    rContent = getClassesByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(classRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getClassesByTeachers(teacherService.getTeacherByName(classRequestForSearch.getSearchValue()));
            case Date ->
                    rContent = getClassesByTeachers(teacherService.getTeacherByBirth(classRequestForSearch.getSearchValue()));
            default -> {
            }
        }
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
        return getClassByClassId((diaryCrudService.getDiaryByDiaryid(mark.getDiaryID())).getClassID());
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
        for (Diary diary : new ArrayList<>(diaryCrudService.getDiariesByScid(student.getId()))) {
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
