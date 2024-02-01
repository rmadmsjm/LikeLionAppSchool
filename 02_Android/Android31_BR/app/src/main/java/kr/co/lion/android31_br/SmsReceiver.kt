package kr.co.lion.android31_br

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast

// AndroidManifest.xml 에 android.provider.Telephony.SMS_RECEIVED 라는 이름으로 등록된 BroadCastReciever

class SmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // 수신된 문자 정보를 가지고 있는 객체가 있는지 확인함
        if(intent.extras != null) {
            // 문자 메세지 정보 객체 추출
            val objs = intent.extras?.get("pdus") as Array<Any?>
            // 수신된 문자가 있다면
            if(objs != null) {
                // 수신된 문자의 수만큼 반복
                objs.forEach {
                    // 문자 메시지 객체 추출
                    val format = intent.extras?.getString("format")
                    // 문자 메시지 객체 생성
                    val currentSMS = SmsMessage.createFromPdu(it as ByteArray?, format)

                    // 전화번호
                    val str1 = """전화번호 : ${currentSMS.displayOriginatingAddress}
                        |내용 : ${currentSMS.displayMessageBody}
                    """.trimMargin()

                    Toast.makeText(context, str1, Toast.LENGTH_LONG).show()
                    Log.d("test1234", str1)
                }
            }
        }
    }
}