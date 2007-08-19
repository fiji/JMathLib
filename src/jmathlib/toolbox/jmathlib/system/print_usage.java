package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;

public class print_usage extends ExternalFunction
{
	public OperandToken evaluate(Token[] operands)
	{	

        throwMathLibException("print_usage");
		
        return  null;
	}
}

/*
@GROUP
system
@SYNTAX
print_usage
@DOC
print out the valid usage of the calling function
@NOTES
@EXAMPLES
@SEE
warning, error
*/

