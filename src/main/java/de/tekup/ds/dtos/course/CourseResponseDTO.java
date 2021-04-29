package de.tekup.ds.dtos.course;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    private String name;
    private float price;
    private String courseType;
}
