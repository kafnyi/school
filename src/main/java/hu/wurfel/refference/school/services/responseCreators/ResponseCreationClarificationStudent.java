package hu.wurfel.refference.school.services.responseCreators;

import hu.wurfel.refference.school.model.Request;

import java.time.Year;
import java.util.ArrayList;

public class ResponseCreationClarificationStudent extends responseCreationTemplate {
	public static ArrayList create(Request request) {
		switch (request.getRWith()) {
			case Student -> {
				sWStudent(request);
			}
			case Diary -> {
				sWDiary(request);
			}
			case Class -> {
				sWClass(request);
			}
			case Subject -> {
				sWSubject(request);
			}
			case Mark -> {
				sWMark(request);
			}
			default -> {
				;
			}
		}
		;
		return rContent;
	}

	private static void sWStudent(Request request) {
		switch (request.getRBy()) {
			case StudentId -> {
				rContent.add(studentService.getStudentByStudentId(Long.parseLong(request.getRValue())));
			}
			case Name -> {
				rContent = studentService.getStudentsByName(request.getRValue());
			}
			case Date -> {
				rContent = studentService.getStudentsByBirth(request.getRValDate());
			}
			default -> {
			}
		}
	}

	private static void sWDiary(Request request) {
		switch (request.getRBy()) {
			case DiaryId -> {
				rContent.add(studentService.getStudentByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getRValue()))));
			}
			case StudentId -> {
				rContent.add(studentService.getStudentByStudentId(Long.parseLong(request.getRValue())));
			}
			case ClassId -> {
				rContent = studentService.getStudentsByDiaries(diaryService.getDiariesByClassid(Integer.parseInt(request.getRValue())));
			}
			default -> {
			}
		}
	}

	private static void sWClass(Request request) {
		switch (request.getRBy()) {
			case ClassId -> {
				rContent = studentService.getStudentsByClass(classService.getClassByClassId(Integer.parseInt(request.getRValue())));
			}
			case Grade -> {
				rContent = studentService.getStudentsByClasses(classService.getClassesByGrade(Short.parseShort(request.getRValue())));
			}
			case Sign -> {
				rContent = studentService.getStudentsByClasses(classService.getClassesBySign(request.getRValue().strip().charAt(0)));
			}
			case Year -> {
				rContent = studentService.getStudentsByClasses(classService.getClassesByYear(Year.parse(request.getRValue())));
			}
			case TeacherId -> {
				rContent = studentService.getStudentsByClasses(classService.getClassesByTid(Long.parseLong(request.getRValue())));
			}
			default -> {
			}
		}
	}

	private static void sWSubject(Request request) {
		switch (request.getRBy()) {
			case SubjectId -> {
				rContent = studentService.getStudentsBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue())));
			}
			case Name -> {
				rContent = studentService.getStudentsBySubjects(subjectService.getSubjectsByName(request.getRValue()));
			}
			case TeacherId -> {
				rContent = studentService.getStudentsBySubjects(subjectService.getSubjectsByTid(Long.parseLong(request.getRValue())));
			}
			default -> {
			}
		}
	}

	private static void sWMark(Request request) {
		switch (request.getRBy()) {
			case MarkId -> {
				rContent.add(studentService.getStudentByMark(markService.getMarkByMarkid(Long.parseLong(request.getRValue()))));
			}
			case DiaryId -> {
				rContent.add(studentService.getStudentByDiary(diaryService.getDiaryByDiaryid(Integer.parseInt(request.getRValue()))));
			}
			case Date -> {
				rContent = studentService.getStudentsByMarks(markService.getMarksByDate(request.getRValDate()));
			}
			case SubjectId -> {
				rContent = studentService.getStudentsBySubject(subjectService.getSubjectBySubjectId(Integer.parseInt(request.getRValue())));
			}
			case Mark -> {
				rContent = studentService.getStudentsByMarks(markService.getMarksByMark(Byte.parseByte(request.getRValue())));
			}
			default -> {
			}
		}
	}
}
