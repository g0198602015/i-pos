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
        setContentView(view);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }
}
