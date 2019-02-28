package no.noroff.SqlWebApp;

public class UserInput {
    String value;
     String attribute;
    String number;

    public void setValue(String v){value = v;}
    public void setAttribute(String v){attribute = v;}
    public void setNumber(String v){number = v;}

    public String getValue(){return value;}
    public String getAttribute(){return attribute;}
    public String getNumber(){return number;}

}
