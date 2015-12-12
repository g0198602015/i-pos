package fileUtility2;

import java.io.File;

/**
 * Created by Jerome on 2015/12/12.
 */
public class FileUtility
{
    public static int FILE_FORMAT_NONE = 1;
    public static int FILE_FORMAT_IMAGE = 2;
    public static int FILE_FORMAT_XML = 4;
    /***
     *
     * @param folderPath
     * @param fileFormat, FILE_FORMAT_NONE/FILE_FORMAT_IMAGE/FILE_FORMAT_XML
     */
    public static void getFile(String folderPath, int fileFormat)
    {
        File f = new File(folderPath);
        File[] files = f.listFiles();
        for (File inFile : files) {
            if (inFile.isDirectory()) {
                // is directory
            }
        }
    }
}
