#include <iostream>
#include <string>
#include <stdlib.h>
#include "Instruccion.h"
using namespace std;

//Declaramos
int revisaCodigoOperacion(string scop);
int revisaTipoDireccionamiento (string stipDir, int cop);
int revisaDireccionDato (string sdirDat, int cop);
void fNOP();
void fCLA();
void fNEG();
void fLDA();
void fSTA();
void fADD();
void fSUB();


void caseCodigoOperacion (int codigoOperacion);
void fLDA();
//Variables Globales
bool errorInstruccion = false;
int registroPC, registroAC, registroMAR, registroMDR,contador,zeta;
int icop, itipDir, iDatoDireccion;
bool registroFRn, registroFRz, registroFRo;
//AQUI va la variable globar de arreglo de integers de 1000 x 6 que es la MainMemory
int iMainMemory[1000];
bool bMainMemory[1000];

//Inicializar en 0 la memoria.
//bMainMemory=false significa que es Direcciones. true=Datos.


int main()
{
    string scop, stipDir, sdirDat;

    bool dato, negativo;

    for(contador=0;contador<1000;contador++)
    {
      iMainMemory[contador]=0;
      bMainMemory[contador]=false;
    }


    //INICIO, DONDE EXPLICAMOS COMO INGRESAR LA INSTRUCCION
    cout<<"\tI N I C I O"<<endl;
    cout<<"Escribe la Instruccion con mayusculas SEPARADAS POR UN ESPACIO"<<endl;
    cout<<"Comenzando por el COP"<<endl;
    cout<<"\tNOP - CLA - NEG - LDA - STA - ADD - SUB - HLT"<<endl;
    cout<<"Después el tipo de direccionamiento"<<endl;
    cout<<"\tINM - R - A - IND"<<endl;
    cout<<"Finalmente, escribe la direccion o dato"<<endl;
    cout<<"\tDato-> +00 Direccion-> 100"<<endl;
    cout<<"Ejemplo: LDA A 200 \t en caso de que la direccion sea 2, se debe ingresar 002"<<endl;
    cout<<"En caso de ser HLT, NOP o CLA, poner dos caracteres despues de esta instruccion"<<endl;
    cout<<"Ejemplo: HLT 0 0 "<<endl;


//AQUI ES DONDE INICIA EL CICLO, QUE SE DETENDRA HASTA QUE PONGA UN HLT!
    do {
        //input
        cin>>scop>>stipDir>>sdirDat;

        //Llamamos a estas funciones para verificar y obtener todo en integers
        icop = revisaCodigoOperacion(scop);
        itipDir = revisaTipoDireccionamiento(stipDir, icop);
        iDatoDireccion = revisaDireccionDato (sdirDat, icop);


        if (errorInstruccion)
            cout<<"Error de Intsruccion, intente de nuevo"<<endl;
        errorInstruccion = false;

    } while (scop != "HLT");

    return 0;
}


//          CODIGO DE OPERACION
//REVISA QUE COP ES, EN CASO DE QUE NO SEA NINGUNO DE LOS POSIBLES
//MANDA UN MENSAJE DE ERROR A PANTALLA Y ACTIVA UN BOOL (errorInstruccion)
int revisaCodigoOperacion(string scop)
{
    int codigoOperacion = 0;

    if(scop == "NOP") { codigoOperacion = 0; }
    else if (scop == "CLA") { codigoOperacion = 1;}
    else if (scop == "NEG") { codigoOperacion = 2;}
    else if (scop == "LDA") { codigoOperacion = 3;}
    else if (scop == "STA") { codigoOperacion = 4;}
    else if (scop == "ADD") { codigoOperacion = 5;}
    else if (scop == "SUB") { codigoOperacion = 6;}
    else if (scop == "HLT") { codigoOperacion = 10;}
    else {
        codigoOperacion = 0;
        errorInstruccion = true;
    }
    return codigoOperacion;
}

//          TIPO DE DIRECCIONAMIENTO
//REVISA EL STRING QUE RECIBE COMO TIPO DE DIRECCIONAMIENTO
//EN CASO DE QUE NO SEA NINGUNO DE LOS POSIBLES
//MANDA UN MENSAJE DE ERROR A PANTALLA Y ACTIVA UN BOOL (errorInstruccion)
int revisaTipoDireccionamiento (string stipDir, int cop)
{
    int itipoDir = -1;

    //En el caso de que sea un NOP, CLA o HLT no importa que caracter haya puesto
    if ((cop == 0) || (cop == 1) || (cop == 10))
        return itipoDir;
    else if(stipDir == "INM") { itipoDir = 0; }
    else if (stipDir == "R") { itipoDir = 1; }
    else if (stipDir == "A") { itipoDir = 2; }
    else if (stipDir == "IND") { itipoDir = 3; }
    else {
        itipoDir = -1;
        errorInstruccion = true;
    }
    return itipoDir;
}

//          DIRECCION O DATO
//REVISA QUE LO INGRESADO SEA DEL TAMAÑO PERMITIDO
//REVISA SI ES DATO O DIRECCION, ACTIVANDO UN BOOL EN SU CASO (dato)
//Y DESPUES LO CONVIERTE A INTEGER PARA PODER USARLO MAS ADELANTE
int revisaDireccionDato (string sdirDat, int cop)
{
    int valor;
    bool dato = false;
    bool negativo = false;

    //En el caso de que sea un NOP, CLA o HLT no importa que caracter haya puesto
    if ((cop == 0) || (cop == 1) || (cop == 10))
        return valor;
    else if(sdirDat.length() > 3)
        errorInstruccion = true;
    else if ((sdirDat[0] == '+') || (sdirDat[0] == '-'))
    {
        dato = true;
        if (sdirDat[0] == '-')
            negativo = true;
        sdirDat.erase(0,1);     //Borramos el caracter de mas o menos
        //checamos que lo ingresado sean puros numeros
        //si es así, convertimos a integer
        if (((sdirDat[0] > 47) && (sdirDat[0] < 58)) && ((sdirDat[1] > 47) && (sdirDat[1] < 58)))
        {
            valor = atoi(sdirDat.c_str());
            if (negativo)
                valor *= -1;
        }
        else
            errorInstruccion = true;
    }
    //En este caso significa que los tres espacios son numeros, se verifica y se convierte a integer
    else if (((sdirDat[0] > 47) && (sdirDat[0] < 58)) && ((sdirDat[1] > 47) && (sdirDat[1] < 58)) && ((sdirDat[2] > 47) && (sdirDat[2] < 58)))
        valor = atoi(sdirDat.c_str());
    else
        errorInstruccion = true;

    return valor;
}


void caseCodigoOperacion (int codigoOperacion)
{
    switch (codigoOperacion)
    {
    case 1:
        fNOP();
        break;
    case 2:
        fCLA();
        break;
    case 3:
        fNEG();
        break;
    case 4:
        fLDA();
        break;
    case 5:
        //Llamar funcion STA
        fSTA();
        break;
    case 6:
        //LLamar funcion ADD
        fADD();
        break;
    case 7:
        //LLamar funcion SUB
        fSUB();
        break;
    }
}

void fNOP()
{
  //que pierda tiempo.
}

void fCLA()
{
  registroAC=0;
}

void fNEG()
{
  registroAC*=-1;
}


void fLDA()
{
  switch(itipDir)
  {
    case 1:
    registroAC=iDatoDireccion;
    break;
    case 2:
    registroMAR=registroPC+iDatoDireccion;
    registroMDR=iMainMemory[registroMAR];
    registroAC=registroMDR;
    break;
    case 3: 
    registroMAR=iDatoDireccion;
    registroMDR=iMainMemory[registroMAR];
    registroAC=registroMDR;
    break;
    case 4:
    registroMAR=iDatoDireccion;
    registroMDR=iMainMemory[registroMAR];
    //Delay
    registroMAR=registroMDR;
    registroMDR=iMainMemory[registroMAR];
    registroAC=registroMDR;
    break;
  }
}

void fSTA()
{
  switch(itipDir)
  {
    case 2: 
    registroMAR=registroPC+iDatoDireccion;
    registroAC=iMainMemory[registroMAR];
    registroMDR=registroAC;
    iMainMemory[registroAC]=registroMDR;
    break;
    case 3:
    registroMAR=iDatoDireccion;
    registroAC=iMainMemory[registroMAR];
    registroMDR=registroAC;
    iMainMemory[registroAC]=registroMDR;
    break;
    case 4:
    registroMAR=iDatoDireccion;
    registroAC=iMainMemory[registroMAR];
    registroMDR=registroAC;
    //Delay
    registroMAR=registroMDR;
    registroAC=iMainMemory[registroMAR];
    registroMDR=registroAC;
    break;
  }
}

void fADD()
{
  swtich(itipDir)
  {
    case 1:registroAC+=iDatoDireccion; break;
    case 2:
    registroMAR=registroPC+iDatoDireccion;
    registroMDR=iMainMemory[registroMAR];
    registroAC+=registroMDR;
    break;
    case 3:
    registroMAR=iDatoDireccion;
    registroMDR=iMainMemory[registroMAR];
    registroAC+=registroMDR;
    break;
    case 4:
    registroMAR=iDatoDireccion;
    registroMDR=iMainMemory[registroMAR];
    //Delay
    registroMAR=registroMDR;
    registroMDR=iMainMemory[registroMAR];
    registroAC+=registroMDR;
    break;
  }
}

void fSUB()
{
  swtich(itipDir)
  {
    case 1: registroAC-=iDatoDireccion;break;
    case 2:
    registroMAR=registroPC+iDatoDireccion;
    registroMDR=iMainMemory[registroMAR];
    registroAC-=registroMDR;
    break;
    case 3:
    registroMAR=iDatoDireccion;
    registroMDR=iMainMemory[registroMAR];
    registroAC-=registroMDR;
    break;
    case 4:
    registroMAR=iDatoDireccion;
    registroMDR=iMainMemory[registroMAR];
    //Delay
    registroMAR=registroMDR;
    registroMDR=iMainMemory[registroMAR];
    registroAC-=registroMDR;
    break;
  }
}
