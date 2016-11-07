package com.nova.app.sys.security.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import com.nova.app.sys.service.SysService;


public class UrlPermissionsFilter extends PermissionsAuthorizationFilter {

	private SysService sysService;
	
	public SysService getSysService() {
		return sysService;
	}

	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}

	/** 
     *@param mappedValue 指的是在声明url时指定的权限字符串，如/User/create.do=perms[User:create].
     *	我们要动态产生这个权限字符串，所以这个配置对我们没用 
     */  
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
    		throws IOException {  
         return super.isAccessAllowed(request, response, buildPermissions(request));  
    }
    
    /** 
     * 根据请求URL产生权限字符串，这里只产生，而比对的事交给Realm 
     * @param request 
     * @return 
     */  
    protected String[] buildPermissions(ServletRequest request) {  
        String[] perms = new String[1];  
        HttpServletRequest req = (HttpServletRequest) request;  
        String path = req.getServletPath(); 
        String[] s = path.split("/");
        
        String urlPath = "/" + s[1] + "/" + s[2];
        String perm = sysService.getPermission(urlPath);
        
        perms[0] = perm;
 
        return perms;  
    }  
}
