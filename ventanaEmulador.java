/**
 @progName Emulador, Organizacion Computacional
 @author A01154891   Raúl Castellanos Herrero
 @author A01038061   José Alberto Gonzalez Arteaga
 @date 19/02/18 a 25/02/18
 @desc Programa que simula una memoria de 100 espacions, y se va programando en lenguaje ensamblador

 */

package emulador;
import java.awt.event.ActionListener;
import javax.swing.Timer;


public class ventanaEmulador extends javax.swing.JFrame {

    //Variables Globales
    boolean errorInstruccion = false;
    int icop, itipDir, iDatoDireccion;
        //Registros
    int registroPC, registroAC, registroMAR;
    int registroMDR;
    boolean registroFRn, registroFRz, registroFRo;
        //Arreglos de MM e indicador de negativo o positivo
    int iMainMemory[] = new int [1000];
    boolean bMainMemory[] = new boolean [1000];
    /**
@funcName reset()
     @desc Funcion que inicializa todas las etiquetas y pone las variables globales (registros) en su valor inicial
     Comienza con reseteando donde se elige la instruccion, despues pone los arreglos en 0 y resetea los registros. Finalmente, llama a la funcion escribir datos
    */
    public void reset()
    {
        //reset de frame
        DireccionDatoTextField.setText("");
        copLista.setSelectedIndex(0);
        tdLista.setSelectedIndex(0);
        
        //reset de arreglos
        for(int i = 0; i < 1000; i++)
        {
            iMainMemory[i]=0;
            bMainMemory[i]=false;   //todos los numeros son positivos
        }
        
        //reset de variables/registros
        errorInstruccion = false;
        registroPC = 0;
        registroAC = 0;
        registroMAR = 0;
        registroMDR = 0;
        registroFRn = false;
        registroFRz = false;
        registroFRo = false;
        escribirDatos();
        mmDireccion.setText("000");
        
        contenidoDireccion.setText(Integer.toString(iMainMemory[registroPC]));
        ir.setText(DireccionDatoTextField.getText());
    }
    /**
@funcName escribirDatos()
     @desc Funcion que actualiza las etiquetas del frame, en base a lo que digan las variables globales (registros).
     */
    public void escribirDatos()
    {
        ac.setText(Integer.toString(registroAC));
        mar.setText(Integer.toString(registroMAR));
        mdr.setText(Integer.toString(registroMDR));
        pc.setText(Integer.toString(registroPC));
        
        //mmIR.setText("000");
        mmDireccion.setText(Integer.toString(registroPC-1));
        
        if(registroFRz)
            cero.setText("V");
        else
            cero.setText("F");
        
        if(registroFRn)
            negativo.setText("V");
        else
            negativo.setText("F");
        
        if(registroFRo)
            overflow.setText("V");
        else
            overflow.setText("F");
        
        contenidoDireccion.setText(Integer.toString(iMainMemory[registroPC-1]));
        //ir.setText(DireccionDatoTextField.getText());
        
    }
    /**
@funcName imprimirIR()
     @desc Función que en base a lo elegido como instrucción, pasa eso a forma de texto para mostrarlo en la etiqueta de IR
     En el caso de que sea NOP, CLA, NEG o HLT, ignora lo demas
     */
    public void imprimirIR()
    {
        
        String irTemporal ="";
        
        icop = copLista.getSelectedIndex();
        itipDir = tdLista.getSelectedIndex();
        
    switch (icop)
    {
        case 0: irTemporal = "";        break;
        case 1: irTemporal = "NOP   ";    break;
        case 2: irTemporal = "CLA   ";    break;
        case 3: irTemporal = "NEG   ";    break;
        case 4: irTemporal = "LDA   ";    break;
        case 5: irTemporal = "STA   ";    break;
        case 6: irTemporal = "ADD   ";    break;
        case 7: irTemporal = "SUB   ";    break;
        case 8: irTemporal = "HLT   ";    break;
    }
    switch(itipDir)
    {
        case 0: irTemporal = "";            break;
        case 1: irTemporal += "Inmediato  ";  break;
        case 2: irTemporal += "Relativo  ";   break;
        case 3: irTemporal += "Absoluto  ";   break;
        case 4: irTemporal += "Indirecto  ";  break;
    }
    if(icop == 0 || itipDir == 0)
        irTemporal = "";
    else
        irTemporal += DireccionDatoTextField.getText();

    
    ir.setText(irTemporal);
    }
    /**
@funcName revisaFR
     @desc Funcion que revisa el acumulador para verificar si una bandera deberia ser verdadero
     */
    public void revisaFR()
    {
        if(registroAC == 0)
            registroFRz = true;
        else
            registroFRz = false;
        if(registroAC < 0)
            registroFRn = true;
        else
            registroFRn = false;
        //if(registroAC overflow)
        //Caso en el que sea overflow
    }
    
    
    
    private void doLogin() {
        Timer delay = new Timer(3000, new ActionListener() 
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comentarios.setText("Algo raro si esta funcionado");
               // displayNextFrame();
            }
       });
       
        delay.setRepeats(false);
        delay.start();
        
    }

    
    
    
    
    /**
@funcName fLDA()
     @desc Funcion que ejecuta las micro-operaciones de la operacion LDA
     dentro de esta función varia para el tipo de direccionamiento que tiene
     */
    public void fLDA()
    {
        switch(itipDir)
        {
            case 0:
                System.out.println("Error LDA, caso 0 de TD... no deberia nunca llegar aqui");
                break;
            case 1:
                //Inmediato
                if((iDatoDireccion > 99) || (iDatoDireccion < -99)) {
                    System.out.println("Error, de ingreso de dato... LDA INM");
                }
                else {
                    registroAC = iDatoDireccion;
                    contenidoDireccion.setText(Integer.toString(iMainMemory[registroPC-1]));
                    mmDireccion.setText(Integer.toString(registroPC - 1));
                }
                
                break;
            case 2:
                //Relativo
                //Obligatoriamente aqui tiene que dar una direccion, por lo tanto debe ser >0
                if(iDatoDireccion > 0) {
                    registroMAR = registroPC+iDatoDireccion;
                    registroMDR = iMainMemory[registroMAR];
                    registroAC = registroMDR;
                }
                else {
                    comentarios.setText("La dirección ingresada no es válida");
                    errorInstruccion = true;
                    registroPC--;
                }
                
                break;
            case 3:
                //Absoluto
                //Obligatoriamente aqui tiene que dar una direccion, por lo tanto debe ser >0
                if(iDatoDireccion > 0) {
                    registroMAR=iDatoDireccion;
                    registroMDR=iMainMemory[registroMAR];
                    registroAC=registroMDR;
                }
                else {
                    comentarios.setText("La dirección ingresada no es válida");
                    errorInstruccion = true;
                    registroPC--;
                }
                
                break;
            case 4:
                //Indirecto
                if(iDatoDireccion > 0) {
                    registroMAR = iDatoDireccion;
                    registroMDR = iMainMemory[registroMAR];
                    ac.setText(Integer.toString(registroAC));
                    mar.setText(Integer.toString(registroMAR));
                    mdr.setText(Integer.toString(registroMDR));
                    //Se crea un delay para alcanzar a visualizar los datos, de las micro-operaciones                  
                    Timer delay = new Timer(2000, new ActionListener()  {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            registroMAR = registroMDR;
                            registroMDR = iMainMemory[registroMAR];
                            registroAC = registroMDR;
                            ac.setText(Integer.toString(registroAC));
                            mar.setText(Integer.toString(registroMAR));
                            mdr.setText(Integer.toString(registroMDR));
                        }
                   });
                    delay.setRepeats(false);
                    delay.start();
                }
                else {
                    comentarios.setText("La dirección ingresada no es válida");
                    errorInstruccion = true;
                    registroPC--;
                }
                break;
        }
    }
    
   
    /**
@funcName ventanaEmulador()
     @desc Función que inicializa el frame y sus componentes, esta es la primera función en ejecutarse al inicial el programa
     */
    //lo equivalente a el MAIN en c++
    public ventanaEmulador() {
        initComponents();
        //Llamar funcion de resetear¿? maybee....
    }

    /*
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        DireccionDatoTextField = new javax.swing.JTextField();
        acLabel = new javax.swing.JLabel();
        mdrLabel = new javax.swing.JLabel();
        marLabel = new javax.swing.JLabel();
        frLabel = new javax.swing.JLabel();
        pcLabel = new javax.swing.JLabel();
        irLabel = new javax.swing.JLabel();
        ac = new javax.swing.JLabel();
        mar = new javax.swing.JLabel();
        mdr = new javax.swing.JLabel();
        pc = new javax.swing.JLabel();
        ir = new javax.swing.JLabel();
        mmLabel = new javax.swing.JLabel();
        mmDireccion = new javax.swing.JLabel();
        contenidoDireccion = new javax.swing.JLabel();
        botonOk = new javax.swing.JButton();
        cero = new javax.swing.JLabel();
        negativo = new javax.swing.JLabel();
        overflow = new javax.swing.JLabel();
        negativoLabel = new javax.swing.JLabel();
        ceroLabel = new javax.swing.JLabel();
        overflowLabel = new javax.swing.JLabel();
        copLista = new javax.swing.JComboBox<>();
        tdLista = new javax.swing.JComboBox<>();
        botonReset = new javax.swing.JButton();
        comentarios = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 800));
        setMinimumSize(new java.awt.Dimension(800, 800));
        setPreferredSize(new java.awt.Dimension(800, 800));
        getContentPane().setLayout(null);

        DireccionDatoTextField.setText("Dir/Dato");
        DireccionDatoTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DireccionDatoTextFieldActionPerformed(evt);
            }
        });
        getContentPane().add(DireccionDatoTextField);
        DireccionDatoTextField.setBounds(430, 400, 62, 34);

        acLabel.setText("Acumulador");
        getContentPane().add(acLabel);
        acLabel.setBounds(390, 80, 77, 39);

        mdrLabel.setText("MDR");
        getContentPane().add(mdrLabel);
        mdrLabel.setBounds(390, 200, 77, 39);

        marLabel.setText("MAR");
        getContentPane().add(marLabel);
        marLabel.setBounds(390, 140, 77, 39);

        frLabel.setText("FR");
        getContentPane().add(frLabel);
        frLabel.setBounds(390, 320, 77, 39);

        pcLabel.setText("PC");
        getContentPane().add(pcLabel);
        pcLabel.setBounds(390, 260, 77, 39);

        irLabel.setText("IR");
        getContentPane().add(irLabel);
        irLabel.setBounds(20, 80, 77, 39);

        ac.setText("+00000");
        getContentPane().add(ac);
        ac.setBounds(510, 80, 77, 39);

        mar.setText("000");
        getContentPane().add(mar);
        mar.setBounds(510, 140, 77, 39);

        mdr.setText("+00");
        getContentPane().add(mdr);
        mdr.setBounds(510, 190, 77, 39);

        pc.setText("000");
        getContentPane().add(pc);
        pc.setBounds(510, 250, 77, 39);

        ir.setText("COP TD Dir/Dato");
        getContentPane().add(ir);
        ir.setBounds(170, 80, 115, 39);

        mmLabel.setText("MM");
        getContentPane().add(mmLabel);
        mmLabel.setBounds(20, 140, 77, 39);

        mmDireccion.setText("000");
        getContentPane().add(mmDireccion);
        mmDireccion.setBounds(110, 140, 39, 39);

        contenidoDireccion.setText("Contenido direccion");
        getContentPane().add(contenidoDireccion);
        contenidoDireccion.setBounds(170, 140, 115, 39);

        botonOk.setText("Ok");
        botonOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonOkMouseClicked(evt);
            }
        });
        botonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOkActionPerformed(evt);
            }
        });
        getContentPane().add(botonOk);
        botonOk.setBounds(510, 400, 70, 30);

        cero.setText("F");
        getContentPane().add(cero);
        cero.setBounds(550, 310, 20, 30);

        negativo.setText("F");
        getContentPane().add(negativo);
        negativo.setBounds(510, 310, 20, 29);

        overflow.setText("F");
        getContentPane().add(overflow);
        overflow.setBounds(580, 310, 20, 29);

        negativoLabel.setText("N");
        getContentPane().add(negativoLabel);
        negativoLabel.setBounds(510, 340, 20, 16);

        ceroLabel.setText("Z");
        getContentPane().add(ceroLabel);
        ceroLabel.setBounds(550, 340, 20, 16);

        overflowLabel.setText("O");
        getContentPane().add(overflowLabel);
        overflowLabel.setBounds(580, 340, 20, 16);

        copLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COP", "NOP", "CLA", "NEG", "LDA", "STA", "ADD", "SUB", "HLT" }));
        getContentPane().add(copLista);
        copLista.setBounds(230, 400, 60, 34);

        tdLista.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TD", "Inmediado", "Relaitvo", "Absoluto", "Indirecto" }));
        getContentPane().add(tdLista);
        tdLista.setBounds(300, 400, 120, 34);

        botonReset.setText("Reset");
        botonReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonResetMouseClicked(evt);
            }
        });
        botonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonResetActionPerformed(evt);
            }
        });
        getContentPane().add(botonReset);
        botonReset.setBounds(20, 400, 90, 40);

        comentarios.setToolTipText("");
        comentarios.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(comentarios);
        comentarios.setBounds(230, 490, 350, 60);

        pack();
    }// </editor-fold>                        

    private void DireccionDatoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        // TODO add your handling code here:
    }                                                      

    private void botonOkActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    /**
@funcName botonOkMouseClicked
    @desc Funcion que es llamada al presionar el boton de Ok
    En esta función, primero revisa que lo ingresado como intrucción sea valido, en caso de que no sea valido, cambia un booleano para despues no ejecutar un switch que llama
    a la funcion correspondiente en base a su codigo de operacion.
    @param evt 
     */    
    private void botonOkMouseClicked(java.awt.event.MouseEvent evt) {                                     
        //Declaramos variables de esta funcion
        String direccionDato;
        comentarios.setText("");
        errorInstruccion = false;
        direccionDato = DireccionDatoTextField.getText();
        itipDir = tdLista.getSelectedIndex();
        icop = copLista.getSelectedIndex();
        
        validacionBasica(direccionDato);
        
        
        
        if (!errorInstruccion)
        {
            if(iDatoDireccion  < 0)
            {
                bMainMemory[registroPC] = true;
                iDatoDireccion *= -1;
            }
            //Convertimos la instrucción a como sera almacenada en el arreglo y almacenamos
            iMainMemory[registroPC] = ((icop*10000) + (itipDir*1000) + iDatoDireccion);
            if(bMainMemory[registroPC])
                iDatoDireccion *= -1;
            
            registroPC++;

            pc.setText(Integer.toString(registroPC));
            imprimirIR();

            switch (icop)
            {
            case 1:
                //NOP
                delay();
                break;
            case 2:
                //CLA
                registroAC = 0;
                ac.setText(Integer.toString(registroAC));
                
                break;
            case 3:
                //NEG
                registroAC *= -1;
                ac.setText(Integer.toString(registroAC));
                break;
            case 4:
                //LDA
                System.out.println(iDatoDireccion);
                fLDA();
                break;
            case 5:
                //fSTA();
                //LLamar funcion STA
                break;
            case 6:
                
                //LLamar funcion ADD
                break;
            case 7:
                
                //Llamar funcion SUB
                break;
            case 8:
                
                //Llamar funcion HLT
                break;
            }
            
            revisaFR();
            escribirDatos();
        }
        
        
        
        
        
    }                                    
    /**
@funcName botonResetMouseClicked
     @desc Funcion que cuando el boton de reset es presionado, manda a llamar a la función reset()
     @param evt 
     */
    private void botonResetMouseClicked(java.awt.event.MouseEvent evt) {                                        
        reset();

    }                                       

    private void botonResetActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    /*
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventanaEmulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaEmulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaEmulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaEmulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaEmulador().setVisible(true);
            }
        });
    }
    /**
@funcName delay()
    @desc Funcion que congela la pantalla durante 2 segundos
    */
    public void delay()
    {
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){}
    }
    
    /**
@funcName validacionBasica(String direccionDato)
     @desc Función que valida lo ingresado en la casilla de Dir/Dato o en los casos de COP, HLT, NEG, CLA o NOP ignora las demas casillas
     @param direccionDato 
     */
    public void validacionBasica(String direccionDato)
    {
        //Revisa que lo ingresado sea un numero
        try {
            iDatoDireccion = Integer.parseInt(direccionDato);
        } catch (NumberFormatException e){
            comentarios.setText("Valor ingresado erroeno, favor de verificarlo");
            System.out.println("Error");
            errorInstruccion = true;
            iDatoDireccion = 0;
        }
        //Revisa que el numero ingresado sea dentro de lo posible
        if ((iDatoDireccion > 999) || (iDatoDireccion < -99)) {
            comentarios.setText("Valor ingresado erroeno, favor de verificarlo");
            System.out.println("Error");
            errorInstruccion = true;
            iDatoDireccion = 0;
        }
        //Si tipo de Direccionamiento es 0, diagmos no eligio una opcion, es un error
        if(itipDir == 0) {
            comentarios.setText("Tipo de Direccionamiento Invalido");
            errorInstruccion = true;
        }
        //Si el tipo de direccion es inmediado, obligatoriamente debe ser un dato (checa que este dentro del rango)
        if((itipDir == 1) && ((iDatoDireccion<-99) || (iDatoDireccion>99))) {
            errorInstruccion = true;
            comentarios.setText("El dato ingresado, no es válido");
        }
        //Si es uno de COP, HLT, NEG, CLA o NOP
        if( ( (icop >= 1) && (icop <= 3) ) || (icop == 8) )
            errorInstruccion = false;
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField DireccionDatoTextField;
    private javax.swing.JLabel ac;
    private javax.swing.JLabel acLabel;
    private javax.swing.JButton botonOk;
    private javax.swing.JButton botonReset;
    private javax.swing.JLabel cero;
    private javax.swing.JLabel ceroLabel;
    private javax.swing.JLabel comentarios;
    private javax.swing.JLabel contenidoDireccion;
    private javax.swing.JComboBox<String> copLista;
    private javax.swing.JLabel frLabel;
    private javax.swing.JLabel ir;
    private javax.swing.JLabel irLabel;
    private javax.swing.JLabel mar;
    private javax.swing.JLabel marLabel;
    private javax.swing.JLabel mdr;
    private javax.swing.JLabel mdrLabel;
    private javax.swing.JLabel mmDireccion;
    private javax.swing.JLabel mmLabel;
    private javax.swing.JLabel negativo;
    private javax.swing.JLabel negativoLabel;
    private javax.swing.JLabel overflow;
    private javax.swing.JLabel overflowLabel;
    private javax.swing.JLabel pc;
    private javax.swing.JLabel pcLabel;
    private javax.swing.JComboBox<String> tdLista;
    // End of variables declaration                   
}
