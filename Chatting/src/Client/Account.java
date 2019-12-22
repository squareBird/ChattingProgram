package Client;

public class Account {

	String signitureNum;
	String id;
	String pwd;
	int age;
	String phoneNum;
	
	void setSignitureNum(String input) {
		this.signitureNum = input;
	}
	
	void setId(String input) {
		this.id = input;
	}
	
	void setPwd(String input) {
		this.pwd = input;
	}
	
	void setAge(String input) {
		this.age = Integer.parseInt(input);
	}
	
	void setPhoneNum(String input) {
		this.phoneNum = input;
	}
	
	String getSignitureNum() {
		return this.signitureNum;
	}
	
	String getId() {
		return this.id;
	}
	
	String getPwd() {
		return this.pwd;
	}

	int getAge() {
		return this.age;
	}
	
	String getPhoneNum() {
		return this.phoneNum;
	}

}
