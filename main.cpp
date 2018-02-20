#include <iostream>
#include <string>
#include <stdlib.h>


using namespace std;
#include "Instruccion.h"

int main()
{
    string scop, stipDir, sdirDat;
    int icop, itipDir, iDato, iDireccion;
    bool dato, negativo, errorInstruccion = false;

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
    //input
    cin>>scop>>stipDir>>sdirDat;

    //CODIGO DE OPERACION
        //REVISA QUE COP ES, EN CASO DE QUE NO SEA NINGUNO DE LOS POSIBLES
        //MANDA UN MENSAJE DE ERROR A PANTALLA Y ACTIVA UN BOOL (errorInstruccion)
    if(scop == "NOP") { icop = 0; }
    else if (scop == "CLA") { icop = 1;}
    else if (scop == "NEG") { icop = 2;}
    else if (scop == "LDA") { icop = 3;}
    else if (scop == "STA") { icop = 4;}
    else if (scop == "ADD") { icop = 5;}
    else if (scop == "SUB") { icop = 6;}
    else if (scop == "HLT") { icop = 10;}
    else {
        cout<<"COP invalido"<<endl;
        icop = 0;
        errorInstruccion = true;
    }

    //TIPO DE DIRECCIONAMIENTO
        //REVISA EL STRING QUE RECIBE COMO TIPO DE DIRECCIONAMIENTO
        //EN CASO DE QUE NO SEA NINGUNO DE LOS POSIBLES
        //MANDA UN MENSAJE DE ERROR A PANTALLA Y ACTIVA UN BOOL (errorInstruccion)
    if(stipDir == "INM") { itipDir = 0; }
    else if (stipDir == "R") { itipDir = 1; }
    else if (stipDir == "A") { itipDir = 2; }
    else if (stipDir == "IND") { itipDir = 3; }
    else {
        cout<<"TD invalido"<<endl;
        itipDir = -1;
        errorInstruccion = true;
    }

    //DIRECCION O DATO
        //REVISA QUE LO INGRESADO SEA DEL TAMAÑO PERMITIDO
        //REVISA SI ES DATO O DIRECCION, ACTIVANDO UN BOOL EN SU CASO (dato)
        //Y DESPUES LO CONVIERTE A INTEGER PARA PODER USARLO MAS ADELANTE
    dato = false;
    negativo = false;
    iDato = 0;
    iDireccion = -1;
    if(sdirDat.length() > 3)
    {
        cout<<"Direccion o dato ingresado invalido (1)"<<endl;
        errorInstruccion = true;
    }
    else if ((sdirDat[0] == '+') || (sdirDat[0] == '-')) {
        dato = true;
        if (sdirDat[0] == '-') { negativo = true; }
        sdirDat.erase(0,1);
        if (((sdirDat[0] > 47) && (sdirDat[0] < 58)) && ((sdirDat[1] > 47) && (sdirDat[1] < 58)))
        {
            iDato = atoi(sdirDat.c_str());
            if (negativo) { iDato *= -1; }
        }
        else
        {
            cout<<"dato ingresado invalido (2)"<<endl;
            errorInstruccion = true;
        }
    }
    else if (((sdirDat[0] > 47) && (sdirDat[0] < 58)) && ((sdirDat[1] > 47) && (sdirDat[1] < 58)) && ((sdirDat[2] > 47) && (sdirDat[2] < 58)))
    {
        iDireccion = atoi(sdirDat.c_str());
    }
    else
    {
        cout<<"dato ingresado invalido (3)"<<endl;
        errorInstruccion = true;
    }

    if (errorInstruccion)
    {
        cout<<"Error de Intsruccion"<<endl;
    }




    cout<<scop<<"  "<<icop<<endl;
    cout<<stipDir<<"  "<<itipDir<<endl;
    cout<<sdirDat<<"   dato = "<<iDato<<"  Direccion = " <<iDireccion<<endl;





    return 0;
}
