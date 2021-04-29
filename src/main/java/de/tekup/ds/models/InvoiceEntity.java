package de.tekup.ds.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity(name = "invoices")
public class InvoiceEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false, unique = true)
	private int number;

	@Column(nullable = false)
	private LocalDateTime date;

	@Column(nullable = false)
	private int numberOfTableSets;

	@Column(nullable = false)
	private double total;

	@ManyToOne
	private ClientEntity client;

	@ManyToOne
	private TableEntity table;

	@ManyToMany
	@JoinTable(name = "invoice_course")
	private List<CourseEntity> courses;

	{
		this.date = LocalDateTime.now();
	}

	public InvoiceEntity(int number, int numberOfTableSets) {
		this.number = number;
		this.numberOfTableSets = numberOfTableSets;
	}
}
