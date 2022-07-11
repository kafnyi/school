package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.division.ClassDto;
import hu.wurfel.new_school_reference.division.ClassService;
import hu.wurfel.new_school_reference.teacher.TeacherDto;
import hu.wurfel.new_school_reference.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DiaryService extends CrudService<Diary, DiaryRepository> {

	@Autowired
	public DiaryService(DiaryRepository repository) {
		super(repository);
	}

	//region withDiary

	public List<Diary> findAllDiaryByDiaryIfNotEmpty(DiaryDto diaryDto) throws Exception {
		if (!diaryDto.isEmpty()){return findAllDiaryByDiaryIdOrStartOrHeadTeacher(diaryDto);}
		throw new Exception("empty search content");
	}

	public List<Diary> findAllDiaryByDiaryIdOrStartOrHeadTeacher(DiaryDto diaryDto) {
		if (diaryDto.getId() != 0) {
			return this.getList(this.findById(diaryDto.getId()));
		}
		return findAllDiaryByDiaryStartOrHeadTeacher(diaryDto);
	}

	private List<Diary> findAllDiaryByDiaryStartOrHeadTeacher(DiaryDto diaryDto) {
		if (diaryDto.getStart() != null) {
			return this.findAllByStart(diaryDto.getStart());
		}
		return findAllDiaryByDiaryHeadTeacher(diaryDto);
	}

	private List<Diary> findAllDiaryByDiaryHeadTeacher(DiaryDto diaryDto) {
		if (diaryDto.getHeadTeacherId() != 0) {
			return this.findAllByHeadTeacherId(diaryDto.getHeadTeacherId());
		}
		throw new InvalidParameterException();
	}

	public List<Diary> findAllByStart(Date date) {
		return repo.findAllByStart(date);
	}

	public List<Diary> findAllByEnd(Date date) {
		return repo.findAllByEnd(date);
	}

	public List<Diary> findAllByHeadTeacherId(long teacherId) {
		return repo.findAllByHeadTeacher(teacherId);
	}

	//endregion withDiary

	//region withTeacher

	public List<Diary> findAllDiaryByTeacherIfNotEmpty(TeacherDto teacherDto) throws Exception {
		if (!teacherDto.isEmpty()){return findAllByTeacherIdOrCardNumberOrName(teacherDto);}
		throw new Exception("empty search content");
	}

	public List<Diary> findAllByTeacherIdOrCardNumberOrName(TeacherDto teacherDto) {
		if (teacherDto.getId() != 0) {
			return this.findAllByTeacherId(teacherDto.getId());
		}
		return findAllDiaryByTeacherCardNumberOrName(teacherDto);
	}

	private List<Diary> findAllDiaryByTeacherCardNumberOrName(TeacherDto teacherDto) {
		if (teacherDto.getCardNumber() != 0) {
			return this.findAllByTeacherCardNumber(teacherDto.getCardNumber());
		}
		return findAllDiaryByTeacherName(teacherDto);
	}

	private List<Diary> findAllDiaryByTeacherName(TeacherDto teacherDto) {
		if (teacherDto.getName() != null) {
			return this.findAllByTeacherName(teacherDto.getName());
		}
		throw new InvalidParameterException();
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

	public List<Diary> findAllDiaryByClassIfNotEmpty(ClassDto classDto) throws Exception {
		if (!classDto.isEmpty()){return findAllDiaryByClassIdOrGradeOrSign(classDto);}
		throw new Exception("empty search content");
	}

		public List<Diary> findAllDiaryByClassIdOrGradeOrSign(ClassDto classDto) {
		if (classDto.hasId()) {
			return this.findAllByClassId(classDto);
		}
		return findAllDiaryByClassGradeAndSign(classDto);
	}

	private List<Diary> findAllDiaryByClassGradeAndSign(ClassDto classDto) {
		if (classDto.hasGrade() && classDto.hasSign()) {
			return this.findAllByClassGradeAndSign(classDto.getGrade(), classDto.getSign());
		}
		return findAllDiaryByClassGrade(classDto);
	}

	private List<Diary> findAllDiaryByClassGrade(ClassDto classDto) {
		if (classDto.hasGrade()) {
			return this.findAllByClassGrade(classDto.getGrade());
		}
		return findAllDiaryByClassSign(classDto);
	}

	private List<Diary> findAllDiaryByClassSign(ClassDto classDto) {
		if (classDto.hasSign()) {
			return this.findAllByClassSign(classDto.getSign());
		}
		throw new InvalidParameterException();
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