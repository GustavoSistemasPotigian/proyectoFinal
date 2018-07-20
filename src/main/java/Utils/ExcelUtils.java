package Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtils 
{
    public static void exportToExcel(File destination, 
            List<String> headers, List<List<String>> content,
            String sheetName) throws IOException
    {
        createExcelFile(destination);
        HSSFWorkbook workBook = getWorkBook(headers, content, sheetName);
        FileOutputStream fileOut = new FileOutputStream(destination);
        workBook.write(fileOut);
        fileOut.close();
        workBook.close();
    }
    
    private static HSSFWorkbook getWorkBook(List<String> headers, 
            List<List<String>> content, String sheetName)
    {
        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet sheet = workBook.createSheet(sheetName);
        HSSFRow header = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) // tengo que hacerlo iterativo
            header.createCell(i).setCellValue(headers.get(i).toUpperCase());
        
        for (int i = 0; i < content.size(); i++)
        {
            HSSFRow row = sheet.createRow(i + 1);
            for (int j = 0; j < content.get(i).size(); j++)
                row.createCell(j).setCellValue(content.get(i).get(j));
        }
        return workBook;
    }
    
    private static void createExcelFile(File destination) throws IOException
    {
        if (destination.exists())
        {
            // Si ya existe, lo borro y creo uno nuevo.
            destination.delete();   
        }
        destination.createNewFile();
    }
}
