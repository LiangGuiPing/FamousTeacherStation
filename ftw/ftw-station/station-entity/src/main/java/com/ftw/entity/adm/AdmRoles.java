package com.ftw.entity.adm;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdmRoles implements Serializable
{
	private static final long serialVersionUID = 1L;

	public Integer id;

    public String rolename;

    public String roledesc;

    public Integer creator;

    public Date createtime;

    public Integer flag = 0;

}