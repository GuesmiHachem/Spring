package de.tekup.ds.models;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("Dessert")
@Entity()

public class DessertEntity extends CourseEntity {

    {
        super.setCourseType("Dessert");
    }
}
