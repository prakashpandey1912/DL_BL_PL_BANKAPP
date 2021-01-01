package com.thinking.machines.banking.bl; 
import com.thinking.machines.banking.bl.interfaces.*; 
import com.thinking.machines.banking.bl.exceptions.*; 
import com.thinking.machines.banking.bl.pojo.*; 
import com.thinking.machines.banking.bl.events.*; 
import com.thinking.machines.banking.dl.interfaces.*; 
import com.thinking.machines.banking.dl.exceptions.*; 
import com.thinking.machines.banking.dl.*; 
import java.util.*; 
public class AccountManager implements AccountManagerInterface

{
private Map<Integer,AccountInterface>   accountNumberDictionary;
private Map<String,AccountInterface>   panNumberDictionary;
private List<AccountInterface> accountsList;
private List<AccountListener> accountsListener;

public AccountManager()
{
populateDataStructures();
}
private void populateDataStructures()
{
accountNumberDictionary= new  HashMap<Integer,AccountInterface>();
panNumberDictionary = new HashMap<String,AccountInterface>();
accountsList=new LinkedList<>();
accountsListener=new LinkedList<>();
AccountDAOInterface accountDAO=new AccountDAO();
List<AccountDTOInterface> daoAccounts;
String vFirstName;
String vLastName;
String vPANNumber;
String vGender;
int vAccountNumber;
try
{
daoAccounts=accountDAO.getAll();
AccountInterface account;
for(AccountDTOInterface dsAccount : daoAccounts)
{
vAccountNumber=dsAccount.getAccountNumber();
vFirstName=dsAccount.getFirstName();
vLastName=dsAccount.getLastName();
vGender=dsAccount.getGender();
vPANNumber=dsAccount.getPANNumber();
account= new Account();
account.setAccountNumber(vAccountNumber); 
account.setFirstName(vFirstName); 
account.setLastName(vLastName); 
account.setGender(vGender); 
account.setPANNumber(vPANNumber); 
accountsList.add(account); 
panNumberDictionary.put(vPANNumber.toUpperCase(),account); 
accountNumberDictionary.put(vAccountNumber,account);
}
Collections.sort(accountsList,new Comparator<AccountInterface>(){ 
public int compare(AccountInterface left,AccountInterface right) { 
String leftName=left.getFirstName()+" "+left.getLastName(); 
leftName=leftName.toUpperCase(); 
String rightName=right.getFirstName()+" "+right.getLastName(); 
rightName=rightName.toUpperCase(); 
return leftName.compareTo(rightName); 
} 
});
}catch(DAOException daoException)
{
daoException.getMessage();
}
}




public void add(AccountInterface account)throws ProcessException,ValidationException
{
int vAccountNumber; 
String vFirstName; 
String vLastName; 
String vGender; 
String vPANNumber; 
vFirstName=account.getFirstName(); 
vLastName=account.getLastName(); 
vGender=account.getGender(); 
vPANNumber=account.getPANNumber(); 
ValidationException validationException=new ValidationException(); 
if(vFirstName==null || vFirstName.trim().length()==0) 
{ 
validationException.addException("firstName","Required"); 
} 
else 
if(vFirstName.trim().length()>20) 
{ 
validationException.addException("firstName","Cannot exceed 20 characters"); 
} 
if(vLastName==null || vLastName.trim().length()==0) 
{ 
validationException.addException("lastName","Required"); 
}
else 
if(vLastName.trim().length()>20) 
{ 
validationException.addException("lastName","Cannot exceed 20 characters"); 
} 
if(vGender==null || (vGender.trim().equals("M")==false && vGender.trim().equals("F")==false)) 
{ 
if(vGender==null) validationException.addException("gender","Required"); 
else 
validationException.addException("gender","Required(M/F)"); 
} 
if(vPANNumber==null || vPANNumber.trim().length()==0) 
{ 
validationException.addException("panNumber","Required"); 
} 
else 
if(vPANNumber.trim().length()>10) 
{ 
validationException.addException("panNumber","Cannot exceed 10 characters"); 
} 
if(validationException.hasException()) 
throw validationException; 
if(panNumberDictionary.containsKey(vPANNumber.toUpperCase().trim())) 
{ 
validationException.addException("panNumber","Exists"); 
throw validationException; 
} 
try 
{
AccountDTOInterface accountDTO=new AccountDTO(); 
accountDTO.setFirstName(vFirstName.trim()); 
accountDTO.setLastName(vLastName.trim()); 
accountDTO.setGender(vGender); 
accountDTO.setPANNumber(vPANNumber.trim()); 
new AccountDAO().add(accountDTO); 
vAccountNumber=accountDTO.getAccountNumber(); 
account.setAccountNumber(vAccountNumber); 
AccountInterface dsAccount=getCopy(account); 
panNumberDictionary.put(vPANNumber.toUpperCase(),dsAccount); 
accountNumberDictionary.put(new Integer(vAccountNumber),dsAccount); 
if(accountsList.size()==0) 
{ 
accountsList.add(dsAccount); 
} 
else 
{ 
Comparator<AccountInterface> comparator=new Comparator<AccountInterface>()
{ public int compare(AccountInterface left,AccountInterface right) {
 String leftName=left.getFirstName().toUpperCase()+" "+left.getLastName().toUpperCase(); 
String rightName=right.getFirstName().toUpperCase()+" "+right.getLastName().toUpperCase(); 
return leftName.compareTo(rightName); } }; AccountInterface tmp=accountsList.get(0); 
if(comparator.compare(dsAccount,tmp)<=0) 
{ 
accountsList.add(0,dsAccount); 
} 
else 
{ 
tmp=accountsList.get(accountsList.size()-1); 
if(comparator.compare(dsAccount,tmp)>=0) 
{ accountsList.add(dsAccount); 
} else 
{  
int lb,ub,mid; 
lb=0; 
ub=accountsList.size()-1; 
int x; 
while(lb<=ub) 
{ 
mid=(int)((lb+ub)/2);
x=comparator.compare(accountsList.get(mid),dsAccount); 
if(x==0) 
{ 
accountsList.add(mid,dsAccount); 
break; 
} 
else 
if(x<0) 
{ 
lb=mid+1; 
} 
else 
if(x>0) 
{ 
if(comparator.compare(accountsList.get(mid-1),dsAccount)<=0) 
{ 
accountsList.add(mid,dsAccount); 
} 
else 
{ 
ub=mid-1; 
} 
} 
} 
} 
}
} 
AccountEvent ev=new AccountEvent(); 
ev.setNewAccount(getCopy(dsAccount)); 
for(AccountListener accountListener:accountsListener) 
{ 
accountListener.accountAdded(ev); 
} 
}catch(DAOException daoException) 
{ 
throw new ProcessException(daoException.getMessage()); 
} 


 
}

public void update(AccountInterface accountInterfae)throws ProcessException,ValidationException
{
throw new ProcessException("Not yet implemented"); 
}
public void delete(int accountNumber)throws ProcessException,ValidationException
{
throw new ProcessException("Not yet implemented"); 
}
public AccountInterface getByAccountNumber(int accountNumber)throws ProcessException,ValidationException
{
throw new ProcessException("Not yet implemented"); 
}

public AccountInterface getByPANNumber(String PANNumber)throws ProcessException,ValidationException
{
throw new ProcessException("Not yet implemented"); 
}


public List<AccountInterface>getAll()throws ProcessException
{
List <AccountInterface> all=new LinkedList<>();
for(AccountInterface getAllAccount : accountsList)
{
all.add(getCopy(getAllAccount));
}
return all;
} 



public int getCount()throws ProcessException
{
return accountsList.size(); 
}


public void addAccountListener(AccountListener accountListener) 
{
accountsListener.add(accountListener);  
}





private AccountInterface getCopy(AccountInterface account) 
{ 
AccountInterface copyOfAccount=new Account(); 
copyOfAccount.setAccountNumber(account.getAccountNumber()); 
copyOfAccount.setFirstName(account.getFirstName()); 
copyOfAccount.setLastName(account.getLastName()); 
copyOfAccount.setGender(account.getGender()); 
copyOfAccount.setPANNumber(account.getPANNumber()); 
return copyOfAccount; 
} 





}///
 