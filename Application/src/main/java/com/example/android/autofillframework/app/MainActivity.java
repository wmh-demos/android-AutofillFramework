/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.autofillframework.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.android.autofillframework.R;

/**
 * This is used to launch sample activities that showcase autofill.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (launchTrampolineActivity()) {
            return;
        }

        setContentView(R.layout.activity_main);
        NavigationItem loginEditTexts = findViewById(R.id.standardViewSignInButton);
        NavigationItem loginCustomVirtual = findViewById(R.id.virtualViewSignInButton);
        loginCustomVirtual.setVisibility(View.GONE);
        NavigationItem creditCard = findViewById(R.id.creditCardButton);
        creditCard.setVisibility(View.GONE);
        NavigationItem creditCardSpinners = findViewById(R.id.creditCardSpinnersButton);
        creditCardSpinners.setVisibility(View.GONE);
        NavigationItem loginAutoComplete = findViewById(R.id.standardLoginWithAutoCompleteButton);
        loginAutoComplete.setVisibility(View.GONE);
        NavigationItem emailCompose = findViewById(R.id.emailComposeButton);
        emailCompose.setVisibility(View.GONE);
        NavigationItem creditCardCompoundView = findViewById(R.id.creditCardCompoundViewButton);
        creditCardCompoundView.setVisibility(View.GONE);
        NavigationItem creditCardDatePicker = findViewById(R.id.creditCardDatePickerButton);
        creditCardDatePicker.setVisibility(View.GONE);
        NavigationItem creditCardAntiPatternPicker = findViewById(R.id.creditCardAntiPatternButton);
        creditCardAntiPatternPicker.setVisibility(View.GONE);
        NavigationItem multiplePartitions = findViewById(R.id.multiplePartitionsButton);
        multiplePartitions.setVisibility(View.GONE);
        NavigationItem loginWebView = findViewById(R.id.webviewSignInButton);
        loginWebView.setVisibility(View.GONE);
        loginEditTexts.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StandardSignInActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        loginCustomVirtual.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(VirtualSignInActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        creditCard.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CreditCardActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        creditCardSpinners.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CreditCardSpinnersActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        loginAutoComplete.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(StandardAutoCompleteSignInActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        emailCompose.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(EmailComposeActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        creditCardCompoundView.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CreditCardCompoundViewActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        creditCardDatePicker.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(CreditCardDatePickerActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        creditCardAntiPatternPicker.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CreditCardAntiPatternActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        multiplePartitions.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MultiplePartitionsActivity.getStartActivityIntent(MainActivity.this));
            }
        });
        loginWebView.setNavigationButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(WebViewSignInActivity.getStartActivityIntent(MainActivity.this));
            }
        });
    }

    private boolean launchTrampolineActivity() {
        Intent intent = getIntent();
        if (intent != null) {
            String target = intent.getStringExtra("target");
            if (target != null) {
                Log.i(TAG, "trampolining into " + target + " instead");
                try {
                    Intent newIntent = new Intent(this,
                            Class.forName("com.example.android.autofillframework." + target));
                    newIntent.putExtras(intent);
                    newIntent.removeExtra("target");
                    getApplicationContext().startActivity(newIntent);
                    finish();
                    return true;
                } catch (Exception e) {
                    Log.e(TAG, "Error launching " + target, e);
                }
            }
        }
        return false;
    }
}