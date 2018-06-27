package presistence;

import model.Record;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class DataPresistence<T> {

	private String username = System.getProperty("user.name");
	private String pathStr = "C:\\Users\\"+username+"\\Documents\\Pastel-Registo";

	public boolean createMainDirectory() {

		Path path = Paths.get(pathStr);

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
	
	public boolean createBackupDirectory() {

		String username = System.getProperty("user.name");
		String pathStr = "C:\\Users\\"+username+"\\Documents\\Pastel-Registo-Backup";
		Path path = Paths.get(pathStr);

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
	
	public boolean readDataFromFile() {
		return true;
	}
	
	public boolean writeDataToFile(T obj) {
		String fileName = "registo-" + LocalDate.now().toString();

		Path path = Paths.get(this.pathStr+"\\"+fileName);

		try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"))){
			writer.write(obj.toString());
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean makeBackup() {
		return true;
	}
	
	public boolean doesDiretoryExists() {
		Path path = Paths.get(pathStr);

		return Files.exists(path);
	}
}
