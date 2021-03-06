package com.iteason.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.iteason.utils.FastDFSClient;
import com.iteason.utils.JsonUtils;

@Controller
public class PictureController {

	//读取配置文件中的图片服务器地址
	@Value("${IMAGE_SERVER_URL}")
	private String IMAGE_SERVER_URL;
	
	//图片地址前缀
	@Value("${IMAGE_ADDR}")
	private String IMAGE_ADDR;
	/**
	 * 
	 * @author 阿荣
	 * @Description:图片上传
	 * @date: 2018年8月14日 下午1:17:57
	 * @param image
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/pic/upload",produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
	@ResponseBody
	public String uploadPicture(MultipartFile uploadFile,HttpServletRequest req) throws Exception {
		
		/*//调用图片服务器fastDfs的客户端
		FastDFSClient client = new FastDFSClient("classpath:client.conf");//加载配置文件
		byte[] fileContent = uploadFile.getBytes();//文件内容
		String originName = uploadFile.getOriginalFilename();//原始文件名
		int dotIndex = originName.indexOf(".");//获得.的索引
		String extName = originName.substring(dotIndex + 1);//获得扩展名
		//调用客户端进行文件上传，回显一个上传地址
		try {
			String file = client.uploadFile(fileContent, extName);
			//图片上传成功
			System.out.println(file);
			
			//json数据回显
			Map map = new HashMap<>();
			map.put("error",0);
			map.put("url", IMAGE_SERVER_URL+file);
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			//图片上传失败
			//json数据回显
			Map map = new HashMap<>();
			map.put("error",1);
			map.put("message", "图片上传失败");
			return JsonUtils.objectToJson(map);
		}
		
		*/
		//根据富文本编辑器的图片上传回显要求进行json回显（成功时：error:0,url:xxx。失败时:error:1,message:错误信息）
		
		/**
		 * 使用原始的方式实现图片上传
		 */
		//项目路径
		String basePath = System.getProperty("test.webapp");
		//获取图片名
		String filename = uploadFile.getOriginalFilename();
		//图片输出路径
		FileOutputStream out = new FileOutputStream(new File("D:/workspace_e3shop/e3-manager-web/src/main/webapp/image/"+filename));
		byte[] content = uploadFile.getBytes();
		int end = content.length;
		//输出
		out.write(content,0,end);
		
		Map map = new HashMap<>();
		map.put("error", 0);
		map.put("url", IMAGE_ADDR+filename);
		return JsonUtils.objectToJson(map);
	}
}
