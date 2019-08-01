package com.ftw.entity.adm;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdmPlatform implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String platformname;

    private String platformdesc;

}