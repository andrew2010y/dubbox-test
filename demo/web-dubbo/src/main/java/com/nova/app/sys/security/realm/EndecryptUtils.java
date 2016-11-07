package com.nova.app.sys.security.realm;

import org.apache.shiro.crypto.hash.Md5Hash;

public class EndecryptUtils {

	/** 
     * 对密码进行md5加密,并返回密文和salt，包含在User对象中 
     * @param username 用户名 
     * @param password 密码 
     * @return 密文和salt 
     */ 
    public static String md5Password(String password){ 
        // Preconditions.checkArgument(!Strings.isNullOrEmpty(password),"password不能为空"); 
//        SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator(); 
//        String salt= secureRandomNumberGenerator.nextBytes().toHex(); 
//        
//        //组合username,两次迭代，对密码进行加密 
//        String passwordmd5= new Md5Hash(password,salt,2).toBase64(); 
        
    	String passwordmd5 = new Md5Hash(password).toHex();
        return passwordmd5; 
    } 
}
