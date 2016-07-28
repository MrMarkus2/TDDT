package main.java.tddt.data;

import main.java.tddt.Coordinator;
import static org.junit.Assert.*;

import main.java.tddt.data.Log;
import main.java.tddt.gui.Controller;
import org.junit.*;

import java.time.LocalDateTime;

public class TestingCoordinator {

    String klassencontent = "public class Bar { \n"
            + " public static int square(int n) { \n"
            + "    return n * n; \n"
            + " }\n"
            + " public static int plusThree(int n) { \n"
            + "    return 3 + n; \n"
            + " }\n"
            + "}";
    String testcontent = "import static org.junit.Assert.*;\n"
            + "import org.junit.Test;\n"
            + "public class BarTest { \n"
            + "   @Test\n"
            + "   public void squareOf3_shouldReturn9() { \n "
            + "       assertEquals(9, Bar.square(3)); \n"
            + "   }\n "
            + "}";

    String klassencontentfalsch = "public class Bar { \n"
            + " public static int square(int n) { \n"
            + "    return n * 12; \n"
            + " }\n"
            + " public static int plusThree(int n) { \n"
            + "    return 3 + n; \n"
            + " }\n"
            + "}";

    @Test
    public void vonphase1zu2Test() {
        Coordinator coor = new Coordinator("Bar", "BarTest");

        try {
            LocalDateTime wer = coor.nextPhase(klassencontentfalsch, testcontent);
        } catch (NullPointerException n) {
        }
        assertEquals(2, coor.phase);
    }

    @Test
    public void testedmethodnotwrittenInphase1() {
        Coordinator coor = new Coordinator("Bar", "BarTest");

        String methodfortestnotdefined = "public class Bar{}";
        try {
            LocalDateTime qwe = coor.nextPhase(methodfortestnotdefined, testcontent);
        } catch (NullPointerException n) {
        }
        assertEquals(2, coor.phase);
    }

    @Test
    public void vonphase2zu3Test() {
        Coordinator coortest = new Coordinator("Bar", "BarTest");
        try {
            LocalDateTime wer = coortest.nextPhase(klassencontentfalsch, testcontent);

        } catch (NullPointerException n) {
        }
        try {
            LocalDateTime wer = coortest.nextPhase(klassencontent, testcontent);

        } catch (NullPointerException n) {
        }
        assertEquals(3, coortest.phase);
    }

    @Test
    public void Refactoringsuccess() {
        Coordinator coortest = new Coordinator("Bar", "BarTest");
        try {
            LocalDateTime ti = coortest.nextPhase(klassencontentfalsch, testcontent);
            //phase sollte hiernach 2 sein
        } catch (NullPointerException n) {
        }
        try {
            LocalDateTime ti = coortest.nextPhase(klassencontent, testcontent);

        } catch (NullPointerException n) {
        }
        try {
            LocalDateTime ti = coortest.nextPhase(klassencontent, testcontent);

        } catch (NullPointerException n) {
        }
        assertEquals(1, coortest.phase);
    }

    @Test
    public void coordinatormitphase2init() {
        Coordinator coortest = new Coordinator("Bar", "BarTest", 2);
        try {
            LocalDateTime t = coortest.nextPhase(klassencontent, testcontent);

        } catch (NullPointerException n) {
        }
        assertEquals(3, coortest.phase);
    }

    @Test
    public void cannotgobacktolastphase2Test() {
        Coordinator coortest = new Coordinator("Bar", "BarTest", 2);
        try {
            Log l = coortest.lastPhase();
        } catch (NullPointerException n) {
        }
        assertEquals(2, coortest.phase);
    }

    @Test
    public void cannotgobacktolastphase3Test() {
        Coordinator coortest = new Coordinator("Bar", "BarTest", 3);
        try {
            Log l = coortest.lastPhase();
        } catch (NullPointerException n) {
        }
        assertEquals(3, coortest.phase);
    }

    @Test
    public void cannotgobacktolastphase1Test() {
        Coordinator coortest = new Coordinator("Bar", "BarTest", 1);
        try {
            Log l = coortest.lastPhase();
        } catch (NullPointerException n) {
        }
        assertEquals(1, coortest.phase);
    }
}
