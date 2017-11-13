package alanse.executor;

import java.util.ArrayList;
//Tokenizer --> Lexer 

/*
 * remarks : the code is poorly written 
 */
/*
 * Lexer Class Description : Accepts 1 word (token.getName()) and assigns a meaning to it and forms a List of Tokens with meaning
 * in the order they received. 
 * 
 * Lexer(Token) : Constructor requires a token variable,
 * finalizeList() : returns a list containing words (marked as UNKNOWN)
 * identifyToken(Token) *PRIVATE 
 */
public class Lexer {
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
		token.setType( identifyToken(token) );//assign meaning to token
		
		if(tokenList==null)
			tokenList=new ArrayList<Token>();
		
		tokenList.add(token);//add it to token list
		
	}
	/*
	 * finalizeList() method is called by Tokenizer for each instruction
	 */
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
	private static int identifyToken(Token t)
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
			return Token.OPCODE;
			//t.setType(Token.OPCODE);
			
		//remark : R0 to R6 are general-purpose Registers
		case "R0":
		case "R1":
		case "R2":
		case "R3":
		case "R4":
		case "R5":
		case "R6":
			return Token.REGISTER;
		//remark "NAMES OF IDENTIFIERS ARE PRE-DEFINED AND NOW ALLOWED TO BE CUSTOM USER BASED AT THE MOMENT"
		case "LOCA":
		case "LOCB":
		case "LOCC":
		case "LOCD":
		case "LOCE":
		case "LOCF":
		case "LOCG":
			return Token.MEMORY;
			
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
			return Token.NUMBER;
		}
		return Token.UNKNOWN;
	}
}
