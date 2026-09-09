package BusinesLogic

import kotlin.math.sqrt

object Calculate{
    fun OperatingCurrentCalculate(p: Double, u: Double, cosFi: Double) : Double{
        return p/(sqrt(3.0) * u * cosFi)
    }
}