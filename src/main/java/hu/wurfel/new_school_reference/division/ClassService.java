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

	public List<Class> findAllByGrade(short grade){
		return repo.findAllByGradeAndDeletedIsFalse(grade);
	}

	public List<Class> findAllBySign(char sign){
		return repo.findAllBySignAndDeletedIsFalse(sign);
	}

	public List<Class> findByGradeAndSign(short grade, char sign){return this.toEntityList(repo.findByGradeAndSignAndDeletedIsFalse(grade, sign));}

}
