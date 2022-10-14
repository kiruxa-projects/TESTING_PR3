import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
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

//        if (!file.exists())
//            return;

        //если это папка, то идем внутрь этой папки и вызываем рекурсивное удаление всего, что там есть
        if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                // рекурсивный вызов
//                recursiveDelete(f);
            }
        }

        file1.delete();
        file2.delete();
        if(dir.isDirectory())
            dir.delete();
        else {
//            deleteFile();
            file1.delete();
            file2.delete();
        }

        dir.delete();

        Assertions.assertEquals(false,dir.exists());
    }

    @Test
    void deleteFile() {
        File file = new File("Example.txt");

        file.delete();
        Assertions.assertEquals(false,file.exists());
    }

    @Test
    void overwriteFile() {

        // Создаём временный файл для тестирования
        try {
            File file = new File("Example.txt");

            // Создание файла
            file.createNewFile();

            // Создание объекта FileWriter
            FileWriter writer = new FileWriter(file);

            // Запись содержимого в файл
            writer.write("Это простой пример,\n в котором мы осуществляем\n с помощью языка Java\n запись в файл\n и чтение из файла\n");
            writer.flush();
            writer.close();

            File fileOverwrite = new File("Example.txt");
            FileWriter writerOverwrite  = new FileWriter(file,false);

            // Перезаписываем файл
            writerOverwrite.write("Новые символы");
            writerOverwrite.close();

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