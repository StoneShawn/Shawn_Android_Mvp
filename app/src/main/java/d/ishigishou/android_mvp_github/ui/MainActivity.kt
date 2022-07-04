package d.ishigishou.android_mvp_github.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import d.ishigishou.android_mvp_github.R
import d.ishigishou.android_mvp_github.contract.HomeContract
import d.ishigishou.android_mvp_github.presenter.HomePresenter

class MainActivity : AppCompatActivity(),HomeContract.IHomeView {
    lateinit var editTextAccount : EditText
    lateinit var editTextPassword : EditText
    lateinit var btnLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextAccount = findViewById(R.id.editText_account)
        editTextPassword = findViewById(R.id.editText_password)
        btnLogin = findViewById(R.id.button_login)
        val presenter = HomePresenter(this)

        btnLogin.setOnClickListener{
            presenter.toCompare(editTextAccount.text.toString(),editTextPassword.text.toString())
        }



    }

    override fun showSuccess() {
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
    }

    override fun showFail() {
        Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()
    }


//    fun showSuccess(){
//        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show()
//    }
//
//    fun showFail(){
//        Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()
//    }
}