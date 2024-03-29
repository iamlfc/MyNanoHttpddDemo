package com.lfc.mynanohttpddemo

/**
 * Date: 2022/4/21
 * Author: SunBinKang
 * Description:
 */
const val HTTP_IP = "10.198.1.32"//这是我当前手机流量下的IP地址
const val HTTP_PORT = 9988
const val HTTP_URL = "http://${HTTP_IP}:${HTTP_PORT}/"

/**
 * POST请求:
 * @param number (integer 数字）
 * {
 *   "code":200,
 *   "data":106,
 *   "msg":"请求成功！"
 * }
 */
const val HTTP_URI_SUM = "/api/sumHundredTime"
/**
 * POST请求:
 * @param name (String 数字）
 * {
 *   "code":200,
 *   "data":"Hello 叫啥来着 !",
 *   "msg":"请求成功！"
 * }
 */
const val HTTP_URI_HELLO = "/api/holleMan"
/**
 * GET请求：
 * @param name (String 数字）
 * {
 *   "code":200,
 *   "data":"哈哈哈哈, say somthing to me 1, ok?",
 *   "msg":"请求成功！"
 * }
 */
const val HTTP_URI_SAYSOMETHING = "/api/saySomething"