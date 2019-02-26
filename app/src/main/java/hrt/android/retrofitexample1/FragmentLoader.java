package hrt.android.retrofitexample1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FragmentLoader extends Fragment
{
    TextView textView;
    ImageView imageView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.viewpager_item,container,false);
        textView = view.findViewById(R.id.textView);
        textView.setText(getArguments().getString("info"));

        imageView = view.findViewById(R.id.imageView);
        Glide.with(getContext()).load(getArguments().getString("image")).into(imageView);
        return view;
    }

    public static FragmentLoader newInstance(String info,String image) {

        Bundle args = new Bundle();
        args.putString("info",info);
        args.putString("image",image);
        FragmentLoader fragment = new FragmentLoader();
        fragment.setArguments(args);
        return fragment;
    }
}
