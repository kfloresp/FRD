package com.teamallqu.frd;

public class utilidades {
    public static final String TABLAFICHA="FICHA";
    public static final String col_numsujeto="numsujeto";
    public static final String col_numhistoria="numhistoria";
    public static final String col_fecha="fecha";
    public static final String col_edad="edad";
    public static final String col_talla="talla";
    public static final String col_peso="peso";
    public static final String col_imc="imc";
    public static final String col_estado="resultado";
    public static final String col_diagnostico="diagnostico";

    public static String CREAR_TABLAFICHA = "CREATE TABLE "+TABLAFICHA+"("+col_numsujeto+" INTEGER PRIMARY KEY AUTOINCREMENT,"+col_numhistoria+" TEXT,"+col_edad+" TEXT,"+col_talla+" TEXT,"+col_peso+" TEXT,"+col_imc+" TEXT,"
            +col_estado+" TEXT,"+col_diagnostico+" TEXT,"+col_fecha+" TEXT"+
            ")";
}
