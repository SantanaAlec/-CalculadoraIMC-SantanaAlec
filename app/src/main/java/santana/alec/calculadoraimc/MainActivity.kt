
package santana.alec.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight: EditText =findViewById(R.id.weight) as EditText
        val height: EditText =findViewById(R.id.height) as EditText
        val calculate: Button =findViewById(R.id.calculate)
        val imc: TextView =findViewById(R.id.imc)
        val range: TextView =findViewById(R.id.range)

        //setBackgroundResource = R.color.colorGreenish

        calculate.setOnClickListener{
            var weightTxt = 0.0f
            var heightTxt = 0.0f
            try{
                weightTxt = weight.text.toString().toFloat()
                heightTxt = height.text.toString().toFloat()
            }catch(e: java.lang.Exception){
                imc.setText("Ingrese valores reales")
                println(e)
            }

            var result = calcularIMC(weightTxt, heightTxt)
            var formatResult = "%.2f".format(result)

            imc.visibility= View.VISIBLE
            range.visibility= View.VISIBLE

            imc.setText(formatResult)

            var health: String
            var color: Int

            when {
                result < 18.5 ->{
                    health = "Bajo Peso"
                    color = R.color.colorRed
                }
                result >= 18.5 && result<= 24.9->{
                    health = "Saludable"
                    color = R.color.colorGreenish
                }
                result >= 25 && result<= 29.9 ->{
                    health = "Sobrepeso"
                    color = R.color.colorYellow
                }
                result >= 30 && result <= 34.9 ->{
                    health = "Obesidad Grado 1"
                    color = R.color.colorOrange
                }
                result >= 35 && result <= 39.9 ->{
                    health = "Obesidad Grado 2"
                    color = R.color.colorBrown
                }
                result >= 40 ->{
                    health = "Obesidad Grado 3"
                    color = R.color.colorRed
                }else -> {
                    imc.setText("Ingrese valores reales")
                    health = "Error"
                    color = 0
                }
            }
            range.setBackgroundResource(color)
            range.setText(health)
        }

    }

    fun calcularIMC(weight: Float, height: Float): Float{
        return weight/(height * height)
    }
}