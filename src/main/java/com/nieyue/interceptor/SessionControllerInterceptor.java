package com.nieyue.interceptor;

import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.nieyue.bean.Acount;
import com.nieyue.bean.Role;
import com.nieyue.exception.MySessionException;

/**
 * 用户session控制拦截器
 * @author yy
 *
 */
@Configuration
public class SessionControllerInterceptor implements HandlerInterceptor {

	@Resource
	StringRedisTemplate stringRedisTemplate;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//cors
//		 response.setHeader("Access-Control-Allow-Credentials", "true");
//		 response.setHeader("Access-Control-Allow-Origin","*");
//		 response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//		 response.setHeader("Access-Control-Max-Age", "3600");
//		 response.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		
		
		//如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
        	System.out.println(handler);
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
       System.out.println(method.getDefaultValue());
        
        //自定义token
//        if(method.getName().equals("loginAdmin")||method.getName().equals("isloginAdmin")||method.getName().equals("tokenAdmin")){
//        	return true;
//        }else if (manager.checkToken("XuDeOAadmin", manager.getToken("XuDeOAadmin", request), request, response)) {
//        	//验证token成功
//            return true;
//        }
       //证书验证
//       if(request.getParameter("certificate")==null||!request.getParameter("certificate")
//    		   .equals(MyDESutil.getMD5Timestamp("jiaxingyufa",new Date().getTime()/30000))){
//    	   System.out.println(new Date().getTime()/30000);
//    	   System.out.println(request.getParameter("certificate"));
//    	   System.out.println(MyDESutil.getMD5Timestamp("jiaxingyufa",new Date().getTime()/30000));
//    	   throw new MyCertificateException();
//       }
        Acount sessionAcount = null;
        Role sessionRole=null;
        if(request.getSession()!=null
        		&&request.getSession().getAttribute("acount")!=null
        		&&request.getSession().getAttribute("role")!=null
        		){
        sessionAcount = (Acount) request.getSession().getAttribute("acount");
        sessionRole = (Role) request.getSession().getAttribute("role");
        }
        
        if(
        		request.getServletPath().equals("/")
        		||request.getRequestURI().indexOf("tool")>-1
        		||request.getRequestURI().indexOf("swagger")>-1 
        		||request.getRequestURI().indexOf("api-docs")>-1
        		//merCate
        		||request.getRequestURI().indexOf("merCate/count")>-1
        		||request.getRequestURI().indexOf("merCate/list")>-1
        		||method.getName().equals("loadMerCate")
        		//mer
        		||request.getRequestURI().indexOf("mer/count")>-1
        		||request.getRequestURI().indexOf("mer/list")>-1
        		||request.getRequestURI().indexOf("mer/loadSmall")>-1
        		||method.getName().equals("loadMer")
        		//merImg
        		||request.getRequestURI().indexOf("merImg/count")>-1
        		||request.getRequestURI().indexOf("merImg/list")>-1
        		||method.getName().equals("loadMerImg")
        		//merOrder
        		||request.getRequestURI().indexOf("merOrder/count")>-1
        		||request.getRequestURI().indexOf("merOrder/list")>-1
        		||method.getName().equals("loadMerOrder")
        		//receiptInfo
        		||request.getRequestURI().indexOf("receiptInfo/count")>-1
        		//orderMer
        		||request.getRequestURI().indexOf("orderMer/count")>-1
        		||request.getRequestURI().indexOf("orderMer/list")>-1
        		||method.getName().equals("loadOrderMer")
       
        		){
        	return true;
        }else if (sessionAcount!=null) {
        	//确定角色存在
        	if(sessionRole!=null ){
        	//超级管理员
        	if(sessionRole.getName().equals("超级管理员")
        			||sessionRole.getName().equals("运营管理员")
        			||sessionRole.getName().equals("编辑管理员")
        			||sessionRole.getName().equals("商城管理员")
        			){
        		return true;
        	}
        	//admin中只许修改自己的值
        	if(sessionRole.getName().equals("用户")){
        		//商品不许删除/增加/修改
        		if( request.getRequestURI().indexOf("/mer/delete")>-1 
        				|| request.getRequestURI().indexOf("/mer/add")>-1
        				|| request.getRequestURI().indexOf("/mer/update")>-1
        				){
        			throw new MySessionException();
        		}
        		//商品类型不许删除/增加/修改
        		if( request.getRequestURI().indexOf("/merCate/delete")>-1 
        				|| request.getRequestURI().indexOf("/merCate/add")>-1
        				|| request.getRequestURI().indexOf("/merCate/update")>-1
        				){
        			throw new MySessionException();
        		}
        		//商品图片不许删除/增加/修改
        		if( request.getRequestURI().indexOf("/merImg/delete")>-1 
        				|| request.getRequestURI().indexOf("/merImg/add")>-1
        				|| request.getRequestURI().indexOf("/merImg/update")>-1
        				){
        			throw new MySessionException();
        		}
        		//商品订单不许删除/增加/修改
        		if( request.getRequestURI().indexOf("/merOrder/delete")>-1 
        				|| request.getRequestURI().indexOf("/merOrder/add")>-1
        				|| request.getRequestURI().indexOf("/merOrder/update")>-1
        				){
        			//增加自身信息
        			if( request.getRequestURI().indexOf("/merOrder/add")>-1 && request.getParameter("acountId").equals(sessionAcount.getAcountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//收货信息不许删除/增加/修改
        		if( request.getRequestURI().indexOf("/receiptInfo/delete")>-1 
        				|| request.getRequestURI().indexOf("/receiptInfo/add")>-1
        				|| request.getRequestURI().indexOf("/receiptInfo/update")>-1
        				){
        			//不能删除
        			if( request.getRequestURI().indexOf("/receiptInfo/delete")>-1 ){
        				throw new MySessionException();
        			}
        			//增改自身信息
        			if( request.getParameter("acountId").equals(sessionAcount.getAcountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		//订单商品信息不许删除/增加/修改
        		if( request.getRequestURI().indexOf("/receiptInfo/delete")>-1 
        				|| request.getRequestURI().indexOf("/receiptInfo/add")>-1
        				|| request.getRequestURI().indexOf("/receiptInfo/update")>-1
        				||request.getRequestURI().indexOf("receiptInfo/list")>-1
                		||method.getName().equals("loadReceiptInfo")
        				){ 
        			//增删改查自身信息
        			if( request.getParameter("acountId").equals(sessionAcount.getAcountId().toString())){
        				return true;
        			}
        			throw new MySessionException();
        		}
        		return true;
        	}
        	}
        	
        }
        //如果验证token失败
       throw new MySessionException();
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
