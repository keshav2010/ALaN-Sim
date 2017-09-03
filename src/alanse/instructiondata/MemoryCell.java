package alanse.instructiondata;

public class MemoryCell //stores name for cell and the integer type value residing in it
{
	public String name=null;
	private int value=0;
	public MemoryCell(String nm)
	{
		name=new String(nm);
		value=0;
	}
	public MemoryCell(String nm,int val)
	{
		name=new String(nm);
		value=val;
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
