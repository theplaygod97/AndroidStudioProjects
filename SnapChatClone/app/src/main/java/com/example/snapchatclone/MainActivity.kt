package com.example.snapchatclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        if (mAuth.currentUser != null) {
            logIn()
        }
    }

    fun goClicked(view: View) {
        // Check if we can log in the user
        mAuth.signInWithEmailAndPassword(emailEditText?.text.toString(), passwordEditText?.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    logIn()
                } else {
                    // Sign up the user
                    mAuth.createUserWithEmailAndPassword(emailEditText?.text.toString(),
                        passwordEditText?.text.toString()).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            /*/database reference pointing to root of database
                           val rootRef = FirebaseDatabase.getInstance().reference

                            //database reference pointing to demo node
                            val demoRef = rootRef.child("users").child(task.result!!.user!!.uid).child("email")
                            Log.i("UID", task.result!!.user!!.uid)

                            demoRef.push().setValue(emailEditText?.text.toString());*/
                            FirebaseDatabase.getInstance().reference.child("users").child(mAuth.currentUser!!.uid).child("email").setValue(emailEditText?.text.toString())
                            Log.i("USER UID ",mAuth.currentUser!!.uid)
                            logIn()
                        } else {
                            Log.w("signInWithEmail:failure", task.exception)
                            Toast.makeText(this,"Login Failed. Try Again.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }

    fun logIn() {
        // Move to next Activity
        val intent = Intent(this, SnapActivity::class.java)
        startActivity(intent)
    }


}
