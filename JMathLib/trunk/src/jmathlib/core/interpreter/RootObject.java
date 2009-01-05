package jmathlib.core.interpreter;

import java.io.*;
import java.text.NumberFormat;
import java.applet.Applet;
import jmathlib.core.interpreter.Interpreter;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.FunctionManager;


/**This universal the base class for all class define by MathLib. 
It defines Global values as class variables and also defines functions for creating and accessing the working environment.*/
abstract public class RootObject implements java.io.Serializable, 
                                            java.lang.Cloneable, 
                                            jmathlib.core.constants.ErrorCodes, 
                                            jmathlib.core.constants.TokenConstants
{
    /**store all the global values*/
    static transient private GlobalValues globals = null;
	
    /**Convert the object into a string*/	
    public String toString()
    {
    	return "";
    }

    /**Converts the object to a string based on the operand list
       @param operands = operands for the expression*/
    public String toString(OperandToken[] operands)
    {
    	return "root object";
    }
    
    /**Set the constant values
       @param _runnginStandalone = true if this is an application
       @param _interpreter = the interpreter object
    */
    protected final void setConstants(boolean _runningStandalone, Interpreter _interpreter, Applet applet)
    {
	    if(globals == null)
	    {
	        globals = new GlobalValues(_interpreter, _runningStandalone, applet);	
	    }
    }

    /**
     * @return the current variable list
     */
    protected final VariableList getLocalVariables()
    {	
	    return globals.getLocalVariables();
    }

    /** 
     * @return the global variable list
     */
    protected final VariableList getGlobalVariables()
    {	
	    return globals.getGlobalVariables();
    }

    /**
     * @param
     * @return the variable from local or global workspace
     */
    protected final Variable getVariable(String name)
    {   
        return globals.getVariable(name);
    }

    /** create a variable in the local or global workspace
     * @param
     * @return
     */
    protected final Variable createVariable(String name)
    {
        return globals.createVariable(name);
    }

    /** set a variable in the local workspace
     * @param
     * @param
     */
    protected final void setVariable(String name, OperandToken value)
    {
        globals.setVariable(name, value);
    }

    /** Allow access to the context list
     * @return
     */    
    protected final ContextList getContextList()
    {
        return globals.getContextList();
    }

    /**
     * @return the interpreter object
     */
    protected final Interpreter getInterpreter()
    {
	    return globals.getInterpreter();
    }

    /**
     * @return the function manager
     */
    public final FunctionManager getFunctionManager()
    {
	    return globals.getFunctionManager();
    }

    /**@return handle to plugins manager*/
    protected jmathlib.plugins.PluginsManager getPluginsManager()
    {
        return globals.getPluginsManager();
    }

    /** @return actual working directory */
    protected File getWorkingDirectory()
    {
	    return globals.getWorkingDirectory();
    }

    /** Sets the base directory used for all file operations
	@param _workingDir = the working directory to use*/
    protected void setWorkingDirectory(File _workingDir)
    {
	    globals.setWorkingDirectory( _workingDir);
    }

    /**@return handle to graphics manager*/
    protected jmathlib.core.graphics.GraphicsManager getGraphicsManager()
    {
	    return globals.getGraphicsManager();
    }


    /**Duplicates the object by serialising it to a piped stream then reading it back into
       the new object*/
    public Object clone() 
    {

        /* reference: Core Java Volume 2 Advanced Features p.66-67 */
        /*            Use a ByteArrayOutputStream                  */
        /* stefan: I implemented the ByteArray in order to get rid         
        of the output.txt file, because as an applet MathLib will           
        crash when it tries to open a file on the local disc.   */        
    	Object copy = null;
    	try
    	{
	    	//create streams, uses a byte array stream
	    	
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            
            //create object stream
	    	ObjectOutputStream objectOutput = new ObjectOutputStream(output);
	    	
	    	objectOutput.writeObject(this);

            ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray());			

            ObjectInputStream objectInput   = new ObjectInputStream(input);
                  	
	    	copy = objectInput.readObject();

			//close output objects	    	
	    	objectOutput.close();
	    	output.close();

			//close input objects
	    	objectInput.close();
	    	input.close();	    	
	 	}
	 	catch(java.io.IOException except)
	 	{
			ErrorLogger.debugLine("IO exception");
			ErrorLogger.debugLine(except.getMessage());
			//except.printStackTrace();
	 	}
	 	catch(java.lang.ClassNotFoundException except)
	 	{
			ErrorLogger.debugLine("Class not found exception");
	 	}
    	
    	return copy;
    }

    /**@return the setting of the debug flag*/
    static public final boolean getDebug()
    {
        return globals.getDebug();
    }
    
    /**sets the debug flag
    @param _debug = should debug information be displayed*/
    static public final void setDebug(boolean _debug)
    {
        globals.setDebug(_debug);
    }

    /**
     * returns the number format for displaying numbers
     * @return format type
     */
    public NumberFormat getNumberFormat()
    {
        return globals.getNumberFormat();
    }

    /**
     * sets the number format for displaying numbers
     * @param _numFormat format type
     */
    public void setNumberFormat(NumberFormat numFormat)
    {
        globals.setNumberFormat(numFormat);
    }

} 
