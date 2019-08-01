package com.ftw.entity.adm;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AdmSystemLogs implements Serializable
{
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer logstypeid;

    private String operation;

    private Date createtime;

    private String ip;

    private String creator;

}