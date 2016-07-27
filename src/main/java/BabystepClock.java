import javafx.scene.control.Label;

/**
 * Created by jonas on 25.06.2016.
 * Hier wie besprochen Babysteps.
 * Benutzung:
 * Erstelle ein Objekt BabystepClock
 * - Sobald es erstellt ist wird im Hintergrund die Zeit laufen.
 * - Ist die Zeit am angegebenen Limit wir back und check ausgefuehrt und der Timer auf 0 zurueck gesetzt.
 * - Sobald der Check Button gedrueckt wird. muss man danach sofort einmal die reset() Methode aufrufen
 * - Idee ist es gibt nur einen Timer, dieser laeuft das gesamte Programm ueber.
 * - Soll die BabystepClock angehalten werden geschieht dies durch das aufrufen der Methode stop();
 * - Soll die BabystepClock wieder gestartet werden, geschieht dies durch restart();
 */
public class BabystepClock {
    int maxtime;
    volatile int currenttime;
    volatile boolean running=true;

    /**
     * Konstuktor, Ließt die Zeitobergrenze aus der PresetDataBase ein, Setzt die Aktuelle Zeit
     * der BabyCLock au fden Startwert, und ruft start(); auf.
     */
    public BabystepClock(){
        maxtime= PresetDataBase.babystepstime;
        currenttime=-1;
        this.start();
    }

    /**
     * Startmethode der BabyClock, erstellt einen neuen Thread dessen Aufgabe es ist jede Sekunde den Zaehler
     * der BabyClock um eins zu erhoehen, undzwa solange wie der Timer der Uhr laufen soll.
     */
    public void start(){
        Thread time = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                	if(maxtime >= currenttime&&running){
	                    if (maxtime == currenttime){
	                        reset();
	                    }
	                    
	                    try {
	                        Thread.sleep(1000);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    currenttime++;
                	}
                }
            }
        });
        time.start();
    }

    /**
     * Setzt den Zaehler der BabyClock wieder auf sienen Uhrspruenglichen Wert zurueck
     */
    public void reset (){
        currenttime =-1;
    }

    /**
     * Stoppt das hochzaehlen des BabyClock Zaehlers.
     */
    public void stop(){
        running=false;
        
    }
    /**
     * Setzt den Zaehler der BabyClock wieder auf den Anfangswert zurueck und startet das sekuendliche
     * Hochzaehlen des Timers wieder.
     */
    public void restart(){
        this.reset();
        running=true;
    }
}
