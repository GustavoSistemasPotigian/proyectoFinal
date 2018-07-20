package Interface;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class PlansInterface implements Interface
{
    
    private final String QUERY = "SELECT * FROM plan_descuento";

    public String getQuery()
    {
        return QUERY;
    } 
}
