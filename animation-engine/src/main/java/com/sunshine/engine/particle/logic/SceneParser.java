package com.sunshine.engine.particle.logic;

import com.sunshine.engine.base.Area;
import com.sunshine.engine.base.XmlParser;

public class SceneParser extends XmlParser {
  private Scene scene = null;

  private static final String MAX = "max";
  private static final String MODEL = "model";
  private static final String CHANCE_RANGE = "chance_range";
  private static final String ACTIVE_TIME = "active_time";

  private static final String MOVE_FROM = "move_from";
  private static final String MOVE_TO = "move_to";
  private static final String MOVE_INTERPOLATOR = "move_interpolator";
  private static final String MATCH_PARENT = "match_parent";
  private static final String OFFSET = "offset:";

  private static final String ROTATE = "rotate";
  private static final String ROTATE_INTERPOLATOR = "rotate_interpolator";
  private static final String ALPHA = "alpha";
  private static final String ALPHA_INTERPOLATOR = "alpha_interpolator";
  private static final String SCALE = "scale";
  private static final String SCALE_INTERPOLATOR = "scale_interpolator";

  public SceneParser(Scene sc) {
    scene = sc;
  }

  @Override
  public void parseTag(String tag, String[] ary, boolean start) {

    if (start) {
      if (tag.equals(MODEL)) {
        scene.addParticleModel(new ParticleModel());
      }
    } else {
      ParticleModel pm;
      switch (tag) {
        case WIDTH_HEIGHT:
          scene.scriptSize.width = Integer.parseInt(ary[0]);
          scene.scriptSize.height = Integer.parseInt(ary[1]);
          break;
        case MAX:
          scene.setMaxParticle(Integer.parseInt(ary[0]));
          break;
        case DURATION:
          scene.duration = Integer.parseInt(ary[0]);
          break;
        case LAYOUT_TYPE:
          scene.layoutType = ary[0];
          break;
        case CHANCE_RANGE:
          pm = scene.getLastParticleModel();
          pm.chanceRange.set(Float.parseFloat(ary[0]), Float.parseFloat(ary[1]));
          break;
        case ACTIVE_TIME:
          pm = scene.getLastParticleModel();
          pm.activeTime.set(Integer.parseInt(ary[0]), Integer.parseInt(ary[1]));
          break;
        case SRC_LTWH:
          pm = scene.getLastParticleModel();
          pm.rcBmp.left = Integer.parseInt(ary[0]);
          pm.rcBmp.top = Integer.parseInt(ary[1]);
          pm.size.width = Integer.parseInt(ary[2]);
          pm.rcBmp.right = pm.rcBmp.left + pm.size.width;
          pm.size.height = Integer.parseInt(ary[3]);
          pm.rcBmp.bottom = pm.rcBmp.top + pm.size.height;
          break;
        case MOVE_FROM:
          pm = scene.getLastParticleModel();
          pm.areaFrom.l = Integer.parseInt(ary[0]);
          pm.areaFrom.t = Integer.parseInt(ary[1]);
          if (MATCH_PARENT.equals(ary[2])) {
            pm.areaFrom.w = Area.MATCH_PARENT;
          } else {
            pm.areaFrom.w = Integer.parseInt(ary[2]);
          }
          pm.areaFrom.h = Integer.parseInt(ary[3]);
          break;
        case MOVE_TO:
          pm = scene.getLastParticleModel();
          if (ary[0].contains(OFFSET)) {
            pm.areaTo.isOffsetLeft = true;
            ary[0] = ary[0].replace(OFFSET, NONE);
            pm.areaTo.l = Integer.parseInt(ary[0]);
          } else {
            pm.areaTo.isOffsetLeft = false;
            pm.areaTo.l = Integer.parseInt(ary[0]);
          }
          if (ary[1].contains(OFFSET)) {
            pm.areaTo.isOffsetTop = true;
            ary[1] = ary[1].replace(OFFSET, NONE);
            pm.areaTo.t = Integer.parseInt(ary[1]);
          } else {
            pm.areaTo.isOffsetTop = false;
            pm.areaTo.t = Integer.parseInt(ary[1]);
          }
          if (MATCH_PARENT.equals(ary[2])) {
            pm.areaTo.w = Area.MATCH_PARENT;
          } else {
            pm.areaTo.w = Integer.parseInt(ary[2]);
          }
          pm.areaTo.h = Integer.parseInt(ary[3]);
          break;
        case MOVE_INTERPOLATOR:
          pm = scene.getLastParticleModel();
          pm.interpolatorMove[0] = ary[0];
          pm.interpolatorMove[1] = ary[1];
          break;
        case ROTATE:
          pm = scene.getLastParticleModel();
          pm.rotateBegin.set(Integer.parseInt(ary[0]), Integer.parseInt(ary[1]));
          if (ary.length == 6) {
            pm.rotateEnd.set(Integer.parseInt(ary[2]), Integer.parseInt(ary[3]));
            pm.ptRotate.x = Float.parseFloat(ary[4]);
            pm.ptRotate.y = Float.parseFloat(ary[5]);
          } else {
            pm.rotateEnd = null;
            pm.ptRotate.x = Float.parseFloat(ary[2]);
            pm.ptRotate.y = Float.parseFloat(ary[3]);
          }
          break;
        case ROTATE_INTERPOLATOR:
          pm = scene.getLastParticleModel();
          pm.interpolatorRotate = ary[0];
          break;
        case ALPHA:
          pm = scene.getLastParticleModel();
          pm.alphaBegin.set(Integer.parseInt(ary[0]), Integer.parseInt(ary[1]));
          if (ary.length == 2) {
            pm.alphaEnd = null;
          } else {
            pm.alphaEnd.set(Integer.parseInt(ary[2]), Integer.parseInt(ary[3]));
          }
          break;
        case ALPHA_INTERPOLATOR:
          pm = scene.getLastParticleModel();
          pm.interpolatorAlpha = ary[0];
          break;
        case SCALE:
          pm = scene.getLastParticleModel();
          pm.scaleBegin.set(Float.parseFloat(ary[0]), Float.parseFloat(ary[1]));
          if (ary.length == 4) {
            pm.scaleEnd.set(Float.parseFloat(ary[2]), Float.parseFloat(ary[3]));
          } else {
            pm.scaleEnd = null;
          }
          break;
        case SCALE_INTERPOLATOR:
          pm = scene.getLastParticleModel();
          pm.interpolatorScale = ary[0];
          break;
        default:
          break;
      }
    }
  }
}
