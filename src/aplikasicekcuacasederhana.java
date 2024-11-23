/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class aplikasicekcuacasederhana extends javax.swing.JFrame {

    /**
     * Creates new form aplikasicekcuacasederhana
     */
    public aplikasicekcuacasederhana() {
        initComponents();
        
    }

    private Map<String, String> parseWeatherData(String dataCuaca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private String getWeatherIconPath(String weatherType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
         *
         */
    
 public class WeatherService {
   private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
private static final String API_KEY = "fc783342868506713ceb9b9ceeb6ec83"; // Ganti dengan API key valid
    
       
public static String getWeatherData(String city) {
    StringBuilder result = new StringBuilder();
    try {
        if (city == null || city.trim().isEmpty()) {
            throw new IllegalArgumentException("City name cannot be null or empty");
        }

        // Log city name
        System.out.println("City: " + city);

        // Encode the city name
        String cityEncoded = URLEncoder.encode(city, "UTF-8");
        System.out.println("Encoded City: " + cityEncoded);

        // Construct the URL
        String urlString = BASE_URL + cityEncoded + "&units=metric&appid=" + API_KEY;
        System.out.println("Request URL: " + urlString);

        // Open connection
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Log response code
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode != HttpURLConnection.HTTP_OK) {
            System.err.println("HTTP Error: " + responseCode);
            return null;
        }

        // Read response
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        }

    } catch (IllegalArgumentException e) {
        System.err.println("Input Error: " + e.getMessage());
        e.printStackTrace();
        return null;
    } catch (IOException e) {
        System.err.println("Network Error: " + e.getMessage());
        e.printStackTrace();
        return null;
    } catch (Exception e) {
        System.err.println("Unexpected Error: " + e.getMessage());
        e.printStackTrace();
        return null;
    }

    // Log final result
    System.out.println("API Response: " + result.toString());
    return result.toString();
}
        

    /**
     * Memproses data cuaca JSON dan mengembalikannya dalam bentuk Map.
     *
     * @param weatherData JSON response dari API.
     * @return Map berisi informasi cuaca (temperature, description, weatherType, iconPath).
     */
    public Map<String, String> parseWeatherData(String weatherData) {
        Map<String, String> weatherInfo = new HashMap<>();
        if (weatherData == null || weatherData.isEmpty()) {
            System.err.println("Weather data is null or empty");
            return weatherInfo;
        }

        try {
            JSONObject jsonObject = new JSONObject(weatherData);

            // Validasi jika API mengembalikan error
            if (jsonObject.has("cod") && jsonObject.getInt("cod") != 200) {
                String errorMessage = jsonObject.optString("message", "Unknown error");
                System.err.println("Error from API: " + errorMessage);
                return weatherInfo;
            }

            JSONObject main = jsonObject.getJSONObject("main");
            JSONArray weatherArray = jsonObject.getJSONArray("weather");
            JSONObject weather = weatherArray.getJSONObject(0);

            // Ambil suhu dan format ke 1 decimal
            double temperature = main.getDouble("temp");
            weatherInfo.put("temperature", String.format("%.1f", temperature));

            // Ambil deskripsi cuaca
            String description = weather.getString("description");
            weatherInfo.put("description", description);

            // Tentukan tipe cuaca berdasarkan suhu
            String weatherType = determineWeatherType((float) temperature);
            weatherInfo.put("weatherType", weatherType);

            // Dapatkan ikon berdasarkan tipe cuaca
            String iconPath = getWeatherIconPath(weatherType);
            weatherInfo.put("iconPath", iconPath);

        } catch (Exception e) {
            System.err.println("Error parsing weather data: " + e.getMessage());
            e.printStackTrace();
        }
        return weatherInfo;
    }

    /**
     * Mendapatkan path ikon berdasarkan tipe cuaca.
     *
     * @param weatherType Tipe cuaca (hot, moderate, cold).
     * @return Path ikon cuaca.
     */
    private String getWeatherIconPath(String weatherType) {
        if (weatherType == null) {
            return "unknown.png";
        }

        switch (weatherType) {
            case "hot":
                return "sun.png";
            case "moderate":
                return "cloud.png";
            case "cold":
                return "snow.png";
            default:
                return "unknown.png";
        }
    }
 }

    /**
     * Menentukan tipe cuaca berdasarkan suhu.
     *
     * @param temperature Suhu dalam derajat Celsius.
     * @return Tipe cuaca (hot, moderate, cold).
     */
    private String determineWeatherType(float temperature) {
        if (temperature > 30) {
            return "hot";
        } else if (temperature >= 15) {
            return "moderate";
        } else {
            return "cold";
        }
    }

    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbLokasiFavorit = new javax.swing.JComboBox<>();
        lbMasukanKota = new javax.swing.JLabel();
        txtKota = new javax.swing.JTextField();
        btnCekCuaca = new javax.swing.JButton();
        lblCuaca = new javax.swing.JLabel();
        lblGambarCuaca = new javax.swing.JLabel();
        lbSuhu = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 255));

        cmbLokasiFavorit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kotabaru", "Banjarmasin", "Surabaya", "Jakarta" }));

        lbMasukanKota.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbMasukanKota.setForeground(new java.awt.Color(255, 255, 255));
        lbMasukanKota.setText("Masukkan Kota :");

        btnCekCuaca.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnCekCuaca.setText("Cek Cuaca");
        btnCekCuaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekCuacaActionPerformed(evt);
            }
        });

        lblCuaca.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblCuaca.setForeground(new java.awt.Color(255, 255, 255));
        lblCuaca.setText("Cuaca :");

        lblGambarCuaca.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lblGambarCuaca.setForeground(new java.awt.Color(255, 255, 255));
        lblGambarCuaca.setText("Gambar");

        lbSuhu.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbSuhu.setForeground(new java.awt.Color(255, 255, 255));
        lbSuhu.setText("Suhu :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Deskripsi :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblGambarCuaca, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbLokasiFavorit, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCuaca, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbMasukanKota, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114)
                                .addComponent(btnCekCuaca))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbSuhu, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)))))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbLokasiFavorit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbMasukanKota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKota, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCekCuaca))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCuaca, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSuhu, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107)
                .addComponent(lblGambarCuaca, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCekCuacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekCuacaActionPerformed
        String kota = txtKota.getText();
    if (kota.isEmpty()) {
        kota = cmbLokasiFavorit.getSelectedItem().toString();
    }
    if (!kota.isEmpty()) {
        String dataCuaca = WeatherService.getWeatherData(kota);
        if (dataCuaca != null) {
            Map<String, String> weatherInfo = parseWeatherData(dataCuaca);
            lblCuaca.setText("Cuaca di " + kota);
            lbSuhu.setText("Suhu: " + weatherInfo.get("temperature"));
            jLabel4.setText("Deskripsi: " + weatherInfo.get("description"));

            // Display icon based on weather type
            String weatherType = weatherInfo.get("weatherType");
            lblGambarCuaca.setIcon(new javax.swing.ImageIcon(getClass().getResource(getWeatherIconPath(weatherType))));
        } else {
            lblCuaca.setText("Data cuaca tidak ditemukan atau terjadi kesalahan.");
            jLabel4.setText("Suhu: N/A");
            jLabel4.setText("Deskripsi: N/A");
            lblGambarCuaca.setIcon(null);
        }
    } else {
        lblCuaca.setText("Masukkan nama kota.");
        jLabel4.setText("Suhu: N/A");
        jLabel4.setText("Deskripsi: N/A");
        lblGambarCuaca.setIcon(null);
    }
    
    }//GEN-LAST:event_btnCekCuacaActionPerformed

    
    private String getWeatherType(String description) {
        if (description.contains("clear")) {
            return "hot";  // Mengasumsikan langit cerah adalah cuaca panas
        } else if (description.contains("clouds")) {
            return "moderate";  // Cuaca berawan dianggap moderat
        } else if (description.contains("rain") || description.contains("drizzle")) {
            return "moderate";  // Cuaca hujan dianggap moderat
        } else if (description.contains("snow")) {
            return "cold";  // Cuaca bersalju dianggap dingin
        } else {
            return "unknown";  // Jika tidak terdeteksi
        }
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
            java.util.logging.Logger.getLogger(aplikasicekcuacasederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(aplikasicekcuacasederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(aplikasicekcuacasederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(aplikasicekcuacasederhana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new aplikasicekcuacasederhana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCekCuaca;
    private javax.swing.JComboBox<String> cmbLokasiFavorit;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbMasukanKota;
    private javax.swing.JLabel lbSuhu;
    private javax.swing.JLabel lblCuaca;
    private javax.swing.JLabel lblGambarCuaca;
    private javax.swing.JTextField txtKota;
    // End of variables declaration//GEN-END:variables
}
