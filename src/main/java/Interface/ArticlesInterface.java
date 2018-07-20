package Interface;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticlesInterface implements Interface 
{
    private final String QUERY = "SELECT * FROM articulo";

    public String getQuery()
    {
        return QUERY;
    }
}
