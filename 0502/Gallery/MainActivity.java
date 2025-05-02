package com.example.gallery;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private final List<Integer> imageResIds = new ArrayList<>();
    private final List<String> captions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 이미지와 자막 데이터
        for (int i = 1; i <= 10; i++) {
            int resId = getResources().getIdentifier("image" + i, "drawable", getPackageName());
            imageResIds.add(resId);
            captions.add("주식 화면 구현");
            captions.add("뷰티앱 구현");
            captions.add("앱 디자인 툴");
            captions.add("사진 저장 플렛폼");
            captions.add("포트폴리오 제작 앱");
            captions.add("전기차 관리 어플");
            captions.add("배달 앱 구현");
            captions.add("얼굴 보정 앱");
            captions.add("티머니 앱");
            captions.add("루모스 앱");
        }

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new GalleryAdapter(this));
    }

    class GalleryAdapter extends FragmentStateAdapter {
        public GalleryAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return SlideFragment.newInstance(imageResIds.get(position), captions.get(position));
        }

        @Override
        public int getItemCount() {
            return imageResIds.size();
        }
    }

    public static class SlideFragment extends Fragment {

        private static final String ARG_IMAGE_RES = "image_res";
        private static final String ARG_CAPTION = "caption";

        public static SlideFragment newInstance(int imageResId, String caption) {
            SlideFragment fragment = new SlideFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_IMAGE_RES, imageResId);
            args.putString(ARG_CAPTION, caption);
            fragment.setArguments(args);
            return fragment;
        }

        @Nullable
        @Override
        public android.view.View onCreateView(@NonNull android.view.LayoutInflater inflater,
                                              @Nullable android.view.ViewGroup container,
                                              @Nullable Bundle savedInstanceState) {

            int imageRes = getArguments().getInt(ARG_IMAGE_RES);
            String caption = getArguments().getString(ARG_CAPTION);

            LinearLayout layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);
            layout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            ));

            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(imageRes);
            imageView.setAdjustViewBounds(true);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(
                    600,
                    600
            ));

            TextView captionView = new TextView(getContext());
            captionView.setText(caption);
            captionView.setGravity(Gravity.CENTER);
            captionView.setTextSize(18);
            captionView.setPadding(0, 20, 0, 0);

            layout.addView(imageView);
            layout.addView(captionView);

            return layout;
        }
    }
}
