package com.jafe.comm.util;

import java.util.HashMap;
import java.util.Map;

//import org.apache.poi.util.SystemOutLogger;
//
//import com.alibaba.druid.pool.vendor.SybaseExceptionSorter;

class RuntimeExceptionTestInner extends RuntimeException {
	private static final long serialVersionUID = 6908889080356626754L;
	private Map<String, String> errorInfos = new HashMap();

	public String getBaseBizRuntimeExceptionInfo() {
		return getErrorNo() + "->" + getErrorInfo() + "->" + getErrorPathInfo();
	}

	public Map<String, String> getErrorInfos() {
		return this.errorInfos;
	}

	public RuntimeExceptionTestInner(String errorNo, String errorInfo, String errorPathInfo) {
		this.errorInfos.put("errorNo", errorNo);
		this.errorInfos.put("errorInfo", errorInfo);
		this.errorInfos.put("errorPathInfo", errorPathInfo);
	}

	public RuntimeExceptionTestInner(Exception e) {
		this.errorInfos.put("errorNo", "-1");
		this.errorInfos.put("errorInfo", e.getLocalizedMessage());
		this.errorInfos.put("errorPathInfo", e.fillInStackTrace().toString());
	}

	public String getErrorNo() {
		return (String) this.errorInfos.get("errorNo");
	}

	public String getErrorInfo() {
		return (String) this.errorInfos.get("errorInfo");
	}

	public String getErrorPathInfo() {
		return (String) this.errorInfos.get("errorPathInfo");
	}
}

public class RuntimeExceptionTest {
	public static void main(String arg[]){
		System.out.println("hello world.");
		try{
			int i = 10/0;
		}catch(Exception e){
			throw new RuntimeExceptionTestInner(e);
		}
	}
}