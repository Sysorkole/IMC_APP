package com.example.imc_app

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners(){
        btCalcular.setOnClickListener {
            calcularIMC(etPeso.text.toString(), etAltura.text.toString())
        }
    }

    private fun calcularIMC(valor1: String, valor2: String){
        val peso = valor1.toFloatOrNull()
        val altura = valor2.toFloatOrNull()
        if(peso != null && altura != null){
            val imc = peso/(altura * altura)
            // O when determina o nível de obesidade de acordo com os valores informados
            when{
                imc < 18.5f ->{
                    txImc.text = "IMC: %.2f - Magreza".format(imc)
                    txImc.setTextColor(Color.BLUE)
                }
                imc < 25.0f ->{
                    txImc.text = "IMC: %.2f - Normal".format(imc)
                    txImc.setTextColor(Color.GREEN)
                }
                imc < 30.0f ->{
                    txImc.text = "IMC: %.2f - Sobrepeso".format(imc)
                    txImc.setTextColor(Color.parseColor("#556b2f"))
                }
                imc < 40.0f ->{
                    txImc.text = "IMC: %.2f - Obesidade".format(imc)
                    txImc.setTextColor(Color.parseColor("#ffa500"))
                }
                imc > 40.0f ->{
                    txImc.text = "IMC: %.2f - Obesidade Grave".format(imc)
                    txImc.setTextColor(Color.RED)
                }
                else ->{
                    txImc.text = "Reveja os valores inseridos..."
                }

            }
            etAltura.setText("") // Após clicar no botão o campo da altura é limpo
            etPeso.setText("") // Após clicar no botão o campo do peso é limpo
            btCalcular.text = "Calcular Novamente" // Após executar pelos menos uma vez, o texto do botão muda para "Calcular novamente"
        }
        else{
            txImc.text = "Complete os campos"
            txImc.setTextColor(Color.RED)
        }
    }
}

