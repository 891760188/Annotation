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
		// ����һ���ַ��������洢��ҳ����
		String result = "";
		// ����һ�������ַ�������
		BufferedReader in = null;
		try {
			// ��stringת��url����
			URL realUrl = new URL(url);
			// ��ʼ��һ�����ӵ��Ǹ�url������
			URLConnection connection = realUrl.openConnection();
			// ��ʼʵ�ʵ�����
			connection.connect();
			// ��ʼ�� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			// ������ʱ�洢ץȡ����ÿһ�е�����
			String line;
			while ((line = in.readLine()) != null) {
				// ����ץȡ����ÿһ�в�����洢��result����
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally���ر�������
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
		// Ԥ����һ��ArrayList���洢���
		ArrayList<ZhihuEntity> results = new ArrayList<ZhihuEntity>();
		// ����ƥ�����
		Pattern questionPattern = Pattern.compile("question_link.+?>(.+?)<");
		Matcher questionMatcher = questionPattern.matcher(content);
		// ����ƥ��url��Ҳ�������������
		Pattern urlPattern = Pattern.compile("question_link.+?href=\"(.+?)\"");
		Matcher urlMatcher = urlPattern.matcher(content);
		// ���������Ҫ����ƥ�䵽
		boolean isFind = questionMatcher.find() && urlMatcher.find();
		while (isFind) {
			// ���}
			String question = questionMatcher.group(1);
			// ����
			String zhihuUrl = "http://www.zhihu.com" + urlMatcher.group(1);
			// ����һ��֪���������洢ץȡ������Ϣ
			ZhihuEntity zhuhuTemp = new ZhihuEntity(question,null, zhihuUrl, null);
			// ��ӳɹ�ƥ��Ľ��
			results.add(zhuhuTemp);
			// ����������һ��ƥ�����
			isFind = questionMatcher.find() && urlMatcher.find();
		}
		return results;
	}
	// ����url
	 public static String getRealUrl(String url) {
	  // ��http://www.zhihu.com/question/22355264/answer/21102139
	  // ת����http://www.zhihu.com/question/22355264
	  // ���򲻱�
	  String zhihuUrl = "" ;
	  Pattern pattern = Pattern.compile("question/(.*?)/");
	  Matcher matcher = pattern.matcher(url);
	  if (matcher.find()) {
	   zhihuUrl = "http://www.zhihu.com/question/" + matcher.group(1);
	  }
	  return zhihuUrl;
	 }
	public static void main(String[] args) {
		// ���弴�����ʵ�����
		String url = "https://www.zhihu.com/explore/recommendations";
		// �������Ӳ���ȡҳ������
		String content = SendGet(url);
		// ��ȡ��ҳ������е�֪������
		ArrayList<ZhihuEntity> myZhihuList = GetZhihuList(content);
		// ��ӡ���
		for (ZhihuEntity zhihuEntity : myZhihuList) {
			System.out.println(zhihuEntity);
		}
		//��ȡ��ʵ��Ч��url list
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
