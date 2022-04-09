package com.acc.sts.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiInfo implements Serializable {

    private static final long serialVersionUID = 6138343878086739499L;

    private String key;

    private String uri;

    private String accessToken;

    private String userName;

    private String password;
}
