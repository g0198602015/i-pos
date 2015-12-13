package BitmapUtility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapUtility
{

	static Bitmap resampleBitmapFromByteArray(byte[] imageBytes, int reqWidth, int reqHeight)
	{
		try
		{
		    final BitmapFactory.Options options = new BitmapFactory.Options();
		    options.inJustDecodeBounds = true;
		    options.inPreferredConfig = Bitmap.Config.RGB_565;  // 捨去透明度, 用顯示品質換取記憶體空間
		    // Calculate inSampleSize
		    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		    // Decode bitmap with inSampleSize set
		    options.inJustDecodeBounds = false;
		    return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length, options);
		}
		catch(OutOfMemoryError ex)
		{
			String message = ex.toString();
			return null;
		}
		catch(Exception ex)
		{
		
			String message = ex.toString();
			return null;
		}
	}

	static Bitmap createThumbnailsBitmap(String originalBitmapPath, int thumbnailsWidth, int thumbnailsHeight)
	{
		return resampleBitmapFromPath(originalBitmapPath, thumbnailsWidth, thumbnailsHeight);
	}

    static Bitmap resampleBitmapFromPath(String imagePath, int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    options.inPreferredConfig = Bitmap.Config.RGB_565;  // 捨去透明度, 用顯示品質換取記憶體空間
	    BitmapFactory.decodeFile(imagePath, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeFile(imagePath, options);
	}
    
    static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        while ((halfHeight / inSampleSize) > reqHeight
	                && (halfWidth / inSampleSize) > reqWidth) {
	            inSampleSize *= 2;
	        }
	    }
	
	    return inSampleSize;
	}

}
