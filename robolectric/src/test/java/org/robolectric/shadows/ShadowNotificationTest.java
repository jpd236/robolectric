package org.robolectric.shadows;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.RuntimeEnvironment.application;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Shadows;
import org.robolectric.TestRunners;
import org.robolectric.annotation.Config;

@RunWith(TestRunners.MultiApiSelfTest.class)
public class ShadowNotificationTest {

  @Test
  public void setLatestEventInfo__shouldCaptureContentIntent() throws Exception {
    PendingIntent pendingIntent = PendingIntent.getActivity(application, 0, new Intent(), 0);
    Notification notification = new Notification();
    notification.setLatestEventInfo(application, "title", "content", pendingIntent);
    assertThat(notification.contentIntent).isSameAs(pendingIntent);
  }

  @Test
  @Config(manifest = "src/test/resources/TestAndroidManifestWithTargetSdkN.xml")
  public void getContentTitle__targetSdkN() throws Exception {
    assertThat(application.getApplicationInfo().targetSdkVersion).isEqualTo(Build.VERSION_CODES.N);
    Notification notification =
        new Notification.Builder(application).setContentTitle("title").build();
    assertThat(Shadows.shadowOf(notification).getContentTitle().toString()).isEqualTo("title");
  }
}
