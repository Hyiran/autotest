package example.projecttool;
/**
 * 
 * @author xian_crazy QQ：330126160
 * @version 2014年10月30日  上午10:19:09
 * @see
 */
public interface ItemPro_example {
	//app测试机设备号
	String DEVICE_NAME="022NQS143F004967";
	//app测试机android版本号
	String PLATFORM_VERSION="4.2.2";
	//app的package全名
	String APP_PACKAGE="com.test.qq";
	//app的主activity
	String APP_ACTIVITY=".StartActivity";
	//app测试启动时等待的启动app包名
	String APP_WAIT_PACKAGE="com.test.qq";
	//apk文件名
	String APK_FILE_NAME="qq.apk";
	//appium的server地址
	String REMOTE_ADDRESS="http://127.0.0.1:4723/wd/hub";
	String WEB_HOMEURL_TEST_HT="";
	String APPFILEURL="/app/"+APK_FILE_NAME;
	String WEB_HOMEURL_TEST_QT="http://login.e6gps.com/";
	String DBFILENAME = "TestData";

}
