package com.sunshine.engine.bone.logic;

import android.graphics.Bitmap;

import com.sunshine.engine.base.AnimLoader;
import com.sunshine.engine.base.Tool;
import com.sunshine.engine.base.ViewHelper;

public class StageHelper extends ViewHelper<Stage> {
  @Override
  protected Stage buildEntity(
      ViewHelper helper, String configPath, String picPath, String soundPath) {
    Stage stage = new Stage(this, configPath, picPath, soundPath);
    AnimLoader.load(stage);
    return stage;
  }

  public void canJump(boolean can) {
    if (entity != null) {
      entity.canJump = can;
    }
  }

  public void setPercent(float percent, int duration) {
    if (entity != null) {
      setPercent(entity.getPercent(), percent, duration);
    }
  }

  public void setPercent(float from, float to, int duration) {
    if (entity != null) {
      from = Tool.checkPercent(from, 0, 1);
      to = Tool.checkPercent(to, 0, 1);
      entity.setPercent(from, to, duration);
    }
  }

  public boolean setExternalBmp(String id, Bitmap bmp) {
    if (entity != null) {
      if (bmp == null || bmp.isRecycled()) {
        entity.mapBmp.remove(id);
      } else {
        entity.mapBmp.put(id, bmp);
      }
      return true;
    } else {
      return false;
    }
  }
}
