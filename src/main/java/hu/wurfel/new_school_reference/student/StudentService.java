package hu.wurfel.new_school_reference.student;

import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentService extends CrudService<Student,StudentRepository, StudentDto> {

	@Autowired
	public StudentService(StudentRepository repository, ModelMapper modelMapper) {
		super(repository, modelMapper);
	}

	@Override
	public StudentDto toDto(Student auditable) {
		return this.mapper.map(auditable, StudentDto.class);
	}

	@Override
	public Student toEntity(StudentDto dto) {
		return this.mapper.map(dto,Student.class);
	}


	public List<Student> findAllByName (String name, boolean deleted){
		return repo.findAllByNameAndDeleted(name, deleted);
	}

	public List<Student> findAllByBirthDate(Date date, boolean deleted){
		return repo.findAllByBirthDateAndDeleted(date, deleted);
	}

	public List<Student> findByCardNumber(long number, boolean deleted){
		return this.toEntityList(repo.findByCardNumberAndDeleted(number, deleted));
	}

}
