import java.util.List;
import java.util.ArrayList;
 

/**
测试List中存储的是对象的引用还是对象本身
*/
public class TestListStoreReferenceOrObj {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
		
		Student s1 = new Student();
        s1.age = 12;
		s1.gender = "male";
        list.add(s1);
		s1.age = 66;//修改s1所引用对象的年龄属性
		s1 = null;//这里让s1引用null,也不会影响输出结果；说明List的add()操作是复制了一份引用，该复制的引用和s1引用的是同一个对象；
				  //所以就算s1引用了null，也不会影响List中保存的拷贝引用	
				  
        Student s2 = new Student();
		s2.age = 18;
		s2.gender = "female";
		s1 = s2;//让A引用B所引用的对象
		
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

// 输出:
// age = 66; gender = male（输出中，年龄不是12而是修改后的66；说明存储在List中的仅仅是对象的引用；当对象的属性发生变化时，通过该引用获取到的对象的属性也会发生变化）
// age = 18; gender = female

// 因此：List中存储的只是引用而非对象本身