package com.CenterFragment.TabFragment.Goods;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Jerome on 2015/10/11.
 */
public class GoodsDetailCartImageView extends ImageView {

    private Paint currentPaint;
    public GoodsDetailCartImageView(Context context) {
        super(context);
        init();
    }

    public GoodsDetailCartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GoodsDetailCartImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    private void init() {
        currentPaint = new Paint();
        currentPaint.setColor(Color.RED);
        currentPaint.setStyle(Paint.Style.FILL);
        currentPaint.setTextSize(20);
        currentPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
//        currentPaint.setColor(0xFF00CC00);  // alpha.r.g.b

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
          //  int height = getDrawable().getIntrinsicHeight();
          //  int width = getDrawable().getIntrinsicWidth();
        Rect clipRect = canvas.getClipBounds();
        //currentPaint.setColor(Color.RED);
        //canvas.drawCircle(0, 10, 5, currentPaint);
        currentPaint.setColor(Color.RED);
        if ( GoodsCartListActivity.getGoodsItemSize() != 0)
            canvas.drawText("" + GoodsCartListActivity.getGoodsItemSize(), clipRect.right - 20, 20, currentPaint);
//        canvas.drawLine(0,0,20,20, currentPaint);
    }
}

