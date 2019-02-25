import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {
        try {
            GraphTextProcessor textProcessor = new GraphTextProcessor(args[0]);
            textProcessor.processFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
