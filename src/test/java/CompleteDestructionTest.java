import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CompleteDestructionTest {

    @Test
    void main() {
    }

    @Test
    void recursiveDelete() {
        File dir = new File("testDirectory");
        dir.mkdir();

        File file1 = new File(dir.getAbsolutePath()+"\\file1.txt");
        File file2 = new File(dir.getAbsolutePath()+"\\file2.txt");
        try {
            file1.createNewFile();
            file2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CompleteDestruction cm = new CompleteDestruction();
        cm.recursiveDelete(dir);

        Assertions.assertEquals(false,dir.exists());
    }

    @Test
    void deleteFile() {
        File file = new File("Example.txt");

        CompleteDestruction cm = new CompleteDestruction();
        cm.deleteFile(file);

        Assertions.assertEquals(false,file.exists());
    }

    @Test
    void overwriteFile() {

        // Создаём временный файл для тестирования
        try {
            File file = new File("Example.txt");

            // Создание файла
            file.createNewFile();

            CompleteDestruction cm = new CompleteDestruction();
            cm.overwriteFile(file,"Новые символы");

            // Создание объекта FileReader
            String returnStr = "";
            FileReader fr = new FileReader(file);
            int c;
            while((c=fr.read())!=-1){
                returnStr = returnStr + (char)c;
            }
            fr.close();

            Assertions.assertEquals("Новые символы",returnStr);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}