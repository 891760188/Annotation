package ye.guo.huang.test01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpiderUtil {
	static String SendGet(String url) {
		// 定义一个字符串用来存储网页内容
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;
		try {
			// 将string转成url对象
			URL realUrl = new URL(url);
			// 初始化一个链接到那个url的连接
			URLConnection connection = realUrl.openConnection();
			// 开始实际的连接
			connection.connect();
			// 初始化 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while ((line = in.readLine()) != null) {
				// 遍历抓取到的每一行并将其存储到result里面
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	static ArrayList<ZhihuEntity> GetZhihuList(String content) {
		// 预定义一个ArrayList来存储结果
		ArrayList<ZhihuEntity> results = new ArrayList<ZhihuEntity>();
		// 用来匹配标题
		Pattern questionPattern = Pattern.compile("question_link.+?>(.+?)<");
		Matcher questionMatcher = questionPattern.matcher(content);
		// 用来匹配url，也就是问题的链接
		Pattern urlPattern = Pattern.compile("question_link.+?href=\"(.+?)\"");
		Matcher urlMatcher = urlPattern.matcher(content);
		// 问题和链接要均能匹配到
		boolean isFind = questionMatcher.find() && urlMatcher.find();
		while (isFind) {
			// 問題
			String question = questionMatcher.group(1);
			// 连接
			String zhihuUrl = "http://www.zhihu.com" + urlMatcher.group(1);
			// 定义一个知乎对象来存储抓取到的信息
			ZhihuEntity zhuhuTemp = new ZhihuEntity(question,null, zhihuUrl, null);
			// 添加成功匹配的结果
			results.add(zhuhuTemp);
			// 继续查找下一个匹配对象
			isFind = questionMatcher.find() && urlMatcher.find();
		}
		return results;
	}
	// 处理url
	 public static String getRealUrl(String url) {
	  // 将http://www.zhihu.com/question/22355264/answer/21102139
	  // 转化成http://www.zhihu.com/question/22355264
	  // 否则不变
	  String zhihuUrl = "" ;
	  Pattern pattern = Pattern.compile("question/(.*?)/");
	  Matcher matcher = pattern.matcher(url);
	  if (matcher.find()) {
	   zhihuUrl = "http://www.zhihu.com/question/" + matcher.group(1);
	  }
	  return zhihuUrl;
	 }
	public static void main(String[] args) {
		// 定义即将访问的链接
		String url = "https://www.zhihu.com/explore/recommendations";
		// 访问链接并获取页面内容
		String content = SendGet(url);
		// 获取该页面的所有的知乎对象
		ArrayList<ZhihuEntity> myZhihuList = GetZhihuList(content);
		// 打印结果
		for (ZhihuEntity zhihuEntity : myZhihuList) {
			System.out.println(zhihuEntity);
		}
		//获取真实有效得url list
		List<String> realUrlList = new ArrayList<String>();
		for (ZhihuEntity zhihuEntity : myZhihuList) {
			String urlTemp = zhihuEntity.getZhihuUrl();
			if(urlTemp == null || "".equals(urlTemp) ){
				continue ;
			}
			String realUrlTemp =  getRealUrl(urlTemp);
			realUrlList.add(realUrlTemp);
		}
	}
}
