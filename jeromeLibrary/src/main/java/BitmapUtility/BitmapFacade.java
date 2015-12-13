package BitmapUtility;

import android.graphics.Bitmap;

/**
 * Created by Jerome on 2015/12/13.
 */
public class BitmapFacade
{
    public static Bitmap resampleBitmapFromByteArray(byte[] imageBytes, int reqWidth, int reqHeight)
    {
        return BitmapUtility.resampleBitmapFromByteArray(imageBytes, reqWidth, reqHeight);
    }
    public static Bitmap createThumbnailsBitmap(String originalBitmapPath, int thumbnailsWidth, int thumbnailsHeight)
    {
        return 	BitmapUtility.createThumbnailsBitmap(originalBitmapPath, thumbnailsWidth, thumbnailsHeight);
    }
    public static Bitmap resampleBitmapFromPath(String imagePath, int reqWidth, int reqHeight)
    {
        return BitmapUtility.resampleBitmapFromPath(imagePath, reqWidth, reqHeight);
    }

}
