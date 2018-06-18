package test;

public class ClassLoaderTest {

	public static void main(String[] args) {
		
		System.out.println(System.getProperty("java.class.path"));
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("sun.boot.class.path"));
		
		Class<ClassLoaderTest> cls =ClassLoaderTest.class;
		
		ClassLoader l1 = cls.getClassLoader();	//本类的classloader	
		ClassLoader l2 =cls.getClassLoader().getParent();
		ClassLoader l3 =cls.getClassLoader().getParent().getParent();
		
		
		System.out.println(l1.toString());
		System.out.println(l2.toString());
		System.out.println(l3.toString());
		
	}
}
