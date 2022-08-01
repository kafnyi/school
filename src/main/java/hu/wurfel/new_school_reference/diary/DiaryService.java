package hu.wurfel.new_school_reference.diary;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.division.ClassService;
import hu.wurfel.new_school_reference.teacher.TeacherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DiaryService extends CrudService<Diary, DiaryRepository> {

	private final ClassService classService;
	private final TeacherService teacherService;
	@Autowired
	public DiaryService(DiaryRepository repository, ModelMapper mapper, ClassService classService, TeacherService teacherService) {
		super(repository, mapper);
		this.classService = classService;
		this.teacherService = teacherService;
	}

	@Override
	public <DTO extends BaseDto> DTO toDto(Diary auditable, Class<DTO> returnType) {
		return null;
	}

	@Override
	public Diary toEntity(BaseDto dto) {
		return null;
	}

	public DiaryDto save(CreateDiaryDtoWithConnectIds dto){
		this.validateDtoIsNotEmpty(dto, "Create failed due to: Diary has no valid " +
				"start/" +
				"end/" +
				"DivisionId/" +
				"HeadTeacherId!");
		Diary diary = new Diary(
				dto.getStart(),
				dto.getEnd(),
				classService.findById(dto.getDivisionId()),
				teacherService.findById(dto.getHeadTeacherId()));
				return new DiaryDto(this.save(diary));
	}

	@Transactional
	public DiaryDto update(UpdateDiaryDtoWithConnectIds dto){
		this.validateDtoIsNotEmpty(dto, "Create failed due to: Diary has no valid " +
				"start/" +
				"end/" +
				"DivisionId/" +
				"HeadTeacherId!");
		Diary diary = this.findById(dto.getId());
		diary.setStart(dto.getStart());
		diary.setEnd(diary.getEnd());
		diary.setDivision(classService.findById(dto.getDivisionId()));
		diary.setHeadTeacher(teacherService.findById(dto.getHeadTeacherId()));
		return new DiaryDto(this.save(diary));
	}


//	//region withDiary
//
//	public List<DiaryDto> findAllDiaryByDiaryIfNotEmpty(DiaryDto diaryDto) throws Exception {
//		if (!diaryDto.isEmpty()) {
//			return (findAllDiaryByDiaryIdOrStartOrHeadTeacher(diaryDto));
//		}
//		throw new Exception("empty search content");
//	}
//
//	public List<DiaryDto> findAllDiaryByDiaryIdOrStartOrHeadTeacher(DiaryDto diaryDto) {
//		if (diaryDto.getId() != 0) {
//			return List.of(this.toDto(this.findById(diaryDto.getId()),DiaryDto.class));
//		}
//		return findAllDiaryByDiaryStartOrHeadTeacher(diaryDto);
//	}
//
//	private List<DiaryDto> findAllDiaryByDiaryStartOrHeadTeacher(DiaryDto diaryDto) {
//		if (diaryDto.getStart() != null) {
//			return List.of(this.findAllByStartAndDeleted(diaryDto.getStart(),diaryDto.isDeleted()));
//		}
//		return findAllDiaryByDiaryHeadTeacher(diaryDto);
//	}
//
//	private List<DiaryDto>findAllDiaryByDiaryHeadTeacher(DiaryDto diaryDto) {
//		if (diaryDto.getHeadTeacherId() != 0) {
//			return this.toDtoList(this.findAllByHeadTeacherIdAndDeleted(diaryDto.getHeadTeacherId(),diaryDto.isDeleted()));
//		}
//		throw new InvalidParameterException();
//	}
//
//		//region baseMethods
//
//	public List<Diary> findAllByStartAndDeleted(Date date, boolean deleted) {
//		return repo.findAllByStartAndDeleted(date ,deleted);
//	}
//
//	public List<Diary> findAllByEndAndDeleted(Date date, boolean deleted) {
//		return repo.findAllByEndAndDeleted(date, deleted);
//	}
//
//	public List<Diary> findAllByHeadTeacherIdAndDeleted(long id, boolean deleted) {
//		return repo.findAllByHeadTeacher_IdAndDeleted(id,deleted);
//	}
//
//	//endregion baseMethods
//
//	//endregion withDiary

//	//region withHeadTeacher
//
//	public List<Diary> findAllDiaryByTeacherIfNotEmpty(TeacherDto teacherDto) throws Exception {
//		if (!teacherDto.isEmpty()){return findAllByTeacherIdOrCardNumberOrName(teacherDto);}
//		throw new Exception("empty search content");
//	}
//
//	public List<Diary> findAllByTeacherIdOrCardNumberOrName(TeacherDto teacherDto) {
//		if (teacherDto.getId() != 0) {
//			return this.findAllByTeacherIdAndDeleted(teacherDto.getId(), teacherDto.isDeleted());
//		}
//		return findAllDiaryByTeacherCardNumberOrName(teacherDto);
//	}
//
//	private List<Diary> findAllDiaryByTeacherCardNumberOrName(TeacherDto teacherDto) {
//		if (teacherDto.getCardNumber() != 0) {
//			return this.findAllByTeacherCardNumberAndDeleted(teacherDto.getCardNumber(), teacherDto.isDeleted());
//		}
//		return findAllDiaryByTeacherName(teacherDto);
//	}
//
//	private List<Diary> findAllDiaryByTeacherName(TeacherDto teacherDto) {
//		if (teacherDto.getName() != null) {
//			return this.findAllByTeacherNameAndDeleted(teacherDto.getName(), teacherDto.isDeleted());
//		}
//		throw new InvalidParameterException();
//	}
//
//		//region baseMethods
//
//	public List<Diary> findAllByTeacherIdAndDeleted(long id, boolean deleted) {
//		return repo.findAllByHeadTeacher_IdAndDeleted(id,deleted);
//	}
//
//	public List<Diary> findAllByTeacherNameAndDeleted(String name, boolean deleted) {
//		return repo.findAllByHeadTeacher_NameAndDeleted(name, deleted);
//	}
//
//	public List<Diary> findAllByTeacherCardNumberAndDeleted(long number, boolean deleted) {
//		return repo.findAllByHeadTeacher_CardNumberAndDeleted(number, deleted);
//	}
//
//	//endregion baseMethods
//
//	//endregion withHeadTeacher

//	//region withClass
//
//	public List<Diary> findAllDiaryByClassIfNotEmpty(ClassDto classDto) throws Exception {
//		if (!classDto.isEmpty()){return findAllDiaryByClassIdOrGradeOrSign(classDto);}
//		throw new Exception("empty search content");
//	}
//
//	public List<Diary> findAllDiaryByClassIdOrGradeOrSign(ClassDto classDto) {
//		if (classDto.hasValidId()) {
//			return this.findAllByClassIdAndDeleted(classDto.getId(), classDto.isDeleted());
//		}
//		return findAllDiaryByClassGradeAndSign(classDto);
//	}
//
//	private List<Diary> findAllDiaryByClassGradeAndSign(ClassDto classDto) {
//		if (classDto.hasGrade() && classDto.hasSign()) {
//			return this.findAllByClassGradeAndSignAndDeleted(classDto.getGrade(), classDto.getSign(), classDto.isDeleted());
//		}
//		return findAllDiaryByClassGrade(classDto);
//	}
//
//	private List<Diary> findAllDiaryByClassGrade(ClassDto classDto) {
//		if (classDto.hasGrade()) {
//			return this.findAllByClassGradeAndDeleted(classDto.getGrade(), classDto.isDeleted());
//		}
//		return findAllDiaryByClassSign(classDto);
//	}
//
//	private List<Diary> findAllDiaryByClassSign(ClassDto classDto) {
//		if (classDto.hasSign()) {
//			return this.findAllByClassSignAndDeleted(classDto.getSign(), classDto.isDeleted());
//		}
//		throw new InvalidParameterException();
//	}
//
//	//region baseMethods
//
//	public List<Diary> findAllByClassIdAndDeleted(Long id, boolean deleted) {
//		return repo.findAllByDivision_IdAndDeleted(id,deleted);
//	}
//
//	public List<Diary> findAllByClassGradeAndSignAndDeleted(short grade, char sign, boolean deleted) {
//		return repo.findAllByDivision_GradeAndDivision_SignAndDeleted(grade, sign, deleted);
//	}
//
//	public List<Diary> findAllByClassGradeAndDeleted(short grade, boolean deleted) {
//		return repo.findAllByDivision_GradeAndDeleted(grade, deleted);
//	}
//
//	public List<Diary> findAllByClassSignAndDeleted(char sign, boolean deleted) {
//		return repo.findAllByDivision_SignAndDeleted(sign , deleted);
//	}
//
//
//	//endregion baseMethods
//
//	//endregion withClass

}