import java.lang.ref.WeakReference;

public class TestWeakReference {
	public static void main(String[] args) {
		WeakReference<String> t = new WeakReference<String>(new String("hello"));
		System.out.println(t.get());
		
		String tmp = t.get(); // 1
		if(t.get() != null) {
			//String tmp = t.get(); // 2
			System.out.println("t.get() != null, tmp = " + tmp);
		}
		System.gc();// 通知gc进行垃圾回收
		System.out.println(t.get());
	}
}


//打印结果：
//	只保留1.  hello
//		t.get() != null, tmp = hello
//		hello
		
//	只保留2.  hello
//		t.get() != null, tmp = hello
//		null
		
//结论：
//只要JVM进行垃圾回收，被弱引用关联的对象必定会被回收掉。不过要注意的是，这里所说的被弱引用关联的对象是指只有弱引用与之关联，如果存在强引用同时与之关联，则进行垃圾回收时也//不会回收该对象（软引用也是如此）。