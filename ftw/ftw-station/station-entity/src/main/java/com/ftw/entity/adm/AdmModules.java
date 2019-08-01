package com.ftw.entity.adm;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdmModules implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer platformid;

    private String modulename;

    private Integer parentid;

    private Date createtime;

    private String remark;

    private String visiturl;

    private Integer displaysort;

    private Integer isdisplay;

}