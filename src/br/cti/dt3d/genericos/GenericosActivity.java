package br.cti.dt3d.genericos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class GenericosActivity extends Activity implements OnClickListener , OnLongClickListener{
    /** Called when the activity is first created. */
	static Database db;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen();
        long max = Runtime.getRuntime().maxMemory();
        if(max<20971520)HeapPequeno();
        AssetManager a = getAssets();
        db= new Database(a);
        //Mostra o conteúdo na tela
        setContentView(R.layout.main);

        if(db.fim)MostraErro();
        //Define os Botões
        Button subst = (Button) findViewById(R.id.subst);
        subst.setOnClickListener(this);
        Button nome = (Button) findViewById(R.id.nome);
        nome.setOnClickListener(this);
        View imagem = findViewById(R.id.logo);
        imagem.setOnClickListener(this);
        imagem.setOnLongClickListener(this);
                      
    }
    
    public void setFullscreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View v){
    	Intent intent;
    	//Verifica qual botão foi pressionado
    	switch(v.getId()){
	    	//Caso seja o de substancias, exibe a lista de substancias
    		case R.id.subst:
	    		intent = new Intent(this, SubstanciasActivity.class);
	    		startActivity(intent);
	    		break;
	    	//Caso seja o de nomes comerciais, exibe a lista correspondente
	       	case R.id.nome:
	       		intent = new Intent(this, NomesActivity.class);
	       		startActivity(intent);
	       		break;
	       	case R.id.logo:
	       		MostraSobre(this);
	       		break;
	    }
    }
    
    @Override
    public boolean onLongClick(View v){
    	
    	
    	if(v.getId()==R.id.logo){
    		String url = "http://www.cti.gov.br";
    		Intent i = new Intent(Intent.ACTION_VIEW);
    		i.setData(Uri.parse(url));
    		startActivity(i);
    	}
    	return false;
    }
    
    @Override
    //Cria menu de opções
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu2, menu);
    	return true;
    }
	
    @Override
    //Verifica qual opção foi selecionada e executa ação para ela
	public boolean onOptionsItemSelected(MenuItem item){
		//Opçao Sobre
    	if(item.getItemId()==R.id.sobre2) GenericosActivity.MostraSobre(this);
    	return super.onOptionsItemSelected(item);
    }
    
    public static void MostraSobre(Context context){
    	AlertDialog.Builder builder = new AlertDialog.Builder(context);
		//Mostra a dialog box "Sobre"
		builder.setMessage("Versão 1.3\nCentro de Tecnologia da Informação Renato Archer\n(www.cti.gov.br)\n\nAuthors:\nGuilherme H. P. da Silva\nGuilherme C. S. Ruppert\n");
		builder.setCancelable(true);	    		
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		builder.show();
    }

	public void MostraErro(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Erro ao ler base de dados\nTerminando");
		builder.setCancelable(true);	    		
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		builder.show();
    }
	
	public void HeapPequeno(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Tamanho Máximo do Heap de seu celular e menor que o recomendado para esse aplicativo\n\nPodem ocorrer problemas na execução");
		builder.setCancelable(true);	    		
		builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		builder.show();
    }
	
	@Override
	public void onBackPressed(){
		db = null;
		finish();
	}
	
}