package presistence;

import model.Record;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class DataPresistence<T> {
	
	public List<String> readDataFromFile(String pathStr, String fileName) {
		List<String> list = new ArrayList<>();
		Path path = Paths.get(pathStr+"\\"+fileName);
		String line = "";

		try(BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))){

			list = reader.lines().collect(Collectors.toList());

		}catch(IOException e){
			e.printStackTrace();
			return null;
		}

		return list;
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
