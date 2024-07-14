package com.PatientMedication.Model;


import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "appointments")
public class Appointments {

    private String name;
    private int age;
    private String dob;
    private String sex;
    private String email;
    private String phone;
    private String date;
    private String time;
    private String reason;
    private String dateTimeIdentifier;
          
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDateTimeIdentifier() {
        return date + "-" + time;
    }

    public void setDateTimeIdentifier(String dateTimeIdentifier) {
        this.dateTimeIdentifier = dateTimeIdentifier;
    }
}