package OXOExceptions;

public class InvalidIdentifierLengthException extends CellDoesNotExistException
{
    int length;

    public InvalidIdentifierLengthException(int len)
    {
        super();
        this.length = len;
    }

    public String toString()
    {
        return "Command you have entered is " + this.length + " character(s) long, commands should be 2 characters long " +
                "with the first character being the row number(a-z / A-Z) and the second the column number(1-9).";
    }
}
