package Interface;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import Interface.Interface;
import java.nio.file.Files;
import java.sql.SQLException;

public class InterfaceManager 
{
    private JFrame parent;
    private String[] interfaceTypes = new String[]{"articulos", "planes"};
    
    public static final int ARTICLES = 0;
    public static final int PLANS = 1;
    
    public InterfaceManager(JFrame parent)
    {
        this.parent = parent;
    }
    
    public boolean onArticlesRequest(File directory) throws IOException, SQLException
    {
        return onInterfaceRequest(directory, ARTICLES);
    }

    public boolean onPlansRequest(File directory) throws IOException, SQLException
    {
        return onInterfaceRequest(directory, PLANS);
    }

    public boolean onExportAllRequested() throws IOException, SQLException
    {
        File outputDirectory = getRequestedFile(false, "");
        if (outputDirectory == null)
            return false; // Se cancelo el JFileChooser. No se requiere avanzar
        for (String type : interfaceTypes)
            onInterfaceRequest(outputDirectory, type);
        return true;
    }
    
    public boolean onInterfaceRequest(File directory, int idType) throws IOException, SQLException
    {
        return onInterfaceRequest(directory, interfaceTypes[idType]);
    }
    
    private void getInterface(File file, String type) throws IOException, SQLException
    {
        Interface interfaceInstance;
        switch (type)
        {
            case "articulos":
                interfaceInstance = new ArticlesInterface();
            break;
            case "planes":
                interfaceInstance = new PlansInterface();
            break;
            default:
                throw new IllegalArgumentException(type);
        }
        interfaceInstance.export(file);
    }
    
    private boolean onInterfaceRequest(File directory, String type) throws IOException, SQLException
    {
        final String fileName = type + "_" + getCurrentYYYYMMDD() + ".txt";
        File outputFile;
        if (directory == null)
            outputFile = getRequestedFile(true, fileName);
        else
            outputFile = new File(directory, fileName);
        if (outputFile == null)
            return false; // Se cancelo el JFileChooser. No se requiere avanzar
        if (outputFile.createNewFile() && (!outputFile.canWrite() & outputFile.delete()))
            throw new IOException("No se puede crear el archivo " + outputFile.getAbsolutePath());
        
        try
        {
            getInterface(outputFile, type);
            return true;
        }
        catch (IllegalArgumentException ex)
        {
            throw new IOException("Tipo de argumento de tipo de interfaz inválido: " + ex.getMessage());
        }
    }
    
    private String getCurrentYYYYMMDD()
    {
        return DateTimeFormatter.ofPattern("yyyyMMdd").format(LocalDate.now());
    }
    
    private File getRequestedFile(boolean fileOnly, String defaultFileName)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccione donde desea guardar la interfaz");
        fileChooser.setMultiSelectionEnabled(false);
        if (fileOnly)
        {
            fileChooser.setApproveButtonText("Exportar a archivo");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setSelectedFile(new File(defaultFileName));
        }
        else
        {
            fileChooser.setApproveButtonText("Exportar archivos");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        }
        
        if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(parent))
            return fileChooser.getSelectedFile();
        return null;
    }
}
