package OXOExceptions;

public class OutsideCellRangeException extends CellDoesNotExistException
{
    RowOrColumn type;
    int position;

    public OutsideCellRangeException(RowOrColumn t, int pos)
    {
        super();
        this.type = t;
        this.position = pos;
    }

    public String toString()
    {
        if (this.type == RowOrColumn.ROW) {
            return "Row " + (this.position + 1) + "(" + (char)(this.position + 'a') + ")" + " is out of range.";
        }
        return "Column " + (this.position + 1) + " is out of range.";
    }
}
