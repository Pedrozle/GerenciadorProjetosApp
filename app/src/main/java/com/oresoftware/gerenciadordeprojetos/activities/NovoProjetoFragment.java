package com.oresoftware.gerenciadordeprojetos.activities;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.oresoftware.gerenciadordeprojetos.R;
import com.oresoftware.gerenciadordeprojetos.models.ProjetoModel;

public class NovoProjetoFragment extends Fragment {

    private Button btn_create;
    private EditText edt_titulo, edt_orcamento, edt_descricao;
    private TextView tv_titulo, tv_subtitulo;
    private Spinner spSpinner;
    private ProjetoModel projeto = new ProjetoModel();

    public NovoProjetoFragment() {
        // Required empty public constructor
    }

    public static NovoProjetoFragment newInstance(String param1, String param2) {
        NovoProjetoFragment fragment = new NovoProjetoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_novo_projeto, container, false);
        tv_titulo = view.findViewById(R.id.novo_projeto_txt_title);
        tv_subtitulo = view.findViewById(R.id.novo_projeto_txt_subtitle);
        edt_titulo = view.findViewById(R.id.novo_projeto_edt_projeto_titulo);
        edt_orcamento = view.findViewById(R.id.novo_projeto_edt_projeto_orcamento);
        edt_descricao = view.findViewById(R.id.novo_projeto_edt_projeto_descricao);
        spSpinner = view.findViewById(R.id.novo_projeto_sp_projeto_categoria);

        btn_create = view.findViewById(R.id.novo_projeto_btn_create);

        Bundle bundle = this.getArguments();

        if(bundle != null){

            projeto = (ProjetoModel) getArguments().getSerializable("proj");

            tv_titulo.setText(R.string.novo_projeto_txt_titulo_editar);
            tv_subtitulo.setText(R.string.novo_projeto_txt_subtitulo_editar);
            edt_titulo.setText(projeto.getTitulo());
            edt_orcamento.setText(projeto.getOrcamento() + "");
            edt_descricao.setText(projeto.getDescricao());
            String categoria = projeto.getCategoria();

            int pos = 0;
            for(int i=0; i < 4; i++){
                if(spSpinner.getItemAtPosition(i).toString().equals(categoria))
                    pos = i;
            }
            spSpinner.setSelection(pos);

            btn_create.setText(R.string.novo_projeto_btn_editar);
            btn_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(spSpinner.getSelectedItemId() == 0){

                        Janela(0);

                    }else if(edt_titulo.getText().toString().isEmpty()){

                        Janela(1);

                    }else if(edt_orcamento.getText().toString().isEmpty()){

                        Janela(2);

                    }else{

                        projeto.setTitulo(edt_titulo.getText().toString());
                        double orcamento = Double.parseDouble(edt_orcamento.getText().toString());
                        projeto.setOrcamento(orcamento);
                        projeto.setDescricao(edt_descricao.getText().toString());
                        projeto.setCategoria(spSpinner.getSelectedItem().toString());

                        projeto.atualizar();
                        Toast.makeText(getContext(), "Projeto Alterado com sucesso!", Toast.LENGTH_SHORT).show();

                        ((MainActivity) getActivity()).replaceFragment(new ProjetosFragment());
                        ((MainActivity) getActivity()).replaceSelectedMenu(2);

                    }
                }
            });

        }else{



            btn_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(spSpinner.getSelectedItemId() == 0){

                        Janela(0);

                    }else if(edt_titulo.getText().toString().isEmpty()){

                        Janela(1);

                    }else if(edt_orcamento.getText().toString().isEmpty()){

                        Janela(2);

                    }else{

                        projeto.setTitulo(edt_titulo.getText().toString());
                        double orcamento = Double.parseDouble(edt_orcamento.getText().toString());
                        projeto.setOrcamento(orcamento);
                        projeto.setDescricao(edt_descricao.getText().toString());
                        projeto.setCategoria(spSpinner.getSelectedItem().toString());

                        projeto.salvar();
                        Toast.makeText(getContext(), "Projeto inserido com sucesso!", Toast.LENGTH_SHORT).show();

                        ((MainActivity) getActivity()).replaceFragment(new ProjetosFragment());
                        ((MainActivity) getActivity()).replaceSelectedMenu(2);

                    }
                }
            });

        }

        return view;

    }

    public void Janela(int op){

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Erro");
        alert.setIcon(R.drawable.atencao);

        switch (op){
            case 0:
                alert.setMessage(R.string.erro_categoria);
                break;

            case 1:
                alert.setMessage(R.string.erro_projeto_titulo);
                break;

            case 2:
                alert.setMessage(R.string.erro_projeto_orcamento);
                break;
        }

        alert.setNeutralButton("ok", null);
        alert.show();
    }

}