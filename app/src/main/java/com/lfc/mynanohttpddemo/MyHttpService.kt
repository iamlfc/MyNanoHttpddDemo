package com.lfc.mynanohttpddemo

/**
 * Created by LFC
on 2023-01-04.
 */
class MyHttpService(hostname: String?, port: Int) : NanoHTTPD(hostname, port) {

    //重写Serve方法，每次请求时会调用该方法
    override fun serve(session: IHTTPSession?): Response {
        //获取请求uri
        //获取请求uri
        val uri = session!!.uri
        //这里默认把接收到的uri返回
        //这里默认把接收到的uri返回
        return newFixedLengthResponse(uri)
    }
}