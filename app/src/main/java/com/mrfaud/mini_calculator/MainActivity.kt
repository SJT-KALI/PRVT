                                   //MINI_CALCULATOR
package com.mrfaud.mini_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    var Pre_Memory=""                                                                                      //first operand
    var Post_Memory=""                                                                                    //second operator
    var Result_Memory:Double?=null                                                                           //final result
    var isFRESH=false                                                                          //check operator is selected
    var skipvalue=false                                                                          //handle the error isFRESH
    var operator=""                                                                                 //to select the operator
    var tem_01=div()                                                         //for overriding the function in abstract class
    var tem_02=mod()                                                         //for overriding the function in abstract class
    fun OprtrInpt(view: View)                                                                        //for inputting operator
    {
       val trigger_select=view as Button

        when(trigger_select.id)                                              //selecting the operator with operator button's id
        {
            btn_add.id->{operator="+"}
            btn_sub.id->{operator="-"}
            btn_mul.id->{operator="*"}
            btn_div.id->{operator="/"}
            btn_mod.id->{operator="%"}
        }
        Pre_Memory=cal_screen.text.toString()                                                         //assigning the operator

        isFRESH=true
    }
    fun NmbrInpt(view: View)                                                                          //for inputting operands
    {


       if(skipvalue==false){                                       //as part error handling in the inputting tht second operand
           if(isFRESH)                                                          //chcking for is an operator is selected or not
        {
            cal_screen.setText("")
            skipvalue=true
        }}
        val btnselect=view as Button
        var btnclickvalue:String=cal_screen.text.toString()                      //creating a variable of type string in which
                                                                                // the value of screen is converted and stored

        when(btnselect.id){
            btn0.id->{
                btnclickvalue+="0"
                           }
            btn1.id->{
                btnclickvalue+="1"
            }
            btn2.id->{
            btnclickvalue+="2"
        }
            btn3.id->{
            btnclickvalue+="3"
        }
            btn4.id->{
            btnclickvalue+="4"
        }
            btn5.id->{
            btnclickvalue+="5"
        }
            btn6.id->{
            btnclickvalue+="6"
        }
            btn7.id->{
            btnclickvalue+="7"
        }
            btn8.id->{
            btnclickvalue+="8"
        }
            btn9.id->{
            btnclickvalue+="9"
        }
            btnDot.id->{
                btnclickvalue+="."
            }
            neg.id->{
                btnclickvalue="-"+btnclickvalue
            }
        }
 cal_screen.setText(btnclickvalue)                      //assigning the new inputted button value to the screen of the calculator
    }
    fun justSHOOT(view:View)                                                                     //when we press the '='  button
    {
      Post_Memory=cal_screen.text.toString()                                         //assigning the values to the second operand
        when(operator)                                                                           //core part-arithmetic operation
        {
            "+"->{
                Result_Memory=ShowLambdas.Add(Pre_Memory.toDouble(),Post_Memory.toDouble(),{x:Double,y:Double->(x+y)})
                          //  (SINGLETON<OBJECT>)                                          //(LAMBDA)
            }
            "-"->{
                Result_Memory=ShowLambdas.Sub(Pre_Memory.toDouble(),Post_Memory.toDouble(),{x:Double,y:Double->(x-y)})
                          //  (SINGLETON<OBJECT>)                                          //(LAMBDA)
            }
            "%"->{
                Result_Memory=tem_02.workplace(Pre_Memory.toDouble(),Post_Memory.toDouble())
                                      //calling function in the class
            }
            "/"->{
                Result_Memory=tem_01.workplace(Pre_Memory.toDouble(),Post_Memory.toDouble())
                //calling function in the class
            }
            "*"->{
                   Result_Memory=java_operation.Multiplication(Pre_Memory.toDouble(),Post_Memory.toDouble())}
                                  //calling the function in the java class

        }
        cal_screen.setText(Result_Memory.toString())                                                      //assigning the result
        skipvalue=false
    }

    fun WASHOUT(view:View)                                                                    //for clearing the calculator sceen
    {
        cal_screen.setText("")
        isFRESH=false
    }

}
abstract class mid_class      //abstract class
{
  abstract fun workplace(a:Double,b:Double):Double
}
class div:mid_class()                                    //class for overring the abstract function in the abstract class (DIVISION)
{
    override fun workplace(a: Double, b: Double):Double {
        return (a/b)
    }
}
class mod:mid_class()                              //class for overring the abstract function in the abstract class (MODULUS:REMAINDER)
{
    override fun workplace(a: Double, b: Double):Double {
        return (a%b)
    }
}
object ShowLambdas                                                                                                //SINGLETON
{
    fun Add(a:Double,b:Double,action:(Double,Double)->Double):Double
    {
        return action(a,b)
    }
    fun Sub(a:Double,b:Double,action:(Double,Double)->Double):Double
    {
        return action(a,b)
    }
}

                    //                      END