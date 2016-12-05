package de.lmu.eipnf.javapong;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

    public void playWave(String wavefilename) {

        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(System.getProperty("user.dir") + "/resources/sound/" + wavefilename));
            Clip audioClip = AudioSystem.getClip();
            audioClip.open(audioIn);
            audioClip.start();
            System.out.println("Die Datei wurde abgespielt");

        } catch (UnsupportedAudioFileException e) {
            System.out.println("Das Dateiformat wird nicht unterstützt");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Auf die Datei kann nicht zugegriffen werden");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }
}
