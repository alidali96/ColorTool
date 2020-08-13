package ca.alidali.colortool.ui.main;


import android.arch.lifecycle.ViewModel;

import ca.alidali.colortool.models.ColorTool;


/**
 * @author Ali Dali
 * @since 12-08-2020
 */
public class ColorToolViewModel extends ViewModel {
    public static final String TAG = ColorToolViewModel.class.getSimpleName();

    public ColorTool colorTool = new ColorTool();

    public ColorToolViewModel() {
        colorTool.setRed(colorTool.getMax());
        colorTool.setGreen(colorTool.getMax());
        colorTool.setBlue(colorTool.getMax());

    }

    public void generateRandomColor() {
        colorTool.generateRandomColor();
    }

}
