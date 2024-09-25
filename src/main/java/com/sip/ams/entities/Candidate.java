package com.sip.ams.entities;

public class Candidate {

private int id;
private String name;
private String email;
private String adress;

public Candidate()
{}

@Override
public String toString() {
return "Candidate [id="+ id+ ", name=" + name + ", email=" +

email + ", adress=" + adress + "]";
}

public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public Candidate(int id,String name, String email, String adress) {
super();
this.id = id;
this.name = name;
this.email = email;
this.adress = adress;
}

public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public String getEmail() {
return email;
}
public void setEmail(String email) {
this.email = email;
}
public String getAdress() {

return adress;
}
public void setAdress(String adress) {
this.adress = adress;
}
}