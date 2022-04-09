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
public class Homeadmin {

    private String ticketId;

    private String ticketType;

    private String ticketDescription;
    private String applicationName;
    private int priority;
    private String activity;
    private String status;
    private String devComment;
    private String tester;
    private String developerName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    private String testComment;
}
