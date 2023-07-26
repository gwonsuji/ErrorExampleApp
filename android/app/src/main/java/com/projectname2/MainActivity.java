package com.projectname2;
import expo.modules.ReactActivityDelegateWrapper;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;

import android.os.Bundle;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Base64;
import android.util.Log;
import org.apache.commons.codec.binary.StringUtils;
import androidx.annotation.NonNull;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "ProjectName2";
  }

  /**
   * Returns the instance of the {@link ReactActivityDelegate}. There the RootView is created and
   * you can specify the renderer you wish to use - the new renderer (Fabric) or the old renderer
   * (Paper).
   */
  @Override
  protected ReactActivityDelegate createReactActivityDelegate() {
    return new MainActivityDelegate(this, getMainComponentName());
  }

  public static class MainActivityDelegate extends ReactActivityDelegate {
    public MainActivityDelegate(ReactActivity activity, String mainComponentName) {
      super(activity, mainComponentName);
    }

    @Override
    protected ReactRootView createRootView() {
      ReactRootView reactRootView = new ReactRootView(getContext());
      // If you opted-in for the New Architecture, we enable the Fabric Renderer.
      reactRootView.setIsFabric(BuildConfig.IS_NEW_ARCHITECTURE_ENABLED);
      return reactRootView;
    }

    @Override
    protected boolean isConcurrentRootEnabled() {
      // If you opted-in for the New Architecture, we enable Concurrent Root (i.e. React 18).
      // More on this on https://reactjs.org/blog/2022/03/29/react-v18.html
      return BuildConfig.IS_NEW_ARCHITECTURE_ENABLED;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // getReleaseHashKey();
    super.onCreate(savedInstanceState);
  }

  /**
   * react-native-orientation-locker 설정
   * @param newConfig
   */
  @Override
  public void onConfigurationChanged(@NonNull Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    Intent intent = new Intent("onConfigurationChanged");
    intent.putExtra("newConfing", newConfig);
    this.sendBroadcast(intent);
  }

  private void getReleaseHashKey() {
    byte[] sha1 = {
      (byte) 0x9C, (byte) 0xDF, 0x6A, (byte) 0x6C, (byte) 0xCF, (byte) 0x9A
      , (byte) 0xEB, (byte) 0x07, (byte) 0xC4, (byte) 0x97, (byte) 0xFA, (byte) 0x4F, (byte) 0xE5
      , (byte) 0x0C, (byte) 0x3E, (byte) 0x50, (byte) 0x5E, (byte) 0x03, (byte) 0xF9, (byte) 0x78
    };

    Log.i("getReleaseHashKey", "getHashKey : " + Base64.encodeToString(sha1, Base64.NO_WRAP));
  }
}
