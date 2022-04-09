package com.acc.sts.model;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)

public class PublicHoliday
{

    private int holidayId;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern= "MM/dd/yyyy")
    private Date holidays;
}
