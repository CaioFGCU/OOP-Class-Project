package sample;

/**
 * inherits Product class to get parameters to be used in constructor
 * Itemtype used for type
 * implements MultimediaControl interface to play, stop, go to previous, or go to next
 */
public class MoviePlayer extends Product implements MultimediaControl{

    Screen screen;

    MonitorType monitorType;

    MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType){
        super(name, manufacturer, ItemType.VISUAL );
        this.monitorType = monitorType;
        this.screen = screen;
    }
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void play() {
        System.out.println("Playing");
    }

    @Override
    public void stop() {
        System.out.println("Stopping");
    }

    @Override
    public void previous() {
        System.out.println("Going back to previous");
    }

    @Override
    public void next() {
        System.out.println("Going to next");
    }

    @Override
    public String toString(){
        return super.toString() + "\nScreen: " + screen + "\nMonitor Type: " + monitorType;
    }
}
