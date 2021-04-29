package de.tekup.ds.services.course;


import de.tekup.ds.models.CourseEntity;

import java.time.LocalDate;
import java.util.List;

public interface CourseService {
    List<CourseEntity> getBestSellingCourse(LocalDate startDate , LocalDate endDate);
    List<CourseEntity> getAllCourses();
    CourseEntity getCourseByID(long id);
    CourseEntity createNewCourse(CourseEntity newCourse);
    CourseEntity deleteCoursebyID(long id);
    CourseEntity updateCourse(long id ,CourseEntity newCourse);
}
