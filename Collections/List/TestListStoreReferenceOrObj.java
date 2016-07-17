import java.util.List;
import java.util.ArrayList;
 

/**
����List�д洢���Ƕ�������û��Ƕ�����
*/
public class TestListStoreReferenceOrObj {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
		
		Student s1 = new Student();
        s1.age = 12;
		s1.gender = "male";
        list.add(s1);
		s1.age = 66;//�޸�s1�����ö������������
		s1 = null;//������s1����null,Ҳ����Ӱ����������˵��List��add()�����Ǹ�����һ�����ã��ø��Ƶ����ú�s1���õ���ͬһ������
				  //���Ծ���s1������null��Ҳ����Ӱ��List�б���Ŀ�������	
				  
        Student s2 = new Student();
		s2.age = 18;
		s2.gender = "female";
		s1 = s2;//��A����B�����õĶ���
		
		list.add(s1);
		
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }
}

class Student {
	public int age;
	public String gender;
	
	public String toString() {
		return "age = " + this.age + "; gender = " + this.gender;
	}
}

// ���:
// age = 66; gender = male������У����䲻��12�����޸ĺ��66��˵���洢��List�еĽ����Ƕ�������ã�����������Է����仯ʱ��ͨ�������û�ȡ���Ķ��������Ҳ�ᷢ���仯��
// age = 18; gender = female

// ��ˣ�List�д洢��ֻ�����ö��Ƕ�����