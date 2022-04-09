package com.acc.sts.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Closed implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

    private String ticketId;

    private String ticketType;

    private String descrip;
    
    private String app;
    
    private String status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") 
    private LocalDate sdate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate edate;
    
    private String remedy;
    
    private String doc;
    
    public Closed(String ticketId,String ticketType,String descrip,String app,String status,LocalDate sdate,LocalDate edate,String remedy,String doc){
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.descrip=descrip;
    	this.app=app;
    	this.status=status;
    	
    	this.sdate=sdate;
    	this.edate=edate;
    	this.remedy=remedy;
    	this.doc=doc;
    }
}
