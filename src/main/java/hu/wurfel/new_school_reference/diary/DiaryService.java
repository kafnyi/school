package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.division.ClassDto;
import hu.wurfel.new_school_reference.teacher.TeacherDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class DiaryService extends CrudService<Diary, DiaryRepository, DiaryDto> {

	@Autowired
	public DiaryService(DiaryRepository repository, ModelMapper mapper) {
		super(repository, mapper);
	}

	@Override
	public DiaryDto toDto(Diary auditable) {
		return mapper.map(auditable,DiaryDto.class);
	}

	@Override
	public Diary toEntity(DiaryDto dto) {
		return mapper.map(dto,Diary.class);
	}


	//region withDiary

	public List<DiaryDto> findAllDiaryByDiaryIfNotEmpty(DiaryDto diaryDto) throws Exception {
		if (!diaryDto.isEmpty()) {
			return (findAllDiaryByDiaryIdOrStartOrHeadTeacher(diaryDto));
		}
		throw new Exception("empty search content");
	}

	public List<DiaryDto> findAllDiaryByDiaryIdOrStartOrHeadTeacher(DiaryDto diaryDto) {
		if (diaryDto.getId() != 0) {
			return this.findById(diaryDto.getId());
		}
		return findAllDiaryByDiaryStartOrHeadTeacher(diaryDto);
	}

	private List<DiaryDto> findAllDiaryByDiaryStartOrHeadTeacher(DiaryDto diaryDto) {
		if (diaryDto.getStart() != null) {
			return this.toDtoList(this.findAllByStart(diaryDto.getStart()));
		}
		return findAllDiaryByDiaryHeadTeacher(diaryDto);
	}

	private List<DiaryDto> findAllDiaryByDiaryHeadTeacher(DiaryDto diaryDto) {
		if (diaryDto.getHeadTeacherId() != 0) {
			return this.toDtoList(this.findAllByHeadTeacherId(diaryDto.getHeadTeacherId()));
		}
		throw new InvalidParameterException();
	}

		//region baseMethods

	public List<Diary> findAllByStart(Date date) {
		return repo.findAllByStartAndDeletedIsFalse(date);
	}

	public List<Diary> findAllByEnd(Date date) {
		return repo.findAllByEndAndDeletedIsFalse(date);
	}

	public List<Diary> findAllByHeadTeacherId(long id) {
		return repo.findAllByHeadTeacher_IdAndDeletedIsFalse(id);
	}

	//endregion baseMethods

	//endregion withDiary

	//region withHeadTeacher

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

		//region baseMethods

	public List<Diary> findAllByTeacherId(long id) {
		return repo.findAllByHeadTeacher_IdAndDeletedIsFalse(id);
	}

	public List<Diary> findAllByTeacherName(String name) {
		return repo.findAllByHeadTeacher_NameAndDeletedIsFalse(name);
	}

	public List<Diary> findAllByTeacherCardNumber(long number) {
		return repo.findAllByHeadTeacher_CardNumberAndDeletedIsFalse(number);
	}

	//endregion baseMethods

	//endregion withHeadTeacher

	//region withClass

	public List<Diary> findAllDiaryByClassIfNotEmpty(ClassDto classDto) throws Exception {
		if (!classDto.isEmpty()){return findAllDiaryByClassIdOrGradeOrSign(classDto);}
		throw new Exception("empty search content");
	}

	public List<Diary> findAllDiaryByClassIdOrGradeOrSign(ClassDto classDto) {
		if (classDto.hasId()) {
			return this.findAllByClassId(classDto.getId());
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

	//region baseMethods

	public List<Diary> findAllByClassId(Long id) {
		return repo.findAllByDivision_IdAndDeletedIsFalse(id);
	}

	public List<Diary> findAllByClassGradeAndSign(short grade, char sign) {
		return repo.findAllByDivision_GradeAndDivision_SignAndDeletedIsFalse(grade, sign);
	}

	public List<Diary> findAllByClassGrade(short grade) {
		return repo.findAllByDivision_GradeAndDeletedIsFalse(grade);
	}

	public List<Diary> findAllByClassSign(char sign) {
		return repo.findAllByDivision_SignAndDeletedIsFalse(sign);
	}


	//endregion baseMethods

	//endregion withClass

}