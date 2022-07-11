package hu.wurfel.new_school_reference.search;

import hu.wurfel.new_school_reference.diary.Diary;
import hu.wurfel.new_school_reference.diary.DiaryDto;
import hu.wurfel.new_school_reference.diary.DiaryService;
import hu.wurfel.new_school_reference.division.Class;
import hu.wurfel.new_school_reference.division.ClassDto;
import hu.wurfel.new_school_reference.division.ClassService;
import hu.wurfel.new_school_reference.mark.MarkService;
import hu.wurfel.new_school_reference.student.StudentService;
import hu.wurfel.new_school_reference.subject.SubjectService;
import hu.wurfel.new_school_reference.teacher.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

	protected List<Class> findClassByClass
	private DiaryService diaryService;
	private ClassService classService;
	private StudentService studentService;
	private SubjectService subjectService;
	private MarkService markService;

	//region Diary
	private TeacherService teacherService;

	public List<Diary> findDiaryByDiary(DiaryDto diaryDto) {
		return diaryService.findAllDiaryByDiaryIfPossible(diaryDto);
	}

	//region withDiary


	//endregion withDiary

	//region withClass

	//endregion withClass

	//endregion Diary

	//region Class

	public List<Diary> findDiaryByClass(ClassDto classDto) {
		return diaryService.findAllClassByClassIdOrGradeOrSign(classDto);
	}

	//endregion Class
}
