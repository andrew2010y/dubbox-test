package com.nova.app.vo;

import java.io.Serializable;


public class TestRedis implements Serializable{
 
	private static final long serialVersionUID = 4287060269487454287L;
	
	private byte[] tempbytes;

	public byte[] getTempbytes() {
		return tempbytes;
	}

	public void setTempbytes(byte[] tempbytes) {
		this.tempbytes = tempbytes;
	}
}
