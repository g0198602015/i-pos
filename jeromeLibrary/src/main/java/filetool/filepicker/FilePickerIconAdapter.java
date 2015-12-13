package filetool.filepicker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.jeromelibrary.R;
import java.io.File;

import BitmapUtility.BitmapFacade;

/**
 * Created by Jerome on 2015/12/13.
 */
public class FilePickerIconAdapter extends BaseAdapter{
    private final Context context;
    private File currentFile;
    private File[] files;
    private boolean hasParentFolder;
    private TextView textView;

    /**
     * Creates a new FilePickerIconAdapter with the given context.
     *
     * @param context
     *            the context of this adapter, through which new Views are created.
     */
    FilePickerIconAdapter(Context context){
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        if (this.files == null) {
            return 0;
        }
        return this.files.length;
    }

    @Override
    public Object getItem(int index) {
        return this.files[index];
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        if (convertView instanceof TextView) {
            // recycle the old view
            this.textView = (TextView) convertView;
        } else {
            // create a new view object
            this.textView = new TextView(this.context);
            this.textView.setLines(2);
            this.textView.setGravity(Gravity.CENTER_HORIZONTAL);
            this.textView.setPadding(5, 10, 5, 10);
        }

        if (index == 0 && this.hasParentFolder) {
            // the parent directory of the current folder
            this.textView.setCompoundDrawablesWithIntrinsicBounds(0,
                    R.drawable.file_picker_back, 0, 0);
            this.textView.setText("..");
        } else {
            this.currentFile = this.files[index];
            if (this.currentFile.isDirectory()) {
                this.textView.setCompoundDrawablesWithIntrinsicBounds(0,
                        R.drawable.file_picker_folder, 0, 0);
            } else
            {
                if ( FilePickerFileFilter.getFilerFormat() == FilePickerFileFilter.IMAGE_Format)
                {
                    Bitmap bitmap = BitmapFacade.createThumbnailsBitmap(this.currentFile.getPath(), this.textView.getWidth(), this.textView.getHeight());
                    Drawable bitmapDrawable= new BitmapDrawable(this.context.getResources(), bitmap);
                    this.textView.setCompoundDrawablesWithIntrinsicBounds(null, bitmapDrawable, null, null);

                }
                else {
                    this.textView.setCompoundDrawablesWithIntrinsicBounds(0,
                            R.drawable.file_picker_file, 0, 0);
                }
            }
            this.textView.setText(this.currentFile.getName());
        }
        return this.textView;
    }

    /**
     * Sets the data of this adapter.
     *
     * @param files
     *            the new files for this adapter.
     * @param newHasParentFolder
     *            true if the file array has a parent folder at index 0, false otherwise.
     */
    void setFiles(File[] files, boolean newHasParentFolder) {
        this.files = files.clone();
        this.hasParentFolder = newHasParentFolder;
    }
}
