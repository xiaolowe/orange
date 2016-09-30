package com.orange.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Arrays;

import com.orange.cas.entity.LogEntity;
import com.orange.cas.entity.UserEntity;
import com.orange.cas.model.LogUser;
import com.orange.cas.service.LogService;
import com.orange.common.ManageContainer;
import com.orange.core.annotation.ControllerLogAnnotation;
import com.orange.core.annotation.ServiceLogAnnotation;

/**
 * AOP切点类
 */
@Aspect
@Component
public class LogAspect {
	// 注入Service用于把日志保存数据库
	// 本地异常日志记录对象

	// Service层切点
	@Pointcut("@annotation(com.orange.core.annotation.ServiceLogAnnotation)")
	public void serviceAspect() {
		
	}

	// Controller层切点
	@Pointcut("@annotation(com.orange.core.annotation.ControllerLogAnnotation)")
	public void controllerAspect() {
		
	}
	
	@After("controllerAspect()")
	public void doControllerAfter(JoinPoint joinPoint) {

//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//				.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
//		LogUser u = (LogUser) session
//				.getAttribute(ManageContainer.session_log_user_info);
//		String ip = request.getRemoteAddr();
//        String params = Arrays.toString(joinPoint.getArgs());
//        LogEntity log = new LogEntity();
//		try {
//			// *========controllerAspect()控制台输出=========*//
//			System.out.println("=====controller后置通知开始=====");
//			System.out.println("请求方法:"
//					+ (joinPoint.getTarget().getClass().getName() + "."
//							+ joinPoint.getSignature().getName() + "()"));
//			System.out.println("方法描述:"
//					+ getControllerMethodDescription(joinPoint));
//			if(u!=null){
//				System.out.println("请求人:" + u.getUserName());
//			}else{
//				System.out.println("请求人:" + "空");
//			}
//			System.out.println("请求IP:" + ip);
//			System.out.println("请求参数:" + params);
//			// *========数据库日志=========*//
//			if(u != null){
//	        	log.setUserName(u.getUserName());
//	    		log.setUserMobile(u.getUserMobile());
//	        }
//	        
//			log.setRequestIp(ip);
//			log.setDescription(getControllerMethodDescription(joinPoint));
//			log.setMethod((joinPoint.getTarget().getClass().getName() + "."
//					+ joinPoint.getSignature().getName() + "()"));
//			log.setClient(getControllerMethodClient(joinPoint));
//			log.setOpt(getControllerMethodOpt(joinPoint));
//			log.setType(LogEntity.type_normal);
//			log.setExceptionCode(null);
//			log.setExceptionDetail(null);
//			log.setParams(params);
//			log.setFroms(LogEntity.from_controller);
//			log.setStatus(LogEntity.S_USED);
//			// 保存数据库
//			System.out.println("=====controller后置通知结束=====");
//		} catch (Exception ex) {
//			// 记录本地异常日志
//			logger.error("==controller后置通知异常==");
//			logger.error("异常信息:{}", ex.getMessage());
//		}
//		casLogService.insert(log);
	}

	
	@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
	public void doControllerAfterThrowing(JoinPoint joinPoint, Throwable e) {

//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//				.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
//		LogUser u = (LogUser) session
//				.getAttribute(ManageContainer.session_log_user_info);
//		String ip = request.getRemoteAddr();
//        String params = "";
//        LogEntity log = new LogEntity();
//		try {
//			// *========controllerAspect()控制台输出=========*//
//			System.out.println("=====controller后置通知开始=====");
//			System.out.println("请求方法:"
//					+ (joinPoint.getTarget().getClass().getName() + "."
//							+ joinPoint.getSignature().getName() + "()"));
//			System.out.println("方法描述:"
//					+ getControllerMethodDescription(joinPoint));
//			if(u!=null){
//				System.out.println("请求人:" + u.getUserName());
//			}else{
//				System.out.println("请求人:" + "空");
//			}
//			System.out.println("请求IP:" + ip);
//			System.out.println("请求参数:" + params);
//			// *========数据库日志=========*//
//			if(u != null){
//	        	log.setUserName(u.getUserName());
//	    		log.setUserMobile(u.getUserMobile());
//	        }
//	        
//			log.setRequestIp(ip);
//			log.setDescription(getControllerMethodDescription(joinPoint));
//			log.setMethod((joinPoint.getTarget().getClass().getName() + "."
//					+ joinPoint.getSignature().getName() + "()"));
//			log.setClient(getControllerMethodClient(joinPoint));
//			log.setType(LogEntity.type_exception);
//			log.setExceptionCode(e.getClass().getName());
//			log.setExceptionDetail(e.getMessage());
//			log.setParams(params);
//			log.setFroms(LogEntity.from_controller);
//			log.setStatus(LogEntity.S_USED);
//			// 保存数据库
//			System.out.println("=====controller后置通知结束=====");
//		} catch (Exception ex) {
//			// 记录本地异常日志
//			logger.error("==controller后置通知异常==");
//			logger.error("异常信息:{}", ex.getMessage());
//			logger.error("异常方法:"+ joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName() 
//					+ "异常代码:" + e.getClass().getName() 
//					+"异常信息:" + e.getMessage() 
//					+"参数:" +params); 
//			log.setExceptionCode(e.getClass().getName());
//			log.setExceptionDetail(e.getMessage());
//		}
//		casLogService.insert(log);
	}

	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doServiceAfterThrowing(JoinPoint joinPoint, Throwable e) {
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
//				.getRequestAttributes()).getRequest();
//		HttpSession session = request.getSession();
//		// 读取session中的用户
//		LogUser u = (LogUser) session
//				.getAttribute(ManageContainer.session_log_user_info);
//		String ip = request.getRemoteAddr();
//		String params = "";
//		LogEntity log = new LogEntity();
//		try {
//			// *========serviceAspect()控制台输出=========*//
//			System.out.println("=====异常通知开始=====");
//			System.out.println("异常代码:" + e.getClass().getName());
//			System.out.println("异常信息:" + e.getMessage());
//			System.out.println("异常方法:"
//					+ (joinPoint.getTarget().getClass().getName() + "."
//							+ joinPoint.getSignature().getName() + "()"));
//			System.out.println("方法描述:" + getServiceMthodDescription(joinPoint));
//			if(u!=null){
//				System.out.println("请求人:" + u.getUserName());
//			}else{
//				System.out.println("请求人:" + "空");
//			}
//			System.out.println("请求IP:" + ip);
//			System.out.println("请求参数:" + params);
//			/* ==========数据库日志========= */
//
//			if(u != null){
//	        	log.setUserName(u.getUserName());
//	    		log.setUserMobile(u.getUserMobile());
//	        }
//			log.setDescription(getServiceMthodDescription(joinPoint));
//			log.setMethod((joinPoint.getTarget().getClass().getName() + "."
//					+ joinPoint.getSignature().getName() + "()"));
//			log.setType(LogEntity.type_exception);
//			log.setRequestIp(ip);
//			log.setExceptionCode(e.getClass().getName());
//			log.setExceptionDetail(e.getMessage());
//			log.setParams(params);
//			log.setFroms(LogEntity.from_service);
//			log.setStatus(LogEntity.S_USED);
//			// 保存数据库
//			System.out.println("=====异常通知结束=====");
//		} catch (Exception ex) {
//			// 记录本地异常日志
//			logger.error("==异常通知异常==");
//			logger.error("异常信息:{}", ex.getMessage());
//			logger.error("异常方法:"+ joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName() 
//					+ "异常代码:" + e.getClass().getName() 
//					+"异常信息:" + e.getMessage() 
//					+"参数:" +params); 
//			log.setExceptionCode(e.getClass().getName());
//			log.setExceptionDetail(e.getMessage());
//		}
//		casLogService.insert(log);
	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getServiceMthodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(
							ServiceLogAnnotation.class).description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(
							ControllerLogAnnotation.class).description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodClient(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String client = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					client = method.getAnnotation(ControllerLogAnnotation.class)
							.client();
					break;
				}
			}
		}
		return client;
	}
	
	public static String getControllerMethodOpt(JoinPoint joinPoint)
			throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String opt = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					opt = method.getAnnotation(ControllerLogAnnotation.class)
							.opt();
					break;
				}
			}
		}
		return opt;
	}
}
