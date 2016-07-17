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
		System.gc();// ֪ͨgc������������
		System.out.println(t.get());
	}
}


//��ӡ�����
//	ֻ����1.  hello
//		t.get() != null, tmp = hello
//		hello
		
//	ֻ����2.  hello
//		t.get() != null, tmp = hello
//		null
		
//���ۣ�
//ֻҪJVM�����������գ��������ù����Ķ���ض��ᱻ���յ�������Ҫע����ǣ�������˵�ı������ù����Ķ�����ָֻ����������֮�������������ǿ����ͬʱ��֮�������������������ʱҲ//������ոö���������Ҳ����ˣ���