package com.thinking.machines.banking.dl.interfaces;
public interface AccountDTOInterface extends java.io.Serializable,Comparable<AccountDTOInterface>
{
public void setAccountNumber(int accountNumber);
public int getAccountNumber();
public void setFirstName(String firstName);
public String getFirstName();
public void setLastName(String lastName);
public String getLastName();
public void setGender(String gender);
public String getGender();
public void setPANNumber(String panNumber);
public String getPANNumber();
}