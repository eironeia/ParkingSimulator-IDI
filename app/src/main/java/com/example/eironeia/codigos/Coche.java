package com.example.eironeia.parking;

/**
 * Created by A on 01/05/2015.
 */
public class Coche {

    private int _id;
    private String _matricula;
    private int _dia;
    private int _mes;
    private int _any;
    private int _hora;
    private int _minut;

    private int _diaSortida;
    private int _mesSortida;
    private int _anySortida;
    private int _horaSortida;
    private int _minutSortida;
    public Coche(){
        //Constructor Vacio
    }
    public Coche(String matricula, int dia, int mes, int any, int hora,
                 int minut, int diaSortida, int mesSortida, int anySortida,
                 int horaSortida, int minutSortida) {

        this._matricula = matricula;
        this._dia = dia;
        this._mes = mes;
        this._any = any;
        this._hora = hora;
        this._minut = minut;
        this._diaSortida = diaSortida;
        this._mesSortida = mesSortida;
        this._anySortida = anySortida;
        this._horaSortida = horaSortida;
        this._minutSortida = minutSortida;
    }



    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_matricula() {
        return _matricula;
    }

    public void set_matricula(String _matricula) {
        this._matricula = _matricula;
    }

    /** ENTRADA **/

    public int get_dia() {
        return _dia;
    }

    public void set_dia(int _dia) {
        this._dia = _dia;
    }

    public int get_mes() {
        return _mes;
    }

    public void set_mes(int _mes) {
        this._mes = _mes;
    }

    public int get_any() {
        return _any;
    }

    public void set_any(int _any) {
        this._any = _any;
    }

    public int get_hora() {
        return _hora;
    }

    public void set_hora(int _hora) {
        this._hora = _hora;
    }

    public int get_minut() {
        return _minut;
    }

    public void set_minut(int _minut) {
        this._minut = _minut;
    }

    /** SORTIDA **/

    public int get_diaSortida() {
        return _diaSortida;
    }

    public void set_diaSortida(int _diaSortida) {
        this._diaSortida = _diaSortida;
    }

    public int get_mesSortida() {
        return _mesSortida;
    }

    public void set_mesSortida(int _mesSortida) {
        this._mesSortida = _mesSortida;
    }

    public int get_anySortida() {
        return _anySortida;
    }

    public void set_anySortida(int _anySortida) {
        this._anySortida = _anySortida;
    }

    public int get_horaSortida() {
        return _horaSortida;
    }

    public void set_horaSortida(int _horaSortida) {
        this._horaSortida = _horaSortida;
    }

    public int get_minutSortida() {
        return _minutSortida;
    }

    public void set_minutSortida(int _minutSortida) {
        this._minutSortida = _minutSortida;
    }


}
