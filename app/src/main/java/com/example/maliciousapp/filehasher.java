package com.example.maliciousapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;
import java.security.MessageDigest;

public class filehasher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_filehasher);
        Intent intent = getIntent();
        Utils.showDialog(this,intent);
        //btn_hashfile
        ((Button) findViewById(R.id.btn_hackit)).setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    if(intent.getData()==null) {
                        Toast.makeText(filehasher.this, "No URI found on the Intent!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Uri uri = intent.getData();
                        String hash = null;
                        try {
                            hash = getFileChecksum(uri, "SHA-256");
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        ((TextView) findViewById(R.id.txt_hash)).setText("Hash : " + hash);
                        Log.i("Hash",hash);
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("hash", hash);
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    }
                }
        });
    }

    /**
     * Computes the hash of the file from the given Uri.
     *
     * @param fileUri The Uri of the file.
     * @param algorithm The hashing algorithm, e.g., "SHA-256".
     * @return The hexadecimal hash string.
     * @throws Exception If an error occurs during file or hash processing.
     */
    public String getFileChecksum(Uri fileUri, String algorithm) throws Exception {
        // Get the content resolver to access the file stream
        ContentResolver contentResolver = getContentResolver();

        // Initialize the MessageDigest for the given algorithm (SHA-256 in this case)
        MessageDigest digest = MessageDigest.getInstance(algorithm);

        // Open an InputStream for the given Uri
        try (InputStream inputStream = contentResolver.openInputStream(fileUri)) {
            if (inputStream == null) {
                throw new Exception("Unable to open input stream from Uri.");
            }

            // Read the file content in chunks
            byte[] byteArray = new byte[1024];
            int bytesCount;
            while ((bytesCount = inputStream.read(byteArray)) != -1) {
                // Update the digest with the bytes read
                digest.update(byteArray, 0, bytesCount);
            }
        }

        // Get the hash's byte array
        byte[] bytes = digest.digest();

        // Convert the byte array to a hexadecimal string
        return bytesToHex(bytes);
    }

    /**
     * Converts a byte array to a hexadecimal string.
     *
     * @param bytes The byte array to convert.
     * @return The hexadecimal string.
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}