package de.tekup.ds.controllers;

import de.tekup.ds.dtos.course.CourseCreateDTO;
import de.tekup.ds.dtos.course.CourseResponseDTO;
import de.tekup.ds.dtos.course.CourseUpdateDTO;
import de.tekup.ds.models.*;
import de.tekup.ds.repositories.CourseRepository;
import de.tekup.ds.services.course.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController

@RequestMapping("api/Course")
@Slf4j
public class CourseController {
	private CourseService service;

	@Autowired
	private CourseRepository repo;
	private ModelMapper modelMapper;

	@Autowired
	public CourseController(CourseService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}

	@GetMapping()
	public List<CourseResponseDTO> getAllCourses() {
		return this.service.getAllCourses().stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public CourseResponseDTO findByid(@PathVariable("id") int id) {
		return this.convertToDto(service.getCourseByID(id));
	}

	@DeleteMapping("/delete/{id}")
	public CourseResponseDTO deleteCourse(@PathVariable("id") long id) {
		return this.convertToDto(this.service.deleteCoursebyID(id));
	}

	@PostMapping("/create/dessert")
	public CourseResponseDTO createNewDessert(@Valid @RequestBody CourseCreateDTO newCourse) {
		return this.convertToDto(service.createNewCourse(modelMapper.map(newCourse, DessertEntity.class)));

	}

	@PostMapping("/update/{id}")
	public CourseResponseDTO createNewCourse(@PathVariable("id") long id,
			@Valid @RequestBody CourseUpdateDTO newCourse) {
		return this.convertToDto(service.updateCourse(id, modelMapper.map(newCourse, CourseEntity.class)));
	}

	@PostMapping("/create/starter")
	public CourseResponseDTO createNewStarter(@Valid @RequestBody CourseCreateDTO newCourse) {

		return this.convertToDto(service.createNewCourse(modelMapper.map(newCourse, StarterEntity.class)));
	}

	@PostMapping("/create/main")
	public CourseResponseDTO createNewMainCourse(@Valid @RequestBody CourseCreateDTO newCourse) {
		return this.convertToDto(service.createNewCourse(modelMapper.map(newCourse, MainCourseEntity.class)));
	}

	// 3 - a) Pour une période donnée quel est le plat le plus acheté ?
	@GetMapping("/getBestSellingCourse")
	public List<CourseResponseDTO> getBestSellingCourse(
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
		return this.convertToDto(service.getBestSellingCourse(dateDebut, dateFin));
	}

	private CourseEntity convertToEntity(CourseCreateDTO courseDTO) {
		return modelMapper.map(courseDTO, CourseEntity.class);
	}

	private CourseResponseDTO convertToDto(CourseEntity course) {
		return modelMapper.map(course, CourseResponseDTO.class);
	}

	private List<CourseResponseDTO> convertToDto(List<CourseEntity> listCourse) {
		return listCourse.stream().map(course -> modelMapper.map(course, CourseResponseDTO.class))
				.collect(Collectors.toList());
	}
}
