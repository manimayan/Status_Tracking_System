package com.acc.sts.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "publicholidays")
public class PublicHoliday implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "HOLIDAY_ID")
    private int holidayId;
    
	 @JsonFormat(shape = JsonFormat.Shape.STRING,pattern= "MM/dd/yyyy")
    @Column(name = "HOLIDAYS")
    private Date holidays;
    
}
