package Base.App;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Voice {
    private final String ApiKey="365bb6487de74de9a06bcc665703affc";

    private int rate=0;
    private static float volume;

    public static float getVolume() {
        return volume;
    }

    public static void setVolume(float volume) {
        Voice.volume = volume;
    }

    public void Speak(String text ,String voice){
        try {
            String baseUrl ="http://api.voicerss.org/?"
                    +"key=" +ApiKey
                    + "&hl="+ URLEncoder.encode(voice, "UTF-8")
                    +"&src="+ URLEncoder.encode(text, "UTF-8");
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(baseUrl)).build();

            HttpClient client =HttpClient.newHttpClient();
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() == 200) {
                byte[] audioFile = response.body();
                ByteArrayInputStream inAudio = new ByteArrayInputStream(audioFile);
                AudioInputStream audioInputStream =AudioSystem.getAudioInputStream(inAudio);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                while (clip.isRunning()) {
                    Thread.sleep(100);
                }
            } else {
                System.out.println("khong thanh cong");

            }
        }catch (Exception e){
            System.out.println("erro: " + e.getMessage());
        }
    }


}
