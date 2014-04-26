# glass-movies

**Note: This app has been tested with XE16.11.**

Google Glass allows you to watch videos that you have recorded with the built-in video, but it does not allow you to watch video files that you transfer to the device. This app solves that problem.

**Warning:** Watching your glass screen for extended periods of time is probably not good for your eyes. Get a real monitor dangit!


## How to install

1. Enable debug mode on your Glass, you can find this in Settings → Device info → ___swipe right twice___ → Turn on debug.
2. Download [glass-movies.apk](https://github.com/stefansundin/glass-movies/releases/download/v0.2/glass-movies.apk).
3. Install the apk with `adb`.
4. Transfer video files to `/mnt/sdcard/Movies/`.
5. Launch the app, ___ok glass, start movies___.
6. Watch your video and enjoy.

```bash
wget https://github.com/stefansundin/glass-movies/releases/download/v0.2/glass-movies.apk
adb install glass-movies.apk
adb push "PSY - GANGNAM STYLE.mp4" /mnt/sdcard/Movies/
```

I have found that Glass can play HD fine, even though the actual screen resolution of Glass is 640×360. The volume seems quite high too.

To uninstall:
```bash
adb uninstall com.stefansundin.glass.movies
```

Handy commands:
```bash
adb shell df
adb shell ls /mnt/sdcard/Movies/
adb shell rm "/mnt/sdcard/Movies/PSY - GANGNAM STYLE.mp4"
```


## Author

Made by Stefan Sundin. Show your appreciation by [donating](http://stefansundin.com/donate).


## Pictures

Launch with ___ok glass, start movies___.

![screen1](/doc/screen1.png "ok glass, start movies")

Find a video that you transferred. I transferred a bunch of trailers and a few music videos.

![screen2](/doc/screen2.png "Launch a video")

Enjoy!

![screen3](/doc/screen3.png "GANGNAM STYLE")
