package de.tekup.ds.dtos.course;

import de.tekup.ds.models.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorColumn;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseCreateDTO {
    private String name;
    private float price;

}
