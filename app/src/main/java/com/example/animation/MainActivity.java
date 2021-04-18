package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
//    AnimationDrawable front_anim, back_anim;
    AnimatorSet front_anim, back_anim, front_anim_re, back_anim_re;
    ImageButton front_card, back_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        float scale = getApplicationContext().getResources().getDisplayMetrics().density;


        front_card = findViewById(R.id.front_card);
        back_card = findViewById(R.id.back_card);

        front_card.setCameraDistance(8000 * scale);
        back_card.setCameraDistance(8000 * scale);

        front_anim = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.front_animation);
        back_anim = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.back_animation);
        front_anim_re = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.front_animation_reverse);
        back_anim_re = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.back_animation_reverse);

        Boolean isMatching = true;
        final Boolean[] isFront = {true};
        View.OnClickListener cardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFront[0] == true){
                    front_anim.setTarget(front_card);
                    back_anim.setTarget(back_card);
                    front_anim.start();
                    back_anim.start();
                    isFront[0] = false;
                }
                else {
                    back_anim_re.setTarget(back_card);
                    front_anim_re.setTarget(front_card);
                    back_anim_re.start();
                    front_anim_re.start();
                    isFront[0] = true;
                }
            }
        };
        front_card.setOnClickListener(cardClickListener);
        back_card.setOnClickListener(cardClickListener);
    }
}