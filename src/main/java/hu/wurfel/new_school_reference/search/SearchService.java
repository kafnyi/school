package hu.wurfel.new_school_reference.search;

import hu.wurfel.new_school_reference.diary.Diary;
import hu.wurfel.new_school_reference.diary.DiaryDto;
import hu.wurfel.new_school_reference.diary.DiaryService;
import hu.wurfel.new_school_reference.division.Class;
import hu.wurfel.new_school_reference.division.ClassDto;
import hu.wurfel.new_school_reference.division.ClassService;
import hu.wurfel.new_school_reference.mark.MarkService;
import hu.wurfel.new_school_reference.student.StudentDto;
import hu.wurfel.new_school_reference.student.StudentService;
import hu.wurfel.new_school_reference.subject.SubjectService;
import hu.wurfel.new_school_reference.teacher.TeacherDto;
import hu.wurfel.new_school_reference.teacher.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

	private DiaryService diaryService;
	private ClassService classService;
	private StudentService studentService;
	private SubjectService subjectService;
	private MarkService markService;
	private TeacherService teacherService;

	//region Diary

	public List<Diary> findDiaryByDiary(DiaryDto diaryDto) throws Exception{
		return diaryService.findAllDiaryByDiaryIfNotEmpty(diaryDto);
	}

	public List<Diary> findDiaryByClass(ClassDto classDto) throws Exception{
		return diaryService.findAllDiaryByClassIfNotEmpty(classDto);
	}

	public List<Diary> findDiaryByHeadTeacher(TeacherDto teacherDto) throws Exception{
		return diaryService.findAllDiaryByTeacherIfNotEmpty(teacherDto);
	}

	//public List<Diary> findDiaryByStudent(StudentDto studentDto) {}

	//region withClass

	//endregion withClass

	//endregion Diary

	//region Class


	//endregion Class
}
