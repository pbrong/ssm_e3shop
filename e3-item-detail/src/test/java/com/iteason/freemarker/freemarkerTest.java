package com.iteason.freemarker;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class freemarkerTest {

	@Test
	public void fun1() throws Exception {
		// 第一步：创建一个Configuration对象，直接new一个对象。构造方法的参数就是freemarker对于的版本号。
		Configuration configuration = new Configuration(Configuration.getVersion());
		// 第二步：设置模板文件所在的路径。
		configuration.setDirectoryForTemplateLoading(new File("D:/workspace_e3shop/e3-item-detail/src/main/webapp/WEB-INF/ftl"));
		// 第三步：设置模板文件使用的字符集。一般就是utf-8.
		configuration.setDefaultEncoding("utf-8");
		// 第四步：加载一个模板，创建一个模板对象。
		Template template = configuration.getTemplate("hello.ftl");
		// 第五步：创建一个模板使用的数据集，可以是pojo也可以是map。一般是Map。
		Map dataModel = new HashMap<>();
		//向数据集中添加数据
		dataModel.put("hello", "this is my first freemarker test.");
		// 第六步：创建一个Writer对象，一般创建一FileWriter对象，指定生成的文件名。
		Writer out = new FileWriter(new File("D:/temp/hello.html"));
		// 第七步：调用模板对象的process方法输出文件。
		template.process(dataModel, out);
		// 第八步：关闭流。
		out.close();
	}
	
	
	@SuppressWarnings("resource")
	@Test
	public void freemarker_hello() throws Exception {
		//1、创建Configuration对象
		Configuration configuration = new Configuration(Configuration.getVersion());
		//2、指定模板文件目录
		configuration.setDirectoryForTemplateLoading(new File("D:/workspace_e3shop/e3-item-detail/src/main/webapp/WEB-INF/ftl"));
		//3、指定模板文件
		Template template = configuration.getTemplate("hello.ftl");
		//4、指定要使用来填充内容的字符集
		configuration.setDefaultEncoding("utf-8");
		//5、创建Map，并将内容以键值对的形式指定
		Map dataModel = new HashMap<>();
		dataModel.put("hello", "first use freemarker --arong");
		//6、指定文件输出位置
		FileWriter out = new FileWriter(new File("D:/temp/hello.html"));
		//7、调用模板对象的process方法输出文件。
		template.process(dataModel, out);
		//8、关流
		out.close();
	}
}
