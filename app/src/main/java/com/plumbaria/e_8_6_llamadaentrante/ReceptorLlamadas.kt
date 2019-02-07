package com.plumbaria.e_8_6_llamadaentrante

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log

class ReceptorLlamadas : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // Sacamos información del intent
        var estado = ""
        var numero = ""
        var extras:Bundle = intent.extras
        if (extras!=null) {
            estado = extras.getString(TelephonyManager.EXTRA_STATE)
            if (estado.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                numero = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER)
            }
        }
        var info = estado + " " + numero
        Log.d("ReceptorAnuncio", info + " intent=" + intent)

        //Creamos Notificacion
        var nm:NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var mIntent = Intent(context, LlamadaEntranteActivity::class.java)
        var pendingIntent = PendingIntent.getActivity(context, 0, mIntent, 0)
        var notificacion:Notification = Notification.Builder(context)
            .setContentTitle("Llamada!!!")
            .setTicker("Llamada Entrante (PMDM) ")
            .setContentText(info)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)
            .notification
        nm.notify(1, notificacion)
    }
}
