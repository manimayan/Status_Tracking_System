

package com.acc.sts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Documentation {
	
	private int documentationId;
	private Ticket ticket;
	private String documentationComment;
	private String remedy;
	private String documentationDescription;

}