package com.ftw.entity.adm;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdmPermission implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Integer id;

    public Integer roleid;

    public String name;

    public String permissionDesc;

}