import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestMain {
    public static void main(String[] args) {
        for (int dir = 'A'; dir < 'D'; dir++) {
            for (int i = 0; i < 10; i++) {
                String prefix = (char) dir + "/testfile/";
                Lexer lexer = new Lexer();
                try {
                    prefix = prefix + "testfile" + (i+1) + ".txt";
                    lexer.src = new String(Files.readAllBytes(Paths.get(prefix)));
                } catch (IOException e) {
                    System.out.println("no such file!");
                }
                lexer.next();
                lexer.print(dir,i);

            }
        }

    }
}
