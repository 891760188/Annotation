package ye.guo.huang.test03;

import java.lang.reflect.Method;


public class TestTool {
	public static void main(String[] args) {
		NoBug testobj = new NoBug();
		//class
		Class clazz = testobj.getClass();
		//反射，获得这个类的方法
		Method	[]	methods = clazz.getMethods();
		//用来记录测试日志
		StringBuilder log = new StringBuilder();
		//记录异常次数
		int errornum = 0 ;
		
		for (Method m:methods) {
			//之观察被注解的方法
			if(m.isAnnotationPresent(Jiecha.class)){
				m.setAccessible(true);//private方法经过这个设置，才可以被访问
				try {
					m.invoke(testobj, null);//方法的动态调用，用哪个对象，参数
				} catch (Exception e) {
					// TODO Auto-generated catch block
				    errornum++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //记录测试过程中，发生的异常的名称
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //记录测试过程中，发生的异常的具体信息
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
					e.printStackTrace();
				}
			}
		}
		
		  log.append(clazz.getSimpleName());
	        log.append(" has  ");
	        log.append(errornum);
	        log.append(" error.");
	
	        // 生成测试报告
	        System.out.println(log.toString());
		
	}
}
