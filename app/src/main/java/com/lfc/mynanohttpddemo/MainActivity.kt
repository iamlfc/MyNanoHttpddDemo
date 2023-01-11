package com.lfc.mynanohttpddemo

import android.annotation.SuppressLint
import android.content.Context
import android.net.wifi.WifiManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.net.Inet4Address
import java.net.NetworkInterface
import java.net.SocketException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val mBtnStartService: Button by lazy { findViewById<Button>(R.id.btn_start_service) }
    private val mTvIpinfo: TextView by lazy { findViewById<TextView>(R.id.tv_ipinfo) }

    private var httpService = MyHttpService(HTTP_IP, HTTP_PORT)

    //    private var httpService = MyHttpServiceWeb("127.0.0.1", 9988)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {

        mTvIpinfo.setText(getLocalIPAddress(this))
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btn_start_service -> {
//开启服务
                startHttpService()
            }
            else -> {}
        }


    }

    /**
     * 启动 服务
     */
    private fun startHttpService() {
        try {
            httpService.start()
//            httpService.asset = assets
            println("服务启动完成")
        } catch (e: IOException) {
            e.printStackTrace()
            println("服务启动错误  " + e.printStackTrace())
        }
    }

    //安卓销毁事件
    override fun onDestroy() {
        super.onDestroy()
        httpService.stop()
    }

    @SuppressLint("DefaultLocale")
    fun getLocalIPAddress(context: Context): String? {
        val wifiManager = context.getSystemService(WIFI_SERVICE) as WifiManager
        val ipAddress = wifiManager.connectionInfo.ipAddress
        if (ipAddress == 0) {
            try {
                val enumerationNi = NetworkInterface.getNetworkInterfaces()
                while (enumerationNi.hasMoreElements()) {
                    val networkInterface = enumerationNi.nextElement()
                    val interfaceName = networkInterface.displayName
                    if (interfaceName == "eth0" || interfaceName == "wlan0") {
                        val enumIpAddr = networkInterface.inetAddresses
                        while (enumIpAddr.hasMoreElements()) {
                            val inetAddress = enumIpAddr.nextElement()
                            if (!inetAddress.isLoopbackAddress && inetAddress is Inet4Address) {
                                return inetAddress.getHostAddress()
                            }
                        }
                    }
                }
            } catch (e: SocketException) {
                e.printStackTrace()
            }
        } else {
            return String.format(
                "%d.%d.%d.%d",
                ipAddress and 0xff,
                ipAddress shr 8 and 0xff,
                ipAddress shr 16 and 0xff,
                ipAddress shr 24 and 0xff
            )
        }
        return "0.0.0.0"
    }


}