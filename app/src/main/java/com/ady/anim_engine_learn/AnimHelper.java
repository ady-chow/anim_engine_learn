package com.ady.anim_engine_learn;

import android.support.v4.util.Pair;
import java.util.HashMap;

/**
 * Created by ady on 2018/2/28.
 */
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

  static {
    ANIMATIONS.put(LIKE, Cu.pair("animations/like/config.xml", "animations/like/pic"));
    ANIMATIONS.put(PASS, Cu.pair("animations/pass/config.xml", "animations/pass/pic"));
    ANIMATIONS.put(MATCH, Cu.pair("animations/match/config.xml", "animations/match/pic"));
    ANIMATIONS.put(MATCH_BG, Cu.pair("animations/match_bg/config.xml", "animations/match_bg/pic"));
    ANIMATIONS.put(
        WELCOME_OMI, Cu.pair("animations/welcome_omi/config.xml", "animations/welcome_omi/pic"));
    ANIMATIONS.put(
        WELCOME_OMI_BG,
        Cu.pair("animations/welcome_omi_bg/config.xml", "animations/welcome_omi_bg/pic"));
    ANIMATIONS.put(ICE_BREAK_LAUGH,
        Cu.pair("animations/ice_break_laugh/config.xml", "animations/ice_break_laugh/pic"));
  }
}
