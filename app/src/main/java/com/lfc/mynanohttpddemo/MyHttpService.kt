package com.lfc.mynanohttpddemo

import android.util.Log
import com.google.gson.Gson

/**
 * Created by LFC
on 2023-01-04.
 */
class MyHttpService(hostname: String?, port: Int) : NanoHTTPD(hostname, port) {
    private val TAG = "binkang"
    private var count = 0 //用于记录请求为第几次
    private var mGson: Gson = Gson() //用于记录请求为第几次

    //重写Serve方法，每次请求时会调用该方法
    override fun serve(session: IHTTPSession?): Response {
        return dealWith(session)

    }

    private fun dealWith(session: IHTTPSession?): Response {
        //日志输出外部请求相关的日志信息
        Log.i(
            TAG,
            "dealWith: session.uri = ${session?.uri}, method = ${session?.method}, header = ${session?.headers}, " +
                    "params = ${session?.parameters}"
        )

//        相应get请求
        if (session?.method == Method.GET) {
            if (session?.uri == HTTP_URI_SAYSOMETHING){
                count++
                val param = session.parameters
                return responseJsonString(
                    200,
                    "${param["name"]?.get(0)}, say somthing to me $count, ok?",
                    "请求成功！"
                )
            }
        }else if (Method.POST == session?.method) {//响应post请求
            //获取请求头数据
            val header = session.headers
            //获取传参参数
            val param = session.parameters

            return when (session.uri) {

                HTTP_URI_SUM -> {
                    var b: Int = param["number"]?.get(0).toString().toInt()
                    for (i in 0..100) {
                        b += 1
                    }
                    responseJsonString(200, b, "Success！")
                }

                HTTP_URI_HELLO -> responseJsonString(
                    200,
                    "Hello ${param["name"]?.get(0)} !",
                    "Success！"
                )

                else -> responseJsonString(404, "It's nothing!", "Success！")

            }
        }
        return responseJsonString(404, "", "Request not support!")
    }

    private fun <T : Any> responseJsonString(code: Int, data: T, msg: String): Response {
        val response = Responser<T>(code, data, msg)
        Log.i(TAG, "responseJsonString: $response")
        return newFixedLengthResponse(mGson.toJson(response))//返回对应的响应体Response
    }
}