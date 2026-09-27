<div align="center">
<img width="1200" height="475" alt="GHBanner" src="https://ai.google.dev/static/site-assets/images/share-ais-513315318.png" />
</div>

# Run and deploy your AI Studio app

This contains everything you need to run your app locally.

View your app in AI Studio: https://ai.studio/apps/1e0bbc37-c4c4-4b78-8d84-4abb0d6cd3a1

## Run Locally

**Prerequisites:**  [Android Studio](https://developer.android.com/studio)

This app is fully offline (local Room database only) — no API key, `.env` file,
or `google-services.json` is required.

1. Open Android Studio
2. Select **Open** and choose the directory containing this project
3. Allow Android Studio to fix any incompatibilities as it imports the project
4. Run the app on an emulator or physical device (the `debugConfig` signing
   config uses the included `debug.keystore`, so a debug build just works)

### Building an APK without Android Studio (CI)

This project includes `.github/workflows/android-build.yml`. Push it to a
GitHub repo and it will build a debug APK automatically on every push
(Actions tab → latest run → Artifacts → `exampprep-pro-debug-apk`). To also
produce a signed release APK, add `KEYSTORE_BASE64`, `STORE_PASSWORD`, and
`KEY_PASSWORD` as repository secrets and set the repository variable
`ENABLE_RELEASE_BUILD` to `true`.

> Note: the Gradle wrapper's binary (`gradle-wrapper.jar`) wasn't included in
> this export, so the workflow installs Gradle directly instead. Android
> Studio doesn't need this file to open the project — it'll offer to
> regenerate the wrapper the first time you sync.
