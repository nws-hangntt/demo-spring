package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Product.
 */
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	/** The product name. */
	@Column(name = "product_name")
	@NotEmpty(message = "*Please provide an Product Name")
	private String productName;
	
	/** The product title. */
	@Column(name = "product_title")
	@NotEmpty(message = "*Please provide an Product Title")
	private String productTitle;

	/** The product company. */
	@Column(name = "product_company")
	@NotEmpty(message = "*Please provide an Product Company")
	private String productCompany;


	/** The product genre. */
	@Column(name = "product_genre")
	@NotEmpty(message = "*Please provide an Product Genre")
	private String productGenre;
	
	/** The product number. */
	@Column(name = "product_number")
	@NotNull(message = "*Please provide an Product Number")
	private Integer  productNumber;

	/** The product price. */
	@Column(name = "product_price")
	@NotNull(message = "*Please provide an Product Price")
	private Integer  productPrice;

	/** The product status. */
	@Column(name = "product_status")
	private Integer  productStatus;

	/** The product start date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "product_startdate")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@NotNull(message = "*Please provide an Product Start Date")
	private Date productStartDate;

	/** The product end date. */
	@Temporal(TemporalType.DATE)
	@Column(name = "product_enddate")
	@DateTimeFormat(pattern ="MM/dd/yyyy")
	@NotNull(message = "*Please provide an Product End Date")
	private Date productEndDate;
}
