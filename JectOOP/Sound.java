import java.net.*;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.*;

public class Sound {
    
    private Clip clip;
    private URL soundURL[] = new URL[10];

    public Sound(){

        soundURL[0] = getClass().getResource("sound/background.wav");
        soundURL[1] = getClass().getResource("sound/stage.wav");
        soundURL[2] = getClass().getResource("sound/speedup.wav");
        soundURL[3] = getClass().getResource("sound/pew.wav");
        soundURL[4] = getClass().getResource("sound/alien_death.wav");
        soundURL[5] = getClass().getResource("sound/gameover.wav");
        soundURL[6] = getClass().getResource("sound/bar.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void stop(){
        clip.stop();
    }

    public void playMusic(int i){
        setFile(i);
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playSound(int i){
        setFile(i);
        clip.start();
    }

    public void delaySound(int i){
        setFile(i);
        try {
            TimeUnit.SECONDS.sleep(2);
            clip.start();
            TimeUnit.MILLISECONDS.sleep(clip.getMicrosecondLength() / 1000);
            clip.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
