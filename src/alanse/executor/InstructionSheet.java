package alanse.executor;

import java.util.ArrayList;

/*
 * 
 * About InstructionSheet : to be filled by SyntaxAnalyzer and to be processed by Executor 
 * 
 * 
 */
/*	available METHODS in InstructionSheet
 * 
 * putInstruction(ArrayList<Token>) : method to append tokenized instruction into sheet
 * getInstruction(int) : returns instruction at a particular index of sheet
 * clearSheet() : empty sheet and set it to null
 * getSheetCount() : returns size of sheet (total number of instructions)
 * getInstructionCount(int) : return number of tokens in single-instruction which exists at index "i" in sheet
 */
public class InstructionSheet {

	private static ArrayList<ArrayList<Token>> sheet=null;
	public static void putInstruction(ArrayList<Token> instructionTokens) {
		// TODO Auto-generated method stub
		if(sheet==null)
			sheet=new ArrayList<ArrayList<Token>>();
		sheet.add(instructionTokens);
	}
	public static ArrayList<Token> getInstruction(int index) throws Exception
	{
		//returns a instruction (tokenzed-list) at index passed as argument
		if(sheet==null)
			throw new Exception("No Instruction stored in sheet");
		return new ArrayList<Token> ( sheet.get(index) );
	}
	public static void clearSheet()
	{
		sheet.clear();
		sheet=null;
	}
	public static int getSheetCount()//return total instructions stored in sheet 
	{
		return sheet.size();
	}
	public static int getInstructionCount(int i)
	{
		return sheet.get(i).size(); //return size of a single-instruction (tokenized list) at index "i" in sheet
	}
}
