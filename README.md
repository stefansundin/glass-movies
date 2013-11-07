# glass-movies

Google Glass allows you to watch videos that you have recorded with the built-in video, but it does not allow you to watch video files that you transfer to the device. This app solves that problem.

**Warning:** Watching your glass screen for extended periods of time is probably not good for your eyes. Get a real monitor dangit!

## How to install

First I recommend you to get [Launchy](https://github.com/kaze0/launchy), an app launcher for Glass.

1. Enable debug mode on your Glass, you can find this in Settings → Device info → ___swipe right___ → Turn on debug.
2. Download [glass-movies.apk](https://github.com/stefansundin/glass-movies/releases/download/0.1/glass-movies.apk).
3. Install the apk with `adb`.
4. Transfer video files to `/mnt/sdcard/Movies/`.
5. Launch the app.
6. Watch your video and enjoy.

```bash
wget https://github.com/stefansundin/glass-movies/releases/download/0.1/glass-movies.apk
adb install glass-movies.apk
adb push "PSY - GANGNAM STYLE.mp4" /mnt/sdcard/Movies/
adb shell am start -n com.stefansundin.glass.movies/.MainActivity
```

I have found that Glass can play HD fine, even though the actual screen resolution of Glass is 640×360. The volume seems quite high too, and for some reason the lowest volume you can set Glass to is 11%.

To uninstall:
```bash
adb uninstall com.stefansundin.glass.movies
adb uninstall com.mikedg.android.glass.launchy
```

Handy commands:
```bash
adb shell df
adb shell ls /mnt/sdcard/Movies/
adb shell rm "/mnt/sdcard/Movies/PSY - GANGNAM STYLE.mp4"
```
