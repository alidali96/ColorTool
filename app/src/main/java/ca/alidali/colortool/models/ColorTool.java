package ca.alidali.colortool.models;

import android.databinding.ObservableInt;
import android.graphics.Color;

import com.android.databinding.library.baseAdapters.BR;

import java.util.Observable;
import java.util.Random;

/**
 * @author Ali Dali
 * @since 12-08-2020
 */
public class ColorTool extends Observable {
    public static final String TAG = ColorTool.class.getSimpleName();

    private final ObservableInt color = new ObservableInt();

    private final ObservableInt red = new ObservableInt();
    private final ObservableInt green = new ObservableInt();
    private final ObservableInt blue = new ObservableInt();

    final private int min = 0;
    final private int max = 255;

    final Random random = new Random();

    public ColorTool() {
    }

    public ColorTool(int red, int green, int blue) {
        this.red.set(red);
        this.green.set(green);
        this.blue.set(blue);
    }

    public ColorTool generateRandomColor() {
        red.set(random.nextInt(max));
        green.set(random.nextInt(max));
        blue.set(random.nextInt(max));
        update();
        return this;
    }

    public ObservableInt getColor() {
        return color;
    }

    public ObservableInt getRed() {
        return red;
    }

    public ObservableInt getGreen() {
        return green;
    }

    public ObservableInt getBlue() {
        return blue;
    }


    public void setColor(int color) {
        this.color.set(color);
        update();
    }

    public void setRed(int red) {
        this.red.set(red);
    }

    public void setGreen(int green) {
        this.green.set(green);
    }

    public void setBlue(int blue) {
        this.blue.set(blue);
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }


    public void update() {
        color.set(Color.rgb(red.get(), green.get(), blue.get()));
        notifyObservers(BR.viewModel);
    }

}
