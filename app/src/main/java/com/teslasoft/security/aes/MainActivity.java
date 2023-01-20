package com.teslasoft.security.aes;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends FragmentActivity {

	private TextView description;
	private TextView large;
	private TextView note;
	private LinearLayout copyInput;
	private LinearLayout clearInput;
	private EditText input;
	private LinearLayout copyPassword;
	private LinearLayout clearPassword;
	private EditText password;
	private LinearLayout copyOutput;
	private LinearLayout clearOutput;
	private EditText output;
	private Button encrypt;
	private Button decrypt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		initialize();
		initializeLogic();
	}
	
	private void initialize() {
		MaterialAlertDialogBuilder info;

		description = findViewById(R.id.description);
		large = findViewById(R.id.large);
		note = findViewById(R.id.note);
		copyInput = findViewById(R.id.copy_input);
		clearInput = findViewById(R.id.clear_input);
		input = findViewById(R.id.input);
		copyPassword = findViewById(R.id.copy_password);
		clearPassword = findViewById(R.id.clear_password);
		password = findViewById(R.id.password);
		copyOutput = findViewById(R.id.copy_output);
		clearOutput = findViewById(R.id.clear_output);
		output = findViewById(R.id.output);
		encrypt = findViewById(R.id.encrypt);
		decrypt = findViewById(R.id.decrypt);
		info = new MaterialAlertDialogBuilder(this);
		
		copyInput.setOnClickListener(v -> {
			((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", input.getText().toString()));
			Toast.makeText(this, "Input copied", Toast.LENGTH_SHORT).show();
		});
		
		clearInput.setOnClickListener(v -> input.setText(""));
		
		copyPassword.setOnClickListener(v -> {
			((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", password.getText().toString()));
			Toast.makeText(this, "Password copied", Toast.LENGTH_SHORT).show();
		});
		
		clearPassword.setOnClickListener(v -> password.setText(""));
		
		copyOutput.setOnClickListener(v -> {
			((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", output.getText().toString()));
			Toast.makeText(this, "Output copied", Toast.LENGTH_SHORT).show();
		});
		
		clearOutput.setOnClickListener(v -> output.setText(""));
		
		encrypt.setOnClickListener(v -> {
			try {
				output.setText(AESCrypt.encrypt(password.getText().toString(),input.getText().toString()));
			} catch(Exception e){
				Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
			}
		});
		
		decrypt.setOnClickListener(v -> {
			try {
				input.setText(AESCrypt.decrypt(password.getText().toString(),output.getText().toString()));
			} catch(Exception e){
				info.setMessage("Failed to decrypt: Invalid key");
				info.setPositiveButton("Close", (dialog, which) -> {

				});
				info.create().show();
			}
		});
	}

	private void initializeLogic() {
		setTitle("Simple AES256 Tools");
		getWindow().getDecorView().setBackgroundColor(0xFF121212);
		setTheme(android.R.style.Theme_Material);

		android.graphics.drawable.RippleDrawable ripdr1 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFF2E8B57 }), new android.graphics.drawable.ColorDrawable(0xFF121212), null);
		android.graphics.drawable.RippleDrawable ripdr2 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFF2E8B57 }), new android.graphics.drawable.ColorDrawable(0xFF212121), null);
		android.graphics.drawable.RippleDrawable ripdr3 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFF2E8B57 }), new android.graphics.drawable.ColorDrawable(0xFF121212), null);
		android.graphics.drawable.RippleDrawable ripdr4 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFF2E8B57 }), new android.graphics.drawable.ColorDrawable(0xFF212121), null);
		android.graphics.drawable.RippleDrawable ripdr5 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFF2E8B57 }), new android.graphics.drawable.ColorDrawable(0xFF212121), null);
		android.graphics.drawable.RippleDrawable ripdr8 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFF2E8B57 }), new android.graphics.drawable.ColorDrawable(0xFF121242), null);
		android.graphics.drawable.RippleDrawable ripdr9 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFF2E8B57 }), new android.graphics.drawable.ColorDrawable(0xFF212142), null);
		android.graphics.drawable.RippleDrawable ripdr10 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFF2E8B57 }), new android.graphics.drawable.ColorDrawable(0xFF121242), null);
		android.graphics.drawable.RippleDrawable ripdr11 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFFDB4437 }), new android.graphics.drawable.ColorDrawable(0xFF421212), null);
		android.graphics.drawable.RippleDrawable ripdr12 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFFDB4437 }), new android.graphics.drawable.ColorDrawable(0xFF422121), null);
		android.graphics.drawable.RippleDrawable ripdr13 = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ 0xFFDB4437 }), new android.graphics.drawable.ColorDrawable(0xFF421212), null);

		input.setBackground(ripdr1);
		password.setBackground(ripdr2);
		output.setBackground(ripdr3);
		encrypt.setBackground(ripdr4);
		decrypt.setBackground(ripdr5);
		copyInput.setBackground(ripdr8);
		copyPassword.setBackground(ripdr9);
		copyOutput.setBackground(ripdr10);
		clearInput.setBackground(ripdr11);
		clearPassword.setBackground(ripdr12);
		clearOutput.setBackground(ripdr13);
		
		input.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ocrb.ttf"), Typeface.NORMAL);
		password.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ocrb.ttf"), Typeface.NORMAL);
		output.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ocrb.ttf"), Typeface.NORMAL);
		encrypt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ocrb.ttf"), Typeface.NORMAL);
		decrypt.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ocrb.ttf"), Typeface.NORMAL);
		description.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ocra.ttf"), Typeface.NORMAL);
		large.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ocra.ttf"), Typeface.NORMAL);
		note.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/ocra.ttf"), Typeface.NORMAL);

		description.setText("The Advanced Encryption Standard (AES), also known by its original name Rijndael, is a specification for the encryption of electronic data established by the U.S. National Institute of Standards and Technology in 2001. AES256 is a military grade encryption standard of USA (Data encryption standard).\n\nThe input is divided to blocks with size 128 bit and encrypted by a 256-bit secret key.\n\nThis program uses default initialization vector and padding pkcs7.\n\nAlso this program allow you to use key size more than 256 bit for enhanced security.");
	}

	public void privacy(View v) {
		Intent i = new Intent();
		i.setAction(Intent.ACTION_VIEW);
		i.setData(Uri.parse("https://teslasoft.org/aes/privacy.html"));
		startActivity(i);
	}
}
