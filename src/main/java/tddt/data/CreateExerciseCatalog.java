package main.java.tddt.data;

import main.java.tddt.gui.dialogs.CreateExerciseController;

import java.io.*;

public class CreateExerciseCatalog {

    public CreateExerciseCatalog(File resFile, File catalogFile) throws Exception {
        File[] files = resFile.listFiles();
        catalogFile.mkdirs();
        for (File file : files) {
            copyFile(file, new File(catalogFile.getAbsolutePath() + System.getProperty("file.separator") + file.getName()));
        }
    }

    private static void copyFile(File file, File dest) throws Exception {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest, true));
        int bytes = 0;
        while ((bytes = in.read()) != -1) {
            out.write(bytes);
        }
        in.close();
        out.close();
    }
}
