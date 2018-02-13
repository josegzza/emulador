#ifndef INSTRUCCION_H_INCLUDED
#define INSTRUCCION_H_INCLUDED

class Instruccion{
public:
    Instruccion();
    Instruccion(int inst, int tipoDir, int dat);
private:
    int tipoInstruccion;
    int tipoDireccionamiento;
    int dato;
};

Instruccion::Instruccion(){
    tipoInstruccion = 00;
    tipoDireccionamiento = 0;
    dato = 0;
}
Instruccion::Instruccion(int inst, int tipoDir, int dat){
    tipoInstruccion = inst;
    tipoDireccionamiento = tipoDir;
    dato = dat;
}
#endif // INSTRUCCION_H_INCLUDED

