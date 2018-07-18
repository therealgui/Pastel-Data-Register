package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;

public final class Setting {

    private static String username;
    private static String mainPathStr;
    private static String bakcupPathStr;
    private static String fileName;
    private static String fileNamePrefix;

    /**
     * set presistence info
     */
    public static void setPresistenceData(){
        username = System.getProperty("user.name");
        String operatingSystem = OSValidator.getOS();

        if(OSValidator.isWindows()) {
            if (System.getProperty("user.language").equals("en")) {
                mainPathStr = "C:\\Users\\" + username + "\\Documents\\Pastel-Records";
                bakcupPathStr = "C:\\Users\\" + username + "\\Documents\\Pastel-Records-Backup";
                fileNamePrefix = "Records";
            }

            if (System.getProperty("user.language").equals("pt")) {
                mainPathStr = "C:\\Utilizadores\\" + username + "\\Documentos\\Pastel-Registos";
                bakcupPathStr = "C:\\Utilizadores\\" + username + "\\Documents\\Pastel-Registos-Backup";
                fileNamePrefix = "Registos";
            }
        }

        if(OSValidator.isUnix()){
            mainPathStr = "/home/" + username + "/Documents/Pastel-Records";
            bakcupPathStr = "/home/" + username + "/Documents/Pastel-Records-Backup";
            fileNamePrefix = "Records";
        }

        setFileName();
    }

    /**
     * set fileName
     */
    private static void setFileName(){
        int monthNumber = LocalDate.now().getMonth().getValue();
        int year = LocalDate.now().getYear();

        fileName = fileNamePrefix+"-"+year+"-"+monthNumber+".txt";
    }

    /**
     * get filename
     * @return String
     */
    public static String retrieveFileName(){
        return fileName.isEmpty() ? "" : fileName;
    }

    /**
     * get path to main folder
     * @return String
     */
    public static String retrieveMainPath(){
        return mainPathStr.isEmpty() ? "" : mainPathStr;
    }

    /**
     * get path to backup folder
     * @return String
     */
    public static String retrieveBackupPath(){
        return bakcupPathStr.isEmpty() ? "" : bakcupPathStr;
    }

    /**
     * create directory to store records
     * @return boolean
     */
    public static boolean createMainDirectory() {

        Path path = Paths.get(mainPathStr);

        if(!Files.exists(path)){
            try{
                Files.createDirectories(path);
            } catch(IOException e){
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * create directory to store records backups
     * @return boolean
     */
    public static boolean createBackupDirectory() {

        Path path = Paths.get(bakcupPathStr);

        if(!Files.exists(path)){
            try{
                Files.createDirectories(path);
            } catch(IOException e){
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * create record file to store info
     */
    public static void createRecordFile(){
        String fullPath = "";

        if(OSValidator.isWindows()){
            fullPath = mainPathStr+"\\"+fileName;
        }

        if(OSValidator.isUnix()){
            fullPath = mainPathStr+"/"+fileName;
        }

        Path path = Paths.get(fullPath);

        if(!Files.exists(path)){
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
