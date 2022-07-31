package hu.wurfel.new_school_reference.student;

import hu.wurfel.new_school_reference.base.BaseDto;
import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StudentService extends CrudService<Student, StudentRepository> {

	@Autowired
	public StudentService(StudentRepository repository, ModelMapper modelMapper) {
		super(repository, modelMapper);
	}

	public StudentDto save(CreateStudentDto dto) {
		this.validateDtoIsNotEmpty(dto, "Create failed due to: Student has no valid name/birthdate/cardNumber!");
		Student student = new Student(
				dto.getName(),
				dto.getBirthDate(),
				dto.getCardNumber()
		);
		return new StudentDto(this.save(student));
	}

	@Transactional
	public StudentDto update(UpdateStudentDto dto) {
		this.validateDtoIsNotEmpty(dto, "Update failed due to: Student has no valid name/birthdate/cardNumber!");
		Student student = this.findById(dto.getId());
		student.setName(dto.getName());
		student.setBirthDate(dto.getBirthDate());
		student.setCardNumber(dto.getCardNumber());
		return new StudentDto(this.save(student));
	}
//		this.validateDtoIsNotEmpty(dto, "Update failed due to: Subject has no valid title!");

	@Override
	public <DTO extends BaseDto> DTO toDto(Student auditable, Class<DTO> returnType) {
		return null;
	}

	@Override
	public Student toEntity(BaseDto dto) {
		return null;
	}

//	@Override
//	public StudentDto toDto(Student auditable) {
//		return this.mapper.map(auditable, StudentDto.class);
//	}
//
//	@Override
//	public Student toEntity(StudentDto dto) {
//		return this.mapper.map(dto,Student.class);
//	}


	public List<Student> findAllByNameAndDeleted(String name, boolean deleted) {
		return repo.findAllByNameAndDeleted(name, deleted);
	}

	public List<Student> findAllByBirthDateAndDeleted(LocalDate date, boolean deleted) {
		return repo.findAllByBirthDateAndDeleted(date, deleted);
	}

//	public List<Student> findByCardNumberAndDeleted(String number, boolean deleted) {
//		return this.toEntityList(repo.findByCardNumberAndDeleted(number, deleted));
//	}


	public StudentDto getById(Long id) {
		return new StudentDto(this.findById(id));
	}

}
