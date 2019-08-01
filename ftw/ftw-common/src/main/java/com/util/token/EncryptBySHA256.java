package com.util.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @foundation : SHA256J加密算法
 * @author     : LiangGuiPing
 * @date       : 2018.10.29
 */
public class EncryptBySHA256 
{
	private static final Logger logger = LoggerFactory.getLogger(EncryptBySHA256.class);
	
	/** SHA256J加密算法 **/
	public static String getEncryptString(String strSrc) 
	{
		MessageDigest md = null;
		String strDes = null;
		String encName = "SHA-256";
		byte[] bt = strSrc.getBytes();
		try 
		{
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytes2Hex(md.digest());
		} 
		catch (NoSuchAlgorithmException e) 
		{
			logger.error("SHA256加密出错:{}", e);
			return "";
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] bts) 
	{
		String des = "";
		String tmp = null;
		for (int i = 0; i < bts.length; i++) 
		{
			tmp = (Integer.toHexString(bts[i] & 0xFF));
			if (tmp.length() == 1) 
			{
				des += "0";
			}
			des += tmp;
		}
		return des;
	}
	
	public static void main(String[] args) 
	{
		String password = "1";
		System.out.println("SHA256加密:{} " + EncryptBySHA256.getEncryptString(password));
	}
	
}
