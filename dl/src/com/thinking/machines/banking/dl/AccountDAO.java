package com.thinking.machines.banking.dl;
import com.thinking.machines.banking.dl.exceptions.*;
import com.thinking.machines.banking.dl.interfaces.*;
import com.thinking.machines.seq.exceptions.*;
import com.thinking.machines.seq.interfaces.*;
import com.thinking.machines.seq.*;
import java.util.*;
import java.io.*;
public class AccountDAO implements AccountDAOInterface
{
private static final String dataFile="account.data";
public void add(AccountDTOInterface accountDTOInterface) throws DAOException
{
try
{
int vAccountNumber;
String vFirstName;
String vLastName;
String vGender;
String vPanNumber;
File file=new File(dataFile);
if (file.exists())
{
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPanNumber=randomAccessFile.readLine();
if(vPanNumber.equalsIgnoreCase(accountDTOInterface.getPANNumber()))
{
randomAccessFile.close();
throw new DAOException("Pan Number :"+vPanNumber+" exist");
}
}
randomAccessFile.close();
}
try
{
SequenceGeneratorInterface sequenceGenerator=SequenceGenerator.getInstance();
vAccountNumber=sequenceGenerator.getNext("account");
}catch(SequenceException sequenceException)
{
sequenceException.printStackTrace();
throw new DAOException("Unable to generator sequence");
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
randomAccessFile.seek(randomAccessFile.length());
randomAccessFile.writeBytes(String.valueOf(vAccountNumber));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(accountDTOInterface.getFirstName()+"\n");
randomAccessFile.writeBytes(accountDTOInterface.getLastName()+"\n");
randomAccessFile.writeBytes(accountDTOInterface.getGender()+"\n");
randomAccessFile.writeBytes(accountDTOInterface.getPANNumber()+"\n");
accountDTOInterface.setAccountNumber(vAccountNumber);
randomAccessFile.close();
}catch(IOException ioException)
{
ioException.printStackTrace();
throw new DAOException(ioException.getMessage());
}
}
public void update(AccountDTOInterface accountDTOInterface) throws DAOException
{
try
{
int vAccountNumber;
String vFirstName;
String vLastName;
String vGender;
String vPANNumber;
int iAccountNumber=accountDTOInterface.getAccountNumber();
File file = new File(dataFile);
if(file.exists()==false)
{
throw new DAOException("Invalid account number" + iAccountNumber);
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid account number" + iAccountNumber);
}
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());	
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(vAccountNumber==iAccountNumber)
{
found=true;
break;
}
}
if(!found)
{
randomAccessFile.close();
throw new DAOException("Invalid account number" + iAccountNumber);
}
randomAccessFile.seek(0);
String iPANNumber=accountDTOInterface.getPANNumber();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());	
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(vPANNumber.equalsIgnoreCase(iPANNumber) && vAccountNumber!=iAccountNumber)
{
randomAccessFile.close();
throw new DAOException(" PANnumber exists :" + iPANNumber);
}
}
randomAccessFile.seek(0);
File tmpFile=new File("tmp.tmp");  
if(tmpFile.exists()) tmpFile.delete(); 
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw"); 
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine()); 
vFirstName=randomAccessFile.readLine(); 
vLastName=randomAccessFile.readLine(); 
vGender=randomAccessFile.readLine(); 
vPANNumber=randomAccessFile.readLine(); 
if(vAccountNumber!=iAccountNumber) 
{ 
tmpRandomAccessFile.writeBytes(vAccountNumber+"\n"); 
tmpRandomAccessFile.writeBytes(vFirstName+"\n"); 
tmpRandomAccessFile.writeBytes(vLastName+"\n"); 
tmpRandomAccessFile.writeBytes(vGender+"\n"); 
tmpRandomAccessFile.writeBytes(vPANNumber+"\n"); 
} 
else 
{ 
tmpRandomAccessFile.writeBytes(accountDTOInterface.getAccountNumber()+"\n"); 
tmpRandomAccessFile.writeBytes(accountDTOInterface.getFirstName()+"\n"); 
tmpRandomAccessFile.writeBytes(accountDTOInterface.getLastName()+"\n"); 
tmpRandomAccessFile.writeBytes(accountDTOInterface.getGender()+"\n"); 
tmpRandomAccessFile.writeBytes(accountDTOInterface.getPANNumber()+"\n"); 
} 
}
randomAccessFile.seek(0); 
tmpRandomAccessFile.seek(0); 
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length()) 
{ 
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n"); 
} 
randomAccessFile.setLength(tmpRandomAccessFile.length()); 
tmpRandomAccessFile.setLength(0); 
randomAccessFile.close(); 
tmpRandomAccessFile.close(); 
}
catch(IOException ioException) 
{ 
ioException.printStackTrace(); 
throw new DAOException(ioException.getMessage());  
} 
}
public void delete(int accountNumber) throws DAOException
{
try
{
int vAccountNumber;
String vFirstName;
String vLastName;
String vGender;
String vPANNumber;
File file = new File(dataFile);
if(file.exists()==false)
{
throw new DAOException("Invalid account number" + accountNumber);
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid account number" + accountNumber);
}
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());	
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(vAccountNumber==accountNumber)
{
found=true;
break;
}
}
if(!found)
{
randomAccessFile.close();
throw new DAOException("Invalid account number" + accountNumber);
}
randomAccessFile.seek(0);
File tmpFile=new File("tmp.tmp");  
if(tmpFile.exists()) tmpFile.delete(); 
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw"); 
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine()); 
vFirstName=randomAccessFile.readLine(); 
vLastName=randomAccessFile.readLine(); 
vGender=randomAccessFile.readLine(); 
vPANNumber=randomAccessFile.readLine(); 
if(vAccountNumber!=accountNumber) 
{ 
tmpRandomAccessFile.writeBytes(vAccountNumber+"\n"); 
tmpRandomAccessFile.writeBytes(vFirstName+"\n"); 
tmpRandomAccessFile.writeBytes(vLastName+"\n"); 
tmpRandomAccessFile.writeBytes(vGender+"\n"); 
tmpRandomAccessFile.writeBytes(vPANNumber+"\n"); 
} 
}
randomAccessFile.seek(0); 
tmpRandomAccessFile.seek(0); 
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length()) 
{ 
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n"); 
} 
randomAccessFile.setLength(tmpRandomAccessFile.length()); 
tmpRandomAccessFile.setLength(0); 
randomAccessFile.close(); 
tmpRandomAccessFile.close(); 
}
catch(IOException ioException) 
{ 
ioException.printStackTrace(); 
throw new DAOException(ioException.getMessage());  
} 
}
public AccountDTOInterface getByAccountNumber(int accountNumber) throws DAOException
{
try
{
File file = new File(dataFile);
if(file.exists()==false)
{
throw new DAOException("Invalid account number" + accountNumber);
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid account number" + accountNumber);
}
int vAccountNumber;
String vFirstName;
String vLastName;
String vGender;
String vPANNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());	
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(vAccountNumber==accountNumber)
{
AccountDTOInterface accountDTOInterface=new AccountDTO(); 
accountDTOInterface.setAccountNumber(vAccountNumber); 
accountDTOInterface.setFirstName(vFirstName); 
accountDTOInterface.setLastName(vLastName); 
accountDTOInterface.setGender(vGender); 
accountDTOInterface.setPANNumber(vPANNumber); 
randomAccessFile.close(); 
return accountDTOInterface; 
}
}
randomAccessFile.close(); 
throw new DAOException("Invalid account number : "+accountNumber); 
}catch(IOException ioException) 
{ 
ioException.printStackTrace(); 
throw new DAOException(ioException.getMessage());  
}
}
public AccountDTOInterface getByPANNumber(String panNumber) throws DAOException
{
try
{
File file = new File(dataFile);
if(file.exists()==false)
{
throw new DAOException("Invalid panNumber" + panNumber);
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid panNumber" + panNumber);
}
int vAccountNumber;
String vFirstName;
String vLastName;
String vGender;
String vPANNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
vAccountNumber=Integer.parseInt(randomAccessFile.readLine());	
vFirstName=randomAccessFile.readLine();
vLastName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine();
vPANNumber=randomAccessFile.readLine();
if(vPANNumber.equalsIgnoreCase(panNumber))
{
AccountDTOInterface accountDTOInterface=new AccountDTO(); 
accountDTOInterface.setAccountNumber(vAccountNumber); 
accountDTOInterface.setFirstName(vFirstName); 
accountDTOInterface.setLastName(vLastName); 
accountDTOInterface.setGender(vGender); 
accountDTOInterface.setPANNumber(vPANNumber); 
randomAccessFile.close(); 
return accountDTOInterface; 
}
}
randomAccessFile.close(); 
throw new DAOException("Invalid panNumber : "+panNumber); 
}catch(IOException ioException) 
{ 
ioException.printStackTrace(); 
throw new DAOException(ioException.getMessage());  
}
}
public List<AccountDTOInterface> getAll() throws DAOException
{
try
{
File file=new File(dataFile);
if(file.exists()==false) throw new DAOException("NO account");
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0) 
{
randomAccessFile.close();
throw new DAOException("NO account");
}
AccountDTOInterface accountDTOInterface=null;
List<AccountDTOInterface> list=new LinkedList<>();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
accountDTOInterface=new AccountDTO();
accountDTOInterface.setAccountNumber(Integer.parseInt(randomAccessFile.readLine()));
accountDTOInterface.setFirstName(randomAccessFile.readLine());
accountDTOInterface.setLastName(randomAccessFile.readLine());
accountDTOInterface.setGender(randomAccessFile.readLine());
accountDTOInterface.setPANNumber(randomAccessFile.readLine());
list.add(accountDTOInterface);
}
randomAccessFile.close();
return list;
}catch(IOException ioException)
{
ioException.printStackTrace();
throw new DAOException(ioException.getMessage());
}
}
public int getCount() throws DAOException
{
try
{
File file = new File(dataFile);
if(file.exists()==false)
{
return 0;
}
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
int count=0; 
while(randomAccessFile.getFilePointer()<randomAccessFile.length()) 
{ 
randomAccessFile.readLine(); 
count++; 
} 
randomAccessFile.close(); 
return count/5; 
}catch(IOException ioException) 
{ 
ioException.printStackTrace(); 
throw new DAOException(ioException.getMessage()); 
} 
}
}