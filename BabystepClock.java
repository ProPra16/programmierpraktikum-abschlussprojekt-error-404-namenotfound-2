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
    volatile Label timelabel;

    public BabystepClock(){
        maxtime= PresetDataBase.babystepstime;
        currenttime=0;
        this.start();
    }
    public void start(){
        Thread time = new Thread(new Runnable() {
            @Override
            public void run() {
                while (maxtime >= currenttime && running){
                    //Ausgabelabel für die Zeit, mit echtzeit Aktuallisierung
                    timelabel.setText("TIMER: "+currenttime+"/"+maxtime);
                    //Wenn die Zeitgrenze erreicht ist checkandback
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
        });
        time.start();
    }
    //Setzt den Zähler zurück auf 0.
    public void reset (){
        currenttime =0;
    }

    //Methode für das Zurücksetzen des Anfangzustandes der GUI von der letzten Runde
    public void checkandback(){
        /*Hier werden die beiden Methoden aufgerufen für Check und Back.
        classname.back();
        classname.check();
        */
    }

    //Stopt den Timer
    public void stop(){
        running=false;
    }

    //Startet den Timer wieder neu.
    public void restart(){
        running=true;
        this.reset();
        this.start();
    }

}
