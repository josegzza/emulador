/*
José González
Raúl Castellanos Herrero

20/02/18
 */
package emulador;


public class ventanaEmulador extends javax.swing.JFrame {

    //Variables Globales
    boolean errorInstruccion = false;
        //Registros
    int registroPC, registroAC, registroMAR;
    int registroMDR;
    boolean registroFRn, registroFRz, registroFRo;
        //Arreglos de MM e indicador de si es dato o direccion de memoria
    int iMainMemory[] = new int [1000];
    boolean bMainMemory[] = new boolean [1000];
    
    
    public void reset()
    {
        //reset de frame
        DireccionDatoTextField.setText(" ");
        ac.setText("0");
        mar.setText("0");
        mdr.setText("0");
        pc.setText("0");
        mmIR.setText("000");
        mmDireccion.setText("000");
        cero.setText("F");
        negativo.setText("F");
        overflow.setText("F");
        copLista.setSelectedIndex(0);
        tdLista.setSelectedIndex(0);
        
        //reset de arreglos
        for(int i = 0; i < 1000; i++)
        {
            iMainMemory[i]=0;
            bMainMemory[i]=false;
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
    }
    
    //Cambiar los valores por las variables globales que seran...
    public void escribirDatos()
    {
        ac.setText(Integer.toString(registroAC));
        mar.setText(Integer.toString(registroMAR));
        mdr.setText(Integer.toString(registroMDR));
        pc.setText(Integer.toString(registroPC));
        mmIR.setText("000");
        mmDireccion.setText("000");
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
        
    }
    
    
    //lo equivalente a el MAIN en c++
    public ventanaEmulador() {
        initComponents();
        //Llamar funcion de resetear
    }

    /**
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
        mmIR = new javax.swing.JLabel();
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

        pc.setText("001");
        getContentPane().add(pc);
        pc.setBounds(510, 250, 77, 39);

        ir.setText("COP TD Dir/Dato");
        getContentPane().add(ir);
        ir.setBounds(170, 80, 115, 39);

        mmIR.setText("000");
        getContentPane().add(mmIR);
        mmIR.setBounds(110, 80, 39, 39);

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

        pack();
    }// </editor-fold>                        

    private void DireccionDatoTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        // TODO add your handling code here:
    }                                                      

    private void botonOkActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    }                                       

    private void botonOkMouseClicked(java.awt.event.MouseEvent evt) {                                     
        int icop;
        int itipDir;
        int iDatoDireccion;
        String direccionDato;
        
        errorInstruccion = false;
        direccionDato = DireccionDatoTextField.getText();
        itipDir = tdLista.getSelectedIndex();
        icop = copLista.getSelectedIndex();
        
        //Revisa que lo ingresado sea un numero
        try {
            iDatoDireccion = Integer.parseInt(direccionDato);
        } catch (NumberFormatException e){
            System.out.println("Error");
            errorInstruccion = true;
            iDatoDireccion = 0;
        }
        //Revisa que el numero ingresado sea dentro de lo posible
        if ((iDatoDireccion > 999) || (iDatoDireccion < -99))
        {
            System.out.println("Error");
            errorInstruccion = true;
            iDatoDireccion = 0;
        }
        
        //Funcion de actualizar todas las "variables globales"
        //FUncion de reset
        
        
        if (!errorInstruccion)
        {
            
            System.out.println(icop);
            System.out.println(itipDir);
            System.out.println(direccionDato);
            System.out.println(iDatoDireccion);



            switch (icop)
            {
            case 0:
                //Llamar funcion NOP
                break;
            case 1:
                //Llamar funcion NOP
                break;
            case 2:
                //Llamar funcion CLA
                break;
            case 3:
                //Llamar funcion NEG
                break;
            case 4:
                //Llamar funcion LDA
                break;
            case 5:
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
        }
        
        
        
        
        
    }                                    

    private void botonResetMouseClicked(java.awt.event.MouseEvent evt) {                                        
        reset();

    }                                       

    private void botonResetActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    /**
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

    // Variables declaration - do not modify                     
    private javax.swing.JTextField DireccionDatoTextField;
    private javax.swing.JLabel ac;
    private javax.swing.JLabel acLabel;
    private javax.swing.JButton botonOk;
    private javax.swing.JButton botonReset;
    private javax.swing.JLabel cero;
    private javax.swing.JLabel ceroLabel;
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
    private javax.swing.JLabel mmIR;
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