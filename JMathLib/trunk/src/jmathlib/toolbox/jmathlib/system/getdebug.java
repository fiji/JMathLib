package jmathlib.toolbox.jmathlib.system;

import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.tokens.*;
import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.interpreter.Errors;
import jmathlib.core.interpreter.ErrorLogger;
import jmathlib.core.interpreter.GlobalValues;

/**External function for controlling the debug setting for the system*/
public class getdebug extends ExternalFunction
{
    public OperandToken evaluate(Token[] operands, GlobalValues globals)
    {
        if (getNArgIn(operands) !=0)
            throwMathLibException("getdebug: number of arguments !=0");
        
       return new LogicalToken(ErrorLogger.getDebug());                
    }
}

/*
@GROUP
system
@SYNTAX
getdebug()
@DOC
returns state of debug logging
@NOTES
@EXAMPLES
getdebug()
@SEE
setdebug
*/

