package br.cti.dt3d.genericos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.TextView;

public class MostraSubstancia extends Activity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		  
		String path = "file://data/data/br.cti.dt3d.genericos/files/result.html";
        String selected = getIntent().getExtras().getString("Subst");
		setContentView(R.layout.subst);	  
		TextView titulo = (TextView)findViewById(R.id.nome);
		titulo.setText(selected);
		WebView web = (WebView)findViewById(R.id.webView1);
		web.getSettings().setSupportZoom(true);
		web.getSettings().setDefaultTextEncodingName("utf-8");
		resultToHtml(GenericosActivity.db.procuraSubst(selected));
		web.loadUrl(path);  
		  
	}
	
	public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu2, menu);
    	return true;
    }
	
	public boolean onOptionsItemSelected(MenuItem item){
    	if(item.getItemId()==R.id.sobre2) GenericosActivity.MostraSobre(this);
    	return super.onOptionsItemSelected(item);
    }
	
//	public String resultToHtml(List<Produto> result){
//		
//		String html="<html><body><b>Produtos disponíveis</b><br><br>";
//		int i;
//		Produto p;
//		
//		for(i=0; i<result.size(); i++){
//			p=result.get(i);
//			html=html.concat("<b>Produto: "+p.nome+"</b><br>");
//			html=html.concat("Laboratório: "+p.lab+"<br>");
//			html=html.concat("Apresentação: "+p.apresentacao+"<br>");
//			html=html.concat("Preço Máximo: R$"+p.preco+"<br><br>");
//			//html=html.concat("Somente Hospitalar? "+p.hospt+"<br><br>");
//		}
//		html=html.concat("</body></html>");
//		return html;		
//	}
//}
	public void resultToHtml(List<Produto> result){
		
		//String html="<html><body><b>Produtos disponíveis</b><br><br>";
		int i;
		Produto p;
		
		try{
			FileOutputStream fos = openFileOutput("result.html", Context.MODE_PRIVATE);
			fos.write(("<html><body><b>Produtos disponíveis</b><br><br>").getBytes());
			
			for(i=0; i<result.size(); i++){
				p=result.get(i);
				fos.write(("<b>Produto: "+p.nome+"</b><br>").getBytes());
				fos.write(("Laboratório: "+p.lab+"<br>").getBytes());
				fos.write(("Apresentação: "+p.apresentacao+"<br>").getBytes());
				fos.write(("Preço Máximo: R$"+p.preco+"<br><br>").getBytes());
				//html=html.concat("Somente Hospitalar? "+p.hospt+"<br><br>");
			}
			fos.write(("</body></html>").getBytes());
			fos.close();
		}catch(FileNotFoundException f){}
		catch(IOException io){}
		
		
	}
}
