package com.oresoftware.gerenciadordeprojetos.activities;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oresoftware.gerenciadordeprojetos.R;
import com.oresoftware.gerenciadordeprojetos.models.ProjetoModel;
import com.oresoftware.gerenciadordeprojetos.models.ServicesModel;

public class NovoServiceFragment extends Fragment {

    private Button btn_create;
    private EditText edt_titulo, edt_custo, edt_descricao;
    private ProjetoModel projeto = new ProjetoModel();

    public NovoServiceFragment() {
        // Required empty public constructor
    }
    public static NovoServiceFragment newInstance(String param1, String param2) {
        NovoServiceFragment fragment = new NovoServiceFragment();
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

        View view = inflater.inflate(R.layout.fragment_novo_service, container, false);
        btn_create = view.findViewById(R.id.services_btn_create);
        edt_titulo = view.findViewById(R.id.services_edt_service_titulo);
        edt_custo = view.findViewById(R.id.services_edt_service_custo);
        edt_descricao = view.findViewById(R.id.services_edt_service_descricao);

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            projeto = (ProjetoModel) getArguments().getSerializable("proj");

            ServicesModel service = new ServicesModel();

            btn_create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (edt_titulo.getText().toString().isEmpty()) {

                        Janela(1);

                    } else if (edt_custo.getText().toString().isEmpty()) {

                        Janela(2);

                    } else {

                        service.setTitulo(edt_titulo.getText().toString());
                        double custo = Double.parseDouble(edt_custo.getText().toString());
                        service.setCusto(custo);
                        service.setDescricao(edt_descricao.getText().toString());

                        if (projeto.addService(service) == 1) {
                            Toast.makeText(getContext(), "Servico adicionado com sucesso!", Toast.LENGTH_SHORT).show();

                            ((MainActivity) getActivity()).replaceFragment(new ProjetoFragment());
                        } else {
                            Janela(3);
                        }


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
            case 1:
                alert.setMessage(R.string.erro_service_titulo);
                break;

            case 2:
                alert.setMessage(R.string.erro_service_orcamento);
                break;

            case 3:
                alert.setMessage(R.string.erro_service_custo);
                break;
        }

        alert.setNeutralButton("ok", null);
        alert.show();
    }

}


