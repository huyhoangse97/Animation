package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
//    AnimationDrawable front_anim, back_anim;
    AnimatorSet front_anim, back_anim, front_anim_re, back_anim_re;
    AnimatorSet front_anim_all, back_anim_all;
    ImageView front_card, back_card;
    Button btn_flip, btn_spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        float scale = getApplicationContext().getResources().getDisplayMetrics().density;


        front_card = findViewById(R.id.front_card);
        back_card = findViewById(R.id.back_card);
        btn_flip = findViewById(R.id.btn_flip);
        btn_spin = findViewById(R.id.btn_spin);

        front_card.setCameraDistance(8000 * scale);
        back_card.setCameraDistance(8000 * scale);

        front_anim = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.front_animation);
        back_anim = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.back_animation);
        front_anim_re = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.front_animation_reverse);
        back_anim_re = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.back_animation_reverse);

        front_anim_all = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.front_animation_allround);
        back_anim_all = (AnimatorSet) AnimatorInflater.loadAnimator(this.getApplicationContext(), R.animator.back_animation_allround);

        Boolean isMatching = true;
        final Boolean[] isFront = {true};
        View.OnClickListener flipClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                if (isFront[0] == true){
                    front_anim.setTarget(front_card);
                    back_anim.setTarget(back_card);
                    Animator.AnimatorListener flipClickListener = new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            btn.setText("Reserve");
                            btn_spin.setEnabled(false);
                            isFront[0] = false;
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {

                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    };
                    front_anim.addListener(flipClickListener);
                    front_anim.start();
                    back_anim.start();
                }
                else {
                    back_anim_re.setTarget(back_card);
                    front_anim_re.setTarget(front_card);
                    Animator.AnimatorListener reserveClickListener = new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                        }

                        @Override
                        public void onAnimationEnd(Animator animation) {
                            isFront[0] = true;
                            btn.setText("Flip");
                            btn_spin.setEnabled(true);
                        }

                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }
                    };
                    front_anim_re.addListener(reserveClickListener);
                    back_anim_re.start();
                    front_anim_re.start();
                }
            }
        };

        View.OnClickListener spinClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back_anim_all.setTarget(back_card);
                front_anim_all.setTarget(front_card);
                Animator.AnimatorListener spinListener = new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        btn_flip.setEnabled(false);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        btn_flip.setEnabled(true);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                };
                front_anim_all.addListener(spinListener);
                back_anim_all.start();
                front_anim_all.start();
            }
        };

        btn_flip.setOnClickListener(flipClickListener);
        btn_spin.setOnClickListener(spinClickListener);
    }
}