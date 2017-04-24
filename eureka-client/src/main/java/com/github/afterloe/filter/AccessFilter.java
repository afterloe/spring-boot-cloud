/**
 * afterloe - com.github.afterloe.filter/AccessFilter.java
 *
 * Copyright(c) afterloe.
 * MIT Licensed
 *
 * Author:
 *		afterloe <lm6289511@gmail.com> (https://github.com/afterloe)
 *
 * Date:2017年4月24日上午10:52:52 
 */
package com.github.afterloe.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter {

	private String logTemplate = "%s resolve [%s] - $s";

	@Override
	public boolean shouldFilter() {
		/**
		 * 判断该过滤器是否要执行
		 */
		return true;
	}

	@Override
	public Object run() {
		/**
		 * 过滤器的具体逻辑。
		 * 
		 * 通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，
		 * 通过ctx.setResponseStatusCode(401)设置了其返回的错误码，
		 * 当然也可以进一步优化返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑
		 */
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		System.out.println(String.format(logTemplate, new Date(), request.getMethod(), request.getRequestURL()));
		Object accessToken = request.getParameter("accessToken");
		if (null == accessToken) {
			System.out.println("accessToken is not found!");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			return null;
		}
		System.out.println(accessToken + " require linked -- handler ok!");
		return null;
	}

	@Override
	public String filterType() {
		/**
		 * pre：可以在请求被路由之前调用 routing：在路由请求时候被调用 post：在routing和error过滤器之后被调用
		 * error：处理请求时发生错误时被调用
		 */
		return "pre";
	}

	@Override
	public int filterOrder() {
		/**
		 * int值来定义过滤器的执行顺序
		 */
		return 0;
	}

}
