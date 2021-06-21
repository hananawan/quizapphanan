package com.example.quizapphanan.utils;

import java.util.Arrays;
import java.util.List;

public class colorpicker {
    int currentcolorindex = 0;
    List<String> colors = Arrays.asList("#3EB9DF",
            "#3685BC",
            "#D36280",
            "#E44F55",
            "#FA8056",
            "#818BCA",
            "#7D659F",
            "#51BAB3",
            "#4FB66C",
            "#E3AD17",
            "#627991",
            "#EF8EAD",
            "#B5BFC6");

    private static colorpicker color_picker = null;
    public static  colorpicker getInstance(){
        if(color_picker == null) {

         color_picker = new colorpicker();

        }
        return color_picker;
    }
    public String getcolor()
    {
        currentcolorindex = (currentcolorindex + 1) % colors.size();
        return colors.get(currentcolorindex);
    }
}
