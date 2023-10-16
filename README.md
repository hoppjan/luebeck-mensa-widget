# LübeckMensaWidget

Lübeck Mensa Widget is an Android application that displays the menu of the cafeteria and mensa located in the university district of Lübeck. The app only provides a widget for users to access the daily menu directly from their device's home screen.

## Installation

1. Download the APK file from the release section of this repository.
1. Enable the installation of apps from unknown sources on your Android device.
1. Open the downloaded APK file and follow the on-screen instructions to install the application.
1. Once installed, add the widget to your home screen by long-pressing on an empty space on the home screen and selecting Widgets. Then, locate the "Lübeck Mensa Widget" and drag it to your desired position on the home screen.

## Build debug version

1. Clone the project
2. go into the repo
3. execute `./gradlew app:assembleDebug`

If you encounter any problems, fix them yourself (or open an issue)!

## Release

When you want to create a new release, follow these steps:

1. Update the version in `app/build.gradle.kts` (e.g. 1.2.3)
1. Commit that change (`git commit -am v1.2.3`)
1. Tag the commit (`git tag v1.2.3`). Make sure your tag name's format is `v*.*.*`
1. Push the changes to GitHub (`git push && git push --tags`)
1. Edit and publish the release draft created by the workflow in GitHub

After building successfully, the action will publish the release artifacts in a new release draft that will be created on GitHub with download links for the app. 

## Thanks

- [@Draculente](https://github.com/Draculente) & [@Importantus](https://github.com/Importantus) for the [Mensa-API](https://github.com/Draculente/mensa-api)
- [@lomenzel](https://github.com/lomenzel) for sitting next to me to make me work!
