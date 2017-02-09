package com.sawaTech.material.feedbackSeekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ammadtarar on 09/02/2017.
 */

public class FeedbackSeekbar extends LinearLayout {


    private View parentView;
    private LayoutInflater mInflater;
    private Context context;
    private SeekBar seekbar;
    private LinearLayout ll_number;
    private List<TextView> texts = new ArrayList<>();
    private ImageView emo;
    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;


    public FeedbackSeekbar(Context context) {
        super(context);
        init(context);
    }

    public FeedbackSeekbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FeedbackSeekbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    /**
     * INIT ALL VIEWS AND LISTENERS
     *
     * @param context - Context
     * @author Ammad Amjad
     */
    private void init(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        parentView = mInflater.inflate(
                R.layout.view_feedback_seekbar,
                this,
                false
        );
        findViews();
        setListeners();
        addView(parentView);
    }

    /**
     * FIND VIEWS AND BIND THEM TO INSTANCES
     *
     * @author Ammad Amjad
     */
    private void findViews() {
        seekbar = (SeekBar) parentView.findViewById(R.id.seekbar);
        ll_number = (LinearLayout) parentView.findViewById(R.id.ll_number);
        emo = (ImageView) parentView.findViewById(R.id.emo);
        emo.setBackgroundResource(R.drawable.emoji_gray);
        setDrawable(R.color.gray);
        populateTextViews();
    }

    /**
     * POPULATE THE NUMBERED TEXT VIEWS (0 - 10)
     *
     * @author Ammad Amjad
     */
    private void populateTextViews() {
        for (int i = 0; i < 11; i++) {
            TextView tv = new TextView(context);
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            tv.setTextColor(getResources().getColor(R.color.gray));
            tv.setText(String.valueOf(i));
            if (i > 0 && i < 10) {
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
            }
            if (i == 0) {
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
            }

            ll_number.addView(tv);
            texts.add(tv);
        }
    }

    /**
     * SETUP UP SEEKBAR LISTENERS
     *
     * @author Ammad Amjad
     */
    private void setListeners() {
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);
                int x = seekBar.getThumb().getBounds().centerX();
                emo.setTranslationX(x - 35);
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onProgressChanged(seekBar, progress, fromUser);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStartTrackingTouch(seekBar);
                }
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                int nearestGreaterTen = (int) (Math.floor((progress + 10) / 10) * 10);
                if (nearestGreaterTen - progress > 5) {
                    nearestGreaterTen = nearestGreaterTen - 10;
                }
                seekbar.setProgress(nearestGreaterTen);
                if (nearestGreaterTen == 0) {
                    int x = seekBar.getThumb().getBounds().centerX();
                    emo.setTranslationX(x - 50);
                }
                if (onSeekBarChangeListener != null) {
                    onSeekBarChangeListener.onStopTrackingTouch(seekBar);
                }
            }
        });
    }


    /**
     * SET UP THE EMOJI ICON DEPENDING ON THE INDEX
     *
     * @param index - int
     * @author Ammad Amjad
     */
    private void setIcon(int index) {
        switch (index) {
            case 0:
                emo.setBackgroundResource(R.drawable.emoji_one);
                break;
            case 1:
                emo.setBackgroundResource(R.drawable.emoji_one);
                break;
            case 2:
                emo.setBackgroundResource(R.drawable.emoji_two);
                break;
            case 3:
                emo.setBackgroundResource(R.drawable.emoji_three);
                break;
            case 4:
                emo.setBackgroundResource(R.drawable.emoji_four);
                break;
            case 5:
                emo.setBackgroundResource(R.drawable.emoji_six);
                break;
            case 6:
                emo.setBackgroundResource(R.drawable.emoji_five);
                break;
            case 7:
                emo.setBackgroundResource(R.drawable.emoji_seven);
                break;
            case 8:
                emo.setBackgroundResource(R.drawable.emoji_eight);
                break;
            case 9:
                emo.setBackgroundResource(R.drawable.emoji_nine);
                break;
            case 10:
                emo.setBackgroundResource(R.drawable.emoji_ten);
                break;
        }
    }

    /**
     * SET UP THE TEXT COLOR OF THE NUMBER TEXTVIEW DEPENDING ON THE INDEX
     *
     * @param index - int
     * @param color - int
     * @author Ammad Amjad
     */
    private void setTextColor(int index, int color) {
        if (index < 0) {
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (i == index) {
                texts.get(i).setTextColor(getResources().getColor(color));
            } else {
                texts.get(i).setTextColor(getResources().getColor(R.color.gray));
            }
        }
        setIcon(index);
    }


    /**
     * UPDATE THE TEXTVIEW AND EMOJI ICON DEPENDING ON THE INDEX
     *
     * @param progress - int
     * @author Ammad Amjad
     */
    private void update(int progress) {
        if (progress == 0 || progress < 6) {
            setDrawable(R.color.zero);
            setTextColor(0, R.color.zero);
        } else if (progress > 5 && progress < 11) {
            setDrawable(R.color.one);
            setTextColor(1, R.color.one);
        } else if (progress > 10 && progress < 21) {
            setDrawable(R.color.two);
            setTextColor(2, R.color.two);
        } else if (progress > 20 && progress < 31) {
            setDrawable(R.color.three);
            setTextColor(3, R.color.three);
        } else if (progress > 30 && progress < 41) {
            setDrawable(R.color.four);
            setTextColor(4, R.color.four);
        } else if (progress > 40 && progress < 51) {
            setDrawable(R.color.five);
            setTextColor(5, R.color.five);
        } else if (progress > 50 && progress < 61) {
            setDrawable(R.color.six);
            setTextColor(6, R.color.six);
        } else if (progress > 60 && progress < 71) {
            setDrawable(R.color.seven);
            setTextColor(7, R.color.seven);
        } else if (progress > 70 && progress < 81) {
            setDrawable(R.color.eight);
            setTextColor(8, R.color.eight);
        } else if (progress > 80 && progress < 91) {
            setDrawable(R.color.nine);
            setTextColor(9, R.color.nine);
        } else {
            setDrawable(R.color.ten);
            setTextColor(10, R.color.ten);
        }


    }


    /**
     * SET THE DRAWABLE FOR SEEKBAR WITH PROGRESS COLOR
     *
     * @param color - int
     * @author Ammad Amjad
     */
    private void setDrawable(int color) {
        SeekBarBackgroundDrawable backgroundDrawable = new SeekBarBackgroundDrawable(context);
        GradientDrawable progressDrawable = new GradientDrawable();
        progressDrawable.setColor(getResources().getColor(color));
        progressDrawable.setCornerRadius(100);
        //Custom seek bar progress drawable. Also allows you to modify appearance.
        SeekBarProgressDrawable clipProgressDrawable = new SeekBarProgressDrawable(progressDrawable, Gravity.LEFT, ClipDrawable.HORIZONTAL, context);
        Drawable[] drawables = new Drawable[]{backgroundDrawable, clipProgressDrawable};
        //Create layer drawables with android pre-defined ids
        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.progress);
        //Set to seek bar
        seekbar.setProgressDrawable(layerDrawable);
        seekbar.getThumb().setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_IN);
    }


    class SeekBarBackgroundDrawable extends Drawable {

        private Paint mPaint = new Paint();
        private float dy;

        public SeekBarBackgroundDrawable(Context ctx) {
            mPaint.setColor(getResources().getColor(R.color.gray));
            dy = ctx.getResources().getDimension(R.dimen.two_dp);
        }

        @Override
        public void draw(Canvas canvas) {
            RectF rectF = new RectF(
                    getBounds().left, getBounds().centerY() - dy / 2, getBounds().right, getBounds().centerY() + dy / 2
            );
            int cornersRadius = 500;
            canvas.drawRoundRect(
                    rectF, // rect
                    cornersRadius, // rx
                    cornersRadius, // ry
                    mPaint // Paint
            );

        }

        @Override
        public void setAlpha(int i) {
            mPaint.setAlpha(i);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }
    }


    class SeekBarProgressDrawable extends ClipDrawable {
        private Paint mPaint = new Paint();
        private float dy;
        private Rect mRect;

        public SeekBarProgressDrawable(Drawable drawable, int gravity, int orientation, Context ctx) {
            super(drawable, gravity, orientation);
            mPaint.setColor(getResources().getColor(R.color.gray));
            dy = ctx.getResources().getDimension(R.dimen.two_dp);
        }

        @Override
        public void draw(Canvas canvas) {
            if (mRect == null) {
                mRect = new Rect(getBounds().left, (int) (getBounds().centerY() - dy / 2), getBounds().right, (int) (getBounds().centerY() + dy / 2));
                setBounds(mRect);
            }
            super.draw(canvas);
        }


        @Override
        public void setAlpha(int i) {
            mPaint.setAlpha(i);
        }

        @Override
        public void setColorFilter(ColorFilter colorFilter) {

        }

        @Override
        public int getOpacity() {
            return PixelFormat.TRANSLUCENT;
        }

    }

    /**
     * SET THE EXTERNAL SEEKBAR CHANGE LISTENER
     *
     * @param onSeekBarChangeListener - SeekBar.OnSeekBarChangeListener
     * @author Ammad Amjad
     */
    public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.onSeekBarChangeListener = onSeekBarChangeListener;
    }
}
