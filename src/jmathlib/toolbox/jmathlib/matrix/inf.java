package jmathlib.toolbox.jmathlib.matrix;

import jmathlib.core.tokens.numbertokens.DoubleNumberToken;
import jmathlib.core.tokens.Token;
import jmathlib.core.tokens.OperandToken;
import jmathlib.core.functions.ExternalFunction;
import jmathlib.core.interpreter.GlobalValues;

public class inf extends ExternalFunction
{
	/**return matrix of infinity
	@param operands[0] = number of rows
	@param operands[1] = number of columns */
	public OperandToken evaluate(Token[] operands, GlobalValues globals)
	{

		int columns;
		int rows;

		// at least one operands (e.g. inf(n) )
		if (getNArgIn(operands)<1)
			throwMathLibException("inf: number of arguments < 1");

		if (!(operands[0] instanceof DoubleNumberToken))
			  throwMathLibException("inf: works only on numbers");
		
		rows    = (int)(((DoubleNumberToken)operands[0]).getReValues())[0][0];
		columns = rows;

		// two operands (e.g. zeros(n,m) )
		if (getNArgIn(operands)==2) 
		{
			if (!(operands[1] instanceof DoubleNumberToken)) return null;

			columns = (int)((DoubleNumberToken)operands[1]).getReValues()[0][0];
		}
		
		// only positive indices
		if ((rows <= 0) || (columns <= 0)) return null;

		// create matrix
		double[][] values = new double[rows][columns];
		for (int yi=0; yi<=(rows-1) ; yi++)
		{
			for (int xi=0; xi<=(columns-1) ; xi++)
			{
				values[yi][xi] = Double.POSITIVE_INFINITY;
			}
		}
		return new DoubleNumberToken(values);		

	} // end eval
}

/*
@GROUP
matrix
@SYNTAX
answer = nan(sizey, [sizex])
@DOC
Returns a matrix filled with infinity.
@NOTES
@EXAMPLES
<programlisting>
nan(1,2) -> [Inf, Inf]
</programlisting>
@SEE
ones, zeros, nan
*/

