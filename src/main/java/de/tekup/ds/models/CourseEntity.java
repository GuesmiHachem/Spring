package de.tekup.ds.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name="courses")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "course_type" )
@Data
@NoArgsConstructor
public abstract class  CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50 ,nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private float price;

    @Column(name = "course_type",insertable = false,updatable = false)
    private String courseType;

    @ManyToMany(mappedBy = "courses")
    private List<InvoiceEntity> invoices;

	@Override
	public String toString() {
		return "CourseEntity [id=" + id + ", name=" + name + ", price=" + price + ", courseType=" + courseType + "]";
	}



}
