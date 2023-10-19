package com.example.todotrab

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todotrab.databinding.ActivityFormRegisterBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FormRegister : AppCompatActivity() {

    private lateinit var binding: ActivityFormRegisterBinding
    private val  auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btCadastrar.setOnClickListener {view ->

            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()


            if (email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos!!!",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else {
                auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener {cadastro ->
                    if (cadastro.isSuccessful) {
                        val snackbar = Snackbar.make(view,"Cadastrado com sucesso!!!",Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.GREEN)
                        snackbar.show()
                        binding.editEmail.setText("")
                        binding.editSenha.setText("")
                    }
                }
            }
        }

        binding.txtTelaLogar.setOnClickListener{
            val intent = Intent(this,FormLogin::class.java)
            startActivity(intent)
        }
    }


}