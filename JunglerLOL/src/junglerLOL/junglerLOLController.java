/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package junglerLOL;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.imageio.ImageIO;
import static junglerLOL.junglerLOLMain.stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author yassin
 */
public class junglerLOLController implements Initializable {

    @FXML
    private ImageView imgPath;
    @FXML
    private Label WinRate;
    @FXML
    private Label PickRate;
    @FXML
    private Label BanRate;
    @FXML
    private Label JungleRank;
    private Label Title;
    @FXML
    private JFXTextField ZoneSearch;

    public String imgRedSide, imgBlueSide, ChampionName;
    public static final String[] TN = {"Olaf", "Poppy", "Evelynn", "Shaco", "Hecarim", "Rumble", "Lee Sin", "Jarvan IV", "KhaZix", "Nocturne", "Kayn",
        "Kindred", "Elise", "Ekko", "Zed", "Fiddlesticks", "Nidalee", "Diana", "Udyr", "Warwick", "Nunu", "Wukong", "Morgana", "Gragas", "Taliyah", "Ivern", "Lillia",
        "Trundle", "Volibear", "Xin Zhao", "Viego", "Rengar", "Rammus", "Sejuani", "Karthus", "Shyvana", "Zac", "Graves", "Vi", "Amumu", "Talon", "Master Yi", "Rek Sai",
        "Skarner", "Jax"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //ZoneSearch.setOnKeyPressed(this::search);
        TextFields.bindAutoCompletion(ZoneSearch, TN);
    }

    private void Setimg(String Champion, String imgSrc) {
        try {
            Document document = null;
            String Url = "https://jungler.gg/champions/";
            document = Jsoup.connect(Url + Champion).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64)").get();

//            Response response = Jsoup.connect(Url + Champion).userAgent("Mozilla/5.0 (Windows NT 6.0)").timeout(100000).ignoreHttpErrors(true).execute();
//            System.out.println("Code : " + response.statusCode());
//            int statusCode = response.statusCode();
            //  if (statusCode == 200) {
            Elements RedSide = document.select("img[id=image-244-24]");
            Elements BlueSide = document.select("img[id=image-614-24]");
            imgBlueSide = BlueSide.attr("src");
            imgRedSide = RedSide.attr("src");
            //textPickRate
            Element contentDivWinRate = document.select("div[id=text_block-49-24]").first();
            String textWinRate = contentDivWinRate.getElementsByTag("div").text();
            //textPickRate
            Element contentDivPickRate = document.select("div[id=text_block-51-24]").first();
            String textPickRate = contentDivPickRate.getElementsByTag("div").text();
            //textBanRate
            Element contentDivBanRate = document.select("div[id=text_block-55-24]").first();
            String textBanRate = contentDivBanRate.getElementsByTag("div").text();
            //textJungleRank
            Element contentDivJungleRank = document.select("div[id=text_block-59-24]").first();
            String textJungleRank = contentDivJungleRank.getElementsByTag("div").text();

            WinRate.setText(textWinRate);
            PickRate.setText(textPickRate);
            BanRate.setText(textBanRate);
            JungleRank.setText(textJungleRank);

            WinRate.setAlignment(Pos.CENTER);
            PickRate.setAlignment(Pos.CENTER);
            BanRate.setAlignment(Pos.CENTER);
            JungleRank.setAlignment(Pos.CENTER);
            if (imgSrc == "Blue") {
                new Thread(() -> {
                    try {
                        URL imageUrl = new URL(imgBlueSide);
                        HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                        BufferedImage propertImage = ImageIO.read(connection.getInputStream());
                        Image imgsss = SwingFXUtils.toFXImage(propertImage, null);
                        imgPath.setImage(imgsss);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            if (imgSrc == "Red") {
                new Thread(() -> {
                    try {
                        URL imageUrl = new URL(imgRedSide);
                        HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
                        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                        BufferedImage propertImage = ImageIO.read(connection.getInputStream());
                        Image imgsss = SwingFXUtils.toFXImage(propertImage, null);
                        imgPath.setImage(imgsss);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            // }
//
        } catch (IOException ex) {
            ex.getStackTrace();
        }
    }

    public static void shake(Node node) {
        new Shake(node).play();
    }

//    private void search(KeyEvent e) {
//        if (e.getCode() == KeyCode.ENTER) {
//            ChampionName = ZoneSearch.getText().toLowerCase();
//            if (ChampionName.isEmpty()) {
//                shake(ZoneSearch);
//                return;
//            }
//            System.out.println("" + ChampionName);
//            Setimg(ChampionName, "Red");
//        }
//    }

    @FXML
    private void BlueBuffClicked(MouseEvent event) {
        Setimg(ChampionName, "Blue");
    }

    @FXML
    private void RedBuffClicked(MouseEvent event) {
        Setimg(ChampionName, "Red");
    }

    @FXML
    private void close(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(MouseEvent event) {
        stage.setIconified(true);
    }

    @FXML
    private void searchiconClicked(MouseEvent event) {

        ChampionName = ZoneSearch.getText().toLowerCase();
        if (ChampionName.isEmpty()) {
            shake(ZoneSearch);
            return;
        }
        System.out.println("" + ChampionName);
        Setimg(ChampionName, "Red");
    }

}
