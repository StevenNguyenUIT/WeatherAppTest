# Weather Tracker App

This is a weather tracking Android application built using **Kotlin**, **Jetpack Compose**, and **Clean Architecture**. The app allows users to search for a city, display its current weather, and persist the selected city across app launches. 

## Features

- Search for cities and fetch weather data from [WeatherAPI.com](https://www.weatherapi.com/).
- Display current weather, including temperature, humidity, UV index, and "feels like" temperature.
- Persist the selected city for future launches.
- MVVM architecture for clean, testable, and modular code.
- Dependency injection with Koin.
- Graceful error handling for invalid cities and network issues.
- UI designed with **Jetpack Compose**, matching provided Figma designs.

## Requirements

- Android Studio Dolphin or later.
- Kotlin 2.0 or later.
- Internet connection for fetching weather data.

## Setup Instructions

### Prerequisites

1. **API Key for WeatherAPI.com**:
   - Sign up at [WeatherAPI](https://www.weatherapi.com/) and generate a free API key.
   - Add your API key to the app as described in the steps below.

2. **Android Studio**:
   - Install the latest version of [Android Studio](https://developer.android.com/studio).

### Steps to Run the Project

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/StevenNguyenUIT/WeatherAppTest.git
   cd WeatherAppTest
   ```

2. **Open the Project in Android Studio**:
   - Launch Android Studio and select `Open an Existing Project`.
   - Navigate to the cloned repository and open it.

3. **Add WeatherAPI Key**:
   - Create a new file named `apikeys.properties` in the root directory of the project (if it doesn’t already exist).
   - Add the following line to the file:
     ```properties
     API_KEY=your_api_key_here
     ```
     Replace `your_api_key_here` with your WeatherAPI key.

4. **Sync the Project**:
   - Click on `File > Sync Project with Gradle Files` to load dependencies.

5. **Build and Run**:
   - Connect a physical Android device or start an emulator.
   - Click the `Run` button in Android Studio or use `Shift + F10` to build and launch the app.

### Additional Notes

- **Dependencies**:
  All dependencies are managed via Gradle. Ensure you have an active internet connection to download the required libraries during the build process.


## Folder Structure

```
app/
├── data/                # Data sources, APIs, and local storage
├── domain/              # Use cases and core business logic
├── presentation/        # Jetpack Compose UI and ViewModels
├── di/                  # Dependency injection modules
└── utils/               # Utility classes and helpers
```

## Contact

If you have any questions or need further assistance, feel free to reach out:
- Email: nhinhnguyenuit@gmail.com
