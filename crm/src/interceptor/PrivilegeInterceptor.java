package interceptor;

import org.apache.struts2.ServletActionContext;

import com.itheima.domain.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor extends MethodFilterInterceptor{

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		User existUser= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null){
			//存入错误信息，页面跳转到登录页面
			ActionSupport actionSupport = (ActionSupport) invocation.getAction();
			actionSupport.addActionError("您还没有登录，无权限访问此页面");
			return "login";
		}else{
			//有登录，直接执行下一个拦截器
			 return invocation.invoke();
		}
	}
	
}
