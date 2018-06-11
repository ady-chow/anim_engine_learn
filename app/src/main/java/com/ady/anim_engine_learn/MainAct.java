package com.ady.anim_engine_learn;

import static com.ady.anim_engine_learn.AnimHelper.ANIMATIONS;
import static com.ady.anim_engine_learn.AnimHelper.ICE_BREAK_KISS;
import static com.ady.anim_engine_learn.AnimHelper.ICE_BREAK_LAUGH;
import static com.ady.anim_engine_learn.AnimHelper.ICE_BREAK_PEACH;
import static com.ady.anim_engine_learn.AnimHelper.LIKE;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Button;
import com.sunshine.engine.bone.StageView;

/** Created by ady on 2018/6/6. */
public class MainAct extends Activity {

  private boolean showingLayout;

  private static final String BONE_ANIM_LIKE = "bone_anim_like";
  private static final String BONE_ANIM_LAUGH = "bone_anim_laugh";
  private static final String BONE_ANIM_PEACH = "bone_anim_peach";
  private static final String BONE_ANIM_KISS = "bone_anim_kiss";

  Button _btn_like;
  Button _btn_laugh;
  Button _btn_peach;
  Button _btn_kiss;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.act_main);
    _btn_like = findViewById(R.id.bone_anim_like);
    _btn_laugh = findViewById(R.id.bone_anim_laugh);
    _btn_peach = findViewById(R.id.bone_anim_peach);
    _btn_kiss = findViewById(R.id.bone_anim_kiss);
  }

  public void show(View v) {
    showingLayout = true;
    String tag = (String) v.getTag();
    int id = getResources().getIdentifier(tag, "layout", getPackageName());
    setContentView(id);
    switch (tag) {
      case BONE_ANIM_LIKE:
        animLike();
        break;
      case BONE_ANIM_LAUGH:
        animLaugh();
        break;
      case BONE_ANIM_PEACH:
        animPeach();
        break;
      case BONE_ANIM_KISS:
        animKiss();
        break;
    }
  }

  private void animLike() {
    StageView like = findViewById(R.id.like);
    Pair<String, String> pair = ANIMATIONS.get(LIKE);
    like.play(pair.first, pair.second);
    like.autoStop(false);
    like.setPercent(0);
    like.setPercent(1f, 200);
  }

  private void animLaugh() {
    StageView laugh = findViewById(R.id.laugh);
    Pair<String, String> pair = ANIMATIONS.get(ICE_BREAK_LAUGH);
    laugh.play(pair.first, pair.second);
    laugh.autoStop(false);
    laugh.isRepeat(true);
    laugh.setPercent(1f, 4000);
  }

  private void animPeach() {
    StageView peach = findViewById(R.id.peach);
    Pair<String, String> pair = ANIMATIONS.get(ICE_BREAK_PEACH);
    peach.play(pair.first, pair.second);
    peach.autoStop(false);
    peach.setPercent(1f, 3500);
  }

  private void animKiss() {
    StageView kiss = findViewById(R.id.kiss);
    Pair<String, String> pair = ANIMATIONS.get(ICE_BREAK_KISS);
    kiss.play(pair.first, pair.second);
    kiss.autoStop(false);
    kiss.isRepeat(true);
    kiss.setPercent(1f, 5000);
  }

  @Override
  public void onBackPressed() {
    if (showingLayout) {
      showingLayout = false;
      setContentView(R.layout.act_main);
    } else {
      super.onBackPressed();
    }
  }
}
