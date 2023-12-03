package com.example.dntn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {
    MqttAndroidClient client;
    ToggleButton ttdk,ttqk,ttdn,ttqn,ttdb,ttqb;
    TextView ndkt,dakt,ndnt,dant;
    int tdk,tqk,tdn,tqn,tdb,tqb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ttdk =(ToggleButton) findViewById(R.id.nndk);
        ttqk =(ToggleButton) findViewById(R.id.nnqk);
        ttdn =(ToggleButton) findViewById(R.id.nndn);
        ttqn =(ToggleButton) findViewById(R.id.nnqn);
        ttdb =(ToggleButton) findViewById(R.id.nndb);
        ttqb =(ToggleButton) findViewById(R.id.nnqb);

        ndkt=(TextView) findViewById(R.id.ndk);
        dakt=(TextView) findViewById(R.id.dak);
        ndnt=(TextView) findViewById(R.id.ndn);
        dant=(TextView) findViewById(R.id.dan);

        tdk=0;
        tqk=0;
        tdb=0;
        tqb=0;
        tdn=0;
        tqn=0;

        MqttConnectOptions options = new MqttConnectOptions();
        options.setUserName("bkyspevk");
        options.setPassword("xWUeCfhepP2D".toCharArray());
        String clientId = MqttClient.generateClientId();
        client =
                new MqttAndroidClient(this.getApplicationContext(), "tcp://driver.cloudmqtt.com:18688",
                        clientId);
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }
            @Override

            public void messageArrived(String topic, MqttMessage message) throws Exception {

                if(topic.contains("DKG"))
                {
                    tdk=Integer.parseInt(message.toString());
                    if(tdk==1)
                    {
                        ttdk.setChecked(true);
                    }
                    else
                    {
                        ttdk.setChecked(false);
                    }
                }

                if(topic.contains("QKG"))
                {
                    tqk=Integer.parseInt(message.toString());
                    if(tqk==1)
                    {
                        ttqk.setChecked(true);
                    }
                    else
                    {
                        ttqk.setChecked(false);
                    }
                }

                if(topic.contains("DNG"))
                {
                    tdn=Integer.parseInt(message.toString());
                    if(tdn==1)
                    {
                        ttdn.setChecked(true);
                    }
                    else
                    {
                        ttdn.setChecked(false);
                    }
                }

                if(topic.contains("QNG"))
                {
                    tqn=Integer.parseInt(message.toString());
                    if(tqn==1)
                    {
                        ttqn.setChecked(true);
                    }
                    else
                    {
                        ttqn.setChecked(false);
                    }
                }

                if(topic.contains("DBG"))
                {
                    tdb=Integer.parseInt(message.toString());
                    if(tdb==1)
                    {
                        ttdb.setChecked(true);
                    }
                    else
                    {
                        ttdb.setChecked(false);
                    }
                }

                if(topic.contains("QBG"))
                {
                    tqb=Integer.parseInt(message.toString());
                    if(tqb==1)
                    {
                        ttqb.setChecked(true);
                    }
                    else
                    {
                        ttqb.setChecked(false);
                    }
                }

                if(topic.contains("NDK"))
                {
//                    String tam = message.toString();
                    ndkt.setText(message.toString()+"°C");
                }
                else if(topic.contains("DAK"))
                {
//                    String tam = message.toString();
                    dakt.setText(message.toString()+"%");
                }
                else if(topic.contains("NDN"))
                {
//                    String tam = message.toString();
                    ndnt.setText(message.toString()+"°C");
                }
                else if(topic.contains("DAN"))
                {
//                    String tam = message.toString();
                    dant.setText(message.toString()+"%");
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
        try {
            IMqttToken token = client.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Toast.makeText(MainActivity.this,"Connected!", Toast.LENGTH_SHORT).show();
                    String topic1 = "DKG";
                    String topic2 = "QKG";
                    String topic3 = "DNG";
                    String topic4 = "QNG";
                    String topic5 = "DBG";
                    String topic6 = "QBG";
                    String topic7 = "NDK";
                    String topic8 = "DAK";
                    String topic9 = "NDN";
                    String topic10 = "DAN";
                    int qos = 1;

                    try {
                        IMqttToken subToken1 = client.subscribe(topic1, qos);
                        IMqttToken subToken2 = client.subscribe(topic2, qos);
                        IMqttToken subToken3 = client.subscribe(topic3, qos);
                        IMqttToken subToken4 = client.subscribe(topic4, qos);
                        IMqttToken subToken5 = client.subscribe(topic5, qos);
                        IMqttToken subToken6 = client.subscribe(topic6, qos);
                        IMqttToken subToken7 = client.subscribe(topic7, qos);
                        IMqttToken subToken8 = client.subscribe(topic8, qos);
                        IMqttToken subToken9 = client.subscribe(topic9, qos);
                        IMqttToken subToken10 = client.subscribe(topic10, qos);

                    } catch (MqttException e) {
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this,"Unable to Connect!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception)
                {
                    Log.d("mqtt", "onFailure");
                }
            });
        }
        catch (MqttException e)
        {
            e.printStackTrace();
        }

        ttdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ttdk.isChecked())
                {
                    tdk=1;
                    pubdk(Integer.toString(tdk));
                }
                else
                {
                    tdk=0;
                    pubdk(Integer.toString(tdk));
                }
            }
        });
        ttqk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ttqk.isChecked())
                {
                    tqk=1;
                    pubqk(Integer.toString(tqk));
                }
                else
                {
                    tqk=0;
                    pubqk(Integer.toString(tqk));
                }
            }
        });

        ttdb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ttdb.isChecked())
                {
                    tdb=1;
                    pubdb(Integer.toString(tdb));
                }
                else
                {
                    tdb=0;
                    pubdb(Integer.toString(tdb));
                }
            }
        });
        ttqb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ttqb.isChecked())
                {
                    tqb=1;
                    pubqb(Integer.toString(tqb));
                }
                else
                {
                    tqb=0;
                    pubqb(Integer.toString(tqb));
                }
            }
        });

        ttdn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ttdn.isChecked())
                {
                    tdn=1;
                    pubdn(Integer.toString(tdn));
                }
                else
                {
                    tdn=0;
                    pubdn(Integer.toString(tdn));
                }
            }
        });
        ttqn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ttqn.isChecked())
                {
                    tqn=1;
                    pubqn(Integer.toString(tqn));
                }
                else
                {
                    tqn=0;
                    pubqn(Integer.toString(tqn));
                }
            }
        });

    }

    void pubdk(String content)
    {
        String topic = "DK";
        String payload = content;
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    void pubqk(String content)
    {
        String topic = "QK";
        String payload = content;
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    void pubdn(String content)
    {
        String topic = "DN";
        String payload = content;
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    void pubqn(String content)
    {
        String topic = "QN";
        String payload = content;
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    void pubdb(String content)
    {
        String topic = "DB";
        String payload = content;
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }

    void pubqb(String content)
    {
        String topic = "QB";
        String payload = content;
        byte[] encodedPayload = new byte[0];
        try {
            encodedPayload = payload.getBytes("UTF-8");
            MqttMessage message = new MqttMessage(encodedPayload);
            client.publish(topic, message);
        } catch (UnsupportedEncodingException | MqttException e) {
            e.printStackTrace();
        }
    }
}

