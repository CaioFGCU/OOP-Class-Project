package sample;

/**
 * class used for details and functions of screen
 * implements ScreenSpec interface to get the methods for resolution, RR, and RT
 * sets and gets resolution, refresh rate, and response time to whatever type of screen is being used from Main class
 */
public class Screen implements ScreenSpec {

    String resolution;
    int refreshrate;
    int responsetime;

    //public Screen(String s, int i, int i1) {
    // }


    /**
     * constructor sets designated fields from interface to variable parameters listed
     *
     * @param resolution
     * @param refreshrate
     * @param responsetime
     */
    Screen(String resolution, int refreshrate, int responsetime) {
        setResolution(resolution);
        setRefreshrate(refreshrate);
        setResponsetime(responsetime);
    }

    @Override
    public String getResolution() {
        return resolution;
    }

    @Override
    public int getRefreshRate() {
        return refreshrate;
    }

    @Override
    public int getResponseTime() {
        return responsetime;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public void setRefreshrate(int refreshrate) {
        this.refreshrate = refreshrate;
    }

    public void setResponsetime(int responsetime) {
        this.responsetime = responsetime;
    }

    @Override
    public String toString() {
        return "Resolution: " + resolution + "\nRefresh Rate: " + refreshrate
                + "\nResponse Time: " + responsetime;

    }
}
