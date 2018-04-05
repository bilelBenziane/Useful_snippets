package bb.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import bb.printer.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;
import android.widget.Toast;

/**
 * 
 * GeneratePDF.java
 * Purpose: Generate a PDF File for a report.
 *
 * @author B.Benziane
 * @version 1.0 2018/04/05
 */

public class BBPdf {
	
	private static Context context;//Store context of the activity calling the class
	private static String PATH=Environment.getExternalStorageDirectory().getPath()+
							   File.separator+"users_list"+File.separator;//PDF Storage Folder path
	private static String FILE_EXTENTION=".pdf";
	
	private static int IMAGE_ORIGINE_X=40;
	private static int IMAGE_ORIGINE_Y=50;
	private static int IMAGE_WIDTH=60;
	private static int IMAGE_HEIGHT=100;
	
	private static int MARGIN_HORIZONTAL_1=40;
	private static int MARGIN_HORIZONTAL_2=150;
	private static int MARGIN_HORIZONTAL_3=280;
	private static int MARGIN_HORIZONTAL_4=390;
	
	private static int MARGIN_VERTICAL_BEGIN_DETAILS=IMAGE_HEIGHT+70;
	private static int LINE_HEIGHT=15;
	
	//________________________Object Attrs______________________
	String pdfName;
	String tvFamilyNameFr;
	String tvGivenNamesFr;
	String tvBirthDate;
	String tvBirthPlace;
	String tvResidancePlace;
	String tvSexe;
	String tvNationality;
	String tvHeight;
	String tvEyeColor;
	String tvHairColor;
	String tvbloodGroup;
	String tvFamilyNameAr;
	String tvGivenNamesAr;
	String tvDocumentNumber;
	String tvNin;
	String tvIssueDate;
	String tvExpiryDate;
	String tvIssueAuthority;
	String tvIssuingState;
	Bitmap bitmapUserPicOrg;
	Bitmap bitmapUserSignatureOrg;
	
	
	
	public BBPdf(Context c,
			 String pdfName,
			 String tvFamilyNameFr,
			 String tvGivenNamesFr,
			 String tvBirthDate,	
			 String tvBirthPlace,
			 String tvResidancePlace,
			 String tvSexe,
			 String tvNationality,
			 String tvHeight,
			 String tvEyeColor,
			 String tvHairColor,
			 String tvbloodGroup,
			 String tvFamilyNameAr,
			 String tvGivenNamesAr,
			 String tvDocumentNumber,
			 String tvNin,
			 String tvIssueDate,
			 String tvExpiryDate,
			 String tvIssueAuthority,
			 String tvIssuingState,
			 Bitmap bitmapUserPicOrg,
			 Bitmap bitmapUserSignatureOrg){
		
		this.pdfName=pdfName;
		this.tvFamilyNameFr=tvFamilyNameFr;
		this.tvGivenNamesFr=tvGivenNamesFr;
		this.tvBirthDate=tvBirthDate;
		this.tvBirthPlace=tvBirthPlace;
		this.tvResidancePlace=tvResidancePlace;
		this.tvSexe=tvSexe;
		this.tvNationality=tvNationality;
		this.tvHeight=tvHeight;
		this.tvEyeColor=tvEyeColor;
		this.tvHairColor=tvHairColor;
		this.tvbloodGroup=tvbloodGroup;
		this.tvFamilyNameAr=tvFamilyNameAr;
		this.tvGivenNamesAr=tvGivenNamesAr;
		this.tvDocumentNumber=tvDocumentNumber;
		this.tvNin=tvNin;
		this.tvIssueDate=tvIssueDate;
		this.tvExpiryDate=tvExpiryDate;
		this.tvIssueAuthority=tvIssueAuthority;
		this.tvIssuingState=tvIssuingState;
		this.bitmapUserPicOrg=bitmapUserPicOrg;
		this.bitmapUserSignatureOrg=bitmapUserSignatureOrg;
		
		context=c;//Getting the context 
		//Creating the folder if it does not exist
		File folder = new File(Environment.getExternalStorageDirectory() + 
        File.separator + "users_list");
		boolean success = true;
		if (!folder.exists()) {
			success = folder.mkdirs();
		}
		if (success) {
			// Do something on success
			//Toast.makeText(context, "Folder created ", Toast.LENGTH_LONG).show();
		} else {
			// Do something else on failure 
			Toast.makeText(context, "Problem While creating the folder, try again !", Toast.LENGTH_LONG).show();
		}
	}
	
	public void createPdf(){
			
			Log.d("BBPdf", "PDF Name : "+pdfName);
			Log.d("BBPdf", "Storage Path : "+Environment.getExternalStorageDirectory().getPath());
		
	        // create a new document
	        PdfDocument document = new PdfDocument();

	        // crate a page description
	        PdfDocument.PageInfo pageInfo =new PdfDocument.PageInfo.Builder(595, 842, 1).create();
	        	
	        PdfDocument.Page page = document.startPage(pageInfo);
	        Canvas canvas = page.getCanvas();
	        createProfile(canvas);   
	        // finish the page
	        document.finishPage(page);
	        
	     
	        // write the document content
	        String targetPdf =PATH+pdfName+FILE_EXTENTION;
	        File filePath = new File(targetPdf);
	      
	        try {
	            document.writeTo(new FileOutputStream(filePath));
	            Toast.makeText(context, "Done", Toast.LENGTH_LONG).show();
	        } catch (IOException e) {
	            e.printStackTrace();
	            Toast.makeText(context, "Something wrong: " + e.toString(),
	                    Toast.LENGTH_LONG).show();
	        }

	        // close the document
	        document.close();
	    }
		
	private void createProfile(Canvas canvas){
			
			Paint textPaint = new Paint();
			textPaint.setColor(Color.BLACK);;
			textPaint.setTextAlign(Align.CENTER);
	
			
			Typeface tf =Typeface.createFromAsset(context.getAssets(),"fonts/Amiri.ttf");		
			TextPaint labelPaint = new TextPaint();
			labelPaint.setColor(Color.BLACK);;
			labelPaint.setTextAlign(Align.LEFT);
			labelPaint.setFakeBoldText(true);
			labelPaint.setTypeface(tf);
			labelPaint.setTextSize(12);
			
			Paint varPaint = new Paint();
			varPaint.setColor(Color.BLACK);;
			varPaint.setTextAlign(Align.LEFT);
			varPaint.setTextSize(11);
			
			
			Paint paint = new Paint();
			paint.setColor(Color.BLUE);
			int xPos = (canvas.getWidth() / 2);
			
	  	    //int yPos = (int) ((canvas.getHeight() / 2) - ((textPaint.descent() + textPaint.ascent()) / 2)) ; 	
			canvas.drawText(pdfName, xPos, 35, textPaint);
			canvas.drawLine(40, 40, 555, 40, paint);	
			
			canvas.drawText("Family Name Fr : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS, labelPaint);
			canvas.drawText(tvFamilyNameFr, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS, varPaint);
			
			canvas.drawText("Given Name Fr : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT, labelPaint);
			canvas.drawText(tvGivenNamesFr, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT, varPaint);
			
			canvas.drawText("Birth Date : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*2, labelPaint);
			canvas.drawText(tvBirthDate, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*2, varPaint);

			canvas.drawText("Birth Place : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*3, labelPaint);
			canvas.drawText(tvBirthPlace, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*3, varPaint);
			
			canvas.drawText("Residance Place : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*4, labelPaint);
			canvas.drawText(tvResidancePlace, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*4, varPaint);
			
			canvas.drawText("Sex : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*5, labelPaint);
			canvas.drawText(tvSexe, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*5, varPaint);
			
			canvas.drawText("Nationality : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*6, labelPaint);
			canvas.drawText(tvNationality, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*6, varPaint);
			
			canvas.drawText("Height : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*7, labelPaint);
			canvas.drawText(tvHeight, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*7, varPaint);
			
			canvas.drawText("Eye Color : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*8, labelPaint);
			canvas.drawText(tvEyeColor, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*8, varPaint);
			
			canvas.drawText("Hair Color : ", MARGIN_HORIZONTAL_1, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*9, labelPaint);
			canvas.drawText(tvHairColor, MARGIN_HORIZONTAL_2, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*9, varPaint);
        	
			//__________________________________secont column______________________________________________
	
			canvas.drawText("Blood Group : ", MARGIN_HORIZONTAL_3, MARGIN_VERTICAL_BEGIN_DETAILS, labelPaint);
			canvas.drawText(tvbloodGroup, MARGIN_HORIZONTAL_4, MARGIN_VERTICAL_BEGIN_DETAILS, varPaint);
  
			canvas.drawText("Family Name Ar : ", MARGIN_HORIZONTAL_3, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT, labelPaint);
			canvas.drawText(tvFamilyNameAr, MARGIN_HORIZONTAL_4, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT, varPaint);
			
			canvas.drawText("Given Names Ar : ", MARGIN_HORIZONTAL_3, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*2, labelPaint);
			canvas.drawText(tvGivenNamesAr, MARGIN_HORIZONTAL_4, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*2, varPaint);
			
			canvas.drawText("Document Number : ", MARGIN_HORIZONTAL_3, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*3, labelPaint);
			canvas.drawText(tvDocumentNumber, MARGIN_HORIZONTAL_4, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*3, varPaint);
			
			canvas.drawText("Nin : ", MARGIN_HORIZONTAL_3, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*4, labelPaint);
			canvas.drawText(tvNin, MARGIN_HORIZONTAL_4, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*4, varPaint);
			
			canvas.drawText("Issue Date : ", MARGIN_HORIZONTAL_3, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*5, labelPaint);
			canvas.drawText(tvIssueDate, MARGIN_HORIZONTAL_4, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*5, varPaint);
			
			canvas.drawText("Expiry Date : ", MARGIN_HORIZONTAL_3, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*6, labelPaint);
			canvas.drawText(tvExpiryDate, MARGIN_HORIZONTAL_4, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*6, varPaint);
			
			canvas.drawText("Issue Authority : ", MARGIN_HORIZONTAL_3, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*7, labelPaint);
			canvas.drawText(tvIssueAuthority, MARGIN_HORIZONTAL_4, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*7, varPaint);
			
			canvas.drawText("Issuing State : ", MARGIN_HORIZONTAL_3, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*8, labelPaint);
			canvas.drawText(tvIssuingState, MARGIN_HORIZONTAL_4, MARGIN_VERTICAL_BEGIN_DETAILS+LINE_HEIGHT*8, varPaint);
		
			//____________________________________________
		    
		    insertBitmap(canvas, paint, bitmapUserPicOrg,IMAGE_ORIGINE_X,IMAGE_ORIGINE_Y); 	        
		    insertText(canvas,"أقصوصة يكون جواب السؤال هو  قصة صغيرة مكونة من 6 احرف? يسعدنا أن نقدم لكم على عالم الحلول جواب سؤال قصة صغيرة من 6 حرو? لعبة ?طحل العرب لغز 31",
		    		MARGIN_HORIZONTAL_1+200,
		    		MARGIN_VERTICAL_BEGIN_DETAILS+500,
		    		200,
		    		labelPaint);
		    insertText(canvas,"aaa aaaaaa bbbbb ccccccccccc vvvvvvvvvv ffffffffffffffffffffffff",
		    		MARGIN_HORIZONTAL_1+200,
		    		MARGIN_VERTICAL_BEGIN_DETAILS+650,
		    		200,
		    		labelPaint);
		  	    
		}

	

	private void insertText(Canvas canvas,String text,int maginX, int marginY, int width, TextPaint textPaint) {
		
		StaticLayout mTextLayout = new StaticLayout(text,textPaint, 200, Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
		canvas.save();
		Log.d("HBPrint", "Length : "+mTextLayout.getHeight()); //56
		canvas.translate(maginX, marginY);
		mTextLayout.draw(canvas);
		canvas.restore();
	}
		
	private void insertBitmap(Canvas canvas, Paint paint, Bitmap bitmapOrg, int imageOrigineX, int imageOrigneY) {
		
		canvas.save();
		canvas.drawBitmap(resizeBitmap(bitmapOrg,IMAGE_WIDTH,IMAGE_HEIGHT), imageOrigineX,imageOrigneY, paint);
		canvas.restore();
		
	}
	private Bitmap resizeBitmap(Bitmap bitmapOrg, int width, int height){
		    
			/**
			 * Insert a bit map into a specific place.
			 *
			 * @return A bitmap.
			 */
			
		    int targetWidth  = width;
	        int targetHeight = height;
	        Bitmap bmp = Bitmap.createBitmap(targetWidth, targetHeight,Bitmap.Config.ARGB_8888);
	        RectF rectf = new RectF(0, 0, targetWidth, targetHeight);  
	        Canvas c = new Canvas(bmp);
	        Path path = new Path();
	        path.addRect(rectf, Path.Direction.CW);
	        c.clipPath(path);
	        c.drawBitmap( bitmapOrg, new Rect(0, 0, bitmapOrg.getWidth(), bitmapOrg.getHeight()),
	                         new Rect(0, 0, targetWidth, targetHeight), null);
	        Matrix matrix = new Matrix();
	        matrix.postScale(1f, 1f);
	        Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, targetWidth, targetHeight, matrix, true);
        
	        return resizedBitmap;	        
		}
	
	
}
