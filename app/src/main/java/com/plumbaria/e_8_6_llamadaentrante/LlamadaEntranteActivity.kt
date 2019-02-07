package com.plumbaria.e_8_6_llamadaentrante

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast

class LlamadaEntranteActivity : AppCompatActivity() {
    private companion object {
        val PERMISOS_INICIALES = arrayOf(Manifest.permission.READ_PHONE_STATE)
    }
    private val PETICION_INICIAL = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!hayPerimos(Manifest.permission.READ_PHONE_STATE)) {
            ActivityCompat.requestPermissions(this, PERMISOS_INICIALES,PETICION_INICIAL)
        }
    }

    fun hayPerimos(perm:String):Boolean {
        return (ContextCompat.checkSelfPermission(this, perm) == PackageManager.PERMISSION_GRANTED)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (!hayPerimos(Manifest.permission.READ_PHONE_STATE)) {
            Toast.makeText(this,
                "No se podrán capturar las llamdas, no has dado permiso",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
