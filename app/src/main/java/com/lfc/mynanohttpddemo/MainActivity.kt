package com.lfc.mynanohttpddemo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var httpService = MyHttpService("127.0.0.1", 9988)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            println("服务启动完成")
        } catch (e: IOException) {
            e.printStackTrace()
            println("服务启动错误")
        }
    }

    //安卓销毁事件
    override fun onDestroy() {
        super.onDestroy()
        httpService.stop()
    }
}