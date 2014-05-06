# glass-movies

**Note: This app has been tested with XE16.2.**

Google Glass allows you to watch videos that you have recorded with the camera, but it does not allow you to watch video files that you transfer to the device. This app solves that problem.

**Warning:** Watching your glass screen for extended periods of time is probably not good for your eyes. Get a real monitor dangit!

This app will block the wink gesture while you're inside the app, so you don't accidentally take pictures while watching movies.


## How to install

1. Enable debug mode on your Glass, you can find this in Settings → Device info → ___swipe right twice___ → Turn on debug.
2. Download [glass-movies.apk](https://github.com/stefansundin/glass-movies/releases/download/v0.3/glass-movies.apk).
3. Install the apk with [`adb`](http://developer.android.com/sdk/).
4. Transfer video files to `/mnt/sdcard/Movies/`.
5. Launch the app, ___ok glass, start a movie___.
6. Watch your video and enjoy.

```bash
wget https://github.com/stefansundin/glass-movies/releases/download/v0.3/glass-movies.apk
adb install glass-movies.apk
adb push "PSY - GANGNAM STYLE.mp4" /mnt/sdcard/Movies/
```

To uninstall:
```bash
adb uninstall com.stefansundin.glass.movies
```

I have found that Glass can play HD fine, even though the actual screen resolution of Glass is 640×360. The volume seems quite high too.

If you happen to have big 1080p videos that take up a bit too much space for comfort, you can easily resize and recompress it with ffmpeg ([more info](https://trac.ffmpeg.org/wiki/Scaling%20(resizing)%20with%20ffmpeg)):
```bash
ffmpeg -i input.mp4 -vf scale=640,-1 resized-for-glass.mp4
```


Handy commands:
```bash
adb shell df
adb shell ls /mnt/sdcard/Movies/
adb shell rm "/mnt/sdcard/Movies/PSY - GANGNAM STYLE.mp4"
adb shell am force-stop com.stefansundin.glass.movies
```


## Author

Made by Stefan Sundin. Show your appreciation by [donating](http://stefansundin.com/donate).


## Pictures

Launch with ___ok glass, start a movie___.

![](/doc/screen1.png "ok glass, start a movie")

Find a video that you transferred. I transferred a bunch of trailers and a few music videos.

![](/doc/screen2.png "Launch a video")

Enjoy!

![](/doc/screen3.png "GANGNAM STYLE")


## Changelog

**Version 0.3** - 2014-05-03:
- Only block wink when running the app, not afterwards. (facepalm)
- Hopefully fix crashing when the first list item for some reason sometimes wasn't automatically selected on startup.

**Version 0.2** - 2014-04-25:
- Updated for XE16.11.
- Block wink gesture.
- No longer block long tap since that has been removed from Glass.
- Tap with two fingers to make the app say the filename without launching video.

**Version 0.1** - 2013-11-06:
- Release for XE10.
- Block long-tap-to-Google.
