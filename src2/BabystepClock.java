/**
 * Created by jonas on 25.06.2016.
 * Hier wie besprochen Babysteps.
 * Benutzung:
 * Erstelle ein Objekt BabystepClock
 * - Sobald es erstellt ist wird im Hintergrund die Zeit laufen.
 * - Ist die Zeit am angegebenen Limit wir back und check ausgeführt und der Timer auf 0 zurück gesetzt.
 * - Sobald der Check Button gedrückt wird. muss man danach sofort einmal die reset() Methode aufrufen
 * - Idee ist es gibt nur einen Timer, dieser läuft das gesamte Programm über.
 * - Soll die BabystepClock angehalten werden geschieht dies durch das aufrufen der Methode end();
 */
public class BabystepClock {
    int maxtime;
    volatile int currenttime;
    volatile boolean running=true;

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
                    if (maxtime == currenttime){
                        checkandback();
                        resettimer();
                    }
                    /*Dieses System.out. muss mit einem Label veerknüpft werden sodass man es anzeigen kann.
                    * System.out.println(currenttime+"/"+maxtime);
                    * Label time = new Label();
                    * BabystepClock timer = new BabystepClock();
                    * time.setText(timer.currenttime+"/"+timer.maxtime);
                    */
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
    public void resettimer (){
        currenttime =0;
    }
    public void checkandback(){
        /*Hier werden die beiden Methoden aufgerufen für Check und Back.
        classname.back();
        classname.check();
        */
    }
    public void end(){
        running=false;
    }

}
