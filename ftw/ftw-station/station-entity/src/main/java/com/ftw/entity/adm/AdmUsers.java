package com.ftw.entity.adm;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdmUsers implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer platformid;

    private String loginname;

    private String realname;

    private String password;

    private Date expiretime;

    private String telephone;

    private Integer status;

    private Integer creator;

    private Date createtime;

    private Date lastedittime;
}