package mdl_10;

// import tools yang di pakai
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class Gui_10 extends javax.swing.JFrame {

    // sebuah konstruktor
    public Gui_10() {
        initComponents(); // untuk menginisialisasi gui
        Koneksi(); // untuk menghubungkann ke database
        event(); // event buat tombol
    }
    
    
    Connection con = null;
    
    // untuk konfigurasi database
    void Koneksi(){
        
        try{
            String connectionURL = "jdbc:mysql://localhost/coba10";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(connectionURL, username , password );
            System.err.println("Terkoneksi, OK!");
        }
        catch(Exception ex)
        {
            System.err.println("Gagal Terkonek, Yah...");
            System.exit(1);
        }
    }
    
    // sebuah fungsi yang mempermudah pengguna mencari data
    void cari() {
        try {
            Statement stat = con.createStatement();
            String sql = "SELECT * FROM crud WHERE kode LIKE '" + txtKode.getText() + "' OR nama LIKE '" + txtKode.getText() + "' OR gender LIKE '" + txtGender.getText() + "'";
            ResultSet isi = stat.executeQuery(sql);
            if (isi.next()) {
                txtNama.setText(isi.getString("nama"));
                txtGender.setText(isi.getString("gender"));
            } else {
                JOptionPane.showMessageDialog(null, "Cari Menggunakan kode");
            }
            stat.close();
        } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex);
            }
    }

    // fungsi untuk menyimpan data
    void simpan() {
        try {
            String kode = txtKode.getText();
            String nama = txtNama.getText();
            String gender = txtGender.getText();

            // Validasi gender
            if (!gender.equalsIgnoreCase("p") && !gender.equalsIgnoreCase("l")) {
                JOptionPane.showMessageDialog(null, "Data gender hanya bisa 'P' (Perempuan) atau 'L' (Laki-laki).", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Menghentikan proses simpan jika tidak memenuhi validasi
            }

            // mengubah huruf menjadi kapital saat berada di database
            gender = gender.toUpperCase();

            Statement statement = con.createStatement();
            String sql = "INSERT INTO crud VALUES('" + kode + "','" + nama + "','" + gender + "');";
            statement.executeUpdate(sql);
            statement.close();
            JOptionPane.showMessageDialog(null, "Disimpan!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Tidak tersimpan.");
            System.err.print(ex);
        }
    }



    
    // untuk mengubah / update sebuah data
    void update() {
        try {
            Statement stm = con.createStatement();
            String sql = "SELECT kode FROM crud WHERE kode = '" + txtKode.getText() + "'";
            ResultSet rs = stm.executeQuery(sql);

            // Mengecek apa user mengupdate kode
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Data kode tidak boleh di update", "Peringatan!", JOptionPane.WARNING_MESSAGE);
                rs.close();
                stm.close();
                return; // Menghentikan proses update jika user mencoba mengubah kode
            }

            rs.close();

            String updatedGender = txtGender.getText();
            if (updatedGender.equalsIgnoreCase("p") || updatedGender.equalsIgnoreCase("l")) {
                updatedGender = updatedGender.toUpperCase(); // mengubah huruf menjadi kapital saat berada di database
            } else {
                JOptionPane.showMessageDialog(null, "Data gender hanya bisa 'P' (Perempuan) atau 'L' (Laki-laki).", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Menghentikan update jika data gender berisi selain P/L
            }

            sql = "update crud set nama='" + txtNama.getText() + "', gender='" + updatedGender + "' where kode='" + txtKode.getText() + "'";
            int rowsAffected = stm.executeUpdate(sql);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Berhasil Update!", "Sukses!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal di update.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            stm.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // untuk menghapuus data yang ada di dalam database
    void hapus() {
        try {
            Statement statement = con.createStatement();
            String kode = txtKode.getText();
            String sql = "DELETE FROM crud WHERE kode='" + kode + "'";
            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Berhasil hapus!");
            } else {
                JOptionPane.showMessageDialog(null, "Data di temukan.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    // untuk memasukan event ke dalam button 
    void event(){
        // button simpan
        btn_simpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                simpan();
            }
        });
        
        // button update
        btn_update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                update();
            }
        });
        
        // button hapus
        btn_hapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                hapus();
            }
        });
        
        // button cari
        btn_cari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cari();
            }
        });
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtGender = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_cari = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Kode:");

        jLabel2.setText("Nama:");

        jLabel3.setText("Gender:");

        jLabel4.setText("MODUL 10 CRUD");

        btn_simpan.setText("Simpan");

        btn_update.setText("Update");

        btn_cari.setText("Cari");

        btn_hapus.setText("Hapus");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtKode, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(txtNama)
                            .addComponent(txtGender))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_simpan)
                        .addGap(18, 18, 18)
                        .addComponent(btn_update)
                        .addGap(28, 28, 28)
                        .addComponent(btn_hapus)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cari)
                        .addContainerGap(53, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(157, 157, 157)
                .addComponent(jLabel4)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_simpan)
                    .addComponent(btn_update)
                    .addComponent(btn_hapus)
                    .addComponent(btn_cari))
                .addContainerGap(73, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Gui_10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui_10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gui_10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gui_10.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui_10().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtGender;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
