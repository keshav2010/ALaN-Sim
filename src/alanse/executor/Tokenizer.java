package alanse.executor;

import java.util.ArrayList;
/*
 * 1. feedInstruction(String) : feed single line
 * 2. feedAll(String[]) : feed entire instruction sheet
 * 3. start() : start the tokenization process on the data fed
 *
 */
public class Tokenizer {
	private static String[] instructionList;
	private static String instructionLine;
	private static boolean useList; //checks if user is feeding a single instruction or entire instructionList to Tokenizer
	//public static ArrayList<Token>tokenList;//the TokenList containing single instruction in Tokenized Form 
	private static Token token;
	

	public Tokenizer()
	{
		useList=false;
		//tokenList=null;
		instructionList=null;
		instructionLine=null;
		token=null;
	}
	public static void feedInstruction(String line) throws Exception
	{
		useList=false;
		instructionLine=new String(line);
		instructionList=null;
		if(instructionLine==null)
			throw new Exception("Tokenizer didn't Received List");
	}
	public static void feedAll(String[] data) throws Exception
	{
		useList=true;
		instructionList=data;
		instructionLine=null;
		if(instructionList==null)
			throw new Exception("Tokenizer didn't Received List");
	}
	public static void start() throws Exception
	{
		if(useList)
		{
			if(instructionList==null)
				throw new Exception("Invalid Call to start");
			
			for(int i=0;i<instructionList.length;i++)
			{

				String word=new String("");
				String instruction=new String(instructionList[i]);//extracting each line
				for(int j=0;j<instruction.length();j++)
				{
					if(instruction.charAt(j)==' ')
					{
						//create a list out of word created
						word=word.trim();//again for being on safer side
						if(word.length()>0)
						{
							token=new Token(word);//by default, type is Unknown
							//updateTokenList(token);//to recreate a single instruction in tokenized form
							Lexer.getToken(token);
							
							token=null;
						}
						
						word=new String("");
						continue;
					}
					word+=String.valueOf(instruction.charAt(j));
					word=word.trim();
				}
				Lexer.finalizeList();//the tokenList created is now complete, and proceed for new list while passing on 
				//currentList to SyntaxAnalyzer
			}
			
		}
		else
		{
			
		}
	}
	/*
	private static void updateTokenList(Token t)
	{
		if(tokenList==null)
		{
			tokenList=new ArrayList<Token>();
		}
		tokenList.add(t);
	}
	*/
}
