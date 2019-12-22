package Client;

public class Account {

	String id;
	String pwd;
	String name;
	int age;
	String phoneNum;
		
	void setId(String input) {
		this.id = input;
	}
	
	void setPwd(String input) {
		this.pwd = input;
	}
	
	void setName(String input) {
		this.name = input;
	}
	
	void setAge(String input) {
		this.age = Integer.parseInt(input);
	}
	
	void setPhoneNum(String input) {
		this.phoneNum = input;
	}
		
	String getId() {
		return this.id;
	}
	
	String getPwd() {
		return this.pwd;
	}

	String getName() {
		return this.name;
	}
	
	int getAge() {
		return this.age;
	}
	
	String getPhoneNum() {
		return this.phoneNum;
	}

}
