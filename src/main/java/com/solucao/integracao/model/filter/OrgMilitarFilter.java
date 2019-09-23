package com.solucao.integracao.model.filter;

public class OrgMilitarFilter extends FiltroPadrao {

    private String sigla;
    private String nomeOm;

    @Override
    public String getOrdemDefault() {
        return "siglas";
    }


    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNomeOm() {
        return nomeOm;
    }

    public void setNomeOm(String nomeOm) {
        this.nomeOm = nomeOm;
    }
}
