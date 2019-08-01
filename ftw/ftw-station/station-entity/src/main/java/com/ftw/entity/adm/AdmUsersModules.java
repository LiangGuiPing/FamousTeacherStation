package com.ftw.entity.adm;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdmUsersModules implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userid;

    private Integer moduleid;

}