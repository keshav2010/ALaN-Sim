package alanse.executor;

import java.util.ArrayList;

/*
 * SyntaxAnalyzer receives tokenList and performs following testing
 * 1. Makes Sure no UNKNOWN Token is present in TOKENLIST
 * 2. Makes Sure the instruction structure is correct ,
 * 			example : ADD R0,R1 is valid instruction , and 
 * 					  R0, ADD, R1 is invalid instruction 
 * 
 */

/*
 * getTokenList(ArrayList<Token>) : feed a tokenized Instruction to Analyzer
 * isValid() : checks for validity of tokenizedList feeded to Analyzer
 * appendTokenList() : pass on the tokenized instruction to InstructionSheet to be processed later by executor
 */
public class SyntaxAnalyzer {
	private static ArrayList<Token> instructionTokens=null;
	
	//Flags : for 1st LEVEL SYNTAX CHECKING
	
	//this initialization during declaration is important if no object of this class is created, and directly static methods are called.
	private static boolean containOPCODE=false;
	private static boolean containADDRESS=false;
	private static boolean containNUMBER=false;
	
	
	public SyntaxAnalyzer()//(ArrayList<Token> tokenList)
	{
		containOPCODE=false;
		containADDRESS=false;
		containNUMBER=false;
		//instructionTokens=new ArrayList<Token>(tokenList);
	}
	public static void getTokenList(ArrayList<Token> tokenlist)
	{
		containOPCODE=containADDRESS=containNUMBER=false; //reseting values
		if(instructionTokens!=null)
		{
			instructionTokens.clear();
			instructionTokens=null;
		}
		instructionTokens=new ArrayList<Token>(tokenlist);
	}
	public static boolean isValid()
	{
		if(instructionTokens==null)
		{
			System.out.println("ERROR : SyntaxAnalyzer failed to check validity due to lack of instruction.");
			return false;
		}
		boolean valid=true;
		
		//checking  for unknown tokens, and set flags for known tokens
		for(int i=0;i<instructionTokens.size();i++)//TODO : assuming size>0 for now
		{
			int type=instructionTokens.get(i).getType();
			if(type==Token.UNKNOWN)
			{
				valid=false;
			}
			else if(type==Token.OPCODE)
			{
				if(containOPCODE)
				{
					valid=false; //can't have more than 1 opcode in single instruction (add,div,sub,mov,mod)
					break;
				}
				containOPCODE=true;
			}
			else if(type==Token.MEMORY || type==Token.REGISTER)
			{
				containADDRESS=true;
			}
			else if(type==Token.NUMBER)
			{
				containNUMBER=true;
			}
		}
		
		/*if if-condition performs following checks 
		 * > Check if OPCODE is present in Instruction (must)
		 * > proceed to check if instruction contains ADDRESS or NUMBERS 
		 * > 
		 */
		if(containOPCODE) //check if contains OPCODE (add,sub,div,mul,mov)
		{
			valid = containOPCODE && ( (containADDRESS&&containNUMBER) || (containADDRESS) );//checks if instruction contains atleast an opcode and combination of aither just numbers or both address  & number
			
			//below if checks if token-type at start is NOT OPCODE or token type at END is a CONSTANT NUMBER, THEN return false
			if(instructionTokens.get(0).getType()!=Token.OPCODE || instructionTokens.get(instructionTokens.size()-1).getType() == Token.NUMBER || !valid )
			{
				System.out.println("Invalid Syntax (SYNTAXANALYZER error)");
				return false;
			}
		}
		else
		{
			System.out.println("[ERROR]SyntaxAnalyzer : OPCODE not mentioned.");
		}
		return valid;
		
	}
	public static void appendTokenList() {
		// TODO when called, this method will append instructionsToken list into another ArrayList which holds all such instruction
		//and eventually used by Executor to simulate a behaviour
		InstructionSheet.putInstruction(instructionTokens); //feed data to sheet 
		
	}
}
