/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.model.ChargeCollection;
import java.awt.Dimension;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * FXML Controller class
 *
 * @author ALPHA
 */
public class AdminStatisticsController implements Initializable {

    private List<Charge> chargeList;

    @FXML
    private AnchorPane apane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stripe.apiKey = "sk_test_51MhGaAGeGEgrQ6hOFaUPvKPr8iOv7UjDwPJ22UAHMhCVD0VCQw3CmEGh0mQoVN7b635WeO2rilB94j2hSWMNDxhu00UQXAHDAc";
        Task<List<Charge>> task = new Task<List<Charge>>() {
            @Override
            protected List<Charge> call() throws Exception {
                Map<String, Object> params = new HashMap<>();
                params.put("limit", 100000);

                ChargeCollection charges = Charge.list(params);
                return charges.getData();
            }
        };

        task.setOnSucceeded(event -> {
            List<Charge> chargeList = task.getValue();
            System.out.println(task.getValue());

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            // Add the charges to the dataset
            Map<String, Double> dailyTotals = new HashMap<>();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            for (Charge charge : chargeList) {
                if (!"succeeded".equals(charge.getStatus())) {
                    continue; // Skip non-successful charges
                }
                Date date = new Date(charge.getCreated() * 1000L);
                String dateStr = sdf.format(date);
                double amount = charge.getAmount() / 100.0; // Convert from cents to dollars
                if (dailyTotals.containsKey(dateStr)) {
                    dailyTotals.put(dateStr, dailyTotals.get(dateStr) + amount);
                } else {
                    dailyTotals.put(dateStr, amount);
                }
            }
            SortedMap<String, Double> sortedTotals = new TreeMap<>(Collections.reverseOrder());
            sortedTotals.putAll(dailyTotals);

            for (String date : sortedTotals.keySet()) {
                double totalAmount = sortedTotals.get(date);
                dataset.addValue(totalAmount, "Total Transaction Price", date);
            }

            // Create the chart
            JFreeChart chart = ChartFactory.createBarChart(
                    "Total Transaction Price by Date",
                    "Date",
                    "Total Transaction Price (USD)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            // Wrap the chartPanel in a SwingNode
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(800, 600));

            SwingNode swingNode = new SwingNode();
            swingNode.setContent(chartPanel);

            // Add the SwingNode to the JavaFX scene graph
            apane.getChildren().add(swingNode);
        });

        new Thread(task).start();
    }

}
