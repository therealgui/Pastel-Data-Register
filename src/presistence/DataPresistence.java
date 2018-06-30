package presistence;

import model.Record;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

public class DataPresistence<T> {
	
	public boolean readDataFromFile() {
		return true;
	}

	/**
	 * write data to file
	 * @param obj generic object
	 * @param pathStr string containing path to folder
	 * @param fileName string containing file name
	 * @return boolean
	 */
	public boolean writeDataToFile(T obj, String pathStr, String fileName) {

		Path path = Paths.get(pathStr+"\\"+fileName);

		try(BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), StandardOpenOption.APPEND)){
			writer.write(obj.toString());
			writer.newLine();
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean makeBackup() {
		return true;
	}

	/**
	 * check if diretory exists at given path
	 * @param pathStr String
	 * @return boolean
	 */
	public boolean doesDiretoryExists(String pathStr) {
		Path path = Paths.get(pathStr);

		return Files.exists(path);
	}
}
