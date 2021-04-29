package de.tekup.ds.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity()
@DiscriminatorValue("Main")
public class MainCourseEntity extends CourseEntity {
    {
        super.setCourseType("Main");
    }
}
