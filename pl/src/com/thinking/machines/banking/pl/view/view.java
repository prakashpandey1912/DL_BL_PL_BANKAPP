import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.thinking.machines.banking.pl.model.*;
import com.thinking.machines.banking.pl.model.exceptions.*;
import com.thinking.machines.banking.bl.*;
import com.thinking.machines.banking.bl.exceptions.*;
import com.thinking.machines.banking.bl.pojo.*;
import com.thinking.machines.banking.bl.interfaces.*;
import java.util.*;
class MyModel extends JFrame implements ActionListener
{
AccountModel m1;
AccountInterface accountInterface;
private String vFirstName,vLastName,vGender,vPANNumber;
private int vAccountNumber;
private JLabel firstNameJLabel,lastNameJLabel,accountNumberJLabel,PANNumberJLabel,sexJLabel;
private JTextField firstName,lastName,accountNumber,PANNumber,gender;
private JButton add,delete,update;
MyModel()
{
super("testcase");
firstNameJLabel=new JLabel("First Name");
lastNameJLabel=new JLabel("Last Name");
accountNumberJLabel=new JLabel("AccountNumber");
PANNumberJLabel=new JLabel("PANNumber");
sexJLabel=new JLabel("Gender");
firstName=new JTextField(20);
lastName=new JTextField(20);
accountNumber=new JTextField(20);
PANNumber=new JTextField(20);
gender=new JTextField(1);
add=new JButton("Add");
delete=new JButton("Delete");
update=new JButton("Update");
add.addActionListener(this);
delete.addActionListener(this);
update.addActionListener(this);
setLayout(new FlowLayout());
add(firstNameJLabel);
add(firstName);
add(lastNameJLabel);
add(lastName);
add(sexJLabel);
add(gender);
add(accountNumberJLabel);
add(accountNumber);
add(PANNumberJLabel);
add(PANNumber);
add(add);
add(delete);
add(update);
setLocation(10,10);
setVisible(true);
}
public void actionPerformed(ActionEvent e) 
{
try
{
m1=new AccountModel();
}
catch(ProcessException me)
{
System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}
accountInterface=new Account();
if(e.getSource()==add)
{
vFirstName=firstName.getText();
vLastName=lastName.getText();
vGender=gender.getText();
vPANNumber=PANNumber.getText();
accountInterface.setFirstName(vFirstName);
accountInterface.setLastName(vLastName);
accountInterface.setGender(vGender);
accountInterface.setPANNumber(vPANNumber);
try
{
m1.add(accountInterface);
}catch( ModelException me)
{

System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}
catch( ProcessException me)
{

System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}
catch(  ValidationException me)
{

System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}

}
if(e.getSource()==delete)
{
vAccountNumber=Integer.parseInt(accountNumber.getText());
try
{
m1.delete(vAccountNumber);
}catch( ModelException me)
{

System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}
catch( ProcessException me)
{

System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}
catch(  ValidationException me)
{

System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}


}
if(e.getSource()==update)
{
vAccountNumber=Integer.parseInt(accountNumber.getText());
vFirstName=firstName.getText();
vLastName=lastName.getText();
vGender=gender.getText();
vPANNumber=PANNumber.getText();
accountInterface.setAccountNumber(vAccountNumber);
accountInterface.setFirstName(vFirstName);
accountInterface.setLastName(vLastName);
accountInterface.setGender(vGender);
accountInterface.setPANNumber(vPANNumber);
try
{
m1.update(accountInterface);
}catch( ModelException me)
{

System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}
catch( ProcessException me)
{

System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}
catch(  ValidationException me)
{

System.out.println("hellooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
}
}
}



}
class view
{
public static void main(String gg[])
{
MyModel m=new MyModel();
}
}







