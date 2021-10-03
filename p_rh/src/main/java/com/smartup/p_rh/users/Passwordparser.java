package com.smartup.p_rh.users;

public class Passwordparser {
Integer id;
String oldpass;
String newpassword;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getoldpass() {
	return oldpass;
}
public void setoldpass(String oldpass) {
	this.oldpass = oldpass;
}
public String getnewpassword() {
	return newpassword;
}
public void setnewpassword(String newpassword) {
	this.newpassword = newpassword;
}
public Passwordparser() {
	super();
	// TODO Auto-generated constructor stub
}
public Passwordparser(Integer id, String oldpass, String newpassword) {
	super();
	this.id = id;
	this.oldpass = oldpass;
	this.newpassword = newpassword;
}
@Override
public String toString() { 
	return "Passwordparser [id=" + id + ", oldpass=" + oldpass + ", newpassword=" + newpassword + "]";
}

}
