package com.thinking.machines.banking.bl.exceptions;

public class ProcessException extends Exception implements java.io.Serializable
{
public ProcessException(String massege)
{
super(massege);
}
}