package com.cny.testupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * 展示登录和注册页面的Controller
 * <p>Title: PageController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class UploadController {


	/*
	 *采用spring提供的上传文件的方法
	 */
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value = {"/","/upload"},method = RequestMethod.POST,produces= "text/plain;charset=UTF-8")
	@ResponseBody()
	public String  springUpload(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException
	{
		response.setContentType("text/plain;charset='utf-8'");
		long  startTime=System.currentTimeMillis();
		//将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
				request.getSession().getServletContext());
		//检查form中是否有enctype="multipart/form-data"
		if(multipartResolver.isMultipart(request))
		{
			//将request变成多部分request
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			//获取multiRequest 中所有的文件名
			Iterator iter=multiRequest.getFileNames();

			while(iter.hasNext())
			{
				//一次遍历所有文件
				MultipartFile file=multiRequest.getFile(iter.next().toString());
				if(file!=null)
				{
					String path="E:/springUpload"+file.getOriginalFilename();
					//上传
					file.transferTo(new File(path));
				}

			}

		}
		long  endTime=System.currentTimeMillis();
		System.out.println("方法三的运行时间："+String.valueOf(endTime-startTime)+"ms");
		return "success";
	}
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value ={"/","/upload"},method = RequestMethod.GET,produces= "text/plain;charset=UTF-8")
	@ResponseBody()
	public String springUploadGet(){
		return "社会主义好";
	}
	/*@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value = "/",method = RequestMethod.GET,produces= "text/plain;charset=UTF-8")
	@ResponseBody()
	public String indexGet(){
		return "资本主义好1";
	}
	@CrossOrigin(origins = "*",maxAge = 3600)
	@RequestMapping(value = "/",method = RequestMethod.POST,produces= "text/plain;charset=UTF-8")
	@ResponseBody()
	public String indexPost(){
		return "资本主义好2";
	}*/

}
