package com.thinking.machines.banking.dl.exceptions;

public class DAOException extends Exception implements java.io.Serializable
{
public DAOException(String massege)
{
super(massege);
}
}