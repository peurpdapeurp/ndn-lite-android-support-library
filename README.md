# ndn-lite-android-support-library

I. OVERVIEW
------------------------------------------------------------------------------------------------
This repository contains an example nRF52840 application and an example Android application.
The example Android application also contains the ndn-lite-android-support-library.

The ndn-lite-android-support-library contains two main features; API's for doing NDN over BLE communication,
and API's for acting as a controller in the secure sign on protocol. Both of these higher level API's depend
on a lower level API for doing basic BLE communication.

a. More Information Regarding the NDN over BLE Support

The library provides the BLEFace object for doing NDN over BLE communication; this object abstracts away the 
lower level BLE communication support of the library and provides the user a partially implemented Face
(which inherits from the Face object of the jndn library). The example shows how this BLEFace object can be used
to send interests and satisfy incoming interests with data.
        
b. More information Regarding the Sign On Protocol Controller Support

The API for the secure sign on protocol controller provided uses the BLE API's of the support library
to handle the sign on protocol for the user. As shown in the example application, the user only needs to initialize
the BLEUnicastConnectionMaintainer and the SignOnBasicControllerBLE, and then add devices that are expected
to undergo the sign on protocol through the SignOnBasicControllerBLE.addDevicePendingSignOn function. All things
related to transport (e.g. maintaining BLE connections to devices using the ndn-lite library) and the secure sign
on protocol (e.g. processing and responding to messages) are handled internally. The user will simply receive a callback
when the sign on protocol has been completed for a particular device.

c. More Information Regarding the Basic BLE Communication Support

The main lower level BLE communication functionality offered by the library are scanning for other devices and 
connecting / communicating with them through BLE unicast connections.

The scanning functionality can be customized, or simply used with default values.

The lower level BLE communication support of the library facilitates communication with BLE devices through 
a predefined "data transfer" characteristic, which it is expected that the BLE devices being connected
to have. If the BLE device being connected to does not have at least one "data transfer" characteristic
in its services with the exact same UUID as is defined in the BLECentralTransport class, then no
data transfer using the lower level BLE communication support of the library will be possible.

It is assumed that the central device (the phone) and the peripheral device (the BLE device) will not
send data to each other at the same time, since they use a single characteristic for both sending and receiving
(for simplicity). Separate characteristics for sending and receiving may be implemented in the future.

The Android application demonstrates using these functionalities of the lower level BLE communication support 
of the library:
    1) Scanning for devices (optionally filtering by service uuid)
    2) Connecting to devices
        a) Establishing connection
        b) Discovering services that contain the data transfer characteristic
        c) Enabling notifications for data transfer characteristics on all services that
            contain the data transfer characteristic
        d) Negotiating an MTU of the maximum characteristic value size, 512
    3) Exchanging data with devices
        a) Sending data to a device
        c) Receiving data from the device through data transfer characteristic notifications
        
II. INSTALLATION
------------------------------------------------------------------------------------------------
If you would like to use the ndn-lite-android-support-library in your Android application, you can follows these steps
(it is assumed that you are using Android Studio):

1) Clone this repository to your machine.
2) Open the android_library_and_example application in Android Studio.
3) Go to the Gradle tool window
        - if you cannot see the Gradle tool window, go to View > Tool Windows > Gradle
        - if you see "nothing to show" in the Gradle tool window, clean and rebuild your project and do a gradle sync
4) In the Gradle tool window, go to android_library_and_example > ndnlitesupport > build, and double click on assemble.
5) After assembling has finished, you should have an aar file for the ndnlitesupport library in the following directory:
        <root repository directory>/android_library_and_example/ndnlitesupport/build/outputs/aar
6) Inside of your Android Studio project, go to File > New > Module
7) Select to "Import .JAR/.AAR Package"
8) When selecting the .aar file to import, navigate to 
    <root repository directory>/android_library_and_example/ndnlitesupport/build/outputs/aar/ndnlitesupport-debug.aar
9) After pressing Finish, go to File > Project Structure, and go to the Modules section. Click on your main application
    module, and go to the dependencies tab.
10) Inside of the dependencies tab, click on the green plus sign on the right hand side of the window, and select to
    add a "Module dependency".
11) Select ndnlitesupport from the window that pops up.

You should now be able to use the library in your Android application.

A NOTE ON USING THE nRF52840 EXAMPLE:
        In order to use the nRF52840 example, you will have to change the root directory of the SDK in the .emProject file.
        To do this, you can open the ndn_lite_nRF52840_example.emProject file with a text editor and search and replace 
        all of the following directories with the appropriate directories for your working environment:
                - "C:/Users/Rena Li/Desktop/nRF5_SDK_15.2.0_9412b96" should be replaced with the path to the nRF SDK on your machine.
                - "C:/Users/Rena Li/Desktop/ndn-lite-android-support-library/nRF52840_example" should be replaced with the path to the
                                                                                               nRF example application on your machine.
