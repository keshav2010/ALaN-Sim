package alanse.executor;

public class Token
{
	private String name=null;//Face value of token 
	private int type=Token.UNKNOWN;//type
	
	/*
	private boolean beginLOOP=false;
	private boolean endLOOP=beginLOOP;
	*/
	//Possible types of a Token
	public static final int 
			UNKNOWN=-1, 
			OPCODE=0,
			REGISTER=1, //REGISTER ADDRESS MODE
			MEMORY=2, // DIRECT ADDRESS MODE
			NUMBER=3; // IMMIDIATE MODE
	/*
			INCREMENT=4,
			DECREMENT=5;
	*/
	public Token(String n)
	{
		name=new String(n);
		type=UNKNOWN;
	}
	public Token(Token t) {
		// TODO Auto-generated constructor stub
		name=new String(t.getName());
		type=t.getType();
	}
	public void setType(int t)
	{
		type=t;
	}
	public String getName()
	{
		return name;
	}
	public int getType()
	{
		return type;
		/*
		switch(type)
		{
		case UNKNOWN : return null;
		case OPCODE : return new String("opcode");
		case REGISTER : return new String("register");
		case MEMORY : return new String("memory");
		case NUMBER : return new String("number");
		}
		return null;
		*/
	}
}
