package alanse.executor;

import java.util.ArrayList;

/*
 * remarks : the code may not be perfect, i had some doubts regarding object-referencing in java 
 * i know it but still to be on safer side, often you might encounter code that seems to be useless, try not to 
 * change such pieces of code.
 */
/*
 * Lexer Class Description : Accepts 1 word (token.getName()) and assigns a meaning to it and forms a List of Tokens with meaning
 * in the order they received. 
 * 
 * Lexer(Token) : Construtor requires a token variable,
 * finalizeList() : returns a list containing words (marked as UNKNOWN)
 * identifyToken(Token) *PRIVATE 
 */
public class Lexer {
	//private	static TokenList tokenlist;
	//public static ArrayList<Token> tokenList=null;
	public static ArrayList<Token> tokenList=null;
	private static ArrayList<Token> copy=null;
	/*
	public Lexer(Token t)
	{
		if(copy!=null){
			copy.clear();
			copy=null;
		}
		t=new Token(identifyToken(t));
		if(tokenList!=null)
			tokenList.add(t);
		else
		{
			//tokenList=new ArrayList<Token>();
			tokenList=new ArrayList<Token>();
			tokenList.add(t);
		}
	}
	*/
	public static void getToken(Token token)
	{
		if(copy!=null){
			copy.clear();
			copy=null;
		}
		token = new Token(identifyToken(token));
		
		if(tokenList==null)
			tokenList=new ArrayList<Token>();
		
		tokenList.add(token);
		
	}
	
	public static /*ArrayList<Token>*/void finalizeList() 
	{
		copy=new ArrayList<Token>(tokenList);
		
		tokenList.clear(); //cleared to accept new tokenList and to make sure no structure in idle state should occupy memory
		tokenList=null;//
		
		SyntaxAnalyzer.getTokenList(copy);
		
		if(SyntaxAnalyzer.isValid()) //checks validity of list passed above (copy)
		{
			//if valid, build a arraylist which stores tokenizedInstruction lists for each index i
			SyntaxAnalyzer.appendTokenList();
		}
		else
		{
			System.out.println("Invalid Instruction : LEXER[ERROR] stopped further");
			return;
		}
		//SyntaxAnalyzer.feedTokenList(copy);
		//return copy;
		// Lexer should now pass this TokenList (copy) to SyntaxAnalyzer
	}
	private static Token identifyToken(Token t)
	{
		//first switch statement checks of token is either opCode, Register or Memory address identifier
		switch(t.getName().toUpperCase().trim())
		{
		//the Op-Codes :
		/*
		 * ADD = addition
		 * MOV = move data from one address to another
		 * SUB = subtraction
		 * MUL = multiplication
		 * DIV = division
		 * MOD = remainder
		 */
		case "ADD":
		case "MOV": //Not Accepting MOV Command at the moment , Code inside Executor is not considering MOV right now
		case "SUB":
		case "MUL":
		case "MOD":
		case "DIV":
			t.setType(Token.OPCODE);
			return t;
			
		//remark : R0 to R6 are general-purpose Registers
		case "R0":
		case "R1":
		case "R2":
		case "R3":
		case "R4":
		case "R5":
		case "R6":
			t.setType(Token.REGISTER);
			return t;
		//remark "NAMES OF IDENTIFIERS ARE PRE-DEFINED AND NOW ALLOWED TO BE CUSTOM USER BASED AT THE MOMENT"
		case "LOCA":
		case "LOCB":
		case "LOCC":
		case "LOCD":
		case "LOCE":
		case "LOCF":
		case "LOCG":
			t.setType(Token.MEMORY);
			return t;
		default:break;
		}
		
		//Check if Number/Constant
		boolean isNumber=false;
		try
		{
			Integer.parseInt(t.getName());
			isNumber=true;
		}catch(NumberFormatException e)
		{
			isNumber=false;
		}
		if(isNumber)
		{
			t.setType(Token.NUMBER);
		}
		
		else //not NUMBER,OPCODE,REGISTER,MEMORY, mark as UNKNOWN and pass it to syntax checker to resolve the issue by generating error
		{
			t.setType(Token.UNKNOWN); //by default Token is marked as UNKNOWN, this statement is not required but just for sake 
			//of being on safer side, do not remove this
		}
		return t;
	}
}
