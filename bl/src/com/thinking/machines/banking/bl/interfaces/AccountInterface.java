package com.thinking.machines.banking.bl.interfaces;
public interface AccountInterface extends java.io.Serializable,Comparable<AccountInterface>
{
public void setAccountNumber(int accountNumber);
public int getAccountNumber();
public void setFirstName(String firstName);
public String getFirstName();
public void setLastName(String lastNumber);
public String getLastName();
public void setGender(String gender);
public String getGender();
public void setPANNumber(String panNumber);
public String getPANNumber();
}