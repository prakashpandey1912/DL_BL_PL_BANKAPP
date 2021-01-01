package com.thinking.machines.banking.bl.events;
import com.thinking.machines.banking.bl.interfaces.*;
public class AccountEvent 
{
private AccountInterface newAccount;
private AccountInterface oldAccount;
public AccountEvent()
{
this.newAccount=null;
this.oldAccount=null;
}
public void setNewAccount(AccountInterface newAccount)
{
 this.newAccount=newAccount;
}
public void setOldAccount(AccountInterface oldAccount)
{
 this.oldAccount=oldAccount;
}
public AccountInterface getNewAccount()
{
return this.newAccount;
}
public AccountInterface getOldAccount()
{
return this.oldAccount;
}
}