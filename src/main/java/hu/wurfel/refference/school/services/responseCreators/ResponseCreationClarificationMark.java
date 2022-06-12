package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.model.Request;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.ArrayList;

@Service
public class ResponseCreationClarificationMark extends ResponseCreationClarificationTemplate {

	public ArrayList create(Request request) {
		rContent = new ArrayList<>();
		switch (request.getRWith()) {
			case Student -> {
				mWStudent(request);
			}
			case Diary -> {
				mWDiary(request);
			}
			case Class -> {
				mWClass(request);
			}
			case Subject -> {
				mWSubject(request);
			}
			case Mark -> {
				mWMark(request);
			}
			case Teacher -> {
				mWTeacher(request);
			}
			default -> {
			}
		}
		return rContent;
	}

	private void mWStudent(Request request) {
		switch (request.getRBy()) {
			case StudentId -> {
				rContent = markService.getMarksByStudent(studentService.getStudentByStudentId(Long.parseLong(request.getRValue())));
			}
			case Name -> {
				rContent = markService.getMarksByStudents(studentService.getStudentsByName(request.getRValue()));
			}
			case Date -> {
				rContent = markService.getMarksByStudents(studentService.getStudentsByBirth(request.getRValue()));
			}
			default -> {
			}
		}
	}

	private void mWDiary(Request request) {
		switch (request.getRBy()) {
			case DiaryId -> {
				rContent = markService.getMarksByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getRValue())));
			}
			case StudentId -> {
				rContent = markService.getMarksByDiaries(diaryService.getDiariesByScid(Long.parseLong(request.getRValue())));
			}
			case ClassId -> {
				rContent = markService.getMarksByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(request.getRValue())));
			}
			default -> {
			}
		}
	}

	private void mWClass(Request request) {
		switch (request.getRBy()) {
			case ClassId -> {
				rContent = markService.getMarksByClass(classService.getClassByClassId(Integer.parseInt(request.getRValue())));
			}
			case Grade -> {
				rContent = markService.getMarksByClasses(classService.getClassesByGrade(Short.parseShort(request.getRValue())));
			}
			case Sign -> {
				rContent = markService.getMarksByClasses(classService.getClassesBySign(request.getRValue().strip().charAt(0)));
			}
			case Year -> {
				rContent = markService.getMarksByClasses(classService.getClassesByYear(Year.parse(request.getRValue())));
			}
			case TeacherId -> {
				rContent = markService.getMarksByClasses(classService.getClassesByTid(Long.parseLong(request.getRValue())));
			}
			default -> {
			}
		}
	}

	private void mWSubject(Request request) {
		switch (request.getRBy()) {
			case SubjectId -> {
				rContent = markService.getMarksBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue())));
			}
			case Name -> {
				rContent = markService.getMarksBySubjects(subjectService.getSubjectsByName(request.getRValue()));
			}
			case TeacherId -> {
				rContent = markService.getMarksBySubjects(subjectService.getSubjectsByTid(Long.parseLong(request.getRValue())));
			}
			default -> {
			}
		}
	}

	private void mWMark(Request request) {
		switch (request.getRBy()) {
			case MarkId -> {
				rContent.add(markService.getMarkByMarkid(Long.parseLong(request.getRValue())));
			}
			case DiaryId -> {
				rContent = markService.getMarksByDiaryid(Integer.parseInt(request.getRValue()));
			}
			case Date -> {
				rContent = markService.getMarksByDate(request.getRValue());
			}
			case SubjectId -> {
				rContent = markService.getMarksBySubjectid(Integer.parseInt(request.getRValue()));
			}
			case Mark -> {
				rContent = markService.getMarksByMark(Byte.parseByte(request.getRValue()));
			}
			default -> {
			}
		}
	}

	private void mWTeacher(Request request) {
		switch (request.getRBy()) {
			case TeacherId -> {
				rContent = markService.getMarksByTeacher(teacherService.getTeacherByTeacherId(Long.parseLong(request.getRValue())));
			}
			case Name -> {
				rContent = markService.getMarksByTeachers(teacherService.getTeacherByName(request.getRValue()));
			}
			case Date -> {
				rContent = markService.getMarksByTeachers(teacherService.getTeacherByBirth(request.getRValue()));
			}
			default -> {
			}
		}
	}
}
