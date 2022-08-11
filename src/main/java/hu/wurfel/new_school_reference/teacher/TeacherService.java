package hu.wurfel.new_school_reference.teacher;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import hu.wurfel.new_school_reference.student.CreateStudentDto;
import hu.wurfel.new_school_reference.student.Student;
import hu.wurfel.new_school_reference.student.StudentDto;
import hu.wurfel.new_school_reference.student.UpdateStudentDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherService extends CrudService<Teacher,TeacherRepository> {

	public TeacherService(TeacherRepository repository, ModelMapper modelMapper) {
		super(repository, modelMapper);
	}

	public TeacherDto save(CreateTeacherDto dto) {
		this.validateDtoIsNotEmpty(dto, "Create failed due to: Teacher has no valid name/birthdate/cardNumber!");
		Teacher teacher = new Teacher(
				dto.getName(),
				dto.getBirthDate(),
				dto.getCardNumber()
		);
		return new TeacherDto(this.save(teacher));
	}

	@Transactional
	public TeacherDto update(UpdateTeacherDto dto) {
		this.validateDtoIsNotEmpty(dto, "Update failed due to: Teacher has no valid name/birthdate/cardNumber!");
		Teacher teacher = this.findById(dto.getId());
		teacher.setName(dto.getName());
		teacher.setBirthDate(dto.getBirthDate());
		teacher.setCardNumber(dto.getCardNumber());
		return new TeacherDto(this.save(teacher));
	}

	@Override
	public <DTO extends BaseDto> DTO toDto(Teacher auditable, Class<DTO> returnType) {
		return null;
	}

	@Override
	public Teacher toEntity(BaseDto dto) {
		return null;
	}

	public List<Teacher> findAllByNameAndDeleted(String name, boolean deleted){
		return repo.findAllByNameAndDeleted(name, deleted);
	}

	public List<Teacher> findAllByBirthDateAndDeleted(LocalDate date, boolean deleted) {
		return repo.findAllByBirthDateAndDeleted(date, deleted);
	}

	public List<Teacher> findByTeacherCardNumberAndDeleted(long number, boolean deleted){
		return List.of(repo.findAllByCardNumberAndDeleted(number, deleted));
	}

	public TeacherDto getById(Long id){
		return  new TeacherDto(this.findById(id));
	}
}
