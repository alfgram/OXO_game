package OXOExceptions;

public class CellAlreadyTakenException extends OXOMoveException
{
    public CellAlreadyTakenException()
    {
        super();
    }

    public String toString()
    {
        return "This Cell is already occupied, please select an empty cell.";
    }
}
