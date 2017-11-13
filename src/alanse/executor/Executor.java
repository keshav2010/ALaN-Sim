package alanse.executor;

import java.util.ArrayList;

import alanse.datatable.*;

/*
 * Works on the final instruction sheet that is tokenized
 */
public class Executor {

	private static MemoryCellTable memCellTable=null;
	private static RegisterCellTable regCellTable=null;
	
	public static void execute() throws Exception
	{
		int totalInstructions=InstructionSheet.getSheetCount();
		for(int i=0;i<totalInstructions;i++)
		{
			ArrayList<Token> instruction=new ArrayList<Token>(InstructionSheet.getInstruction(i));
			
			executeInstruction(instruction);
			
			
			
		}
	
	}
	public static void registerTableObject(MemoryCellTable memTable, RegisterCellTable regTable)
	{
		memCellTable = memTable;
		regCellTable= regTable;
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
					case "ADD":
					case "SUB":
					case "MUL":
					case "DIV":
					case "MOD":
						ArithmeticUnit.feedInstruction(instruction);
						ArithmeticUnit.execute(memCellTable,regCellTable); //pass table objects also, ArithmeticUnit::operationResult variable holds final result now
	
						break;
				}
			}
		}
	}
}

class ArithmeticUnit
{
	private static int opcodeType=0;
	private static ArrayList<Token> executableInstruction=null;
	private static int operationResult=0;//performs opCODE operation on operands,
	
	private static String DestinationAddress=null;

	public static final int
		ADD=0,
		SUB=1,
		MUL=2,
		DIV=3,
		MOD=4;
	public static void feedInstruction(ArrayList<Token> instruction)
	{
		operationResult=0;//result reset
		

		if(executableInstruction!=null)
			executableInstruction.clear();
		executableInstruction=new ArrayList<Token>(instruction);
		setOpcodeType(executableInstruction.get(0));
	}
	public static void execute(MemoryCellTable memTableReference,RegisterCellTable regTableReference) {
		// TODO execute method
		
		String valueSourceName=null;
		int valueSourceType = Token.REGISTER;//default
		
		int size = executableInstruction.size();
		DestinationAddress=new String ( executableInstruction.get(size-1).getName() ); //last element is always DestinationAddress
		for(int i=1;i<size;i++)
		{
			valueSourceName = new String(executableInstruction.get(i).getName());
			valueSourceType = executableInstruction.get(i).getType();
			int value=0;
			if(valueSourceType == Token.MEMORY )
			{
				value=((memTableModel)memTableReference.getModel()).getData(valueSourceName);
			}
			else if(valueSourceType == Token.REGISTER)
			{
				value=((regTableModel)regTableReference.getModel()).getData(valueSourceName);
			}
			if(opcodeType==ADD)
				operationResult = operationResult+value; 
			else if(opcodeType==SUB)
				operationResult = operationResult-value;
			else if(opcodeType==MUL)
				operationResult = operationResult*value;
			/*
			else if(opcodeType==DIV)
			else if(opcodeType==MOD)
			*/
			if(i==size-1)
			{	//TODO : updating Table values by saving result to destinationAddress
				if(valueSourceType==Token.MEMORY)
				{
					((memTableModel)memTableReference.getModel()).setData(DestinationAddress,operationResult);
				}
				else if(valueSourceType==Token.REGISTER)
				{
					((regTableModel)regTableReference.getModel()).setData(DestinationAddress,operationResult);
				}
			}
		}
		
		/*if(valueSourceType==Token.MEMORY)
		{
			((memTableModel)memTableReference.getModel()).setData(valueSourceName,operationResult);
		}*/
		//save to Destination
		memTableReference.updateUI();
		regTableReference.updateUI();
	}
	public static int getResult()
	{
		return operationResult;
	}
	private static void setOpcodeType(Token token)
	{
		switch(token.getName())
		{
		case "ADD":opcodeType=ADD;
		break;
		case "SUB":opcodeType=SUB;
		break;
		case "MUL":opcodeType=MUL;
		break;
		case "DIV":opcodeType=DIV;
		break;
		case "MOD":opcodeType=MOD;
		break;
		}
	}

}
