package FileUtility.filePicker;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jerome on 2015/12/12.
 */
public class FilePickerFileFilter implements FileFilter {
    private static List<String> mFileExtensions = new ArrayList<String>();


    public static int IMAGE_Format = 1;
    public static int DOCUMENT_Format = 2 ;
    public static int VIDEO_FORMAT = 4;
    private static int mFilterFormat = IMAGE_Format |  DOCUMENT_Format | VIDEO_FORMAT;
    /**
     *
     * @param filterFormat
     *  IMAGE_Format(1), DOCUMENT_Format(2), VIDEO_FORMAT(4)
     */
    public FilePickerFileFilter(int filterFormat)
    {
        setFilerFormat(filterFormat);
    }
    /**
     *
     * @param filterFormat
     *  IMAGE_Format(1), DOCUMENT_Format(2), VIDEO_FORMAT(4)
     */
    public static void setFilerFormat(int filterFormat)
    {
        mFilterFormat = filterFormat;
        mFileExtensions.clear();
        if ((mFilterFormat & IMAGE_Format) == IMAGE_Format)
        {
            mFileExtensions.add("jpg");
            mFileExtensions.add("png");
            mFileExtensions.add("gif");
        }
        if ((mFilterFormat & DOCUMENT_Format) == DOCUMENT_Format)
        {

        }
        if ((mFilterFormat & VIDEO_FORMAT) == VIDEO_FORMAT)
        {

        }
    }

    /**
     *
     * @return filterFormat
     *  IMAGE_Format(1), DOCUMENT_Format(2), VIDEO_FORMAT(4)
     */
    public static int getFilerFormat()
    {
        return mFilterFormat;
    }

    /**
     *
     * @param file
     * @return
     */
    public boolean accept(File file)
    {
        for (String extension : mFileExtensions) {
            if (file.isDirectory() || file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}
