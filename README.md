# iotobex-client-example-mqtt-java

Example of client implementation demonstrating a method of connection to the Iotobex service, in this case an MQTT connection implemented in Java.

Iotobex is an information exchange for the Internet of Things. It enables:

- The dissemination of, and easy access to, “public” Internet of Things (IoT) information.
- The sharing of IoT-derived “private” information between associated providers and subscribers, as authorised.

MQTT is a client-server publish/subscribe messaging transport protocol. It is light weight, open, simple, and designed so as to be easy to implement. Library implementations are available, such as the Eclipse Paho project, providing client libraries for a range of programming languages including Python, C++, Java, JavaScript and many others. In addition to client libraries, standalone apps such as MQTT.fx enable a straightforward testing and monitoring environment.

This code utilizes the Eclipse Paho MQTT Client v3.1 blocking API.

You will need an Iotobex user account. Be sure to configure the credentials and access URL and port in ClientExampleStompPython.py as per the Credentials and STOMP specifics as provided through the Iotobex Client Console.

Note that the stream name utilized in the example must be configured for the user whose credentials are specified. To do this, configure the stream name as both a publishable and subscribed stream by means of the Iotobex Client Console.
