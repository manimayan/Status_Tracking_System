package com.acc.sts.model;

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
public class Homedoc {

    private String ticketId;

    private String ticketType;

    private String ticketDescription;
    private String applicationName;
    private String status;
 //private String commentDescription;
    private String remedy;
    private String documentationDescription;
    private String documentationComment;
}
