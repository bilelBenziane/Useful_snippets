package bb.printer;


import bb.printer.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import bb.tools.BBPdf;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	TextView tvFamilyNameFr;
	TextView tvGivenNamesFr;
	TextView tvFamilyNameAr;
	TextView tvGivenNamesAr;
	TextView tvBirthDate;
	TextView tvBirthPlace;
	TextView tvResidancePlace;
	TextView tvSexe;
	TextView tvNationality;
	TextView tvHeight;
	TextView tvEyeColor;
	TextView tvHairColor;
	TextView tvbloodGroup;

	TextView tvDocumentNumber;
	TextView tvNin;
	TextView tvIssueDate;
	TextView tvExpiryDate;
	TextView tvIssueAuthority;
	TextView tvIssuingState;
	
	ImageView ivFace;
	ImageView ivSignature;
	
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tvFamilyNameFr = (TextView) findViewById(R.id.tv_display_familyName);
        tvGivenNamesFr = (TextView) findViewById(R.id.tv_display_firstName);
		tvBirthDate = (TextView) findViewById(R.id.tv_display_birth_date);
		tvBirthPlace = (TextView) findViewById(R.id.tv_display_birth_place);
		tvResidancePlace = (TextView) findViewById(R.id.tv_display_residance_place);
		
		tvSexe = (TextView) findViewById(R.id.tv_display_sexe);
		tvNationality = (TextView) findViewById(R.id.tv_display_nationality);
		tvHeight = (TextView) findViewById(R.id.tv_display_height);
		tvEyeColor = (TextView) findViewById(R.id.tv_display_eye_color);
		tvHairColor = (TextView) findViewById(R.id.tv_display_hair_color);
		
		tvbloodGroup = (TextView) findViewById(R.id.tv_display_blood_group);
		tvFamilyNameAr = (TextView) findViewById(R.id.tv_display_family_name_ar);
		tvGivenNamesAr = (TextView) findViewById(R.id.tv_display_given_names_ar);
		tvDocumentNumber = (TextView) findViewById(R.id.tv_display_document_number);
		tvNin = (TextView) findViewById(R.id.tv_display_nin);
		
		tvIssueDate = (TextView) findViewById(R.id.tv_display_issue_date);
		tvExpiryDate = (TextView) findViewById(R.id.tv_display_expiry_date);
		tvIssueAuthority = (TextView) findViewById(R.id.tv_display_issue_authority);
		tvIssuingState = (TextView) findViewById(R.id.tv_display_issuing_state);

        //______________________________________________
		tvFamilyNameFr.setText("Bla bla 1");
        tvGivenNamesFr.setText("Bla bla 2");
        tvBirthDate.setText("Bla bla 3");
        tvBirthPlace.setText("Bla bla 4");
        tvResidancePlace.setText("Bla bla 5");
        
        tvSexe.setText("Bla bla 6");
        tvNationality.setText("Bla bla 7");
        tvHeight.setText("Bla bla 8");
        tvEyeColor.setText("Bla bla 9");
        tvHairColor.setText("Bla bla 10");
        
        tvbloodGroup.setText("Bla bla 11");
        tvFamilyNameAr.setText("Bla bla 12");
        tvGivenNamesAr.setText("Bla bla 13");
        tvDocumentNumber.setText("Bla bla 14");
        tvNin.setText("Bla bla 15");
        
        tvIssueDate.setText("Bla bla 16");
        tvExpiryDate.setText("Bla bla 17");
        tvIssueAuthority.setText("Bla bla 18");
        tvIssuingState.setText("Bla bla 19");     
    }

    public void onClickGeneratePdf(View v) {
    	Log.d("BBPdf", "PDF Generation Button Clicked" );
    	
    	Bitmap bitmapUserPicOrg = BitmapFactory.decodeResource(
	   			getResources(),
	   			R.drawable.user);

    	
    	String pdfName=tvFamilyNameFr.getText().toString()+" "+tvGivenNamesFr.getText().toString();
    	
    	BBPdf bbPdf=new BBPdf(this,
    			pdfName,
    			tvFamilyNameFr.getText().toString(),
    			tvGivenNamesFr.getText().toString(),
    			tvBirthDate.getText().toString(),
    			tvBirthPlace.getText().toString(),
    			tvResidancePlace.getText().toString(),
    			tvSexe.getText().toString(),
    			tvNationality.getText().toString(),
    			tvHeight.getText().toString(),
    			tvEyeColor.getText().toString(),
    			tvHairColor.getText().toString(),
    			tvbloodGroup.getText().toString(),
    			tvFamilyNameAr.getText().toString(),
    			tvGivenNamesAr.getText().toString(),
    			tvDocumentNumber.getText().toString(),
    			tvNin.getText().toString(),
    			tvIssueDate.getText().toString(),
    			tvExpiryDate.getText().toString(),
    			tvIssueAuthority.getText().toString(),
    			tvIssuingState.getText().toString(),
    			bitmapUserPicOrg,
    			bitmapUserPicOrg
    			);
    	bbPdf.createPdf();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
