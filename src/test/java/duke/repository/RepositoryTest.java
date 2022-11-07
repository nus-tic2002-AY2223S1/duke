package duke.repository;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Encapsulate the methods required to access a file.
 */
public class RepositoryTest {

    String repoDirectory = "./data/";
    String repoFileName = "textUiTest.txt";

    @Test
    public void testGetFileDirectory() {
        assertEquals("./data/textUiTest.txt", new Repository(repoDirectory, repoFileName).getFileDirectory());
    }

    @Test
    public void testLoadExistingDirectory() throws IOException {
        File dir = new File(repoDirectory);
        assertFalse(dir.createNewFile());
    }

    @Test
    public void testLoadExistingFile() throws IOException {
        File f = new File(repoDirectory + repoFileName);
        assertFalse(f.createNewFile());
    }

    @Test
    public void testReadFile() throws IOException {
        assertTrue(new Repository(repoDirectory, repoFileName).readFile().isEmpty());
    }

}
