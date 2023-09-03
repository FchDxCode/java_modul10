/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mdl_10;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Fachru
 */
public class Crud_10 {
    
    //variable
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

    Connection con = null;
    void Koneksi(){
        
        try{
            String connectionURL = "jdbc:mysql://localhost/coba10";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(connectionURL, username , password );
            System.err.println("Sukses Koneksi");
        }
        catch(Exception ex)
        {
            System.err.println("Tidak Berhasil Koneksi");
            System.exit(1);
        }
    }
    
    void simpan(){
        try{
            Statement statement = con.createStatement();
            String sql="insert into mahasiswa values('"+txtKode.getText()+"','"+txtNama.getText()+"','"+txtGender.getText()+"');";
            statement.executeUpdate(sql);
            statement.close();
            JOptionPane.showMessageDialog(null,"Berhasil disimpan");
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null,"nim data sudah ada");
            System.err.print(ex);
        }
    }
    
     void aksi(){
        btn_simpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                simpan();
            }
        });
    }
}
