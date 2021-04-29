package de.tekup.ds.services.course;

import de.tekup.ds.helpers.DataHelper;
import de.tekup.ds.models.CourseEntity;
import de.tekup.ds.models.CourseEntity;
import de.tekup.ds.repositories.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CourseServiceImpl implements CourseService {
	private CourseRepository courseRepo;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepo) {
		this.courseRepo = courseRepo;
	}

	@Override
	public List<CourseEntity> getAllCourses() {
		return this.courseRepo.findAll();
	}

	@Override
	public CourseEntity getCourseByID(long id) {
		return this.courseRepo.findById(id).orElseThrow(() -> new NoSuchElementException("Course Not Found"));
	}

	@Override
	public CourseEntity createNewCourse(CourseEntity newCourse) {
		return courseRepo.save(newCourse);
	}

	@Override
	public CourseEntity deleteCoursebyID(long id) {
		CourseEntity course = this.getCourseByID(id);
		this.courseRepo.deleteById(id);
		return course;
	}

	@Override
	public CourseEntity updateCourse(long id, CourseEntity newCourse) {
		CourseEntity course = this.getCourseByID(id);
		BeanUtils.copyProperties(newCourse, course, DataHelper.getNullPropertyNames(newCourse));
		return this.courseRepo.save(course);
	}

	@Override
	public List<CourseEntity> getBestSellingCourse(LocalDate startDate, LocalDate endDate) {
		// ==============================
		List<CourseEntity> listCourseEntity;
		Map<CourseEntity, Long> listcount;
		Optional<Entry<CourseEntity, Long>> maxListcount;
		// ==============================

		// All courses between startDate and endDate
		listCourseEntity = this.courseRepo//
				.findAllByInvoices_dateBetween(startDate.atStartOfDay(), endDate.atTime(23, 59, 59));//

		// count of All courses between startDate and endDate
		listcount = listCourseEntity//
				.stream()//
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));//

		// max count of All courses between startDate and endDate
		maxListcount = listcount//
				.entrySet()//
				.stream()//
				.max(Comparator.comparing(Map.Entry::getValue));//

		if (maxListcount.isPresent()) {
			return listcount//
					.entrySet()//
					.stream()//
					.filter(c -> c.getValue() == maxListcount.get().getValue())//
					.collect(Collectors.toList())//
					.stream()//
					.map(c -> c.getKey())//
					.collect(Collectors.toList());//
		}
		return new ArrayList<>();
	}
}
