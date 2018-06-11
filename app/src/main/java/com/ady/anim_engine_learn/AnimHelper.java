package com.ady.anim_engine_learn;

import android.support.v4.util.Pair;
import java.util.HashMap;

/** Created by ady on 2018/2/28. */
public class AnimHelper {

  public static HashMap<String, Pair<String, String>> ANIMATIONS = new HashMap<>();
  public static final String LIKE = "like";
  public static final String PASS = "pass";
  public static final String MATCH = "match";
  public static final String MATCH_BG = "match_bg";
  public static final String WELCOME_OMI = "welcome_omi";
  public static final String WELCOME_OMI_BG = "welcome_omi_bg";
  public static final String WELCOME_OMI_SOUND = "animations/welcome_omi/sound.mp3";
  public static final String ICE_BREAK_LAUGH = "ice_break_laugh";
  public static final String ICE_BREAK_PEACH = "ice_break_peach";
  public static final String ICE_BREAK_KISS = "ice_break_kiss";

  static {
    ANIMATIONS.put(LIKE, ANIM_PAIR(LIKE));
    ANIMATIONS.put(PASS, ANIM_PAIR(PASS));
    ANIMATIONS.put(MATCH, ANIM_PAIR(MATCH));
    ANIMATIONS.put(MATCH_BG, ANIM_PAIR(MATCH_BG));
    ANIMATIONS.put(WELCOME_OMI, ANIM_PAIR(WELCOME_OMI));
    ANIMATIONS.put(WELCOME_OMI_BG, ANIM_PAIR(WELCOME_OMI_BG));
    ANIMATIONS.put(ICE_BREAK_LAUGH, ANIM_PAIR(ICE_BREAK_LAUGH));
    ANIMATIONS.put(ICE_BREAK_PEACH, ANIM_PAIR(ICE_BREAK_PEACH));
    ANIMATIONS.put(ICE_BREAK_KISS, ANIM_PAIR(ICE_BREAK_KISS));
  }

  private static Pair<String, String> ANIM_PAIR(String name) {
    return Cu.pair("animations/" + name + "/config.xml", "animations/" + name + "/pic");
  }
}
