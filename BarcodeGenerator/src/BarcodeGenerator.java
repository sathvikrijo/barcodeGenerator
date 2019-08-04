import java.io.BufferedReader;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileReader;

public class BarcodeGenerator {

    public static void main(String[] args) throws Exception {
        createPDF("barcodes.pdf");
    }

    public static void createPDF(String pdfFilename) throws Exception {
        FileReader fr = new FileReader("C:\\Users\\Sathvik Rijo\\Desktop\\barCodeScan\\barcodes.txt");
        Document doc = new Document();
        PdfWriter docWriter = null;
        try {
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Sathvik Rijo\\Desktop\\barCodeScan\\" + pdfFilename));

            doc.setPageSize(PageSize.LETTER);
            doc.open();
            PdfContentByte cb = docWriter.getDirectContent();

            Barcode128 code128 = new Barcode128();
            code128.setCodeType(Barcode128.CODE128);
            code128.setFont(null);

            String myString = new String();
            int xInc = 0, yInc = 0 ;
            BufferedReader br = new BufferedReader(fr);
            for(int index=1 ; index <= 31 ; index++ )
            {
                for(int row = 0 ; row < 8 ; row++)
                {
                    for(int col = 0 ; col < 4 ; col++ )
                    {
                        myString = br.readLine();
                        if(myString != null){
                            code128.setCode(myString.trim());
                            Image code128Image = code128.createImageWithBarcode(cb, null, null);
                            code128Image.setAbsolutePosition(25 + xInc, 700 + yInc);
                            code128Image.scalePercent(125);
                            doc.add(code128Image);
                            xInc = xInc + 150;
                        }
                        else
                            break;
                    }
                    xInc = 0;
                    yInc = yInc - 90;
                }
                doc.newPage();
                xInc = 0;
                yInc = 0;
                //8rows 4cols
                //xinc = 150 start from 10
                //yinc = 100 start from 700
            }
        }
        catch (DocumentException dex) {
            dex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (doc != null) {
                doc.close();
            }
            if (docWriter != null) {
                docWriter.close();
            }
        }
    }
}
