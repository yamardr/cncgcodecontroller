/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cnc.gcode.controller;

import gnu.io.NRSerialPort;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author patrick
 */
public final class MainForm extends javax.swing.JFrame implements IGUIEvent{

    private IEvent GUIEvent=null;
    
    /**
     * Creates new form MainForm
     */
    public MainForm() {
        
        //Load Database
        if(!Database.load(null))
            JOptionPane.showMessageDialog(null,"Could not load Settings!");
        
        initComponents();
        
        //GuiUpdateHandler
        final IGUIEvent[] panels= new IGUIEvent[]{this,jPanelControl,jPanelAutoLevel,jPanelCNCMilling,jPanelCommunication,jPanelSettings};
        IEvent updateGUI= new IEvent() {
            @Override
            public void fired() {
                for(IGUIEvent panel:panels)
                    panel.updateGUI(Communication.getInstance().isConnect(), jPanelCNCMilling.isRunning() || jPanelAutoLevel.isWorking());
            }
        };
        for(IGUIEvent panel:panels)
            panel.setGUIEvent(updateGUI);
        
        
        //Load last Speed   
        jCBSpeed.setSelectedIndex(
                Integer.parseInt(
                Database.SPEED.get()));

        //Show Comports avilable
        Set<String> ports=NRSerialPort.getAvailableSerialPorts();
        if(ports.isEmpty())
        {
            ports.add("No Serialport found!");
        }

        jCBPort.setModel(new DefaultComboBoxModel(ports.toArray(new String[0])));
        int index=0;
        for (String port:ports) {
            if(port.equals(Database.PORT.get()))
            {
                jCBPort.setSelectedIndex(index);
                break;
            }
            index++;
        }
        
        Communication.getInstance().addChangedEvent(new IEvent() {
            @Override
            public void fired() {
                jLStatus.setText(Communication.getInstance().getStatus());

                fireupdateGUI();
            }
        });
       
        //Update Comport Status
        Communication.getInstance().disconnect();
        
    }
    
    @Override
    public void setGUIEvent(IEvent event) {
        GUIEvent=event;
    }

    @Override
    public void updateGUI(boolean serial, boolean isworking) {
        //Controll      
        jCBPort.setEnabled(!serial);
        jCBSpeed.setEnabled(!serial);
        jBConnect.setEnabled(!isworking);
        jBConnect.setText(serial?"Disconnect":"Connect");
    }
    
    private void fireupdateGUI()
    {
        if(GUIEvent==null)
            throw new RuntimeException("GUI EVENT NOT USED!");
        GUIEvent.fired();
    }
    

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanelControl = new cnc.gcode.controller.JPanelControl();
        jPanelAutoLevel = new cnc.gcode.controller.JPanelAutoLevel();
        jPanelCNCMilling = new cnc.gcode.controller.JPanelCNCMilling();
        jPanelCommunication = new cnc.gcode.controller.JPanelCommunication();
        jScrollPane = new javax.swing.JScrollPane();
        jPanelSettings = new cnc.gcode.controller.JPanelSettings();
        jBConnect = new javax.swing.JButton();
        jLStatus = new javax.swing.JLabel();
        jCBPort = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jCBSpeed = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("CNC-GCode-Controller");
        setName("jframe"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane2.addTab("Control", jPanelControl);
        jTabbedPane2.addTab("Auto Leveler", jPanelAutoLevel);
        jTabbedPane2.addTab("CNC Milling", jPanelCNCMilling);
        jTabbedPane2.addTab("Communication", jPanelCommunication);

        jScrollPane.setViewportView(jPanelSettings);

        jTabbedPane2.addTab("Settings", jScrollPane);

        jBConnect.setText("Connect");
        jBConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBConnectActionPerformed(evt);
            }
        });

        jLStatus.setText("Not connected");

        jLabel6.setText("@");

        jCBSpeed.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2400", "4800", "9600", "14400", "19200", "28800", "38400", "57600", "76800", "115200", "230400" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jCBPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCBSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBConnect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1054, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBConnect)
                    .addComponent(jLStatus)
                    .addComponent(jCBPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jCBSpeed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        //close Connection
        if(Communication.getInstance().isConnect())
            Communication.getInstance().disconnect();
        
        //Save Database
        if(!Database.save(null))
            JOptionPane.showMessageDialog(this,"Could not Save Settings!");
        
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    private void jBConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBConnectActionPerformed
        if(Communication.getInstance().isConnect())
        {
            Communication.getInstance().disconnect();
        }
        else
        {
            Communication.getInstance().connect((String)jCBPort.getModel().getSelectedItem(), Integer.parseInt((String)jCBSpeed.getSelectedItem()));
            if(Communication.getInstance().isConnect())
            {
                //Save config
                Database.PORT.set((String)jCBPort.getModel().getSelectedItem());
                Database.SPEED.set(((Integer)jCBSpeed.getSelectedIndex()).toString());

                Communication.getInstance().send("G90");
            }
            else
            {
                jLStatus.setText(Communication.getInstance().getStatus());
            }
        }
    }//GEN-LAST:event_jBConnectActionPerformed
   

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBConnect;
    private javax.swing.JComboBox jCBPort;
    private javax.swing.JComboBox jCBSpeed;
    private javax.swing.JLabel jLStatus;
    private javax.swing.JLabel jLabel6;
    private cnc.gcode.controller.JPanelAutoLevel jPanelAutoLevel;
    private cnc.gcode.controller.JPanelCNCMilling jPanelCNCMilling;
    private cnc.gcode.controller.JPanelCommunication jPanelCommunication;
    private cnc.gcode.controller.JPanelControl jPanelControl;
    private cnc.gcode.controller.JPanelSettings jPanelSettings;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JTabbedPane jTabbedPane2;
    // End of variables declaration//GEN-END:variables

    

}
