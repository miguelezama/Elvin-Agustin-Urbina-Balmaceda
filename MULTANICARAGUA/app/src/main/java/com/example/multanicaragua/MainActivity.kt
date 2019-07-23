package com.example.multanicaragua

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btncrear.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this,"multa", null, 1)
            val bd = admin.writableDatabase
            val registro = ContentValues()
            if (rbmp.isChecked){
                registro.put("tipo_de_infraccion", "MP")
            }else if(rbp.isChecked){
                registro.put("tipo_de_infraccion","P")
            }else if (rbvn.isChecked){
                registro.put("tipo_de_infraccion","VN")
            }else{
                Toast.makeText(this,"selecctione un tipo de infraccion",Toast.LENGTH_LONG).show()
            }

            //VALIDACION DE CAMPOS

            if(etnumeral.text.isEmpty()){
                etnumeral.error="LLENAR CAMPO"
            }else if(etconductor.text.isEmpty()){
                etconductor.error="LLENAR CAMPO"
            }else if(etmonto.text.isEmpty()){
                etmonto.error="LLENAR CAMPO"
            }else if (etplaca.text.isEmpty()){
                etplaca.error="LLENAR CAMPO"
            }else if (etfecha.text.isEmpty()){
                etfecha.error="LLENAR CAMPO"
            }else{
                /*registro.put("numeral",etnumeral.getText().toString())
                registro.put("monto",etmonto.getText().toString())
                registro.put("placa",etplaca.getText().toString())
                registro.put("conductor",etconductor.getText().toString())
                registro.put("fecha",etfecha.getText().toString())

*/
            }
            registro.put("numeral",etnumeral.getText().toString())
            registro.put("monto",etmonto.getText().toString())
            registro.put("placa",etplaca.getText().toString())
            registro.put("conductor",etconductor.getText().toString())
            registro.put("fecha",etfecha.getText().toString())
            bd.insert("multa", null, registro)
            bd.close()
            etnumeral.setText("")
            etconductor.setText("")
            etfecha.setText("")
            etmonto.setText("")
            etplaca.setText("")
            Toast.makeText(this, "Se cargaron los datos de la multa", Toast.LENGTH_SHORT).show()
        }

        btnconsultar.setOnClickListener {
            val admin = AdminSQLiteOpenHelper(this, "multa", null, 1)
            val bd = admin.writableDatabase
            val fila = bd.rawQuery("select numeral,monto from multa where conductor='${etconductor.text.toString()}'", null)
            if (fila.moveToFirst()) {
                etnumeral.setText(fila.getString(2))
                etmonto.setText(fila.getString(3))
            } else
                Toast.makeText(this, "No existe un artículo con dicha descripción", Toast.LENGTH_SHORT).show()
            bd.close()
        }

    }


}
