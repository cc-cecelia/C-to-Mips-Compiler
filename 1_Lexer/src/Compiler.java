
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Compiler {
    public static void main(String[] args) {
        Lexer lexer = Lexer.getInstance();
        try {
            lexer.src = new String(Files.readAllBytes(Paths.get("testfile.txt")));
        } catch (IOException e) {
            System.out.println("no such file!");
        }
        lexer.next();
        lexer.print();
    }
}