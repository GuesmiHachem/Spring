package de.tekup.ds.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity()
@DiscriminatorValue("Starter")
public class StarterEntity extends CourseEntity {
    {
        super.setCourseType("Starter");
    }
}
