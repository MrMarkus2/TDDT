package main.java.tddt.data;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FilenameFilter;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogList {

   
    private List<Log> logs = new ArrayList<>();

   
    public static File directoryPath;

   
    public LogList(File directory) {
        directoryPath = directory;
   
        File[] allLogFiles = directory.listFiles();

   
        Log log = null;
        JAXBContext creation = null;
        for (File logFile : allLogFiles) {
            try {
                creation = JAXBContext.newInstance(Log.class);
                Unmarshaller unmarshaller = creation.createUnmarshaller();
                log = (Log) unmarshaller.unmarshal(logFile);
                logs.add(log);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

   
    public void addLog(Log log) throws JAXBException {
        logs.add(log);
        Log.createLog(log.getPhase(), log.getTime(), log.getTimer(), log.getClassText(), log.getTestText(), log.getCompileMessage(),directoryPath);
    }

   
    public void deleteAll() {
        int size = logs.size();
        for(int i = 0; i < size; i++){
            this.deleteLast();
        }
    }

   
    public void deleteLast() {
   
        String fileNameLastLog = logs.get(logs.size()-1 ).getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));

   
        logs.remove(logs.size()-1);

   
        File fileLastLog = new File(directoryPath, File.separator + fileNameLastLog);
        fileLastLog.delete();

    }

    public Log getLog(int index) {
        return logs.get(index);
    }

    public int size(){
        return logs.size();
    }
}