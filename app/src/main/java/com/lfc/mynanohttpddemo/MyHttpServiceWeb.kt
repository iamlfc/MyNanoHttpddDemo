package com.lfc.mynanohttpddemo

import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


/**
 * Created by LFC
on 2023-01-04.
 */
class MyHttpServiceWeb(hostname: String?, port: Int) : NanoHTTPD(hostname, port) {
    var asset: AssetManager? = null

    override fun serve(iHTTPSession: IHTTPSession): Response? {
        //获取参数
        val parms: Map<*, *> = iHTTPSession.parms
        println(parms)
        println(iHTTPSession.uri)
        if (iHTTPSession.uri == "/") {
            println(asset)
            return index()
        }
        return newFixedLengthResponse("500")
    }

    private fun index(): Response? {
        //加载Assets里的html文件
        return newFixedLengthResponse(getIndex())
    }

    fun getIndex(): String? {
        var inputStream: InputStream? = null
        var isr: InputStreamReader? = null
        var br: BufferedReader? = null
        val sb = StringBuffer()
        try {
            inputStream = asset!!.open("index.html")
            isr = InputStreamReader(inputStream)
            br = BufferedReader(isr)
            sb.append(br.readLine())
            var line: String? = null
            while (br.readLine().also { line = it } != null) {
                sb.append(
                    """
                    
                    $line
                    """.trimIndent()
                )
            }
            br.close()
            isr.close()
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (br != null) {
                    br.close()
                }
                if (isr != null) {
                    isr.close()
                }
                if (inputStream != null) {
                    inputStream.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return sb.toString()
    }
}