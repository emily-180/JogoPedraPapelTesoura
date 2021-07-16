package com.example.jogopedrapapeltesoura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Atributos relacionados com os objetos gráficos da interface:
    private ImageView imgPedra;
    private ImageView imgPapel;
    private ImageView imgTesoura;
    private ImageView imgApp;
    private TextView tvResultado;
    private TextView pontoJogador;
    private TextView pontoApp;

    int pontosJogador = 0;
    int pontosApp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ligar os atributos com os objetos da interface,
        // procurando pelo ID dos objetos:
        imgPedra = findViewById( R.id.imgPedra );
        imgPapel = findViewById( R.id.imgPapel );
        imgTesoura = findViewById( R.id.imgTesoura );
        imgApp = findViewById( R.id.imgApp );
        tvResultado = findViewById( R.id.tvResultado );
        pontoApp = findViewById(R.id.pontoApp);
        pontoJogador = findViewById(R.id.pontoJogador);


        // Criando um objeto escutador de cliques:
        EscutadorClickImagem eci = new EscutadorClickImagem();
        // Associando o objeto criado acima nas imagens:
        imgPedra.setOnClickListener( eci );
        imgPapel.setOnClickListener( eci );
        imgTesoura.setOnClickListener( eci );

    }

    private class EscutadorClickImagem implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            // Variável para guardar a escolha do usuário:
            int escolhaUsuario = 0;
            // Precisamos identificar qual imagem o usuário clicou.
            // Devemos converter View em ImageView, e depois utilizar o metodo getId().
            ImageView img = (ImageView) view;
            switch ( img.getId() ) {
                case R.id.imgPedra:
                    escolhaUsuario = 1;
                    break;
                case R.id.imgPapel:
                    escolhaUsuario = 2;
                    break;
                case R.id.imgTesoura:
                    escolhaUsuario = 3;
                    break;
            }

            // Vamos gerar aleatoriamente a escolha do app.
            // O método nextInt(n) gera um número aleatório entre 0 e (n-1).
            // Como queremos de 1 a 3, geramos de 0 a 2, e somamos 1.
            // OBS: Isso é Java básico!!!

            int escolhaApp = new Random().nextInt(3) + 1;

            // Precisamos colocar a imagem correta que reflete
            // A escolha do App:

            switch ( escolhaApp ) {
                case 1:
                    imgApp.setImageResource(R.drawable.pedra);
                    break;
                case 2:
                    imgApp.setImageResource(R.drawable.papel);
                    break;
                case 3:
                    imgApp.setImageResource(R.drawable.tesoura);
                    break;
            }

            // Vamos ver quem ganhou... e informar o resultado.
            if ( ( escolhaApp == 1 && escolhaUsuario == 3 ) ||
                    ( escolhaApp == 2 && escolhaUsuario == 1 ) ||
                    ( escolhaApp == 3 && escolhaUsuario == 2 ) )
            {
                tvResultado.setText("O app ganhou!!!!");
                pontosApp = pontosApp + 1;
                pontoApp.setText(String.valueOf(pontosApp));
            }
            else
            {
                if ( (escolhaApp == 3 && escolhaUsuario == 1) ||
                        (escolhaApp == 1 && escolhaUsuario == 2) ||
                        (escolhaApp == 2 && escolhaUsuario == 3) )
                {
                    tvResultado.setText("Você ganhou!!");
                    pontosJogador = pontosJogador + 1;
                    pontoJogador.setText(String.valueOf(pontosJogador));
                }
                else
                {
                    tvResultado.setText("Deu empate!");
                }
            }

        }

    }
}