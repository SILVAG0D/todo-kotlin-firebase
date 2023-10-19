package com.example.todotrab

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todotrab.databinding.ActivityFormLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class FormLogin : AppCompatActivity() {
    private lateinit var binding: ActivityFormLoginBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogar.setOnClickListener{view ->
            val email = binding.editEmail.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view,"Preencha todos os campos!!!",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else {
                auth.signInWithEmailAndPassword(email,senha).addOnCompleteListener{ autenticacao ->
                    if (autenticacao.isSuccessful){
                        navegaTelaHome()
                    }
                }.addOnFailureListener{
                    val snackbar = Snackbar.make(view,"ERRO AO FAZER LOGIN DO USUÁRIO!!!",Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
            }
        }


        binding.txtTelaCadastro.setOnClickListener{
            val intent = Intent(this,FormRegister::class.java)
            startActivity(intent)
        }
    }

     fun navegaTelaHome(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if(usuarioAtual != null){
            navegaTelaHome()
        }
    }
}