package hu.wurfel.new_school_reference.division;

import hu.wurfel.new_school_reference.base.CrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClassService extends CrudService<Class, ClassRepository, ClassDto> {

	@Autowired
	public ClassService(ClassRepository repository, ModelMapper mapper) {
		super(repository, mapper);
	}

	@Override
	public ClassDto toDto(Class auditable) {
		return this.mapper.map(auditable,ClassDto.class);
	}

	@Override
	public Class toEntity(ClassDto dto) {
		return this.mapper.map(dto,Class.class);
	}

	public List<Class> findAllByGrade(short grade, boolean deleted){
		return repo.findAllByGradeAndDeleted(grade, deleted);
	}

	public List<Class> findAllBySign(char sign, boolean deleted){
		return repo.findAllBySignAndDeleted(sign, deleted);
	}

	public List<Class> findByGradeAndSign(short grade, char sign, boolean deleted){return this.toEntityList(repo.findByGradeAndSignAndDeleted(grade, sign, deleted));}

}
