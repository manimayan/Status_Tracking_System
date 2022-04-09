package com.acc.sts.model;

import java.io.Serializable;	
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
public class Clarify implements Serializable {
    private static final long serialVersionUID = 40586895471103592L;

    private String ticketId;

    private String ticketType;

    private String employeeName;
    
    private String ticketDescription;
    
   private String applicationName;
    
    private String status;
   
    private String clarificationDescription;
    
    private String response;
    
    private int flag;
  }

