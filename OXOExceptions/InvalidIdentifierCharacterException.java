package OXOExceptions;

public class InvalidIdentifierCharacterException extends CellDoesNotExistException
{
    RowOrColumn type;
    char character;

    public InvalidIdentifierCharacterException(RowOrColumn t, char c)
    {
        super();
        this.character = c;
        this.type = t;
    }

    public String toString()
    {
        if (this.type == RowOrColumn.ROW) {
            return "You have entered an invalid row identifier: '" + this.character +
                    "'. The first character of your command should be a valid letter (a-z / A-Z).";
        }
        return "You have entered an invalid column identifier: '" + this.character +
                "'. The second character of your command should be a valid single digit number (1-9).";
    }
}
