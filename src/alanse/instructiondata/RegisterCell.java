package alanse.instructiondata;

public class RegisterCell
{
	private String name=null;
	private int value=0;
	public RegisterCell(int val)
	{
		value=val;
	}
	public RegisterCell(String nm,int val){
		name=new String(nm);
		value=val;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String nm)
	{
		name=new String(nm);
	}
	public int getValue()
	{
		return value;
	}
	public void setValue(int val)
	{
		value=val;
	}
	
}