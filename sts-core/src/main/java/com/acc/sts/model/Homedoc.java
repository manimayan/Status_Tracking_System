package com.acc.sts.model;

import java.io.Serializable;
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
public class Homedoc implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

    private String ticketId;

    private String ticketType;

    private String ticketDescription;
    
    private String applicationName;
    
    private String status;
  //private String commentDescription;
    private String remedy;
    
    private String documentationDescription;
    private int documentationId;
    private String documentationComment;
  
    
    public Homedoc(String ticketId,String ticketType,String descrip,String app,String status,String remedy,String doc,int documentation_id,String documentationComment){
    	this.ticketId=ticketId;
    	this.ticketType=ticketType;
    	this.ticketDescription=descrip;
    	this.applicationName=app;
    	this.status=status;
    	this.remedy=remedy;
    	this.documentationDescription=doc;
    	this.documentationId=documentation_id;
    	this.documentationComment=documentationComment;

    }
}
