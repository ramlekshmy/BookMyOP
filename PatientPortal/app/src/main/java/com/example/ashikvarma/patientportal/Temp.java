package com.example.ashikvarma.patientportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bavithra on 11/14/2017.
 */

public class Temp extends AppCompatActivity {
    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Users");
    // public FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    // DatabaseReference users= FirebaseDatabase.getInstance().getReference("Users");
    EditText MobileNumber, OTPEditview;
    ImageView Submit, OTPButton;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    LinearLayout phoneLay, OTPLay;

    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    EditText edt1, edt2, edt3, edt4, edt5, edt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp);
        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), DashboardActivity.class));

        }

        MobileNumber = (EditText) findViewById(R.id.mobileNumber);
        OTPEditview = (EditText) findViewById(R.id.otp_editText);
        OTPButton = (ImageView) findViewById(R.id.otp_submit);
        Submit = (ImageView) findViewById(R.id.submit);
        phoneLay = (LinearLayout) findViewById(R.id.mobileNumberLay);
        OTPLay = (LinearLayout) findViewById(R.id.otpLay);

        edt1 = (EditText) findViewById(R.id.edt1);
        edt1.addTextChangedListener(new GenericTextWatcher(edt1));
        edt2 = (EditText) findViewById(R.id.edt2);
        edt2.addTextChangedListener(new GenericTextWatcher(edt2));
        edt3 = (EditText) findViewById(R.id.edt3);
        edt3.addTextChangedListener(new GenericTextWatcher(edt3));
        edt4 = (EditText) findViewById(R.id.edt4);
        edt4.addTextChangedListener(new GenericTextWatcher(edt4));
        edt5 = (EditText) findViewById(R.id.edt5);
        edt5.addTextChangedListener(new GenericTextWatcher(edt5));
        edt6 = (EditText) findViewById(R.id.edt6);
        edt6.addTextChangedListener(new GenericTextWatcher(edt6));


        mAuth = FirebaseAuth.getInstance();


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(Temp.this, "verification done" + phoneAuthCredential, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Temp.this, "verification failed", Toast.LENGTH_LONG).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    Toast.makeText(Temp.this, "invalid mobile number", Toast.LENGTH_LONG).show();
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Toast.makeText(Temp.this, "quota over", Toast.LENGTH_LONG).show();
                    // [END_EXCLUDE]
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(Temp.this, "Verification code sent to mobile", Toast.LENGTH_LONG).show();
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
//                MobileNumber.setVisibility(View.GONE);
//                Submit.setVisibility(View.GONE);
//                Textview.setVisibility(View.GONE);
//                OTPButton.setVisibility(View.VISIBLE);
//                OTPEditview.setVisibility(View.VISIBLE);
//                Otp.setVisibility(View.VISIBLE);

                phoneLay.setVisibility(View.GONE);
                OTPLay.setVisibility(View.VISIBLE);

                // ...
            }
        };
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + MobileNumber.getText().toString(),        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        Temp.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks
            }
        });

        OTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setError(null);
                edt2.setError(null);
                edt3.setError(null);
                edt4.setError(null);
                edt5.setError(null);
                edt6.setError(null);
                if (edt1.getText().toString().trim().length() == 0)
                    edt1.setError("");
                else if (edt2.getText().toString().trim().length() == 0)
                    edt2.setError("");
                else if (edt3.getText().toString().trim().length() == 0)
                    edt3.setError("");
                else if (edt4.getText().toString().trim().length() == 0)
                    edt4.setError("");
                else if (edt5.getText().toString().trim().length() == 0)
                    edt5.setError("");
                else if (edt6.getText().toString().trim().length() == 0)
                    edt6.setError("");
                else {
                    String OTP = edt1.getText().toString().trim() + edt2.getText().toString().trim() +
                            edt3.getText().toString().trim() + edt4.getText().toString().trim() +
                            edt5.getText().toString().trim() + edt6.getText().toString().trim();
                    //  Toast.makeText(Temp.this, OTP + "", Toast.LENGTH_SHORT).show();
                    PhoneAuthCredential credential = PhoneAuthProvider.
                            getCredential(mVerificationId, OTP);
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(Temp.this, "Verification done", Toast.LENGTH_LONG).show();
                            user = task.getResult().getUser();

                            // ...
                            finish();
                            startActivity(new Intent(Temp.this, DashboardActivity.class));


                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(Temp.this, "Verification failed code invalid", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }


    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.edt1:
                    if (text.length() == 1)
                        edt2.requestFocus();
                    break;
                case R.id.edt2:
                    if (text.length() == 1)
                        edt3.requestFocus();
                    break;
                case R.id.edt3:
                    if (text.length() == 1)
                        edt4.requestFocus();
                    break;
                case R.id.edt4:
                    if (text.length() == 1)
                        edt5.requestFocus();
                    break;
                case R.id.edt5:
                    if (text.length() == 1)
                        edt6.requestFocus();
                    break;
                case R.id.edt6:
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }
}
