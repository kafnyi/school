package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.model.Request;

import java.time.Year;
import java.util.ArrayList;

public class ResponseCreationClarificationClass extends responseCreationTemplate {
	public static ArrayList create(Request request) {
		switch (request.getRWith()) {
			case Student -> {
				cWStudent(request);
			}
			case Diary -> {
				cWDiary(request);
			}
			case Class -> {
				cWClass(request);
			}
			case Subject -> {
				cWSubject(request);
			}
			case Mark -> {
				cWMark(request);
			}
			default -> {
			}
		}
		return rContent;
	}

	private static void cWStudent(Request request) {
		switch (request.getRBy()) {
			case StudentId -> {
				rContent.add(classService.getClassesByStudent(studentService.getStudentByStudentId(Long.parseLong(request.getRValue()))));
			}
			case Name -> {
				rContent = classService.getClassesByStudents(studentService.getStudentsByName(request.getRValue()));
			}
			case Date -> {
				rContent = classService.getClassesByStudents(studentService.getStudentsByBirth(request.getRValDate()));
			}
			default -> {
			}
		}
	}

	private static void cWDiary(Request request) {
		switch (request.getRBy()) {
			case DiaryId -> {
				rContent.add(classService.getClassByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getRValue()))));
			}
			case StudentId -> {
				rContent = classService.getClassesByDiaries(diaryService.getDiariesByScid(Long.parseLong(request.getRValue())));
			}
			case ClassId -> {
				rContent.add(classService.getClassByClassId(Integer.parseInt(request.getRValue())));
			}
			default -> {
				;
			}
		}
	}

	private static void cWClass(Request request) {
		switch (request.getRBy()) {
			case ClassId -> {
				rContent.add(classService.getClassByClassId(Integer.parseInt(request.getRValue())));
			}
			case Grade -> {
				rContent = classService.getClassesByGrade(Short.parseShort(request.getRValue()));
			}
			case Sign -> {
				rContent = classService.getClassesBySign(request.getRValue().strip().charAt(0));
			}
			case Year -> {
				rContent = classService.getClassesByYear(Year.parse(request.getRValue()));
			}
			case TeacherId -> {
				rContent = classService.getClassesByTid(Long.parseLong(request.getRValue()));
			}
			default -> {
			}
		}
	}

	private static void cWSubject(Request request) {
		switch (request.getRBy()) {
			case SubjectId -> {
				rContent = classService.getClassesBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue())));
			}
			case Name -> {
				rContent = classService.getClassesBySubjects(subjectService.getSubjectsByName(request.getRValue()));
			}
			case TeacherId -> {
				rContent = classService.getClassesBySubjects(subjectService.getSubjectsByTid(Long.parseLong(request.getRValue())));
			}
			default -> {
			}
		}
	}

	private static void cWMark(Request request) {
		switch (request.getRBy()) {
			case MarkId -> {
				rContent.add(classService.getClassByMark(markService.getMarkByMarkid(Long.parseLong(request.getRValue()))));
			}
			case DiaryId -> {
				rContent.add(classService.getClassByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getRValue()))));
			}
			case Date -> {
				rContent = classService.getClassesByMarks(markService.getMarksByDate(request.getRValDate()));
			}
			case SubjectId -> {
				rContent = classService.getClassesByMarks(markService.getMarksBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue()))));
			}
			case Mark -> {
				rContent = classService.getClassesByMarks(markService.getMarksByMark(Byte.parseByte(request.getRValue())));
			}
			default -> {
			}
		}
	}
}
