package com.example.quizapphanan.utils;

import com.example.quizapphanan.R;

import java.util.Arrays;
import java.util.List;

public class IconPicker {
    int currenticonindex = 0;
    List<Integer> icons = Arrays.asList(R.drawable.ic_icon_1,R.drawable.ic_icon_2,R.drawable.ic_icon_3,R.drawable.ic_icon_4,R.drawable.ic_icon_5,R.drawable.ic_icon_6,R.drawable.ic_icon_7,R.drawable.ic_icon_8);
    private static IconPicker icon_picker = null;
    public static  IconPicker getInstance(){
        if(icon_picker == null) {

            icon_picker = new IconPicker();

        }
        return icon_picker;
    }
    public Integer getIcon()
    {
        currenticonindex = (currenticonindex + 1) % icons.size();
        return icons.get(currenticonindex);
    }
}

