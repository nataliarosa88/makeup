package br.com.andretecnologia.makeup.log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MakeupLog {
	public void setLog(String subject, String activity) throws IOException {
		
		 File arquivo = new File("MakeupLog.txt");
	     
	     if( !arquivo.exists()){
	         arquivo.createNewFile();
	     }
	     
	     List<String> lista = new ArrayList<>();
	     lista.add(subject + " | " + activity + " | " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	     
	     Files.write(Paths.get(arquivo.getPath()), lista, StandardOpenOption.APPEND);
	}
}
