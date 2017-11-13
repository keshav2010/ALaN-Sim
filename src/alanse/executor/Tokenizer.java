package alanse.executor;

// Tokenizer is the starting point for entire executor package, 
// Tokenizer recieves entire raw data first and is the starting checkpoint of entire process

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
	// if useList is true, it implies we are feeding all instructions at once
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
			
			for(int i=0; i<instructionList.length; i++)
			{
				
				String word=new String("");
				String instruction=new String(instructionList[i]);//extracting each line
				
				for(int j=0; j<instruction.length(); j++)
				{
					if(instruction.charAt(j)==' ' || instruction.charAt(j)==',')
					{
						//create a list out of word created
						word=word.trim().toUpperCase();//again for being on safer side
						if(word.length()>0)
						{	
							token=new Token(word);//create a token out of given word.
							
							//updateTokenList(token);//to recreate a single instruction in tokenized form
							
							Lexer.getToken(token);//pass it on to Lexer
							
							token=null; //TODO: inspect its affect, if its doing any benefit or not
							System.out.println(word + " Tokenizing");
						}
						word=new String("");
						continue;
					}
					word+= String.valueOf(instruction.charAt(j)); //append character to word
					//word= word.trim();
					System.out.println(word);
				}
				
				Lexer.finalizeList();//the tokenList created is now complete, and proceed for new list while passing on 
				//currentList for syntax analysis
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
