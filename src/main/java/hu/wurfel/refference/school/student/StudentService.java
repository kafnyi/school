package hu.wurfel.refference.school.student;

import hu.wurfel.refference.school.diary.Diary;
import hu.wurfel.refference.school.diary.DiaryCrudService;
import hu.wurfel.refference.school.division.Class;
import hu.wurfel.refference.school.division.ClassCrudService;
import hu.wurfel.refference.school.mark.Mark;
import hu.wurfel.refference.school.mark.MarkCrudService;
import hu.wurfel.refference.school.subject.Subject;
import hu.wurfel.refference.school.teacher.Teacher;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService extends StudentCrudService {

    @Autowired
    private ClassCrudService classCrudService;
    @Autowired
    private DiaryCrudService diaryCrudService;
    @Autowired
    private MarkCrudService markCrudService;

    public Student getStudentByDiary(@NotNull Diary diary) {
        return getStudentByStudentId(diary.getStudentId());
    }

    public ArrayList<Student> getStudentsByDiaries(@NotNull ArrayList<Diary> diaries) {
        ArrayList<Student> students = new ArrayList<Student>();
        for (Diary diary : diaries) {
            students.add(getStudentByDiary(diary));
        }
        return students;
    }

    public Student getStudentByMark(@NotNull Mark mark) {
        return getStudentByStudentId(diaryCrudService.getDiaryByDiaryid(mark.getDiaryID()).getId());
    }

    public ArrayList<Student> getStudentsByMarks(@NotNull ArrayList<Mark> marks) {
        ArrayList<Student> students = new ArrayList<>();
        for (Mark mark : marks) {
            students.add(getStudentByMark(mark));
        }
        return students;
    }

    public ArrayList<Student> getStudentsByClass(@NotNull Class division) {
        ArrayList<Student> students = new ArrayList<>();
        for (Diary diary : new ArrayList<Diary>(diaryCrudService.getDiariesByClassid(division.getId()))) {
            students.add(getStudentByDiary(diary));
        }
        return students;
    }

    public ArrayList<Student> getStudentsByClasses(@NotNull ArrayList<Class> classes) {
        ArrayList<Student> students = new ArrayList<>();
        for (Class division : classes) {
            students.addAll(getStudentsByClass(division));
        }
        return students;
    }

    public ArrayList<Student> getStudentsBySubject(@NotNull Subject subject) {
        ArrayList<Student> students = new ArrayList<>();
        for (Mark mark : new ArrayList<Mark>(markCrudService.getMarksBySubjectid(subject.getId()))) {
            students.add(getStudentByMark(mark));
        }
        return students;
    }

    public ArrayList<Student> getStudentsBySubjects(@NotNull ArrayList<Subject> subjects) {
        ArrayList<Student> students = new ArrayList<>();
        for (Subject subject : subjects) {
            students.addAll(getStudentsBySubject(subject));
        }
        return students;
    }

    public ArrayList<Student> getStudentsByTeacher(@NotNull Teacher teacher) {
        ArrayList<Student> students = new ArrayList<>();
        for (Class division : new ArrayList<Class>(classCrudService.getClassesByTid(teacher.getId()))) {
            students.addAll(getStudentsByClass(division));
        }
        return students;
    }

    public ArrayList<Student> getStudentsByTeachers(@NotNull ArrayList<Teacher> teachers) {
        ArrayList<Student> students = new ArrayList<>();
        for (Teacher teacher : teachers) {
            students.addAll(getStudentsByTeacher(teacher));
        }
        return students;
    }
}