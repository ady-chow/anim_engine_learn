package com.sunshine.engine.base;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AnimLoader {
  protected static List<Entity> queue = new ArrayList<>();
  protected static boolean isRun = false;

  public static synchronized void load(Entity entity) {
    if (entity != null) {
      queue.add(entity);
      if (!isRun) {
        isRun = true;
        new Thread(new ParseRunnable()).start();
      }
    }
  }

  private static synchronized Entity getEntity() {
    if (queue.size() > 0) {
      return queue.remove(0);
    } else {
      isRun = false;
      return null;
    }
  }

  private static boolean parse(InputStream is, Entity entity) {
    boolean success = false;
    try {
      SAXParserFactory sf = SAXParserFactory.newInstance();
      SAXParser sp = sf.newSAXParser();
      sp.parse(is, entity.getParser());
      success = true;
    } catch (Exception e) {
      Tool.log(e);
    }
    return success;
  }

  private static class ParseRunnable implements Runnable {
    @Override
    public void run() {
      android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_BACKGROUND);
      Entity entity = getEntity();
      while (entity != null) {
        InputStream is = null;
        boolean success = false;
        try {
          boolean readXml;
          boolean inAsset = !new File(entity.configPath).exists();
          if (inAsset) {
            Context context = entity.helper.getContext();
            is = context.getAssets().open(entity.configPath);
          } else {
            File f = new File(entity.configPath);
            is = new FileInputStream(f);
          }
          readXml = parse(is, entity);
          if (readXml) {
            entity.parsed = true;
            LayoutHelper.resize(entity);
            Bitmap bmp = null;
            if (inAsset) {
              bmp = Tool.getBmpByAssets(entity.helper.getContext(), entity.picPath);
            } else {
              bmp = BitmapFactory.decodeFile(entity.picPath);
            }
            MediaPlayer sound = null;
            if (entity.soundPath != null) {
              if (inAsset) {
                AssetFileDescriptor fd =
                    Tool.getAssetFileDescriptor(entity.helper.getContext(), entity.soundPath);
                if (fd != null) {
                  sound = new MediaPlayer();
                  sound.reset();
                  sound.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
                }
              } else {
                sound = new MediaPlayer();
                sound.reset();
                sound.setDataSource(entity.soundPath);
              }
            }
            if (bmp != null) {
              if (sound != null) {
                sound.prepare();
              }
              entity.setSrcAsync(bmp, sound);
              success = true;
            }
          }
        } catch (Exception e) {
          Tool.log(e);
        } finally {
          if (is != null) {
            try {
              is.close();
            } catch (IOException e) {
              Tool.log(e);
            }
          }
          if (!success) {
            entity.helper.stopAsync(entity);
          }
        }
        entity = getEntity();
      }
    }
  }
}
