package com.thinking.machines.banking.bl.exceptions;
import java.util.*;
import java.io.*;
public class ValidationException extends Exception implements Serializable 
{
private Map<String,String> exceptions= new HashMap<>();
public void addException(String property,String exception)
{
 this.exceptions.put(property,exception);
}
public List<String> getException()
{
return new LinkedList(exceptions.keySet());
}
public String getByProperty(String property)
{
return exceptions.get(property);
}
public boolean hasException()
{
return exceptions.size()>0;
}
public int countException()
{
return exceptions.size();
}
}