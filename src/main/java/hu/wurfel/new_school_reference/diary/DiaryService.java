package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.division.ClassDto;
import hu.wurfel.new_school_reference.division.ClassService;
import hu.wurfel.new_school_reference.teacher.Teacher;
import hu.wurfel.new_school_reference.teacher.TeacherDto;
import hu.wurfel.new_school_reference.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DiaryService extends CrudService<Diary, DiaryRepository> {

	private ClassService classService;
	private TeacherService teacherService;

	@Autowired
	public DiaryService(DiaryRepository repository) {
		super(repository);
	}

	//region withDiary

	public List<Diary> findAllDiaryByDiaryIfPossible(DiaryDto diaryDto) {
		if (diaryDto.getId() != 0) {
			return this.getList(this.findById(diaryDto.getId()));
		}
		return findDiaryByDiaryStartIfPossible(diaryDto);
	}

	private List<Diary> findDiaryByDiaryStartIfPossible(DiaryDto diaryDto) {
		if (diaryDto.getStart() != null) {
			return this.findAllByStart(diaryDto.getStart());
		}
		return findDiaryByDiaryHeadTeacherIfPossible(diaryDto);
	}

	private List<Diary> findDiaryByDiaryHeadTeacherIfPossible(DiaryDto diaryDto) {
		if (diaryDto.getHeadTeacherId() != 0) {
			return this.findAllByHeadTeacherId(diaryDto.getHeadTeacherId());
		}
		return new ArrayList<Diary>();
	}

	public List<Diary> findAllByStart(Date date) {
		return repo.findAllByStart(date);
	}

	public List<Diary> findAllByEnd(Date date) {
		return repo.findAllByEnd(date);
	}


	public List<Diary> findAllByHeadTeacher(Teacher teacher) {
		return repo.findAllByHeadTeacher(teacher);
	}

	public List<Diary> findAllByHeadTeacherId(long teacherId) {
		return repo.findAllByHeadTeacher(teacherId);
	}

	//endregion withDiary

	//region withTeacher

	public List<Diary> findAllByTeacherIdOrNameOrCardNumberIfPossible(TeacherDto teacherDto) {
		if (teacherDto.getId() != 0) {
			return this.findAllByTeacherId(teacherDto.getId());
		}
		return findAllDiaryByTeacherCardNumberIfPossible(teacherDto);
	}

	private List<Diary> findAllDiaryByTeacherCardNumberIfPossible(TeacherDto teacherDto) {
		if (teacherDto.getCardNumber() != 0) {
			return this.findAllByTeacherCardNumber(teacherDto.getCardNumber());
		}
		return findDiaryByTeacherNameIfPossible(teacherDto);
	}

	private List<Diary> findDiaryByTeacherNameIfPossible(TeacherDto teacherDto) {
		if (teacherDto.getName() != null) {
			return this.findAllByTeacherName(teacherDto.getName());
		}
		return new ArrayList<>();
	}

	public List<Diary> findAllByTeacherId(long id) {
		return repo.findAllByHeadTeacher_Id(id);
	}

	public List<Diary> findAllByTeacherName(String name) {
		return repo.findAllByHeadTeacher_Name(name);
	}

	public List<Diary> findAllByTeacherCardNumber(long number) {
		return repo.findAllByHeadTeacher_CardNumber(number);
	}

	//endregion withTeacher

	//region withClass

	public List<Diary> findAllClassByClassIdOrGradeOrSign(ClassDto classDto) {
		if (classDto.hasId()) {
			return this.findAllByClassId(classDto);
		}
		return findAllByClassGradeAndSignIfPossible(classDto);
	}

	private List<Diary> findAllByClassGradeAndSignIfPossible(ClassDto classDto) {
		if (classDto.hasGrade() && classDto.hasSign()) {
			return this.findAllByClassGradeAndSign(classDto.getGrade(), classDto.getSign());
		}
		return findAllByClassGradeIfPossible(classDto);
	}

	private List<Diary> findAllByClassGradeIfPossible(ClassDto classDto) {
		if (classDto.hasGrade()) {
			return this.findAllByClassGrade(classDto.getGrade());
		}
		return findAllByClassSignIfPossible(classDto);
	}

	private List<Diary> findAllByClassSignIfPossible(ClassDto classDto) {
		if (classDto.hasSign()) {
			return this.findAllByClassSign(classDto.getSign());
		}
		return new ArrayList<>();
	}

	public List<Diary> findAllByClassId(ClassDto classDto) {
		return repo.findAllByDivision_Id(classDto.getId());
	}

	public List<Diary> findAllByClassGradeAndSign(short grade, char sign) {
		return repo.findAllByDivision_GradeAndDivision_Sign(grade, sign);
	}

	public List<Diary> findAllByClassGrade(short grade) {
		return repo.findAllByDivision_Grade(grade);
	}

	public List<Diary> findAllByClassSign(char sign) {
		return repo.findAllByDivision_Sign(sign);
	}

	//endregion withClass

	public List<Diary> getList(Diary diary) {
		List<Diary> diaryList = new ArrayList<>();
		diaryList.add(diary);
		return diaryList;
	}

}