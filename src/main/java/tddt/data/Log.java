package main.java.tddt.data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@XmlRootElement(name = "log")
@XmlAccessorType(XmlAccessType.FIELD)
public class Log {

    private int phase;
    private String time;
    private String timer;
    private String classText;
    private String testText;
    private String compileMessage;

    
    public Log() {
    }

    public Log(int phase, LocalDateTime time, LocalDateTime timer,
            String classText, String testText, String compileMessage) {
        this.phase = phase;

    
        this.setTime(time);
        this.setTimer(timer);

        this.classText = classText;
        this.testText = testText;
        this.compileMessage = compileMessage;
    }

    
    public static void createLog(int phase, LocalDateTime time, LocalDateTime timer,
            String classText, String testText, String compileMessage,
            File file) throws JAXBException {
        JAXBContext creation = JAXBContext.newInstance(Log.class);
        Marshaller marshaller = creation.createMarshaller();

    
        marshaller.marshal(new Log(phase, time, timer, classText, testText, compileMessage), new File(file + File.separator, time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"))));
    }

    public void setPhase(int phase) {
        this.phase = phase;
    }

    public void setTime(LocalDateTime time) {
        this.time = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
    }

    public void setTimer(LocalDateTime timer) {
        if (timer != null) {
            this.timer = timer.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        } else {
            this.timer = "";
        }
    }

    public void setClassText(String classText) {
        this.classText = testText;
    }

    public void setTestText(String testText) {
        this.testText = testText;
    }

    public void setCompileMessage(String compileMessage) {
        this.compileMessage = compileMessage;
    }

    
    public static Log getLog(File file) throws JAXBException {
        JAXBContext creation = JAXBContext.newInstance(Log.class);
        Unmarshaller unmarshaller = creation.createUnmarshaller();
        return (Log) unmarshaller.unmarshal(file);
    }

    public LocalDateTime getTime() {
    
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        return LocalDateTime.parse(time, timeFormat);

    }

    public LocalDateTime getTimer() {
        DateTimeFormatter timerFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        if (timer.equals("")) {
            return null;
        }
        return LocalDateTime.parse(timer, timerFormat);
    }

    public int getPhase() {
        return this.phase;
    }

    public String getClassText() {
        return this.classText;
    }

    public String getTestText() {
        return this.testText;
    }

    public String getCompileMessage() {
        return this.compileMessage;
    }
}
