import javafx.scene.control.Label;

/**
 * Created by jonas on 25.06.2016.
 * Hier wie besprochen Babysteps.
 * Benutzung:
 * Erstelle ein Objekt BabystepClock
 * - Sobald es erstellt ist wird im Hintergrund die Zeit laufen.
 * - Ist die Zeit am angegebenen Limit wir back und check ausgeführt und der Timer auf 0 zurück gesetzt.
 * - Sobald der Check Button gedrückt wird. muss man danach sofort einmal die reset() Methode aufrufen
 * - Idee ist es gibt nur einen Timer, dieser läuft das gesamte Programm über.
 * - Soll die BabystepClock angehalten werden geschieht dies durch das aufrufen der Methode stop();
 * - Soll die BabystepClock wieder gestartet werden, geschieht dies durch restart();
 * - Das BabystepClock Objekt besitzt ein Label welches die Aktuelle Zeit immer aktuallisiert besitzt.
 */
public class BabystepClock {
    int maxtime;
    volatile int currenttime;
    volatile boolean running=true;

    public BabystepClock(){
        maxtime= PresetDataBase.babystepstime;
        currenttime=-1;
        this.start();
    }
    public void start(){
        Thread time = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                	if(maxtime >= currenttime&&running){
	                    if (maxtime == currenttime){
	                        checkandback();
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
    public void reset (){
        currenttime =-1;
    }
    public void checkandback(){
        //Controller.checkandback();
    }
    public void stop(){
        running=false;
        
    }
    public void restart(){
        
        
        this.reset();
        running=true;
    }

}
