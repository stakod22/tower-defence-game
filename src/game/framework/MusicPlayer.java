package game.framework;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private static Clip clip;  // Making clip static so we can access it outside the method

    public static void playWav(String filePath, float volume) {
        try {
            // Load the audio file
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get a sound clip resource
            clip = AudioSystem.getClip();

            // Open the audio clip
            clip.open(audioStream);

            // Adjust the volume using FloatControl
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

            // Set the volume (0.0 to 1.0 for the range, converting to the actual volume control range)
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float volumeValue = min + (volume * (max - min));
            volumeControl.setValue(volumeValue);

            // Start playing the sound
            clip.start();

            // Loop the music indefinitely
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // This will loop the audio indefinitely

            // Optionally, you can sleep here if you want to keep the thread alive.
            System.out.println("Playing audio in loop...");

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();  // Stop the audio clip
            clip.close(); // Close the clip to release resources
            System.out.println("Music stopped.");
        }
    }
}
