package ye.guo.huang.test03;

import java.lang.reflect.Method;


public class TestTool {
	public static void main(String[] args) {
		NoBug testobj = new NoBug();
		//class
		Class clazz = testobj.getClass();
		//���䣬��������ķ���
		Method	[]	methods = clazz.getMethods();
		//������¼������־
		StringBuilder log = new StringBuilder();
		//��¼�쳣����
		int errornum = 0 ;
		
		for (Method m:methods) {
			//֮�۲챻ע��ķ���
			if(m.isAnnotationPresent(Jiecha.class)){
				m.setAccessible(true);//private��������������ã��ſ��Ա�����
				try {
					m.invoke(testobj, null);//�����Ķ�̬���ã����ĸ����󣬲���
				} catch (Exception e) {
					// TODO Auto-generated catch block
				    errornum++;
                    log.append(m.getName());
                    log.append(" ");
                    log.append("has error:");
                    log.append("\n\r  caused by ");
                    //��¼���Թ����У��������쳣������
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    //��¼���Թ����У��������쳣�ľ�����Ϣ
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
	
	        // ���ɲ��Ա���
	        System.out.println(log.toString());
		
	}
}
