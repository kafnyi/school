package hu.wurfel.refference.school.services;

import hu.wurfel.refference.school.model.daos.Class;
import hu.wurfel.refference.school.model.daos.Diary;
import hu.wurfel.refference.school.model.daos.Mark;
import hu.wurfel.refference.school.services.cruds.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {
	@Autowired
	private ClassCrudService ccs;
	@Autowired
	private DiaryCrudService dcs;
	@Autowired
	private MarkCrudService mcs;
	@Autowired
	private StudentCrudService scs;
	@Autowired
	private SubjectCrudService sjcs;
	@Autowired
	private TeacherCrudService tcs;

	/**
	 * @param diary Entity
	 * @return Class Entity
	 */
	public Class getClassByDiary(Diary diary) {
		return ccs.getClassByClassId(diary.getClassID());
	}

	/**
	 * @param mark Entity
	 * @return Class Entity
	 */
	public Class getClassByMark(Mark mark) {
		return ccs.getClassByClassId(dcs.getDiaryByDiaryid(mark.getDiaryID()).getClassID());
	}


}
