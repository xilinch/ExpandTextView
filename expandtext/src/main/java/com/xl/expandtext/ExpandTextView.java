package com.xl.expandtext;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ExpandTextView extends FrameLayout {

    private String textContent = "";

    private String textExpand = "展开";

    private TextView textViewContent;

    private TextView textViewExpand;

    public ExpandTextView(Context context) {
        super(context);
        init();
    }

    public ExpandTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExpandTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        textViewContent = new TextView(getContext());
        textViewContent.setTextColor(Color.parseColor("#666666"));
        textViewContent.setTextSize(13);
        textViewExpand = new TextView(getContext());
        textViewExpand.setText(textExpand);
        textViewExpand.setTextSize(13);
        textViewExpand.setTextColor(Color.BLUE);
        textViewExpand.setVisibility(GONE);
        FrameLayout.LayoutParams layoutParamForTextViewContent = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams layoutParamForTextViewExpand = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//        layoutParamForTextViewExpand.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        layoutParamForTextViewExpand.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParamForTextViewExpand.gravity = Gravity.BOTTOM|Gravity.RIGHT;
        addView(textViewContent,layoutParamForTextViewContent);
        addView(textViewExpand,layoutParamForTextViewExpand);
    }

    /**
     * 设置内容
     * @param content
     */
    public void setTextContent(String content){
        this.textContent = content;
        if(textContent == null){
            textContent = "";
        }
        calculateTextExpand();
    }


    private void calculateTextExpand(){
        String showDescribe = textContent;
        float mesureLength = textViewContent.getPaint().measureText(textContent);
        mesureLength = DensityUtils.pxTodip(getContext(), mesureLength);


        float infoLength = DensityUtils.getScreenWidth(getContext()) - DensityUtils.dipTopx(getContext(), 100);
        infoLength = DensityUtils.pxTodip(getContext(), infoLength);
        float lineLengthFor2 = infoLength *2 - 10;
        int maxNum = 0;
        if(mesureLength > lineLengthFor2){
            textViewExpand.setVisibility(View.VISIBLE);
            maxNum = (int)((lineLengthFor2 - 30) * textContent.length() / mesureLength);
            if(maxNum > 0 && maxNum <= textContent.length()){
                showDescribe = showDescribe.substring(0,maxNum) + "...";
            }
        } else {
            textViewExpand.setVisibility(View.GONE);
        }
        Log.e("my","mesureLength:" + mesureLength + " infoLength:" + infoLength + "  lineLengthFor2:" + lineLengthFor2 + " maxNum:" + maxNum);
        textViewContent.setText(showDescribe);
        textViewContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewContent.setText(textContent);
                textViewExpand.setVisibility(View.GONE);
                textViewContent.setMaxLines(Integer.MAX_VALUE);
            }
        });
    }



}
