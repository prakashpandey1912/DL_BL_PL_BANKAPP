package com.thinking.machines.banking.pl.model;
import com.thinking.machines.banking.pl.model.exceptions.*;
import javax.swing.table.*;
import java.util.*;
import com.thinking.machines.banking.bl.*;
import com.thinking.machines.banking.bl.exceptions.*;
import com.thinking.machines.banking.bl.events.*;
import com.thinking.machines.banking.bl.interfaces.*;
import com.thinking.machines.banking.bl.pojo.*;
public class AccountModel extends AbstractTableModel implements AccountListener
{
private AccountManager accountManager;
private List<AccountInterface> accounts;
private String title[]={"S.No.","A/c No.","First Name"};
public AccountModel() throws ProcessException
{
this.accountManager=new AccountManager();
this.populateDataStructure();
this.addListeners();
}
private void populateDataStructure()
{
try
{
accounts=accountManager.getAll();
}catch(ProcessException processException)
{
accounts=new LinkedList<>();
}
}
private void addListeners()
{
this.accountManager.addAccountListener(this);
}
public void add(AccountInterface account) throws ModelException,ProcessException,ValidationException
{
accountManager.add(account);
}
public void update(AccountInterface account) throws ModelException,ProcessException,ValidationException
{
accountManager.update(account);
}
public void delete(int accountNumber) throws ModelException,ProcessException,ValidationException
{
accountManager.delete(accountNumber);
}
public int getColumnCount()
{
return this.title.length;
}
public int getRowCount()
{
return this.accounts.size();
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public Class getColumnClass(int columnIndex)
{
if(columnIndex==0 || columnIndex==1)
{
return Integer.class;
}
return String.class; 
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0) return rowIndex+1;
if(columnIndex==1) return accounts.get(rowIndex).getAccountNumber();
AccountInterface account=accounts.get(rowIndex);
String name=account.getFirstName()+" "+account.getLastName();
return name;
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public void accountAdded(AccountEvent ev)
{
System.out.println("hello");
}
public void accountUpdated(AccountEvent ev)
{

}
public void accountDeleted(AccountEvent ev)
{

}
}