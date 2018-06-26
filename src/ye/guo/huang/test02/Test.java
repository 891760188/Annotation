package ye.guo.huang.test02;

import java.lang.reflect.Field;

@TestAnnotation(msg="helloworld")
public class Test {
	
	@TestAnnotation02("����")
	private String name ;
	
	public static void main(String[] args) {
		//�����Ƿ�Ӧ����ĳ��ע��
		boolean hasAnnotation =  Test.class.isAnnotationPresent(TestAnnotation.class);
		if(hasAnnotation){
			TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
			  System.out.println("id:"+testAnnotation.id());
	            System.out.println("msg:"+testAnnotation.msg());
		}
		
		try {
			//ͨ�������ȡname����ֶ�
			Field name = Test.class.getDeclaredField("name");
			name.setAccessible(true);//���еĳ�Ա����Ϊprivate,�ʱ�����д˲���  �������ȡ����������ݡ���
			TestAnnotation02 testAnnotation02 = name.getAnnotation(TestAnnotation02.class);
			if(testAnnotation02 != null){
				System.out.println(testAnnotation02.value());
			}
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
