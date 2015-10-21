package jerome.i_pos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import com.CenterFragment.TabFragment.Goods.GoodsCartListActivity;

public class MainViewActivity extends Activity {

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        enableNetwork();
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_view, null);
        mContext = this;
        ImageButton orderImageButton = (ImageButton)view.findViewById(R.id.orderImageButton);
        ImageButton shippmentImageButton = (ImageButton)view.findViewById(R.id.shippmentImageButton);
        ImageButton inventoryImageButton = (ImageButton)view.findViewById(R.id.inventoryImageButton);
        ImageButton recordImageButton = (ImageButton)view.findViewById(R.id.recordImageButton);
        orderImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Type", 0);
                intent.putExtras(bundle);
                startActivityForResult(intent, ActivityRequestConstants.MAIN_ACTVITIY);
            }
        });
        shippmentImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Type", 1);
                intent.putExtras(bundle);
                startActivityForResult(intent, ActivityRequestConstants.MAIN_ACTVITIY);
            }
        });
        inventoryImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //do something

            }
        });
        recordImageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(mContext, GoodsCartListActivity.class);
                startActivity(intent);
            }
        });
        new Thread() {
            @Override
            public void run() {
                try
                {
                    Util.WebServiceAPI.GetProducts();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                }
            }
        }.start(); //開始執行執行緒
        setContentView(view);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void enableNetwork()
    {
        android.os.StrictMode.setThreadPolicy(new android.os.StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build());
        android.os.StrictMode.setVmPolicy(new android.os.StrictMode.VmPolicy.Builder()
                .detectLeakedSqlLiteObjects()
                .detectLeakedSqlLiteObjects()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }
}
