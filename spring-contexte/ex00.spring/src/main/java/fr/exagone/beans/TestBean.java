package fr.exagone.beans;

public class TestBean {

	private String test;

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getTest();
	}
}
