package ash.sasamkar.plobaltask

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class ChartActivity : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)

        val bundle : Bundle? = intent.extras
        val jan : Float? = bundle?.getFloat("jan")
        val feb : Float? = bundle?.getFloat("feb")
        val mar : Float? = bundle?.getFloat("mar")
        val apr : Float? = bundle?.getFloat("apr")
        val may : Float? = bundle?.getFloat("may")
        val jun : Float? = bundle?.getFloat("jun")

        val chart = findViewById<View>(R.id.chart) as BarChart

        val yAxisValues = ArrayList<BarEntry>()
        yAxisValues.add(BarEntry(jan!!,0f))
        yAxisValues.add(BarEntry(feb!!, 1f))
        yAxisValues.add(BarEntry(mar!!, 2f))
        yAxisValues.add(BarEntry(apr!!, 3f))
        yAxisValues.add(BarEntry(may!!, 4f))
        yAxisValues.add(BarEntry(jun!!, 5f))



        val barDataSet = BarDataSet(yAxisValues, "Month Data Chart")

        barDataSet.color = ContextCompat.getColor(this, R.color.colorAccent)
        val data = BarData(barDataSet)
        chart.setData(data)
        chart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chart.xAxis.labelCount = 11
        chart.xAxis.enableGridDashedLine(5f, 5f, 0f)
        chart.axisRight.enableGridDashedLine(5f, 5f, 0f)
        chart.axisLeft.enableGridDashedLine(5f, 5f, 0f)
        chart.description.isEnabled = false
        chart.animateY(2000)
        chart.legend.isEnabled = false
        chart.data.setDrawValues(false)

    }
    private fun getXAxisValues(): List<Any> {
        val xAxis = ArrayList<String>()
        xAxis.add("JAN")
        xAxis.add("FEB")
        xAxis.add("MAR")
        xAxis.add("APR")
        xAxis.add("MAY")
        xAxis.add("JUN")
        return xAxis
    }


}