package com.xczn.substation;

import android.os.Bundle;

import com.xczn.substation.fragment.MainFragment;
import com.xczn.substation.fragment.ServerFragment;
import com.xczn.substation.mqtt.MqttUtils;
import com.xczn.substation.util.SharedPreferencesUtils;

import org.eclipse.paho.client.mqttv3.MqttException;

import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPreferencesUtils.getInstance().isFirstEnter()) {
            if (findFragment(ServerFragment.class) == null) {
                loadRootFragment(R.id.main_container, MainFragment.newInstance());
            }
        } else if (findFragment(MainFragment.class) == null) {
            loadRootFragment(R.id.main_container, MainFragment.newInstance());
        }
    }
}
