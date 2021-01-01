package com.thinking.machines.banking.bl.interfaces;

import com.thinking.machines.banking.bl.exceptions.*;

import com.thinking.machines.banking.bl.events.*;
import java.util.*;

public interface AccountManagerInterface

{

public void add(AccountInterface accountInterface)throws ProcessException,ValidationException;

public void update(AccountInterface accountInterfae)throws ProcessException,ValidationException;

public void delete(int accountNumber)throws ProcessException,ValidationException;

public AccountInterface getByAccountNumber(int accountNumber)throws ProcessException,ValidationException;

public AccountInterface getByPANNumber(String PANNumber)throws ProcessException,ValidationException;

public List<AccountInterface>getAll()throws ProcessException;

public int getCount()throws ProcessException;
public void addAccountListener(AccountListener accountListener); 
}