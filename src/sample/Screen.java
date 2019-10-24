package sample;

public class Screen implements ScreenSpec {

    String resolution;
    int refreshrate;
    int responsetime;

    //public Screen(String s, int i, int i1) {
   // }



    Screen(String resolution, int refreshrate, int responsetime){
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
    public String toString(){
        return "Resolution: " + resolution + "\nRefresh Rate: " + refreshrate
                + "\nResponse Time: " + responsetime;

    }
}
