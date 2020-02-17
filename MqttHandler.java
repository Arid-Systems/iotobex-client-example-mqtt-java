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

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttHandler implements org.eclipse.paho.client.mqttv3.MqttCallback {
    /*
    * These methods implement the MqttCallback interface.
    */

    public void connectionLost(Throwable cause) {
        // This method is called when the connection to the server is lost.
        System.out.println("Connection was lost");
    }

    public void messageArrived(String topic, MqttMessage message) throws MqttException {
        // This method is called when a message arrives from the server (i.e. matching any
        // subscription made by the client).
        System.out.println("Message received - topic: " + topic + "  - message: " + message);
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
        // Called when delivery for a message has been completed, and all acknowledgments have been received.
        // For QoS 0 messages it is called once the message has been handed to the network for delivery. For
        // QoS 1 it is called when PUBACK is received and for QoS 2 when PUBCOMP is received. The token will
        // be the same token as that returned when the message was published.
        System.out.println("Delivery of message complete");
    }
}
