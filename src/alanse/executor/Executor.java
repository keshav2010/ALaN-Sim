package alanse.executor;

import java.util.ArrayList;

/*
 * Works on the final instruction sheet that is tokenized
 */
public class Executor {

	public static void execute() throws Exception
	{
		int totalInstructions=InstructionSheet.getSheetCount();
		for(int i=0;i<totalInstructions;i++)
		{
			ArrayList<Token> instruction=new ArrayList<Token>(InstructionSheet.getInstruction(i));
			
			executeInstruction(instruction);
			
			
			
		}
	
	}

	private static void executeInstruction(ArrayList<Token> instruction) {
		// TODO Auto-generated method stub
		int length=instruction.size();
		for(int i=0;i<length;i++)
		{

			
			String name=new String( instruction.get(i).getName() );
			name=name.trim().toUpperCase();
			int type=instruction.get(i).getType();
			
			if(type==Token.OPCODE)
			{
				switch(name)
				{
					case "ADD":ArithmeticUnit.setOpcodeType(ArithmeticUnit.ADD);
						break;
					case "SUB":ArithmeticUnit.setOpcodeType(ArithmeticUnit.SUB);
						break;
					case "MUL":ArithmeticUnit.setOpcodeType(ArithmeticUnit.MUL);
						break;
					case "DIV":ArithmeticUnit.setOpcodeType(ArithmeticUnit.DIV);
						break;
					case "MOD":ArithmeticUnit.setOpcodeType(ArithmeticUnit.MOD);
						break;
				}
			}
			else if(type==Token.MEMORY)
			{
				ArithmeticUnit.readMemoryAddressData(name);
			}
			else if(type==Token.REGISTER)
			{
				ArithmeticUnit.readRegisterData(name);
			}
		}
	}
}
class ArithmeticUnit
{
	private static boolean isUSED=false;
	private static int opcodeType;
	
	private static int operationResult=0;//performs opCODE operation on operands,
	
	public static final int
		ADD=0,
		SUB=1,
		MUL=2,
		DIV=3,
		MOD=4;
	
	public static void setOpcodeType(int type)
	{
		opcodeType=type;
	}
	public static void readRegisterData(String regAddressName) {
		// TODO Auto-generated method stub
		
	}
	public static void readMemoryAddressData(String memAddressName)
	{
		/*
		 * read memAddressName value from its respective class
		 * store the value in memAddress in a variable
		 * perform operation on operationResult
		 */
	}
}
