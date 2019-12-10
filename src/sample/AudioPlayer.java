package sample;

public class AudioPlayer extends Product implements MultimediaControl {

    private String supportedAudioFormats;
    private String supportedPlaylistFormats;


    /**
     * Constructor created for variables above as well as variables from Product class by calling super.
     *
     * @param name                     parameter for name in Product
     * @param manufacturer             parameter for manufacturer in Product
     * @param supportedAudioFormats    parameter to supportedAudioFormats which is private
     * @param supportedPlaylistFormats parameter to supportedPlaylistFormats which is private
     */
    AudioPlayer(String name, String manufacturer, String supportedAudioFormats, String supportedPlaylistFormats) {
        super(name, manufacturer, ItemType.AUDIO);
        this.supportedAudioFormats = supportedAudioFormats;
        this.supportedPlaylistFormats = supportedPlaylistFormats;

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
        System.out.println("Going to previous");
    }

    @Override
    public void next() {
        System.out.println("Going to next");
    }


    @Override
    public int getID() {
        return 0;
    }

    /**
     * prints out on bottom the formats and names used once the class is used.
     *
     * @return
     */
    @Override
    public String toString() {
        return ("Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type
                + "\nSupported Audio Formats: " + supportedAudioFormats + "\nSupported Playlist Formats: "
                + supportedPlaylistFormats);
    }
}
