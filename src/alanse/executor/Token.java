package alanse.executor;
/*
 * Token represent a single word in an instruction that may or may not be a keyword
 * 
 */
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
			UNKNOWN=-1,  //not a keyword
			OPCODE=0, //an opcode, like ADD, SUB, 
			REGISTER=1, //REGISTER ADDRESS MODE
			MEMORY=2, // DIRECT ADDRESS MODE
			NUMBER=3; // IMMIDIATE MODE
	/*
			INCREMENT=4,
			DECREMENT=5;
	*/
	public Token(String n) //by default each token is treated as unknown type
	{
		name=new String(n);
		type=UNKNOWN;
	}
	public Token(Token t) { //duplicate copy
		name=new String(t.getName());
		type=t.getType();
	}
	public void setType(int t) //set the type for a token, either its OPCODE, or register or memory location or a constant
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
