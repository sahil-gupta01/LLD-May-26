package 3_StructuralDPs.AdaptorDP;

// Adaptor design pattern is a structural design pattern that allows objects with incompatible interfaces to work together. 
// It acts as a bridge between two incompatible interfaces by converting the interface of one class into an interface expected by the clients. 
// The adaptor pattern is useful when you want to use an existing class but its interface does not match the one you need.

// In the Adaptor design pattern, we have the following components:
// 1. Target: This is the interface that the client expects. It defines the methods that the client can use.
// 2. Adaptee: This is the existing class that has an incompatible interface.
// 3. Adaptor: This is the class that implements the Target interface and contains a reference to an Adaptee object.

// Example of Adaptor design pattern is a media player application that can play different types of audio files.

// adaptee class
class AudioPlayer {
    public void playAudio(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file. Name: " + fileName);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            System.out.println("Playing vlc file. Name: " + fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            System.out.println("Playing mp4 file. Name: " + fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}

// target interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// adaptor class
class MediaAdapter implements MediaPlayer {
    AudioPlayer audioPlayer;

    public MediaAdapter(String audioType) {
        audioPlayer = new AudioPlayer();
    }

    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc") || audioType.equalsIgnoreCase("mp4")) {
            audioPlayer.playAudio(audioType, fileName);
        }
    }
}


public class AdaptorDemo {
    public static void main(String[] args) {
        MediaPlayer mediaPlayer = new MediaAdapter("vlc");
        mediaPlayer.play("vlc", "example.vlc");

        mediaPlayer = new MediaAdapter("mp4");
        mediaPlayer.play("mp4", "example.mp4");

        mediaPlayer = new MediaAdapter("mp3");
        mediaPlayer.play("mp3", "example.mp3");
    }

}