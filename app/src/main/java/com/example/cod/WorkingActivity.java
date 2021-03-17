package com.example.cod;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

public class WorkingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working_activity);
        String clientId = MqttClient.generateClientId();
        MqttAndroidClient mqttAndroidClient =  new MqttAndroidClient(getApplicationContext(), "tcp://broker.hivemq.com:1883",
                clientId);
    }

    //play alaram sound
    //call this method for rining the alaram sound when co2 levels are reached...
    void ringAlaram(){
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.play();
    }


   //connect to mqtt
    void connectToMqtt(final MqttAndroidClient client) {
        try {
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    subscribeToMqttChannel(client);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    void subscribeToMqttChannel(MqttAndroidClient client) {
        String topic = "demo";
        int qos = 1;
        try {
            IMqttToken subToken = client.subscribe(topic, qos, iMqttMessageListener);

            /*subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // The message was published
                    Timber.d("Mqtt channel subscribe success");
                }
                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    Timber.d("Mqtt channel subscribe error %s",exception.getMessage());
                    // The subscription could not be performed, maybe the user was not
                    // authorized to subscribe on the specified topic e.g. using wildcards
                }
            });
            */
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    // this is the main method which reads data from mqtt, co2 values
    IMqttMessageListener iMqttMessageListener = new IMqttMessageListener() {
        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
            JSONObject signallStatus = new JSONObject(message.toString());

        }
    };

    void unSubscribeMqttChannel(MqttAndroidClient client) {
        final String topic = "demo";
        try {
            IMqttToken unsubToken = client.unsubscribe(topic);
            unsubToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // The subscription could successfully be removed from the client
                    Toast.makeText(getApplicationContext(), "The subscription could successfully be removed from the client",Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken,
                                      Throwable exception) {
                    // some error occurred, this is very unlikely as even if the client
                    // did not had a subscription to the topic the unsubscribe action
                    // will be successfully
                    Toast.makeText(getApplicationContext(), "some error occurred",Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    //disconect mqtt
    void disconnectMqtt(MqttAndroidClient client) {
        try {
            IMqttToken token = client.disconnect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // We are connected
                    Toast.makeText(getApplicationContext(), "Connected",Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Something went wrong e.g. connection timeout or firewall problems
                    Toast.makeText(getApplicationContext(), "something went wrong",Toast.LENGTH_SHORT).show();

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    }

