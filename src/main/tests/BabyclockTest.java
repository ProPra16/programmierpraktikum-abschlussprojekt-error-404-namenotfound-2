/**
 * Created by jonas on 09.07.2016.
 */
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BabyclockTest {
    @Test
    public void timetest1(){
        BabystepClock test = new BabystepClock();
        test.maxtime=10;
        try {
            Thread.sleep(5100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(5,test.currenttime,1);
    }
    @Test
    public void timetest2() {
        BabystepClock test = new BabystepClock();
        test.maxtime = 25;
        try {
            Thread.sleep(21100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(21, test.currenttime,1);
    }
    @Test
    public void resettest (){
        BabystepClock test = new BabystepClock();
        test.maxtime=10;
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.reset();
        assertEquals(0,test.currenttime,1);
    }
    @Test
    public void stoptest(){
        BabystepClock test = new BabystepClock();
        test.maxtime=10;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.stop();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(3,test.currenttime,1);
    }
    @Test
    public void restarttest(){
        BabystepClock test = new BabystepClock();
        test.maxtime=20;
        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.stop();
        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        test.restart();
        try {
            Thread.sleep(3100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(3,test.currenttime,1);
    }
}
