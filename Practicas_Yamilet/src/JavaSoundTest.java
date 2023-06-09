import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

public class JavaSoundTest {
    public static void main(String[] args) {
        Mixer.Info[] mixers = AudioSystem.getMixerInfo();
        if (mixers.length > 0) {
            System.out.println("Java Sound API está disponible.");
        } else {
            System.out.println("Java Sound API no está disponible.");
        }
    }
}
