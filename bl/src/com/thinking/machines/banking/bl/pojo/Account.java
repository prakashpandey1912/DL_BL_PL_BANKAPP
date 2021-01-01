package com.thinking.machines.banking.bl.pojo;
import com.thinking.machines.banking.bl.interfaces.*;

public class Account implements AccountInterface
{
private int accountNumber;
private String firstName;
private String lastName;
private String panNumber;
private String gender;
public Account()
{
accountNumber=0;
firstName="";
lastName="";
gender="";
panNumber="";
}
public void setAccountNumber(int accountNumber)
{
this.accountNumber=accountNumber;
}
public int getAccountNumber()
{
return accountNumber;
}
public void setFirstName(String firstName)
{
this.firstName=firstName;
}
public String getFirstName()
{
return firstName;
}
public void setLastName(String lastName)
{
this.lastName=lastName;
}
public String getLastName()
{
return lastName;
}
public void setGender(String gender)
{
this.gender=gender;
}
public String getGender()
{
return gender;
}
public void setPANNumber(String panNumber)
{
this.panNumber=panNumber;
}
public String getPANNumber()
{
return panNumber;
}
public boolean equals(Object object)
{
if (object==null) return false;
if(!(object instanceof AccountInterface)) return false;
AccountInterface other =(AccountInterface) object;
return this.accountNumber==other.getAccountNumber();
}

public int compareTo(AccountInterface other)
{
return this.accountNumber-other.getAccountNumber();
}

public int hashCode()
{
return accountNumber;
}
}