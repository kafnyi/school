package hu.wurfel.refference.school.teacher;

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
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.subject.SubjectCrudService;
import hu.wurfel.refference.school.subject.SubjectService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class TeacherService extends TeacherCrudService {
    private DiaryCrudService diaryCrudService;
    private MarkCrudService markCrudService;
    private SubjectCrudService subjectCrudService;
    private DiaryService diaryService;
    private ClassService classService;
    private MarkService markService;
    private StudentService studentService;
    private SubjectService subjectService;
    private ArrayList<Teacher> rContent;

    public ArrayList getAutomated(TeacherRequest teacherRequest) {
        rContent = new ArrayList<>();
        switch (teacherRequest.getSearchWith()) {
            case Student -> tWStudent(teacherRequest);
            case Diary -> tWDiary(teacherRequest);
            case Class -> tWClass(teacherRequest);
            case Subject -> tWSubject(teacherRequest);
            case Mark -> tWMark(teacherRequest);
            case Teacher -> tWTeacher(teacherRequest);
            default -> {
            }
        }
        return rContent;
    }

    private void tWStudent(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case StudentId ->
                    rContent = getTeachersByStudent(studentService.getStudentByStudentId(Long.parseLong(teacherRequest.getSearchValue())));
            case Name ->
                    rContent = getTeachersByStudents(studentService.getStudentsByName(teacherRequest.getSearchValue()));
            case Date ->
                    rContent = getTeachersByStudents(studentService.getStudentsByBirth(teacherRequest.getSearchValue()));
            default -> {
            }
        }
    }

    private void tWDiary(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case DiaryId ->
                    rContent = getTeachersByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(teacherRequest.getSearchValue())));
            case StudentId ->
                    rContent = getTeachersByDiaries(diaryService.getDiariesByScid(Long.parseLong(teacherRequest.getSearchValue())));
            case ClassId ->
                    rContent = getTeachersByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(teacherRequest.getSearchValue())));
            default -> {
            }
        }
    }

    private void tWClass(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case ClassId ->
                    rContent.add(getTeacherByClass(classService.getClassByClassId(Integer.parseInt(teacherRequest.getSearchValue()))));
            case Grade ->
                    rContent = getTeachersByClasses(classService.getClassesByGrade(Short.parseShort(teacherRequest.getSearchValue())));
            case Sign ->
                    rContent = getTeachersByClasses(classService.getClassesBySign(teacherRequest.getSearchValue().strip().charAt(0)));
            case Year ->
                    rContent = getTeachersByClasses(classService.getClassesByYear(Year.parse(teacherRequest.getSearchValue())));
            case TeacherId -> rContent.add(getTeacherByTeacherId(Long.parseLong(teacherRequest.getSearchValue())));
            default -> {
            }
        }
    }

    private void tWSubject(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case SubjectId ->
                    rContent.add(getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(teacherRequest.getSearchValue()))));
            case Name ->
                    rContent = getTeacherBySubjects(subjectService.getSubjectsByName(teacherRequest.getSearchValue()));
            case TeacherId -> rContent.add(getTeacherByTeacherId(Long.parseLong(teacherRequest.getSearchValue())));
            default -> {
            }
        }
    }

    private void tWMark(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case MarkId ->
                    rContent.add(getTeacherByMark(markService.getMarkByMarkid(Long.parseLong(teacherRequest.getSearchValue()))));
            case DiaryId ->
                    rContent = getTeachersByMarks(markService.getMarksByDiaryid(Integer.parseInt(teacherRequest.getSearchValue())));
            case Date -> rContent = getTeachersByMarks(markService.getMarksByDate(teacherRequest.getSearchValue()));
            case SubjectId ->
                    rContent.add(getTeacherBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(teacherRequest.getSearchValue()))));
            case Mark ->
                    rContent = getTeachersByMarks(markService.getMarksByMark(Byte.parseByte(teacherRequest.getSearchValue())));
            default -> {
            }
        }
    }

    private void tWTeacher(TeacherRequest teacherRequest) {
        switch (teacherRequest.getSearchBy()) {
            case TeacherId -> rContent.add(getTeacherByTeacherId(Long.parseLong(teacherRequest.getSearchValue())));
            case Name -> rContent = getTeacherByName(teacherRequest.getSearchValue());
            case Date -> rContent = getTeacherByBirth(teacherRequest.getSearchValue());
            default -> {
            }
        }
    }

    protected Teacher getTeacherByClass(@NotNull Class division) {
        return getTeacherByTeacherId(division.getTeacherId());
    }

    protected ArrayList<Teacher> getTeachersByClasses(@NotNull ArrayList<Class> classes) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Class division : classes) {
            teachers.add(getTeacherByClass(division));
        }
        return teachers;
    }

    protected Teacher getTeacherBySubject(@NotNull Subject subject) {
        return getTeacherByTeacherId(subject.getTid());
    }

    protected ArrayList<Teacher> getTeacherBySubjects(@NotNull ArrayList<Subject> subjects) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Subject subject : subjects) {
            teachers.add(getTeacherBySubject(subject));
        }
        return teachers;
    }

    protected Teacher getTeacherByMark(@NotNull Mark mark) {
        return getTeacherBySubject(subjectCrudService.getSubjectBySubjectId(mark.getSubjectID()));
    }

    protected ArrayList<Teacher> getTeachersByMarks(@NotNull ArrayList<Mark> marks) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Mark mark : marks) {
            teachers.add(getTeacherByMark(mark));
        }
        return teachers;
    }

    protected ArrayList<Teacher> getTeachersByDiary(@NotNull Diary diary) {
        ArrayList<Teacher> result = new ArrayList<>();
        for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksByDiaryid(diary.getId()))) {
            result.add(getTeacherByMark(mark));
        }
        return result;
    }

    protected ArrayList<Teacher> getTeachersByDiaries(@NotNull ArrayList<Diary> diaries) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Diary diary : diaries) {
            teachers.addAll(getTeachersByDiary(diary));
        }
        return teachers;
    }

    protected ArrayList<Teacher> getTeachersByStudents(@NotNull ArrayList<Student> students) {
        ArrayList<Teacher> teachers = new ArrayList<>();
        for (Student student : students) {
            teachers.addAll(getTeachersByStudent(student));
        }
        return teachers;
    }

    protected ArrayList<Teacher> getTeachersByStudent(@NotNull Student student) {
        ArrayList<Teacher> result = new ArrayList<>();
        for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByScid(student.getId()))) {
            result.addAll(getTeachersByDiary(diary));
        }
        return result;
    }
}
