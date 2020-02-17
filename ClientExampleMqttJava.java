package com.aridsys.clientexamplemqttjava;

/********************************************************************************
 * Copyright (c) 2020 Arid Systems Pty Ltd.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the Apache License, Version 2.0
 * which is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 ********************************************************************************/

/*
 * Example of a Java program to demonstrate the use of the Eclipse Paho MQTT Client
 * v3.1 blocking API with Iotobex. This program publishes a single message then waits
 * momentarily for reception of that message before terminating.
 */

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class ClientExampleMqttJava {

    public static void main(String[] args) {

        // Configure the following as per the Credentials and MQTT specifics
        // as detailed in the Iotobex Client Console.
        String USERNAME = "client-username";  // refer Iotobex Client Console
        String PASSWORD = "client-password";  // refer Iotobex Client Console
        String ENDPOINT = "OpenWire-endpoint";  // refer Iotobex Client Console
        String PORT = "1883";   // confirm - refer Iotobex Client Console

        // The stream name below must be configured for the user whose credentials are
        // entered above. By means of the Iotobex Client Console, configure the stream
        // name as both a publishable and subscribed stream.
        String SAMPLE_STREAM_NAME        = "com/example/sample/topic";
        String MESSAGE_TEXT      = "Sample message";

        // Set the QoS. Qos of 2 is the highest level of service in MQTT, and guarantees that
        // a message is received only once by the intended recipients.
        int qos = 2;

        String clientId     = "client-example-mqtt-java";
        MemoryPersistence persistence = new MemoryPersistence();

        try {
            String broker = "tcp://" + ENDPOINT + ":" + PORT;
            MqttClient exampleClient = new MqttClient(broker, clientId, persistence);

            // An implementation of org.eclipse.paho.client.mqttv3.MqttCallback handles message
            // reception, delivery status and indication of a lost connection.
            MqttHandler mqttHandler = new MqttHandler();
            exampleClient.setCallback(mqttHandler);

            // Set up an MQTT connection to Iotobex.
            MqttConnectOptions conOpts = new MqttConnectOptions();
            conOpts.setCleanSession(true);
            conOpts.setUserName(USERNAME);
            conOpts.setPassword(PASSWORD.toCharArray());
            System.out.println("Connecting to" + ENDPOINT + " via port " + PORT + " with username: " + USERNAME + " and password: " + PASSWORD);
            exampleClient.connect(conOpts);
            System.out.println("Connected to " + ENDPOINT);

            // Subscribe to the same stream to which a message is about to be published.
            System.out.println("Subscribing to topic: " + SAMPLE_STREAM_NAME + " with qos: " + qos);
            exampleClient.subscribe(SAMPLE_STREAM_NAME, qos);

            MqttMessage message = new MqttMessage(MESSAGE_TEXT.getBytes());
            message.setQos(qos);
            System.out.println("Publishing message to stream: " + SAMPLE_STREAM_NAME + " - message: " + MESSAGE_TEXT);
            exampleClient.publish(SAMPLE_STREAM_NAME, message);
            //System.out.println("Message published");

            // Wait two seconds to provide sufficient time for message reception - a response is expected
            // since the client has subscribed to same stream). Should appear at console.
            Thread.sleep(2000);

            System.out.println("Disconnecting from " + ENDPOINT);
            exampleClient.disconnect();

            System.exit(0);
        } catch(MqttException mexc) {
            System.out.println("MqttException - reason code: " + mexc.getReasonCode());
            System.out.println("Message: " + mexc.getMessage());
            System.out.println("Localized message: " + mexc.getLocalizedMessage());
            System.out.println("Cause: " + mexc.getCause());
            System.out.println("Exception: " + mexc);
            mexc.printStackTrace();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
