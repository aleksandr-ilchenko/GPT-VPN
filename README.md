# GPT VPN — Android starter project

This is the first Android Studio project scaffold for the GPT VPN app.

## What is included
- Kotlin + Jetpack Compose dark interface
- Android 8.0+ baseline (minSdk 26), suitable as a starting point for Honor devices
- Placeholder server/protocol status and setup explanation

## Important: VPN is not active yet
This starter project does **not** route or encrypt network traffic. The UI is not a working VPN.
A real connection requires:
1. A reachable VPN server.
2. A valid WireGuard client configuration (private key, server public key, endpoint, allowed IPs, DNS).
3. Integration of a maintained WireGuard tunnel engine and Android `VpnService` permission flow.
4. Testing DNS handling, reconnect behavior, and Android/Honor background restrictions.

Do not enter or share private keys in chat. Keep WireGuard configuration secrets on your device.

## Open in Android Studio
1. Install Android Studio (current stable) and Android SDK Platform 35.
2. Open this folder as a project.
3. Let Gradle sync.
4. Run the `app` configuration on your Honor phone with USB debugging enabled, or use an emulator.

## Next development milestone
Add a secure local import of a `.conf` WireGuard profile, integrate the WireGuard tunnel library, and implement per-app routing. The package name is `com.example.gptvpn`; change it before publishing.
