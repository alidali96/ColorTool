package ca.alidali.colortool.ui.main;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ObservableInt;
import android.os.Bundle;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import java.util.ArrayList;

import ca.alidali.colortool.databinding.ColorToolFragmentBinding;


/**
 * @author Ali Dali
 * @since 12-08-2020
 */
public class ColorToolFragment extends Fragment {

    public static final String TAG = ColorToolFragment.class.getSimpleName();

    private ColorToolViewModel mViewModel;
    private ColorToolFragmentBinding binding;

    public static ColorToolFragment newInstance() {
        return new ColorToolFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ColorToolViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = ColorToolFragmentBinding.inflate(inflater);
        binding.setViewModel(mViewModel);
        setObservers();
        return binding.getRoot();
    }

    private void setObservers() {
        if (binding == null) return;

        final ArrayList<ObservableInt> colorObservers = new ArrayList<>();
        colorObservers.add(mViewModel.colorTool.getRed());
        colorObservers.add(mViewModel.colorTool.getGreen());
        colorObservers.add(mViewModel.colorTool.getBlue());


        final ArrayList<SeekBar> colorSeekBars = new ArrayList<>();
        colorSeekBars.add(binding.redSeekBar);
        colorSeekBars.add(binding.greenSeekBar);
        colorSeekBars.add(binding.blueSeekBar);

        for (int i = 0; i < colorObservers.size(); i++) {
            final int index = i;
            colorSeekBars.get(index).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    colorObservers.get(index).set(seekBar.getProgress());
                    mViewModel.colorTool.update();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });

        }
    }
}
