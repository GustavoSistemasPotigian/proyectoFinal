
package Interface;

import BaseDeDatos.ConexionMySQL;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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

public interface Interface 
{
    final static public String SEPARATOR = "|@|";

    ConexionMySQL mysqlConnection = new ConexionMySQL();
    
    public String getQuery();
    
    default public void export(File dest) throws IOException, SQLException
    {
        Connection dbConn = null;
        try
        {
            dbConn = mysqlConnection.Conectar();
            Statement statement = dbConn.createStatement();
            ResultSet resultSet = statement.executeQuery(getQuery());
            
            List<String> content = new ArrayList<>();
            
            // Para los DATE
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            while (resultSet.next())
            {
                StringBuilder stringBuilder = new StringBuilder();
                ResultSetMetaData metaData = resultSet.getMetaData();
                // Consideramos que hay solo 4 TIPOS en la tabla que traemos
                // DATE, DECIMAL, INT y VARCHAR
                for (int i = 1; i < metaData.getColumnCount() + 1; i++)
                {
                    String columnContent = "NULL" + SEPARATOR;
                    switch (metaData.getColumnType(i))
                    {
                        case java.sql.Types.DATE:
                            Date dateValue = resultSet.getDate(i);
                            if (!resultSet.wasNull())
                            {
                                columnContent = format.format(dateValue) + SEPARATOR;
                            }
                        break;
                        case java.sql.Types.DECIMAL:
                            double doubleValue = resultSet.getDouble(i);
                            if (!resultSet.wasNull())
                            {
                                columnContent = Double.toString(doubleValue) + SEPARATOR;
                            }
                        break;
                        case java.sql.Types.INTEGER:
                            int intValue = resultSet.getInt(i);
                            if (!resultSet.wasNull())
                            {
                                columnContent = Integer.toString(intValue) + SEPARATOR;
                            }
                        break;
                        case java.sql.Types.VARCHAR:
                            String stringValue = resultSet.getString(i);
                            if (!resultSet.wasNull())
                            {
                                columnContent = stringValue + SEPARATOR;
                            }
                        break;
                    }
                    stringBuilder.append(columnContent);
                }
                
                stringBuilder.setLength(stringBuilder.length() - SEPARATOR.length());
                content.add(stringBuilder.toString());
            }
            
            outputToFile(content, dest);
        }
        finally
        {
            if (dbConn != null)
                dbConn.close();
        }
    } 

    default public void outputToFile(List<String> content, File dest) throws IOException
    {
        Files.write(dest.toPath(), content);
    }
}
