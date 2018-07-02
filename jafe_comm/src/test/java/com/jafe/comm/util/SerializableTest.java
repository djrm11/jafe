import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 接口参数校验
 * 
 */
public class SerializableTest {

	/**
	 * Title:应用学生类 Description:实现学生类实例的序列化与反序列化 Copyright: copyright(c) 2012
	 * Filename: UseStudent.java
	 * 
	 * @author Wang Luqing
	 * @version 1.0
	 */

	public static void main(String[] args) {
		Student st = new Student("Tom", 'M', 20, 3.6);
//		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";
//		filePath = filePath.replaceAll("file:/", "");
//		filePath = filePath.replaceAll("%20", " ");
//		File file = new File(filePath+"student.txt");
		File file = new File("D:\\yzftest\\SerializableTestClass\\student.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			// Student对象序列化过程
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(st);
			oos.flush();
			oos.close();
			fos.close();

			// Student对象反序列化过程
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			Student st1 = (Student) ois.readObject();
			System.out.println("name = " + st1.getName());
			System.out.println("sex = " + st1.getSex());
			System.out.println("year = " + st1.getYear());
			System.out.println("gpa = " + st1.getGpa());
			ois.close();
			fis.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * Title:学生类 Description:实现序列化接口的学生类 Copyright: copyright(c) 2012 Filename:
 *
 * @author Wang Luqing
 * @version 1.0
 */
class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6599279536740024172L;
	private  String name;//transient
	private char sex;
	private int year;
	private double gpa;

	public Student() {

	}

	public Student(String name, char sex, int year, double gpa) {
		this.name = name;
		this.sex = sex;
		this.year = year;
		this.gpa = gpa;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getName() {
		return this.name;
	}

	public char getSex() {
		return this.sex;
	}

	public int getYear() {
		return this.year;
	}

	public double getGpa() {
		return this.gpa;
	}
}
