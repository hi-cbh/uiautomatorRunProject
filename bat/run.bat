adb shell rm -rf /mnt/sdcard/AppTestReport/
adb shell mkdir /mnt/sdcard/AppTestReportPic/
adb shell mkdir /mnt/sdcard/AppTestReportLog/
adb shell mkdir /mnt/sdcard/AppTestReportAppRunLog/
adb push xxx.jar /data/local/tmp/
adb push MonkeyScript.txt /data/local/tmp/
adb shell monkey -f /data/local/tmp/MonkeyScript.txt  --ignore-crashes --ignore-timeouts --ignore-security-exceptions --ignore-native-crashes 20
pause
