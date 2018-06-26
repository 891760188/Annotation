package ye.guo.huang.test02;

import java.lang.reflect.Field;

@TestAnnotation(msg="helloworld")
public class Test {
	
	@TestAnnotation02("李四")
	private String name ;
	
	public static void main(String[] args) {
		//该类是否应用了某个注解
		boolean hasAnnotation =  Test.class.isAnnotationPresent(TestAnnotation.class);
		if(hasAnnotation){
			TestAnnotation testAnnotation = Test.class.getAnnotation(TestAnnotation.class);
			  System.out.println("id:"+testAnnotation.id());
	            System.out.println("msg:"+testAnnotation.msg());
		}
		
		try {
			//通过反射获取name这个字段
			Field name = Test.class.getDeclaredField("name");
			name.setAccessible(true);//类中的成员变量为private,故必须进行此操作  ，否则获取不到相关数据。。
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
