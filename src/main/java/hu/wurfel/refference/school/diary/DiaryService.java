package hu.wurfel.refference.school.diary;

import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
import hu.wurfel.refference.school.division.ClassService;
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
public class DiaryService extends DiaryCrudService {

    private final ClassCrudService classCrudService;
    private final MarkCrudService markCrudService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final ClassService classService;
    private final MarkService markService;
    private List<Diary> rContent;


    public DiaryService(DiaryRepository diaryRepository, ClassCrudService classCrudService, MarkCrudService markCrudService, StudentService studentService, TeacherService teacherService, SubjectService subjectService, ClassService classService, MarkService markService) {
        super(diaryRepository);
        this.classCrudService = classCrudService;
        this.markCrudService = markCrudService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.subjectService = subjectService;
        this.classService = classService;
        this.markService = markService;
    }


    public List<Diary> getAutomated(DiaryRequestForSearch diaryRequestForSearch) {
        rContent = new ArrayList<>();
        switch (diaryRequestForSearch.getSearchWith()) {
            case Student -> dWStudent(diaryRequestForSearch);
            case Diary -> dWDiary(diaryRequestForSearch);
            case Class -> dWClass(diaryRequestForSearch);
            case Subject -> dWSubject(diaryRequestForSearch);
            case Mark -> dWMark(diaryRequestForSearch);
            case Teacher -> dWTeacher(diaryRequestForSearch);
            default -> {
            }
        }
        return rContent;
    }

    private void dWStudent(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case StudentId ->
                    rContent = getDiariesByStudent(studentService.getStudentByStudentId(Long.parseLong(diaryRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getDiariesByStudents(studentService.getStudentsByName(diaryRequestForSearch.getSearchValue()));
            case Date ->
                    rContent = getDiariesByStudents(studentService.getStudentsByBirth(diaryRequestForSearch.getSearchValue()));
            default -> {
            }
        }
    }

    private void dWDiary(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case DiaryId -> rContent.add(getDiaryByDiaryid(Integer.parseInt(diaryRequestForSearch.getSearchValue())));
            case StudentId -> rContent = getDiariesByScid(Long.parseLong(diaryRequestForSearch.getSearchValue()));
            case ClassId -> rContent = getDiariesByClassid(Integer.parseInt(diaryRequestForSearch.getSearchValue()));
            default -> {
            }
        }
    }

    private void dWClass(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case ClassId -> rContent = getDiariesByClassid(Integer.parseInt(diaryRequestForSearch.getSearchValue()));
            case Grade ->
                    rContent = getDiariesByClasses(classService.getClassesByGrade(Short.parseShort(diaryRequestForSearch.getSearchValue())));
            case Sign ->
                    rContent = getDiariesByClasses(classService.getClassesBySign(diaryRequestForSearch.getSearchValue().strip().charAt(0)));
            case Year ->
                    rContent = getDiariesByClasses(classService.getClassesByYear(Year.parse(diaryRequestForSearch.getSearchValue())));
            case TeacherId ->
                    rContent = getDiariesByClasses(classService.getClassesByTid(Long.parseLong(diaryRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void dWSubject(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case SubjectId ->
                    rContent = getDiariesBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(diaryRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getDiariesBySubjects(subjectService.getSubjectsByName(diaryRequestForSearch.getSearchValue()));
            case TeacherId ->
                    rContent = getDiariesBySubjects(subjectService.getSubjectsByTid(Long.parseLong(diaryRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void dWMark(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case MarkId ->
                    rContent.add(getDiaryByMark(markService.getMarkByMarkid(Long.parseLong(diaryRequestForSearch.getSearchValue()))));
            case DiaryId -> rContent.add(getDiaryByDiaryid(Integer.parseInt(diaryRequestForSearch.getSearchValue())));
            case Date ->
                    rContent = getDiariesByMarks(markService.getMarksByDate(diaryRequestForSearch.getSearchValue()));
            case SubjectId ->
                    rContent = getDiariesByMarks(markService.getMarksBySubjectid(Integer.parseInt(diaryRequestForSearch.getSearchValue())));
            case Mark ->
                    rContent = getDiariesByMarks(markService.getMarksByMark(Byte.parseByte(diaryRequestForSearch.getSearchValue())));
            default -> {
            }
        }
    }

    private void dWTeacher(DiaryRequestForSearch diaryRequestForSearch) {
        switch (diaryRequestForSearch.getSearchBy()) {
            case TeacherId ->
                    rContent = getDiariesByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(diaryRequestForSearch.getSearchValue())));
            case Name ->
                    rContent = getDiariesByTeachers(teacherService.getTeacherByName(diaryRequestForSearch.getSearchValue()));
            case Date ->
                    rContent = getDiariesByTeachers(teacherService.getTeacherByBirth(diaryRequestForSearch.getSearchValue()));
            default -> {
            }
        }
    }

    protected List<Diary> getDiariesByStudent(@NotNull Student student) {
        return getDiariesByScid(student.getId());
    }

    protected List<Diary> getDiariesByStudents(@NotNull List<Student> students) {
        List<Diary> diaries = new ArrayList<>();
        for (Student student : students) {
            diaries.addAll(getDiariesByStudents(students));
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
